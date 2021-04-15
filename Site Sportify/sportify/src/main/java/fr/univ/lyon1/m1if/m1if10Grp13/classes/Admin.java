package fr.univ.lyon1.m1if.m1if10Grp13.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @Column(name = "adminid")
    private Long adminId;

    @Column(name = "nomadmin")
    private String nomAdmin;

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public void setNomAdmin(String nomAdmin) {
        this.nomAdmin = nomAdmin;
    }

    public Long getAdminId() {
        return adminId;
    }

    public String getNomAdmin() {
        return nomAdmin;
    }
}
