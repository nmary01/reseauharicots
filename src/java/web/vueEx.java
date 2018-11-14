/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.DemandeFacadeLocal;
import dao.OffreFacadeLocal;
import dao.UtilisateurFacadeLocal;
import entity.Demande;
import entity.Offre;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Nathan
 */
@Named(value = "vueEx")
@SessionScoped
public class vueEx implements Serializable {
    
    @EJB
    DemandeFacadeLocal demandeDAO;
    @EJB
    OffreFacadeLocal offreDAO;
    @EJB
    UtilisateurFacadeLocal utilisateurDAO;

    /**
     * Creates a new instance of vueEx
     */
    public vueEx() {
        
    }

    public DemandeFacadeLocal getDemandeDAO() {
        return demandeDAO;
    }

    public void setDemandeDAO(DemandeFacadeLocal demandeDAO) {
        this.demandeDAO = demandeDAO;
    }

    public OffreFacadeLocal getOffreDAO() {
        return offreDAO;
    }

    public void setOffreDAO(OffreFacadeLocal offreDAO) {
        this.offreDAO = offreDAO;
    }

    public UtilisateurFacadeLocal getUtilisateurDAO() {
        return utilisateurDAO;
    }

    public void setUtilisateurDAO(UtilisateurFacadeLocal utilisateurDAO) {
        this.utilisateurDAO = utilisateurDAO;
    }
    
    public List<Offre> getRelevantOffers(String login){
        List<Demande> tmpD = demandeDAO.findAll();
        List<Demande> demandes = new ArrayList<>();
        for (Demande d : tmpD) {
            if (d.getLogin().getLogin().equals(login)) {
                demandes.add(d);
            }
        }
        
        List<Offre> tmpO = offreDAO.findAll();
        List<Offre> offresRegion= new ArrayList<>();
        for (Demande d: demandes){
            for (Offre o: tmpO){
                if(o.getIdProduit().equals(d.getIdProduit()) && !o.getLogin().equals(d.getLogin())){ //Meme produit et différent user
                    if (o.getPrixUnitaireMin()<=d.getPrixUnitaireMax()){ //Prix demandés concordent
                        if (o.getLogin().getIdVille().getIdRegion().equals(d.getLogin().getIdVille().getIdRegion())){ //Même région
                            offresRegion.add(o);
                        }
                    }
                }
            }
        }
        return offresRegion;
    }
    
    public List<Demande> getRelevantDemandes(String login){
        List<Offre> tmpO = offreDAO.findAll();
        List<Offre> offres = new ArrayList<>();
        for (Offre o : tmpO) {
            if (o.getLogin().getLogin().equals(login)) {
                offres.add(o);
            }
        }
        
        List<Demande> tmpD = demandeDAO.findAll();
        List<Demande> demandesRegion= new ArrayList<>();
        for (Offre o: offres){
            for (Demande d: tmpD){
                if(o.getIdProduit().equals(d.getIdProduit()) && !o.getLogin().equals(d.getLogin())){ //Meme produit et différent user
                    if (o.getPrixUnitaireMin()<=d.getPrixUnitaireMax()){ //Prix demandés concordent
                        if (o.getLogin().getIdVille().getIdRegion().equals(d.getLogin().getIdVille().getIdRegion())){ //Même région
                            demandesRegion.add(d);
                        }
                    }
                }
            }
        }
        return demandesRegion;
    }
            
}
