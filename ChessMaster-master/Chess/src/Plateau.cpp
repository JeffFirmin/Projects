#include "Plateau.h"
#include "Pieces.h"
#include <iostream>
#include <stdlib.h>
#include <string.h>

using namespace std;


Plateau::Plateau(){

   char noir[9][3] ={"TN", "CN","FN","DN", "RN", "FN", "CN","TN","PN"};
   char blanc[9][3] ={"TB", "CB","FB","DB","RB","FB","CB","TB","PB"};
    for(int i=2;i<6;i++)
{
    for(int j=0;j<8;j++){
            t[j][i][0]='0';
    }
}
for(int i=0;i<8;i++){
    strcpy(t[i][0],noir[i]);
    strcpy(t[i][1],noir[8]);
    strcpy(t[i][7],blanc[i]);
    strcpy(t[i][6],blanc[8]);
}}

void Plateau :: AfficherPlateau(){
    for(int i=0; i<8; i++){
    for(int x=0;x<3;x++){

        for(int j=0;j<8;j++){

            if(j==0){if(x==1){cout<<i+1 << "  ";} // Affichage des nombres à gauche
            else{cout<<"   ";}} // Fin

        if(t[j][i][0]=='0'){
            for(int largeur=0; largeur<4; largeur++){
            {
            if((i+j)%2==0){cout <<'*';
            }
            else(cout<<' ');}
            }
        }

        else{
        for(int largeur=0; largeur<4; largeur++){
            {
            if((i+j)%2==0){
                    if((x==1)&& (largeur==1)){cout << t[j][i][0];}
                    if((x==1)&& (largeur==2)){cout << t[j][i][1];} // Afficher selon le tableau
                    else if((x!=1) || (largeur ==0)||(largeur==3)){cout << '*';}
            }
            else if((i+j)%2==1){
                if((x==1)&& (largeur==1)){cout << t[j][i][0];}
                    if((x==1)&& (largeur==2)){cout << t[j][i][1];} // Afficher selon le tableau
                    else if((x!=1) || (largeur ==0)||(largeur==3)){cout << ' ';}
            }
               }

            }
        }}

                cout << endl;
        }
    }


cout << ' '; // Affichage des nombres du bas
for(int j=0; j <8; j++){cout << "   " << j+1;}

}

char Plateau:: gett() const{
}





Plateau::~Plateau(){

}
