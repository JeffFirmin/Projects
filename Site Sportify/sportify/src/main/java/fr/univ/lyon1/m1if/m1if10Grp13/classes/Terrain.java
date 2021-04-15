package fr.univ.lyon1.m1if.m1if10Grp13.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "terrain")
public class Terrain {

    @Id
    @Column(name = "terrainid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long terrainId;
    private boolean dispo;

    public Terrain(Long i) {
        this.terrainId = i;
    }

    public Terrain() {

    }

    public Long getTerrainId() {
        return terrainId;
    }

    public void setTerrainId(Long terrainId) {
        this.terrainId = terrainId;
    }

    public boolean isDispo() {
        return dispo;
    }

    public void setDispo(boolean dispo) {
        this.dispo = dispo;
    }

}
