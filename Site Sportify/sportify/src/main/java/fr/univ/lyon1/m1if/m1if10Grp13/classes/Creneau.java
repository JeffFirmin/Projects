package fr.univ.lyon1.m1if.m1if10Grp13.classes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import java.util.Date;

@Entity
@Table(name = "creneau")
@IdClass(CreneauCompositeKey.class)
public class Creneau {
    @Id
    private Date datecreneau;
    @Id
    private Date heurecreneau;
    private Date duree;

    public Creneau(Date dateCreneau, Date heureCreneau, Date duree) {
        this.datecreneau = dateCreneau;
        this.heurecreneau = heureCreneau;
        this.duree = duree;
    }

    public Creneau() {

    }

    public Date getDateCreneau() {
        return datecreneau;
    }

    public void setDateCreneau(Date dateCreneau) {
        this.datecreneau = dateCreneau;
    }

    public Date getHeureCreneau() {
        return heurecreneau;
    }

    public void setHeureCreneau(Date heureCreneau) {
        this.heurecreneau = heureCreneau;
    }

    public Date getDuree() {
        return duree;
    }

    public void setDuree(Date duree) {
        this.duree = duree;
    }

}
