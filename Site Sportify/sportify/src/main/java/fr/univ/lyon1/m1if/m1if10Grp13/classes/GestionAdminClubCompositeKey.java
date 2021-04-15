package fr.univ.lyon1.m1if.m1if10Grp13.classes;

import java.io.Serializable;

public class GestionAdminClubCompositeKey implements Serializable {
    private Club club;
    private Admin admin;

    public GestionAdminClubCompositeKey() {
        super();
        // TODO Auto-generated constructor stub
    }

    public GestionAdminClubCompositeKey(Club club, Admin admin) {
        super();
        this.club = club;
        this.admin = admin;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((admin == null) ? 0 : admin.hashCode());
        result = prime * result + ((club == null) ? 0 : club.hashCode());
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
        GestionAdminClubCompositeKey other = (GestionAdminClubCompositeKey) obj;
        if (admin == null) {
            if (other.admin != null)
                return false;
        } else if (!admin.equals(other.admin))
            return false;
        if (club == null) {
            if (other.club != null)
                return false;
        } else if (!club.equals(other.club))
            return false;
        return true;
    }

}
