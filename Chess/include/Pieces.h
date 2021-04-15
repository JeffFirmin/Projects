#ifndef PIECES_H
#define PIECES_H
#include <vector>
#include <stdlib.h>
#include "Plateau.h"
using namespace std;

class Pieces
{
public:
    /** @brief : Constructeur des pieces **/
    Pieces();
    /** @brief : Constructeur par copie de pièces**/
    Pieces(char nom , char couleur);
    /** @brief : Destructeur de pieces **/
    ~Pieces();
    /** @brief : Accesseur nom**/
    char getNom() const ;
    /** @brief : Mutateur nom **/
    void setNom(int n);
    /** @brief : Accesseur couleur**/
    char getCouleur()const ;
    /** @brief : Mutateur couleur**/
    void setCouleur(int co);




private:

    /** @brief : Identifier chaque piece d'échechs **/
    char nom;

    /**@brief : Couleur afin de mettre 'b' pour les blanches et 'n' pour les noirs**/
    char couleur;

};

#endif // PIECES_H
