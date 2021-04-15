package fr.univ.lyon1.m1if.m1if10Grp13.classes;

import java.io.Serializable;

public class ReservationTerrainCompositeKey implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Terrain terrain;
    private Creneau creneau;

    public ReservationTerrainCompositeKey() {

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((creneau == null) ? 0 : creneau.hashCode());
        result = prime * result + ((terrain == null) ? 0 : terrain.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ReservationTerrainCompositeKey other = (ReservationTerrainCompositeKey) obj;
        if (creneau == null) {
            if (other.creneau != null)
                return false;
        } else if (!creneau.equals(other.creneau))
            return false;
        if (terrain == null) {
            if (other.terrain != null)
                return false;
        } else if (!terrain.equals(other.terrain))
            return false;
        return true;
    }

}
