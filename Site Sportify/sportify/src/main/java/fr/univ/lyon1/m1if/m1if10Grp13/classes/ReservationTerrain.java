package fr.univ.lyon1.m1if.m1if10Grp13.classes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reservation_terrain")
@IdClass(ReservationTerrainCompositeKey.class)
public class ReservationTerrain {
    @Id
    @ManyToOne
    @JoinColumn(name = "terrainId")
    private Terrain terrain;
    @Id
    @ManyToOne
    @JoinColumns({ @JoinColumn(name = "datecreneau", referencedColumnName = "datecreneau"),
            @JoinColumn(name = "heurecreneau", referencedColumnName = "heurecreneau") })
    private Creneau creneau;

    @ManyToOne
    @JoinColumn(name = "emailclub")
    private Club club;

    @ManyToOne
    @JoinColumn(name = "emailinscrit")
    private Inscrit inscrit;

    public ReservationTerrain(Terrain terrain, Creneau creneau, Club club, Inscrit inscrit) {
        this.terrain = terrain;
        this.creneau = creneau;
        this.club = club;
        this.inscrit = inscrit;
    }

    public ReservationTerrain() {

    }

    public Terrain getTerrainId() {
        return terrain;
    }

    public void setTerrainId(Terrain terrain) {
        this.terrain = terrain;
    }

    public Creneau getCreneauId() {
        return this.creneau;
    }

    public void setCreneauId(Creneau creneau) {
        this.creneau = creneau;
    }

    public Club getClub() {
        return club;
    }

    public void setClubId(Club club) {
        this.club = club;
    }

    public Inscrit getInscrit() {
        return inscrit;
    }

    public void setEmailInscrit(Inscrit inscrit) {
        this.inscrit = inscrit;
    }

}
