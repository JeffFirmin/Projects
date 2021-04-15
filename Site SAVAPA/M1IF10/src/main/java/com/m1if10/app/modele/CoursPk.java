package com.m1if10.app.modele;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Class of the Primary Key of Cours
 */
@Embeddable
public class CoursPk implements Serializable {

    // TD, CM ...
    @Column(name="nature")
    private String nature;

    @Column(name="date")
    private Date date;

    @Column(name="salle")
    private String salle;

    // sera limité à A (pour tous les alternants) où A1,A2 (pour les TP par exemple)
    @Column(name="groupe")
    private String groupe;

    @Column(name="duree")
    private double duree;


    public CoursPk() {
        this.date = new Date();
        this.salle = "";
    }
    public CoursPk(String salle) {
        this.date = new Date();
        this.salle = salle;
    }
    public CoursPk(Date date, String salle) {
        this.date = date;
        this.salle = salle;
    }

    public CoursPk(String nature, Date date, String salle, String groupe, double duree) {
        this.date = date;
        this.duree = duree;
        this.groupe = groupe;
        this.nature = nature;
        this.salle = salle;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public double getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    /**
     * Checks if Cours is equal to another one
     * @param o: Cours object
     * @return true if Cours object equals another Cours
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoursPk coursPk = (CoursPk) o;
        return duree == coursPk.duree &&
                Objects.equals(nature, coursPk.nature) &&
                Objects.equals(date, coursPk.date) &&
                Objects.equals(salle, coursPk.salle) &&
                Objects.equals(groupe, coursPk.groupe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nature, date, salle, groupe, duree);
    }
}
