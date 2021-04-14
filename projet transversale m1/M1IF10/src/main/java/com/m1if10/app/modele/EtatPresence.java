package com.m1if10.app.modele;

/**
 * Enumeration of Presence states
 */
public enum EtatPresence {
    ABINJ ("ABINJ"),
    ABJ ("ABJ"),
    PRST ("PRST"),
    NULL ("NULL"); // etat pour les cours à venir

    private String name;

    EtatPresence(String name){
        this.name=name;
    }
    public String toString(){
        return name;
    }

}
