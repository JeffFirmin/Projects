package com.m1if10.app.modele;


import javax.persistence.*;
import java.util.Objects;

/**
 * Class of User model used for different models in the DB
 */
@Entity
@Table(name="users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User{

    @Id
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "nom")
    private String nom;
    @Column(name = "prenom")
    private String prenom;
    @Column(name = "urlPhoto")
    private String urlPhoto;

    public User(){
        this.email = "";
        this.password = "";
        this.nom = "";
        this.prenom = "";
        this.urlPhoto = "";
    }

    public User(String email, String password, String nom, String prenom, String urlPhoto) {
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.urlPhoto = urlPhoto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    /**
     * Checks if User is equal to another one
     * @param o: User object
     * @return true if User object equals another User
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return  Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(nom, user.nom) &&
                Objects.equals(prenom, user.prenom) &&
                Objects.equals(urlPhoto, user.urlPhoto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, nom, prenom, urlPhoto);
    }

}
