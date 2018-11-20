/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.DemandeFacadeLocal;
import dao.OffreFacadeLocal;
import entity.Demande;
import entity.Offre;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author Nathan
 */
@Named(value = "prodStat")
@SessionScoped
public class ProdStat implements Serializable {

     @EJB
    DemandeFacadeLocal demandeDAO;

    @EJB
    OffreFacadeLocal offreDAO;

    String nomProduit;
    String qtedemand;
    String qtepropos;

    public ProdStat(){
    }
    
    public ProdStat(String nomProduit) {
        this.nomProduit = nomProduit;    
        System.out.println("Fin constructeur");
        init();
    }
    
    public ProdStat(String nomProduit, String qtedemand, String qtepropos) {
        this.nomProduit = nomProduit;
        this.qtedemand = qtedemand;
        this.qtepropos = qtepropos;
    }

    public void init() {
        System.out.println("Entrée dans init");
        setQuantDemand();
        setQuantPropos();
        
    }

    public void setDemandeDAO(DemandeFacadeLocal demandeDAO) {
        this.demandeDAO = demandeDAO;
    }

    public void setOffreDAO(OffreFacadeLocal offreDAO) {
        this.offreDAO = offreDAO;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public void setQuantDemand() {
        try {
            int sum = 0;
            for (Demande d : demandeDAO.findAll()) {
                if (d.getIdProduit().getNomProduit().equals(nomProduit)) {
                    sum += d.getQuantite();
                }
            }
            qtedemand = Integer.toString(sum);
        } catch (Exception e) {
            qtedemand = "-";
            System.out.println(e);
        }
    }

    public void setQuantPropos() {
        try {
            int sum = 0;
            for (Offre o : offreDAO.findAll()) {
                if (o.getIdProduit().getNomProduit().equals(nomProduit)) {
                    sum += o.getQuantite();
                }
            }
            qtepropos = Integer.toString(sum);
            
        } catch (Exception e) {
            qtepropos = "-";
        }
    }

    public DemandeFacadeLocal getDemandeDAO() {
        return demandeDAO;
    }

    public OffreFacadeLocal getOffreDAO() {
        return offreDAO;
    }

    public String getQtedemand() {
        return qtedemand;
    }

    public String getQtepropos() {
        return qtepropos;
    }
    
}
