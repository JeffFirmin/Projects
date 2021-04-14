package com.m1if10.app.modele;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

/**
 * Class of the Primary Key of Alternant Presence
 */
@Embeddable
public class PresenceAltPk implements Serializable {

    @ManyToOne
    @JoinColumn(name="id")
    private Alternant alternant;

    @ManyToOne
    @JoinColumn(name="idCour")
    private Cours cours;

    public PresenceAltPk(){}

    public PresenceAltPk(Alternant alternant, Cours cours) {
        this.alternant = alternant;
        this.cours = cours;
    }

    public Alternant getAlternant() {
        return alternant;
    }

    public void setAlternant(Alternant alternant) {
        this.alternant = alternant;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    /**
     * Checks if Alternant Presence is equal to another one
     * @param o: Alternant Presence object
     * @return true if Alternant Presence object equals another Alternant Presence
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PresenceAltPk that = (PresenceAltPk) o;
        return Objects.equals(alternant, that.alternant) &&
                Objects.equals(cours, that.cours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alternant, cours);
    }
}