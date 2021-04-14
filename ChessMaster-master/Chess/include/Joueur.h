#ifndef JOUEUR_H
#define JOUEUR_H
#include "Pieces.h"
#include <vector>
#include <stdlib.h>
#include <string>

using namespace std;

class Joueur
{
public:
    /** @brief : Constructeur joueur **/
    Joueur();
    /** @brief : Constructeur avec parametre **/
    Joueur(string nom);
    string getNom();
    void setNom(string &n);

    /** @brief : Destructeur **/
    ~Joueur();


private:
string nom;

};

#endif // JOUEUR_H
