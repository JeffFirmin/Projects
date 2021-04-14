package com.m1if10.app.modele;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Class of the Primary Key of Inscription to UE in the DB
 */
@Embeddable
public class InscriptionUePk implements Serializable {

    @ManyToOne
    @JoinColumn(name="idAlt")
    private Alternant alternant;

    @ManyToOne
    @JoinColumn(name="idUe")
    private UE ue;

    public InscriptionUePk() {
        this.alternant = null;
        this.ue = null;
    }

    public InscriptionUePk(Alternant alternant, UE ue) {
        this.alternant = alternant;
        this.ue = ue;
    }

    public Alternant getAlternant() {
        return alternant;
    }

    public void setAlternant(Alternant alternant) {
        this.alternant = alternant;
    }

    public UE getUe() {
        return ue;
    }

    public void setUe(UE ue) {
        this.ue = ue;
    }
}
