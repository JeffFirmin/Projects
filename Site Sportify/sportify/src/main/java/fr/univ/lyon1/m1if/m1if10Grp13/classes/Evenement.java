package fr.univ.lyon1.m1if.m1if10Grp13.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "evenements")
public class Evenement {
    @Id
    @Column(name = "evenementid")
    private Long evenementId;
    private String description;
    private Date datedebut;
    private Date datefin;

    public Evenement(Long evenenementId, String description, Date dateDebut, Date dateFin) {
        this.evenementId = evenenementId;
        this.description = description;
        this.datedebut = dateDebut;
        this.datefin = dateFin;
    }

    public Evenement() {

    }

    public Long getEvenenementId() {
        return evenementId;
    }

    public void setEvenenementId(Long evenenementId) {
        this.evenementId = evenenementId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateDebut() {
        return datedebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.datedebut = dateDebut;
    }

    public Date getDateFin() {
        return datefin;
    }

    public void setDateFin(Date dateFin) {
        this.datefin = dateFin;
    }
}
