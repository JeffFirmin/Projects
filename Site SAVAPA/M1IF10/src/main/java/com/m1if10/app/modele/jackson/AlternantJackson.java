package com.m1if10.app.modele.jackson;

import com.m1if10.app.modele.Alternant;

/**
 * Class to prepare Alternant to be transformed into .json file
 */
public class AlternantJackson {
    private String nom;
    private String prenom;
    private String numetu;
    private String cleQr;
    private String photo;

    public AlternantJackson() {
        nom = "";
        prenom = "";
        numetu ="";
        cleQr = "";
        photo = "";
    }

    public AlternantJackson(Alternant alt, String photo) {
        nom = alt.getNom();
        this.prenom = alt.getPrenom();
        this.cleQr = alt.getCleQr();
        this.numetu = alt.getNumEtu();
        this.photo = photo;
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

    public String getCleQr() {
        return cleQr;
    }

    public void setCleQr(String cleQr) {
        this.cleQr = cleQr;
    }

    public String getNumetu() {
        return numetu;
    }

    public void setNumetu(String numetu) {
        this.numetu = numetu;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
