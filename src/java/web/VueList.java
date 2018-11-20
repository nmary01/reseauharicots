/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.CategorieFacadeLocal;
import dao.ProduitFacadeLocal;
import entity.Categorie;
import entity.Produit;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Nathan
 */
@Named(value = "vueList")
@SessionScoped
public class VueList implements Serializable {

    @EJB
    ProduitFacadeLocal produitDAO;

    @EJB
    CategorieFacadeLocal categorieDAO;

    private String nomProduit;
    private String nomCategorie;

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

    public List<Produit> getProduits() {
        List<Produit> produits = produitDAO.findAll();
        return produits;
    }

    public CategorieFacadeLocal getCategorieDAO() {
        return categorieDAO;
    }

    public void setCategorieDAO(CategorieFacadeLocal categorieDAO) {
        this.categorieDAO = categorieDAO;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }
    
    public List<Produit> getProduitsbyCategorie(int categorie) {
        List<Produit> tmpP = produitDAO.findAll();
        List<Produit> produits = new ArrayList<>();
        for (Produit p : tmpP) {
            if (p.getIdCategorie().getIdCategorie().equals(categorie)) {
                produits.add(p);
            }
        }
        return produits;
    }

    public void addProduit() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (findCategorie(nomCategorie) != null) {
            Produit newProduit = new Produit();
            newProduit.setNomProduit(nomProduit);
            newProduit.setIdCategorie(findCategorie(nomCategorie));
            newProduit.setImage("resources/example.png");

            produitDAO.create(newProduit);
            context.addMessage(null, new FacesMessage("Produit ajouté avec succès:",nomProduit+" ("+nomCategorie+")"));
            nomProduit = null;
            nomCategorie = null;
        }
    }

    public Categorie findCategorie(String nomCategorie) {
        List<Categorie> categories = categorieDAO.findAll();
        for (Categorie c : categories) {
            if (c.getNomcategorie().equals(nomCategorie)) {
                return c;
            }
        }
        return null;
    }
    
    public List<String> getCategorieNames(){
        List<String> categorieNames= new ArrayList<>();
        for (Categorie c:categorieDAO.findAll()){
            categorieNames.add(c.getNomcategorie());
        }
        return categorieNames;
    }
}
