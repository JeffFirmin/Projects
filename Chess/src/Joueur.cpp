#include <iostream>
#include "Joueur.h"
#include "Pieces.h"
#include <vector>
#include <stdlib.h>
#include <string>
using namespace std;


Joueur::Joueur()
{
    nom= "00";
    //Agagne = false;
}

Joueur::Joueur(string nom1){
    nom1 = nom ;
}

void Joueur::setNom(string &n){
    nom = n;
}

string Joueur::getNom() {
    return nom;
}





Joueur::~Joueur(){
}
