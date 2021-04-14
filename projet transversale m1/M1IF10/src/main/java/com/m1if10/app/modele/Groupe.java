package com.m1if10.app.modele;

/**
 * Enumeration of different Groupes of Alternant
 */
public enum Groupe {
    A1("A1"),
    A2("A2");

        private String groupe;

        Groupe(String name){
            this.groupe = name;
        }

        public String toString() {
            return groupe;
        }
}
