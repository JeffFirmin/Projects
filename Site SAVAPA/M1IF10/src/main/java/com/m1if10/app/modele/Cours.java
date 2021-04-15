package com.m1if10.app.modele;

import javax.persistence.*;
import java.util.Date;

/**
 * Class of Cours model in the DB
 */
@Entity
@Table(name="cours")
public class Cours {

    @Id
    @Column(name="idCour")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idCour;

    /**
     * Description of the Cours
     */
    private CoursPk content;

    @ManyToOne
    @JoinColumns ({
            @JoinColumn(name="nameUE", referencedColumnName = "idUe")
    })
    public UE nameUe; //unidirectional

    public Cours() {
        this.content = new CoursPk();
        this.nameUe = new UE();
    }

    public Cours (UE nameUe,String nature, String salle, Date date, String groupe, double duree) {
        this.nameUe = nameUe;
        this.content = new CoursPk(nature,date,salle,groupe,duree);
    }

    public int getIdCour() {
        return idCour;
    }

    public void setIdCour(int idCour) {
        this.idCour = idCour;
    }

    public CoursPk getContent() {
        return content;
    }

    public void setContent(CoursPk content) {
        this.content = content;
    }

    public UE getNameUe() {
        return nameUe;
    }

    public void setNameUe(UE ue) {
        this.nameUe = ue;
    }
}
