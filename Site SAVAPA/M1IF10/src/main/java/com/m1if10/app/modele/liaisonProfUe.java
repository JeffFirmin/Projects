package com.m1if10.app.modele;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class of links between Prof and UE model in the DB
 */
@Entity
@Table(name="liaisonProfUe")
@AssociationOverrides({
        @AssociationOverride(name = "idLpu.prof",
                joinColumns = @JoinColumn(name = "email")),
        @AssociationOverride(name = "idLpu.ue",
                joinColumns = @JoinColumn(name = "idUe")) })
public class liaisonProfUe {

    /**
     * Primary key of Prof/UE link
     */
    @EmbeddedId
    private liaisonProfUePk idLpu;

    /**
     * Responsable of the UE
     */
    @Column(name="responsable")
    private Boolean responsable;

    public liaisonProfUe(Prof prof, UE ue, boolean responsable) {
        this.idLpu = new liaisonProfUePk(prof,ue);
        this.responsable = responsable;
    }

    public liaisonProfUe() {
        this.idLpu = new liaisonProfUePk();
        this.responsable = false;
    }

    public liaisonProfUePk getIdLpu() {
        return idLpu;
    }

    public void setIdLpu(liaisonProfUePk idLpu) {
        this.idLpu = idLpu;
    }

    @Transient
    public User getProf() {
        return getIdLpu().getProf();
    }

    public void setProf(Prof prof) {
        getIdLpu().setProf(prof);
    }

    @Transient
    public UE getUE() {
        return getIdLpu().getUe();
    }

    public void setUE(UE ue) {
        getIdLpu().setUe(ue);
    }

    public Boolean getResponsable() {
        return responsable;
    }

    public void setResponsable(Boolean responsable) {
        this.responsable = responsable;
    }

    /**
     * Checks if link Prof/UE is equal to another one
     * @param o: link Prof/UE
     * @return true if link Prof/UE equals another link Prof/UE
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        liaisonProfUe that = (liaisonProfUe) o;
        return Objects.equals(idLpu, that.idLpu) &&
                Objects.equals(responsable, that.responsable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLpu, responsable);
    }
}
