#ifndef PLATEAU_H
#define PLATEAU_H
#include "Pieces.h"

class Plateau // à mettre a jour
{
public:

    /** @brief : Constructeur plateau **/
    Plateau();
    /** @brief : Destructeur tableau**/
    ~Plateau();

    char gett()const ;
    void sett(char t1[8][8][3]);
    void AfficherPlateau();
    char t[8][8][3];
private:


};

#endif // PLATEAU_H
