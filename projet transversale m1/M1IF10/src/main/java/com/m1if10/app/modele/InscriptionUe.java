package com.m1if10.app.modele;

import javax.persistence.*;

/**
 * Class of registration to an UE model in the DB
 */
@Entity
@Table(name="InscriptionUe")
@AssociationOverrides({
        @AssociationOverride(name = "idIu.alternant",
                joinColumns = @JoinColumn(name = "id")),
        @AssociationOverride(name = "idIu.ue",
                joinColumns = @JoinColumn(name = "idUe")) })
public class InscriptionUe {

    /**
     * Primary key of Inscription to an UE
     */
    @EmbeddedId
    private InscriptionUePk idIu;

    public InscriptionUe() {
        this.idIu = null;
    }

    public InscriptionUe(InscriptionUePk idIu) {
        this.idIu = idIu;
    }

    public InscriptionUe(Alternant alternant, UE ue) {
        this.idIu = new InscriptionUePk(alternant,ue);
    }

    public InscriptionUePk getIdIu() {
        return idIu;
    }

    public void setIdIu(InscriptionUePk idIu) {
        this.idIu = idIu;
    }
}
