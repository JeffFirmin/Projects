package com.m1if10.app.modele;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Class of Prof model in the DB
 */
@Entity
@Table(name = "prof")
@PrimaryKeyJoinColumn(name = "email")
public class Prof extends User {

    @OneToMany(
            mappedBy = "idLpu.prof",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    /**
     * List of links between UEs and Prof
     */
    private List<liaisonProfUe> UEs = new ArrayList<>();

    public Prof() {
    }

    public Prof(String email, String password, String nom, String prenom, String urlPhoto) {
        super(email, password, nom, prenom, urlPhoto);
    }

    public void addUE(UE ue, boolean responsable) {
        liaisonProfUe lpu = new liaisonProfUe(this, ue, responsable);
        UEs.add(lpu);
        ue.getProfs().add(lpu);
    }

    public void removeUE(UE ue) {
        for (Iterator<liaisonProfUe> iterator = UEs.iterator(); iterator.hasNext(); ) {
            liaisonProfUe lpu = iterator.next();
            if (lpu.getUE().equals(ue) && lpu.getProf().equals(this)) {
                iterator.remove();
                lpu.getUE().getProfs().remove(lpu);
                lpu.setProf(null);
                lpu.setUE(null);
            }
        }
    }

    public List<liaisonProfUe> getUEs() {
        return UEs;
    }

    /**
     * Checks if Prof is equal to another one
     * @param o: Prof object
     * @return true if Prof object equals another Prof
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Prof prof = (Prof) o;
        return Objects.equals(UEs, prof.UEs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), UEs);
    }
}
