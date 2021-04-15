package fr.univ.lyon1.m1if.m1if10Grp13.classes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "gestion_admin_club")
@IdClass(GestionAdminClubCompositeKey.class)
public class GestionAdminClub implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @Id
    @ManyToOne
    @JoinColumn(name = "emailclub")
    private Club club;
    @Id
    @ManyToOne
    @JoinColumn(name = "adminId")
    private Admin admin;
    private Date dategestion;
    private AdminOperation operation;

    public GestionAdminClub(Club club, Admin admin, Date dateGestion, AdminOperation operation) {
        this.club = club;
        this.admin = admin;
        this.dategestion = dateGestion;
        this.operation = operation;
    }

    public GestionAdminClub() {

    }

    public Club getClubId() {
        return club;
    }

    public void setClubId(Club club) {
        this.club = club;
    }

    public Admin getAdminId() {
        return admin;
    }

    public void setAdminId(Admin admin) {
        this.admin = admin;
    }

    public Date getDateGestion() {
        return dategestion;
    }

    public void setDateGestion(Date dateGestion) {
        this.dategestion = dateGestion;
    }

    public AdminOperation getOperation() {
        return operation;
    }

    public void setOperation(AdminOperation operation) {
        this.operation = operation;
    }
}
