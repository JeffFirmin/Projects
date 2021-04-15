package fr.univ.lyon1.m1if.m1if10Grp13.classes;

import java.io.Serializable;
import java.util.Date;


public class CreneauCompositeKey implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Date datecreneau;
    private Date heurecreneau;

    public CreneauCompositeKey(Date dateCreneau, Date heureCreneau) {
        this.datecreneau = dateCreneau;
        this.heurecreneau = heureCreneau;
    }
    public CreneauCompositeKey() {
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((datecreneau == null) ? 0 : datecreneau.hashCode());
        result = prime * result + ((heurecreneau == null) ? 0 : heurecreneau.hashCode());
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
        CreneauCompositeKey other = (CreneauCompositeKey) obj;
        if (datecreneau == null) {
            if (other.datecreneau != null)
                return false;
        } else if (!datecreneau.equals(other.datecreneau))
            return false;
        if (heurecreneau == null) {
            if (other.heurecreneau != null)
                return false;
        } else if (!heurecreneau.equals(other.heurecreneau))
            return false;
        return true;
    }

}
