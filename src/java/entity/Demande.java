/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Nathan
 */
@Entity
@Table(name = "demande")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Demande.findAll", query = "SELECT d FROM Demande d")
    , @NamedQuery(name = "Demande.findByIdDemande", query = "SELECT d FROM Demande d WHERE d.idDemande = :idDemande")
    , @NamedQuery(name = "Demande.findByDate", query = "SELECT d FROM Demande d WHERE d.date = :date")
    , @NamedQuery(name = "Demande.findByQuantite", query = "SELECT d FROM Demande d WHERE d.quantite = :quantite")
    , @NamedQuery(name = "Demande.findByPrixUnitaireMax", query = "SELECT d FROM Demande d WHERE d.prixUnitaireMax = :prixUnitaireMax")})
public class Demande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdDemande")
    private Integer idDemande;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Quantite")
    private int quantite;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PrixUnitaireMax")
    private float prixUnitaireMax;
    @JoinColumn(name = "IdProduit", referencedColumnName = "IdProduit")
    @ManyToOne(optional = false)
    private Produit idProduit;
    @JoinColumn(name = "login", referencedColumnName = "login")
    @ManyToOne(optional = false)
    private Utilisateur login;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "demande")
    private Collection<Vente> venteCollection;

    public Demande() {
    }

    public Demande(Integer idDemande) {
        this.idDemande = idDemande;
    }

    public Demande(Integer idDemande, Date date, int quantite, int prixUnitaireMax) {
        this.idDemande = idDemande;
        this.date = date;
        this.quantite = quantite;
        this.prixUnitaireMax = prixUnitaireMax;
    }

    public Integer getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(Integer idDemande) {
        this.idDemande = idDemande;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getPrixUnitaireMax() {
        return prixUnitaireMax;
    }

    public void setPrixUnitaireMax(float prixUnitaireMax) {
        this.prixUnitaireMax = prixUnitaireMax;
    }

    public Produit getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Produit idProduit) {
        this.idProduit = idProduit;
    }

    public Utilisateur getLogin() {
        return login;
    }

    public void setLogin(Utilisateur login) {
        this.login = login;
    }

    @XmlTransient
    public Collection<Vente> getVenteCollection() {
        return venteCollection;
    }

    public void setVenteCollection(Collection<Vente> venteCollection) {
        this.venteCollection = venteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDemande != null ? idDemande.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Demande)) {
            return false;
        }
        Demande other = (Demande) object;
        if ((this.idDemande == null && other.idDemande != null) || (this.idDemande != null && !this.idDemande.equals(other.idDemande))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Demande[ idDemande=" + idDemande + " ]";
    }
    
}
