/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.DemandeFacadeLocal;
import dao.ProduitFacadeLocal;
import entity.Demande;
import entity.Produit;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
import javax.ejb.EJB;

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
    //String idD;
    //Date date;
    int quantite;
    int prixMax;
    String nomProduit;
    
    /**
     * Creates a new instance of VueD
     */
    
    public VueD() {
        
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

    public List<Demande> getDemandes(){
        List<Demande> demandes=demandeDAO.findAll();
        return demandes;
    }
    
    public int findIdProduit(String nomP){
        for(Produit p:produitDAO.findAll()){
            if (p.getNomProduit().equals(nomP)){
                return p.getIdProduit();
            }
        }
        return -1;
    }
    public List<String> completeText(String query) {
        List<String> results = new ArrayList<>();
        for (Produit p:produitDAO.findAll()){
            if (p.getNomProduit().startsWith(query)){
                results.add(p.getNomProduit());
            }
        }
         
        return results;
    }
    
    public void addDemande(){
        Demande newDemande=new Demande();
        newDemande.setIdProduit(produitDAO.find(findIdProduit(nomProduit)));
        long millis=System.currentTimeMillis();  
        Date date=new Date(millis);
        System.out.println(date);
        newDemande.setDate(date);
        newDemande.setPrixUnitaireMax(prixMax);
        newDemande.setQuantite(quantite);
        //newDemande.setUtilisateurCollection(utilisateurCollection);
        
        demandeDAO.create(newDemande);
    }
}
