/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nathan
 */
@Entity
@Table(name = "vente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vente.findAll", query = "SELECT v FROM Vente v")
    , @NamedQuery(name = "Vente.findByIdOffre", query = "SELECT v FROM Vente v WHERE v.ventePK.idOffre = :idOffre")
    , @NamedQuery(name = "Vente.findByIdDemande", query = "SELECT v FROM Vente v WHERE v.ventePK.idDemande = :idDemande")
    , @NamedQuery(name = "Vente.findByDate", query = "SELECT v FROM Vente v WHERE v.date = :date")})
public class Vente implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VentePK ventePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "IdDemande", referencedColumnName = "IdDemande", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Demande demande;
    @JoinColumn(name = "IdOffre", referencedColumnName = "IdOffre", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Offre offre;

    public Vente() {
    }

    public Vente(VentePK ventePK) {
        this.ventePK = ventePK;
    }

    public Vente(VentePK ventePK, Date date) {
        this.ventePK = ventePK;
        this.date = date;
    }

    public Vente(int idOffre, int idDemande) {
        this.ventePK = new VentePK(idOffre, idDemande);
    }

    public VentePK getVentePK() {
        return ventePK;
    }

    public void setVentePK(VentePK ventePK) {
        this.ventePK = ventePK;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ventePK != null ? ventePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vente)) {
            return false;
        }
        Vente other = (Vente) object;
        if ((this.ventePK == null && other.ventePK != null) || (this.ventePK != null && !this.ventePK.equals(other.ventePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.Vente[ ventePK=" + ventePK + " ]";
    }
    
}
