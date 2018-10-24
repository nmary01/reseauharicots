/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.DemandeFacadeLocal;
import dao.ProduitFacadeLocal;
import dao.UtilisateurFacadeLocal;
import entity.Demande;
import entity.Produit;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Nathan
 */
@Named(value = "vueD")
@SessionScoped
public class VueD implements Serializable {

    @EJB
    DemandeFacadeLocal demandeDAO;
    @EJB
    ProduitFacadeLocal produitDAO;
    @EJB
    UtilisateurFacadeLocal utilisateurDAO;

    int quantite;
    int prixMax;
    String nomProduit;

    /**
     * Creates a new instance of VueD
     */
    public VueD() {

    }

    public UtilisateurFacadeLocal getUtilisateurDAO() {
        return utilisateurDAO;
    }

    public void setUtilisateurDAO(UtilisateurFacadeLocal utilisateurDAO) {
        this.utilisateurDAO = utilisateurDAO;
    }

    public DemandeFacadeLocal getDemandeDAO() {
        return demandeDAO;
    }

    public void setDemandeDAO(DemandeFacadeLocal demandeDAO) {
        this.demandeDAO = demandeDAO;
    }

    public ProduitFacadeLocal getProduitDAO() {
        return produitDAO;
    }

    public void setProduitDAO(ProduitFacadeLocal produitDAO) {
        this.produitDAO = produitDAO;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getPrixMax() {
        return prixMax;
    }

    public void setPrixMax(int prixMax) {
        this.prixMax = prixMax;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public List<Demande> getDemandes() {
        List<Demande> demandes = demandeDAO.findAll();
        return demandes;
    }

    public List<Demande> getDemandesUser(String login) {
        List<Demande> demandes = demandeDAO.findAll();
        for (Demande d : demandes) {
            if (!d.getLogin().getLogin().equals(login)) {
                demandes.remove(d);
            }
        }
        return demandes;
    }

    public int findIdProduit(String nomP) {
        for (Produit p : produitDAO.findAll()) {
            if (p.getNomProduit().equals(nomP)) {
                return p.getIdProduit();
            }
        }
        return -1;
    }

    public List<String> completeText(String query) {
        List<String> results = new ArrayList<>();
        for (Produit p : produitDAO.findAll()) {
            if (p.getNomProduit().toLowerCase().startsWith(query.toLowerCase())) {
                results.add(p.getNomProduit());
            }
        }

        return results;
    }

    public void addDemande(String login) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (login.isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Veuillez vous connecter"));
        } else {
            Demande newDemande = new Demande();
            newDemande.setIdProduit(produitDAO.find(findIdProduit(nomProduit)));
            long millis = System.currentTimeMillis();
            Date date = new Date(millis);
            newDemande.setDate(date);
            newDemande.setPrixUnitaireMax(prixMax);
            newDemande.setQuantite(quantite);
            newDemande.setLogin(utilisateurDAO.find(login));

            demandeDAO.create(newDemande);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Succès","Demande enregistrée !"));
        }
        prixMax = 0;
        nomProduit = "";
        quantite = 0;
    }
}
