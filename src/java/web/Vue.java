/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.RegionFacadeLocal;
import dao.UtilisateurFacadeLocal;
import dao.VilleFacadeLocal;
import entity.Region;
import entity.Utilisateur;
import entity.Ville;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Nathan
 */
@Named(value = "vue")
@SessionScoped
public class Vue implements Serializable {

    @EJB
    UtilisateurFacadeLocal utilisateurDAO;
    private String login;
    private String mdp;
    private String prenom;
    private String nom;
    private String email;
    private String ville;
    private String region;
    private String nomDestinaire;
    private String raison;
    private String message;

    @EJB
    VilleFacadeLocal villeDAO;
    @EJB
    RegionFacadeLocal regionDAO;
    private List<String> regionsNames;

    public Vue() {
        login = "";
        mdp = "";
        prenom = "";
        nom = "";
        email = "";
        ville = "";
    }

    public UtilisateurFacadeLocal getUtilisateurDAO() {
        return utilisateurDAO;
    }

    public void setUtilisateurDAO(UtilisateurFacadeLocal utilisateurDAO) {
        this.utilisateurDAO = utilisateurDAO;
    }

    public VilleFacadeLocal getVilleDAO() {
        return villeDAO;
    }

    public void setVilleDAO(VilleFacadeLocal villeDAO) {
        this.villeDAO = villeDAO;
    }

    public RegionFacadeLocal getRegionDAO() {
        return regionDAO;
    }

    public void setRegionDAO(RegionFacadeLocal regionDAO) {
        this.regionDAO = regionDAO;
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

    public List<Utilisateur> getUtilisateurs() {
        List<Utilisateur> utilisateurs = utilisateurDAO.findAll();
        return utilisateurs;
    }

    public List<Ville> getVilles() {
        List<Ville> villes = villeDAO.findAll();
        return villes;
    }

    public List<Region> getRegions() {
        List<Region> regions = regionDAO.findAll();
        return regions;
    }

    public List<String> getRegionsNames() {
        List<String> regionsNames = new ArrayList<>();
        for (Region r : regionDAO.findAll()) {
            regionsNames.add(r.getNomRegion());
        }
        return regionsNames;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Ville findVille(String ville, int idRegion) {
        for (Ville v : villeDAO.findAll()) {
            if (v.getNomVille().equals(ville)) {
                return v;
            }
        }
        Ville newVille = new Ville();
        newVille.setNomVille(ville);
        newVille.setIdRegion(regionDAO.find(idRegion));
        villeDAO.create(newVille);
        return newVille;
    }

    public int findIdRegion(String nomRegion) {
        for (Region r : regionDAO.findAll()) {
            if (r.getNomRegion().equals(nomRegion)) {
                return r.getIdRegion();
            }
        }
        return -1;
    }

    public List<String> completeText(String query) {
        List<String> results = new ArrayList<>();
        for (Ville v : villeDAO.findAll()) {
            if (v.getNomVille().startsWith(query)) {
                results.add(v.getNomVille());
            }
        }

        return results;
    }

    public void addUtilisateur() {
        Utilisateur newUtilisateur = new Utilisateur();
        newUtilisateur.setLogin(login);
        newUtilisateur.setMdp(mdp);
        newUtilisateur.setPrenom(prenom);
        newUtilisateur.setNom(nom);
        newUtilisateur.setEmail(email);
        newUtilisateur.setAdmin(false);
        newUtilisateur.setIdVille(findVille(ville, findIdRegion(region)));

        utilisateurDAO.create(newUtilisateur);
        login = "";
        mdp = "";
        prenom = "";
        nom = "";
        email = "";
        ville = "";

    }

    public void removeUtilisateur(String pseudo) {
        FacesContext context = FacesContext.getCurrentInstance();
        Utilisateur utilisateur = utilisateurDAO.find(pseudo);
        utilisateurDAO.remove(utilisateur);
        context.addMessage(null, new FacesMessage("Utilisateur supprimé", pseudo + " a été supprimé"));
    }
    
    public void notRemoveUtilisateur() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Suppression annulée"));
    }

    public void checkConnect() {
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage("Connexion Réussie"));
    }

    public String getNomDestinaire() {
        return nomDestinaire;
    }

    public void setNomDestinaire(String nomDestinaire) {
        this.nomDestinaire = nomDestinaire;
    }

    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void envoiMessage() {
        Utilisateur user = utilisateurDAO.find(nomDestinaire);
        FacesContext context = FacesContext.getCurrentInstance();

        if (user == null) {
            context.addMessage(null, new FacesMessage("Utilisateur inconnu, veuillez réessayer"));
        } else {
            if (raison != null && !message.equals("")) {
                context.addMessage(null, new FacesMessage("Succès !", "Message envoyé à " + nomDestinaire));
                //Implémentation ici pour stocker le message
            } else {
                context.addMessage(null, new FacesMessage("Erreur", "Champs incomplets"));
            }
        }
        nomDestinaire = null;
        raison = null;
        message = null;
    }

}
