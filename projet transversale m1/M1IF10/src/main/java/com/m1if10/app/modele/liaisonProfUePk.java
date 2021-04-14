package com.m1if10.app.modele;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

/**
 * Class of the Primary Key of the link between Prof and UE
 */
@Embeddable
public class liaisonProfUePk implements Serializable {

    @ManyToOne
    @JoinColumn(name="email")
    private Prof prof;
    @ManyToOne
    @JoinColumn(name="idUe")
    private UE ue;

    public liaisonProfUePk(Prof prof, UE ue) {
        this.prof = prof;
        this.ue = ue;
    }

    public liaisonProfUePk() {
        this.prof=new Prof();
        this.ue = new UE();
    }

    public Prof getProf() {
        return prof;
    }

    public void setProf(Prof prof) {
        this.prof = prof;
    }

    public UE getUe() {
        return ue;
    }

    public void setUe(UE ue) {
        this.ue = ue;
    }

    /**
     * Checks if Alternant is equal to another one
     * @param o: Alternant object
     * @return true if Alternant object equals another Alternant
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        liaisonProfUePk that = (liaisonProfUePk) o;
        return Objects.equals(prof, that.prof) &&
                Objects.equals(ue, that.ue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prof, ue);
    }
}
