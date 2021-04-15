package com.m1if10.app.modele;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Class of administration model in the DB
 */
@Entity
@Table(name="adminSco")
@PrimaryKeyJoinColumn(name = "email")
public class AdminSco extends User {
    public AdminSco() {
        super();
    }

    public AdminSco(String email, String password, String nom, String prenom, String urlPhoto) {
        super(email, password, nom, prenom, urlPhoto);
    }
}

