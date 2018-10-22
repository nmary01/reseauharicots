/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Nathan
 */
@Embeddable
public class VentePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "IdOffre")
    private int idOffre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IdDemande")
    private int idDemande;

    public VentePK() {
    }

    public VentePK(int idOffre, int idDemande) {
        this.idOffre = idOffre;
        this.idDemande = idDemande;
    }

    public int getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(int idOffre) {
        this.idOffre = idOffre;
    }

    public int getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(int idDemande) {
        this.idDemande = idDemande;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idOffre;
        hash += (int) idDemande;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentePK)) {
            return false;
        }
        VentePK other = (VentePK) object;
        if (this.idOffre != other.idOffre) {
            return false;
        }
        if (this.idDemande != other.idDemande) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.VentePK[ idOffre=" + idOffre + ", idDemande=" + idDemande + " ]";
    }
    
}
