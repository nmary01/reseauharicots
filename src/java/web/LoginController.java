/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.UtilisateurFacadeLocal;
import entity.Utilisateur;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Nathan
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private String loginU;
    private String mdp;

    @EJB
    private UtilisateurFacadeLocal utilisateurDAO;
    
    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {
    }
    
    public String login() {
        Utilisateur user = utilisateurDAO.find(loginU);
        FacesContext context = FacesContext.getCurrentInstance();

        if (user == null) {
            context.addMessage(null, new FacesMessage("Login inconnu, veuillez réessayer"));
            loginU = null;
            mdp = null;
            return null;
        } else {
            if (user.getMdp().equals(mdp)){
                context.getExternalContext().getSessionMap().put("user", user);
                return "index?faces-redirect=true";
            }
            else{
                context.addMessage(null, new FacesMessage("Mot de passe invalide, veuillez réessayer"));
                return null;
            }
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }

    public String getLoginU() {
        return loginU;
    }

    public void setLoginU(String loginU) {
        this.loginU = loginU;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public UtilisateurFacadeLocal getUtilisateurDAO() {
        return utilisateurDAO;
    }

    public void setUtilisateurDAO(UtilisateurFacadeLocal utilisateurDAO) {
        this.utilisateurDAO = utilisateurDAO;
    }
    
    
    
}
