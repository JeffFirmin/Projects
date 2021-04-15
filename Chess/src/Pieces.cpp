#include "Pieces.h"
#include <iostream>
#include <string>
#include <string.h>
using namespace std;

Pieces::Pieces()
{
    char nom = 'P';
    char couleur = 'B';

}

Pieces::Pieces(char n1,char col){
    if ( (n1=='P' || n1 == 'C' || n1 =='T' || n1 == 'D' || n1 == 'R' || n1 == 'F') && (col == 'B' || col == 'N')){
        nom = n1;
        couleur = col;

    }
    else{
        cout <<  " La piece n'est pas valide "<<endl;
    }
}


char Pieces::getNom() const {
    return nom;
}

void Pieces::setNom(int n){
    if (n=='P' || n == 'C' || n =='T' || n == 'D' || n == 'R' || n == 'F'){
        nom = n ;
    }
}

char Pieces::getCouleur() const {
    return couleur;
}

void Pieces::setCouleur(int co){
    if (co == 'B' || co == 'N'){
        couleur = co ;
    }
}


Pieces::~Pieces()
{
    cout<<" La piece est supprimé  "<<endl;
}
