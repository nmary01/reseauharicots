/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.UtilisateurFacadeLocal;
import entity.Region;
import entity.Utilisateur;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Nathan
 */
@Named(value = "insert")
@SessionScoped
public class WebUtilisateur implements Serializable{
    
    @EJB
    UtilisateurFacadeLocal utilisateurDAO;
    String login;
    String mdp;
    String prenom;
    String nom;
    String email;
    String ville;
    Region region;
    
    public WebUtilisateur(){
        login="";
        mdp="";
        prenom="";
        nom="";
        email="";
    }

    public UtilisateurFacadeLocal getUtilisateurDAO() {
        return utilisateurDAO;
    }

    public void setUtilisateurDAO(UtilisateurFacadeLocal utilisateurDAO) {
        this.utilisateurDAO = utilisateurDAO;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void addUtilisateur(){
        Utilisateur newUtilisateur=new Utilisateur();
        newUtilisateur.setLogin(login);
        newUtilisateur.setMdp(mdp);
        newUtilisateur.setPrenom(prenom);
        newUtilisateur.setNom(nom);
        newUtilisateur.setEmail(email);
        newUtilisateur.setAdmin(false);
        
        utilisateurDAO.create(newUtilisateur);
        
    }
    
    
}
