/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;


import dao.OffreFacadeLocal;
import dao.ProduitFacadeLocal;
import entity.Offre;
import entity.Produit;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

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
    //String idD;
    //Date date;
    int quantite;
    int prixMin;
    String nomProduit;
    
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

    public int getPrixMin() {
        return prixMin;
    }

    public void setPrixMin(int prixMin) {
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

    public List<Offre> getOffres(){
        List<Offre> offres=offreDAO.findAll();
        return offres;
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
    
    public void addOffre(){
        Offre newOffre=new Offre();
        newOffre.setIdProduit(produitDAO.find(findIdProduit(nomProduit)));
        long millis=System.currentTimeMillis();  
        Date date=new Date(millis);
        System.out.println(date);
        newOffre.setDate(date);
        newOffre.setPrixUnitaireMin(prixMin);
        newOffre.setQuantite(quantite);
        
        offreDAO.create(newOffre);
    }
}
