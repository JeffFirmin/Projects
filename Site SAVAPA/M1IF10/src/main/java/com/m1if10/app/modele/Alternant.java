package com.m1if10.app.modele;

import com.m1if10.app.classes.RandomString;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class of alternant model in the DB
 */
@Entity
@Table(name="alternants")
@PrimaryKeyJoinColumn(name = "email")
@NamedQueries({
        @NamedQuery(name = "Alternant.findByCleQr",
                query = "select alternant from Alternant as alternant where cleQr = :cleQr")
})
public class Alternant extends User {

    @Column(name = "cleQr")
    private String cleQr;
    @Column(name = "entreprise")
    private String entreprise;
    @Column(name = "nbrHeuresContrat")
    private int nbrHeuresContrat;
    @Column(name = "groupe")
    private String groupe;
    @Column(name = "num")
    private String numEtu;

    public Alternant(){
        super();
        this.cleQr=new RandomString(32).nextString(); // Random string for the QRCode key
        this.entreprise="";
        this.nbrHeuresContrat=35;
        this.groupe="A1";
    }

    public Alternant(String email, String password, String nom, String prenom, String photo, String entreprise,Groupe groupe,String numEtu) {
        super(email, password, nom, prenom, photo);
        this.cleQr = new RandomString(32).nextString();
        this.entreprise = entreprise;
        this.nbrHeuresContrat = 35;
        this.groupe = groupe.toString();
        this.numEtu = numEtu;
    }

    public String getCleQr() {
        return cleQr;
    }

    public void setCleQr(String cleQr) {
        this.cleQr = cleQr;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public int getNbrHeuresContrat() {
        return nbrHeuresContrat;
    }

    public void setNnbrHeuresContrat(int nbrHeuresContrat) {
        this.nbrHeuresContrat = nbrHeuresContrat;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public String getNumEtu() {
        return numEtu;
    }

    public void setNumEtu(String numEtu) {
        this.numEtu = numEtu;
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
        if (!super.equals(o)) return false;
        Alternant alternant = (Alternant) o;
        return nbrHeuresContrat == alternant.nbrHeuresContrat &&
                Objects.equals(cleQr, alternant.cleQr) &&
                Objects.equals(entreprise, alternant.entreprise);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cleQr, entreprise, nbrHeuresContrat);
    }
}
