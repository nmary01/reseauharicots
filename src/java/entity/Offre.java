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
@Table(name = "offre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Offre.findAll", query = "SELECT o FROM Offre o")
    , @NamedQuery(name = "Offre.findByIdOffre", query = "SELECT o FROM Offre o WHERE o.idOffre = :idOffre")
    , @NamedQuery(name = "Offre.findByDate", query = "SELECT o FROM Offre o WHERE o.date = :date")
    , @NamedQuery(name = "Offre.findByQuantite", query = "SELECT o FROM Offre o WHERE o.quantite = :quantite")
    , @NamedQuery(name = "Offre.findByPrixUnitaireMin", query = "SELECT o FROM Offre o WHERE o.prixUnitaireMin = :prixUnitaireMin")})
public class Offre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdOffre")
    private Integer idOffre;
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
    @Column(name = "PrixUnitaireMin")
    private float prixUnitaireMin;
    @JoinColumn(name = "IdProduit", referencedColumnName = "IdProduit")
    @ManyToOne(optional = false)
    private Produit idProduit;
    @JoinColumn(name = "login", referencedColumnName = "login")
    @ManyToOne(optional = false)
    private Utilisateur login;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "offre")
    private Collection<Vente> venteCollection;

    public Offre() {
    }

    public Offre(Integer idOffre) {
        this.idOffre = idOffre;
    }

    public Offre(Integer idOffre, Date date, int quantite, float prixUnitaireMin) {
        this.idOffre = idOffre;
        this.date = date;
        this.quantite = quantite;
        this.prixUnitaireMin = prixUnitaireMin;
    }

    public Integer getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(Integer idOffre) {
        this.idOffre = idOffre;
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

    public float getPrixUnitaireMin() {
        return prixUnitaireMin;
    }

    public void setPrixUnitaireMin(float prixUnitaireMin) {
        this.prixUnitaireMin = prixUnitaireMin;
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
        hash += (idOffre != null ? idOffre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Offre)) {
            return false;
        }
        Offre other = (Offre) object;
        if ((this.idOffre == null && other.idOffre != null) || (this.idOffre != null && !this.idOffre.equals(other.idOffre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Offre[ idOffre=" + idOffre + " ]";
    }
    
}
