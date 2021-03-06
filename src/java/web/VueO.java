/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.OffreFacadeLocal;
import dao.ProduitFacadeLocal;
import dao.UtilisateurFacadeLocal;
import entity.Offre;
import entity.Produit;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Nathan
 */
@Named(value = "vueO")
@SessionScoped
public class VueO implements Serializable {

    @EJB
    OffreFacadeLocal offreDAO;
    @EJB
    ProduitFacadeLocal produitDAO;
    @EJB
    UtilisateurFacadeLocal utilisateurDAO;

    private int quantite;
    private float prixMin;
    private String nomProduit;
    private String message;

    /**
     * Creates a new instance of VueD
     */
    public VueO() {

    }

    public OffreFacadeLocal getOffreDAO() {
        return offreDAO;
    }

    public void setOffreDAO(OffreFacadeLocal offreDAO) {
        this.offreDAO = offreDAO;
    }

    public float getPrixMin() {
        return prixMin;
    }

    public void setPrixMin(float prixMin) {
        this.prixMin = prixMin;
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

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public List<Offre> getOffres() {
        List<Offre> offres = offreDAO.findAll();
        return offres;
    }

    public List<Offre> getOffresUser(String login) {
        List<Offre> offres = offreDAO.findAll();
        List<Offre> tmp = new ArrayList<>();
        for (Offre o : offres) {
            if (o.getLogin().getLogin().equals(login)) {
                tmp.add(o);
            }
        }
        return tmp;
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

    public void addOffre(String login) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (login.isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Veuillez vous connecter"));
        } else {
            Offre newOffre = new Offre();
            newOffre.setIdProduit(produitDAO.find(findIdProduit(nomProduit)));
            long millis = System.currentTimeMillis();
            Date date = new Date(millis);
            newOffre.setDate(date);
            newOffre.setPrixUnitaireMin(prixMin);
            newOffre.setQuantite(quantite);
            newOffre.setLogin(utilisateurDAO.find(login));

            offreDAO.create(newOffre);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès", "Offre enregistrée !"));
        }
        prixMin = 0;
        nomProduit = "";
        quantite = 0;
    }

    public void deleteOffre(int idOffre) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            Offre offre = offreDAO.find(idOffre);
            offreDAO.remove(offre);
            context.addMessage(null, new FacesMessage("Succès", "Offre supprimée !"));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage("Erreur", "La suppression a échouée !"));
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void envoiMessageO(Offre o) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (!message.equals("")) {
            context.addMessage(null, new FacesMessage("Succès !", "Message envoyé à " + o.getLogin().getLogin()));
            //Implémentation ici pour stocker le message
        } else {
            context.addMessage(null, new FacesMessage("Erreur", "Champs incomplets"));
        }

    }
}
