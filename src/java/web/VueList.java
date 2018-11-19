/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.ProduitFacadeLocal;
import entity.Produit;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Nathan
 */
@Named(value = "vueList")
@SessionScoped
public class VueList implements Serializable {
    
    @EJB
    ProduitFacadeLocal produitDAO;
    
    /**
     * Creates a new instance of VueList
     */
    public VueList() {
    }

    public ProduitFacadeLocal getProduitDAO() {
        return produitDAO;
    }

    public void setProduitDAO(ProduitFacadeLocal produitDAO) {
        this.produitDAO = produitDAO;
    }
    
    public List<Produit> getProduits(){
        List<Produit> produits = produitDAO.findAll();
        return produits;
    }
    
    public List<Produit> getProduitsbyCategorie(int categorie){
        List<Produit> tmpP = produitDAO.findAll();
        List<Produit> produits= new ArrayList<>();
        for (Produit p: tmpP){
            if (p.getIdCategorie().getIdCategorie().equals(categorie)){
                produits.add(p);
            }
        }
        return produits;
    }
}
