package com.m1if10.app.modele;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Class of UE model in the DB
 */
@Entity
@Table(name="ue")
public class UE {

    @Id
    @Column(name="idUe")
    private String idUe;
    @Column(name="intitule")
    private String intitule;

    @OneToMany(
            mappedBy = "idLpu.ue",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<liaisonProfUe> profs = new ArrayList<>();

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumns ({
            @JoinColumn(name="nameUe", referencedColumnName = "idUe")
    })
    public Set<Cours> cours; //unidirectional

    public UE() {
        this.idUe = "";
        this.intitule = "";
        this.cours = null;
    }

    public UE(String idUe, String intitule) {
        this.idUe = idUe;
        this.intitule = intitule;
        this.cours = null;
    }

    public String getIdUe() {
        return idUe;
    }

    public void setIdUe(String id) {
        this.idUe = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public List<liaisonProfUe> getProfs() {
        return profs;
    }

    public void setProfs(List<liaisonProfUe> profs) {
        this.profs = profs;
    }

    public Set<Cours> getCours() {
        return cours;
    }

    public void setCours(Set<Cours> cours) {
        this.cours = cours;
    }
}
