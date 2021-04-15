#ifndef JEU_H
#define JEU_H
#include "Pieces.h"
#include "Joueur.h"
#include "Plateau.h"
#include <string.h>

using namespace std;

class Jeu {
public:

    /** @brief : Constructeur jeu **/
    Jeu();

    Plateau P;

    int DeplacementDame(char t[8][8][3], int x1,int y1, int x2, int y2);
    int DeplacementTour(char t[8][8][3], int x1,int y1, int x2, int y2);
    int DeplacementChevalier(char t[8][8][3], int x1,int y1, int x2, int y2);
    int DeplacementPionNoir(char t[8][8][3], int x1,int y1, int x2, int y2);
    int DeplacementPionBlanc(char t[8][8][3], int x1,int y1, int x2, int y2);
    int DeplacementFou(char t[8][8][3],int x1,int y1, int x2, int y2);
    int DeplacementRoi(char t[8][8][3], int x1,int y1, int x2, int y2);

    int Deplacement(char t[8][8][3], int x1, int y1, int x2, int y2, char couleur);

    void DeroulementJeu();

    /** @brief : destructeur jeu **/
    ~Jeu();




};

#endif // JEU_H

