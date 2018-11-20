/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.CategorieFacadeLocal;
import dao.DemandeFacadeLocal;
import dao.OffreFacadeLocal;
import dao.ProduitFacadeLocal;
import entity.Demande;
import entity.Produit;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author Nathan
 */
@Named(value = "treeProduits")
@SessionScoped
public class TreeProduits implements Serializable {
    
    @EJB
    ProduitFacadeLocal produitDAO;
    
    @EJB
    CategorieFacadeLocal categorieDAO;
    
    TreeNode legumes;
    TreeNode fruits;
    TreeNode fruitsSecs;
    List<TreeNode> list;
    
    private TreeNode root = new DefaultTreeNode("Root Node", null);
    
    private LineChartModel dateModel;
    private BarChartModel barModel;
    
    /**
     * Creates a new instance of TreeProduits
     */
    public TreeProduits() {
        list=new ArrayList<>();
        legumes = new DefaultTreeNode(new ProdStat("Légumes"), this.root);
        fruits= new DefaultTreeNode(new ProdStat("Fruits"), this.root);
        fruitsSecs = new DefaultTreeNode(new ProdStat("Fruits Secs"), this.root);
    }
    
    @PostConstruct
    public void init(){
        addNodes();
        createDateModel();
        createBarModel();
    }

    public TreeNode getLegumes() {
        return legumes;
    }

    public void setLegumes(TreeNode legumes) {
        this.legumes = legumes;
    }

    public TreeNode getFruits() {
        return fruits;
    }

    public void setFruits(TreeNode fruits) {
        this.fruits = fruits;
    }

    public TreeNode getFruitsSecs() {
        return fruitsSecs;
    }

    public void setFruitsSecs(TreeNode fruitsSecs) {
        this.fruitsSecs = fruitsSecs;
    }
    
    

    public ProduitFacadeLocal getProduitDAO() {
        return produitDAO;
    }

    public void setProduitDAO(ProduitFacadeLocal produitDAO) {
        this.produitDAO = produitDAO;
    }

    public CategorieFacadeLocal getCategorieDAO() {
        return categorieDAO;
    }

    public void setCategorieDAO(CategorieFacadeLocal categorieDAO) {
        this.categorieDAO = categorieDAO;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
    
    public void addNodes(){
        List<Produit> produits= produitDAO.findAll();
        for (Produit p: produits){
            ProdStat produitT= new ProdStat(p.getNomProduit());
            switch (p.getIdCategorie().getIdCategorie()){
                case 1: list.add(new DefaultTreeNode(produitT, legumes));break;
                case 2: list.add(new DefaultTreeNode(produitT, fruits)); break;
                case 3: list.add(new DefaultTreeNode(produitT, fruitsSecs));break;
                default: System.out.println ("Erreur");               
            }
        }
    }
    
    private void createDateModel() {
        dateModel = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Ventes de légumes");
 
        series1.set("2018-10-01", 51);
        series1.set("2018-10-15", 22);
        series1.set("2018-10-24", 65);
        series1.set("2018-11-06", 74);
        series1.set("2018-11-12", 24);
        series1.set("2018-11-19", 51);
 
        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Ventes de fruits");
 
        series2.set("2018-10-01", 32);
        series2.set("2018-10-15", 73);
        series2.set("2018-10-24", 24);
        series2.set("2018-11-06", 12);
        series2.set("2018-11-12", 74);
        series2.set("2018-11-19", 62);
        
        LineChartSeries series3 = new LineChartSeries();
        series3.setLabel("Ventes de fruits secs");
 
        series3.set("2018-10-01", 4);
        series3.set("2018-10-15", 10);
        series3.set("2018-10-24", 15);
        series3.set("2018-11-06", 8);
        series3.set("2018-11-12", 11);
        series3.set("2018-11-19", 9);
 
        dateModel.addSeries(series1);
        dateModel.addSeries(series2);
        dateModel.addSeries(series3);
 
        dateModel.setTitle("Ventes par catégorie de produits");
        dateModel.setLegendPosition("nw");
        dateModel.setZoom(true);
        dateModel.getAxis(AxisType.Y).setLabel("Nb de ventes");
        DateAxis axis = new DateAxis("Dates");
        axis.setTickAngle(-50);
        axis.setMax("2018-11-27");
 
        dateModel.getAxes().put(AxisType.X, axis);
    }

    public LineChartModel getDateModel() {
        return dateModel;
    }

    public void setDateModel(LineChartModel dateModel) {
        this.dateModel = dateModel;
    }
    
    
    public BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
 
        ChartSeries users = new ChartSeries();
        users.setLabel("Utilisateurs");
        users.set("Juillet 2018", 120);
        users.set("Août 2018", 153);
        users.set("Septembre 2018", 212);
        users.set("Octobre 2018", 304);
        users.set("Novembre 2018", 567);

        model.addSeries(users);
 
        return model;
    }
    
    public void createBarModel() {
        barModel = initBarModel();
 
        barModel.setTitle("Nombre d'utilisateurs inscrits");
        barModel.setLegendPosition("nw");
 
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Mois");
 
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Nb d'utilisateurs");
        yAxis.setMin(0);
        yAxis.setMax(600);
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }
    
    
}
