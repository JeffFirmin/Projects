package com.m1if10.app.modele;

import javax.persistence.*;

/**
 * Class of Alternant Presence model in the DB
 */
@Entity
@Table(name="PresenceAlt")
@AssociationOverrides({
        @AssociationOverride(name = "idPa.alternant",
                joinColumns = @JoinColumn(name = "id")),
        @AssociationOverride(name = "idPa.cours",
                joinColumns = @JoinColumn(name = "idCour")) })

public class PresenceAlt {

    /**
     * Primary key of Alternant Presence
     */
    @EmbeddedId
    private PresenceAltPk idPa;

    /**
     * Presence of Alternant for Cours
     */
    @Column(name="present")
    private String present;

    public PresenceAlt() {
    }

    public PresenceAlt(Alternant alternant, Cours cours, EtatPresence valPresence) {
        this.idPa = new PresenceAltPk(alternant,cours);
        this.present = valPresence.toString();
    }

    @Transient
    public PresenceAltPk getIdPa() {
        return idPa;
    }

    public void setIdPa(PresenceAltPk idPa) {
        this.idPa = idPa;
    }

    @Transient
    public String getPresent() {
        return present;
    }

    public void setPresent(String present) {
        this.present = present;
    }
}
