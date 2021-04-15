#include "Jeu.h"
#include "Pieces.h"
#include "Joueur.h"
#include "Plateau.h"
#include <iostream>
#include <string.h>
#include <math.h>
#include <stdlib.h>
using namespace std ;

Jeu::Jeu()
{

}


int Jeu::DeplacementDame(char t[8][8][3], int x1,int y1, int x2, int y2)
{int i; int d=1;
if((x2!=x1)&&(y2!=y1)&&(abs(x1-x2)!=abs(y1-y2))){cout << "mauvais déplacement"; return 0;}

else if((x1<x2)&&(y1==y2)){
			for(i=1;(i+x1)<x2;i++)
			{
				 if(t[x1+i][y1][0]!='0'){
                        d = 0;
                 }
            }
                if(d==1){
                    strcpy(t[x2][y2],t[x1][y1]);
                    t[x1][y1][0]='0';}
                    return d;
}
else if((x1>x2)&&(y1==y2)){d = 1;
			for(i=1;(x1-i)>x2;i++)
			{
				 if(t[x1-i][y1][0]!='0'){
                        d = 0;
                 }
            }
                if(d==1){
                    strcpy(t[x2][y2],t[x1][y1]);
                    t[x1][y1][0]='0';
                    }
                    return d;}

    else if((y1<y2)&&(x1==x2)){for(i=1;(i+y1)<y2;i++)
			{
				 if(t[x1][y1+i][0]!='0'){
                        d = 0;
                 }
            }
                if(d==1){
                    strcpy(t[x2][y2],t[x1][y1]);
                    t[x1][y1][0]='0';
                    }
                    return d;}


    else if((y1>y2)&&(x1==x2)){for(i=1;(y1-i)>y2;i++)
			{
				 if(t[x1][y1-i][0]!='0'){
                        d = 0;
                 }
            }
                if(d==1){
                    strcpy(t[x2][y2],t[x1][y1]);
                    t[x1][y1][0]='0';
                }
                    return d;
    }



else if((x1<x2)&&(y1<y2))
		{  d = 1;
			for(i=1;(i+x1)<x2;i++)
			{
				 if(t[x1+i][y1+i][0]!='0'){
                        d = 0;
                 }
            }
                if(d==1){
                    strcpy(t[x2][y2],t[x1][y1]);
                    t[x1][y1][0]='0';
                    t[x1][y1][1]='0';}
                    return d;
                }

else if((x1>x2)&&(y1>y2)){
        d=1;
	for(i=1;(x1-i)<x2;i++){
        if(t[x1-i][y1-i][0]!='0'){
            d = 0;
        } }
        if(d==1){
            strcpy(t[x2][y2],t[x1][y1]);
            t[x1][y1][0]='0';
            t[x1][y1][1]='0';}
            return d;
        }

	else if((x1>x2)&&(y1<y2)){
	    d=1;
	for(i=1;(x1-i)<x2;i++)
			{
				 if(t[x1-i][y1+i][0]!='0'){
                        d = 0;
                 }
                }
                if(d==1){
                    strcpy(t[x2][y2],t[x1][y1]);
                    t[x1][y1][0]='0';
                    t[x1][y1][1]='0';}
                    return d;
                }

	else if((x1<x2)&&(y1>y2)){
	    d=1;
	for(i=1;(y1-i)<y2;i++)
			{
				 if(t[x1+i][y1-i][0]!='0'){
                        d = 0;
                 }
                }
                if(d==1){
                    strcpy(t[x2][y2],t[x1][y1]);
                    t[x1][y1][0]='0';
                    t[x1][y1][1]='0';}
                    return d;
                }
}

int Jeu::DeplacementTour(char t[8][8][3], int x1,int y1, int x2, int y2){
    int d = 1; int i;
if((x2!=x1)&&(y2!=y1)){cout << "mauvais déplacement"; return 0;}


else if(x1<x2){
        d = 1;
			for(i=1;(i+x1)<x2;i++)
			{
				 if(t[x1+i][y1][0]!='0'){
                        d = 0;
                 }
            }
                if(d==1){
                    strcpy(t[x2][y2],t[x1][y1]);
                    t[x1][y1][0]='0';}
                    cout << "Deplacement 1";
                    return d;
}
else if(x1>x2){d = 1;
			for(i=1;(x1-i)>x2;i++)
			{
				 if(t[x1-i][y1][0]!='0'){
                        d = 0;
                 }
            }
                if(d==1){
                    strcpy(t[x2][y2],t[x1][y1]);
                    t[x1][y1][0]='0';
                    }
                    cout << "Déplacement 2";
                    return d;}

    else if(y1<y2){for(i=1;(i+y1)<y2;i++)
			{
				 if(t[x1][y1+i][0]!='0'){
                        d = 0;
                 }
            }
                if(d==1){
                    strcpy(t[x2][y2],t[x1][y1]);
                    t[x1][y1][0]='0';
                    }
                    cout << "Déplacement 3";
                    return d;}


    else if(y1>y2){for(i=1;(y1-i)>y2;i++)
			{
				 if(t[x1][y1-i][0]!='0'){
                        d = 0;
                 }
            }
                if(d==1){
                    strcpy(t[x2][y2],t[x1][y1]);
                    t[x1][y1][0]='0';
                }
                    cout << "Déplacement 4";
                    return d;
    }
}

int Jeu::DeplacementRoi(char t[8][8][3], int x1,int y1, int x2, int y2){
    if(t[x2][y2][1]==t[x1][y1][1]){
        cout << "Même pièce";
        return 0;
    }

    if(((y2==y1+1)&&((x2==x1)||(x2==x1+1)||(x2==x1-1))) || ((y2==y1)&&((x2==x1+1)||(x2==x1-1))) ||  ((y2==y1-1)&&((x2==x1)||(x2==x1+1)||(x2==x1-1))))
    {
        strcpy(t[x2][y2],t[x1][y1]);
        t[x1][y1][0]='0';
        t[x1][y1][1]='0';
        return 1;
    }

}

int Jeu::DeplacementChevalier(char t[8][8][3], int x1,int y1, int x2, int y2){


    if((x2==x1+1 && y2==y1+2)||
       (x2==x1+1 && y2==y1-2)||
       (x2==x1-1 && y2==y1+2)||
       (x2==x1-1 && y2==y1-2)||
       (x2==x1+2 && y2==y1+1)||
       (x2==x1+2 && y2==y1-1)||
       (x2==x1-2 && y2==y1+1)||
       (x2==x1-2 && y2==y1-1)){

            strcpy(t[x2][y2],t[x1][y1]);
            t[x1][y1][0]='0';
            t[x1][y1][1]='0';
            return 1;
       }
       else{cout << "mauvais déplacement";
       return 0;}
   }

int Jeu::DeplacementPionNoir(char t[8][8][3], int x1,int y1, int x2, int y2)
{
    if((x1 == x2) && (y2==(y1+1))&&(t[x2][y2][0]=='0')){
        strcpy(t[x2][y2],t[x1][y1]);
        t[x1][y1][0]='0';
        t[x1][y1][1]='0';
        return 1;
    }

    else if((x2==x1)&& (y1==1) &&(y2==(y1+2))  && (t[x1][y1+1][0]=='0') && (t[x2][y2][0]== '0'))
    {
        strcpy(t[x2][y2],t[x1][y1]);
        t[x1][y1][0]='0';
        t[x1][y1][1]='0'; return 1;
    }
     // Manger le pion
else if((x2==x1-1) && (y2==y1+1) && (t[x2][y2][0]!='0') && (t[x2][y2][1]=='B')){
        strcpy(t[x2][y2],t[x1][y1]);
        t[x1][y1][0]='0';
        t[x1][y1][1]='0'; return 1;}

else if((x2==x1+1) && (y2==y1+1) && (t[x2][y2][0]!='0') && (t[x2][y2][1]=='B')){
        strcpy(t[x2][y2],t[x1][y1]);
        t[x1][y1][0]='0';
        t[x1][y1][1]='0'; return 1;}

else{cout << "Deplacement Impossible" << endl;
return 0;}
}

int Jeu::DeplacementPionBlanc(char t[8][8][3], int x1,int y1, int x2, int y2)
{
    if((x2==x1) && (y2==(y1-1))&&(t[x2][y2][0]=='0')){
        strcpy(t[x2][y2],t[x1][y1]);
        t[x1][y1][0]='0';
        t[x1][y1][1]='0';
         return 1;
    }
    else if((x1==x2) && (y2==(y1-2)) && (y1==6) && (t[x2][y2+1][0]=='0') && (t[x2][y2][0]== '0'))
    {
        strcpy(t[x2][y2],t[x1][y1]);
        t[x1][y1][0]='0';
        t[x1][y1][1]='0'; return 1;
    } // Manger le pion
else if((x2==x1-1) && (y2==y1-1) && (t[x2][y2][0]!='0') && (t[x2][y2][1]=='N')){
        strcpy(t[x2][y2],t[x1][y1]);
        t[x1][y1][0]='0';
        t[x1][y1][1]='0'; return 1;}

else if((x2==x1+1) && (y2==y1-1) && (t[x2][y2][0]!='0') && (t[x2][y2][1]=='N')){
        strcpy(t[x2][y2],t[x1][y1]);
        t[x1][y1][0]='0';
        t[x1][y1][1]='0'; return 1;}

else{cout << "Deplacement Impossible" << endl;
return 0;}
}

int Jeu::DeplacementFou(char t[8][8][3],int x1,int y1, int x2, int y2){
    int i; int d;

    if(abs(x1 - x2) != abs(y1 - y2)){
        cout << "C'EST FAUX T'es nul";
            return 0;
    }

else if((x1<x2)&&(y1<y2))
		{  d = 1;
			for(i=1;(i+x1)<x2;i++)
			{

				 if(t[x1+i][y1+i][0]!='0'){
                        d = 0;
                 }
            }
                if(d==1){
                    strcpy(t[x2][y2],t[x1][y1]);
                    t[x1][y1][0]='0';
                    t[x1][y1][1]='0';}
                    cout << "d " << d << "Déplacement 1";
                    return d;
                }

else if((x1>x2)&&(y1>y2)){
        d=1;
	for(i=1;(x1-i)<x2;i++){
        if(t[x1-i][y1-i][0]!='0'){
            d = 0;
        } }
        if(d==1){
            strcpy(t[x2][y2],t[x1][y1]);
            t[x1][y1][0]='0';
            t[x1][y1][1]='0';}
            cout << "d " << d << "Déplacement 2";
            return d;
        }

	else if((x1>x2)&&(y1<y2)){
	    d=1;
	for(i=1;(x1-i)<x2;i++)
			{
				 if(t[x1-i][y1+i][0]!='0'){
                        d = 0;
                 }
                }
                if(d==1){
                    strcpy(t[x2][y2],t[x1][y1]);
                    t[x1][y1][0]='0';
                    t[x1][y1][1]='0';}
                    cout << "d " << d << "Déplacement 3";
                    return d;
                }

	else if((x1<x2)&&(y1>y2)){
	    d=1;
	for(i=1;(y1-i)<y2;i++)
			{
				 if(t[x1+i][y1-i][0]!='0'){
                        d = 0;
                 }
                }
                if(d==1){
                    strcpy(t[x2][y2],t[x1][y1]);
                    t[x1][y1][0]='0';
                    t[x1][y1][1]='0';}
                    cout << "d " << d << "Déplacement 4";
                    return d;
                }
	 }

int Jeu::Deplacement(char t[8][8][3], int x1, int y1, int x2, int y2, char couleur){
cout << couleur;

        if((x1>7) || (x1<0) ||(y1>7)||(y1<0)){ // C'est bon
        cout << "Dehors du plateau";
        return 0;
    }
    else if((x2>7) || (x2<0) ||(y2>7)||(y2<0)){ // C'est bon
        cout << "Dehors du plateau";
        return 0;
    }
    else if(t[x1][y1][1]!= couleur){cout << "Couleur fausse"; return 0; }

else if(t[x2][y2][0]=='0' || t[x2][y2][1]!=couleur){
    if((t[x1][y1][0]=='P')&&(couleur == 'N')){DeplacementPionNoir(t,x1,y1,x2,y2);}
    else if((t[x1][y1][0]=='P')&&(couleur == 'B')){DeplacementPionBlanc(t,x1,y1,x2,y2);}
    else if(t[x1][y1][0]=='T'){DeplacementTour(t,x1,y1,x2,y2);}
    else if(t[x1][y1][0]=='C'){DeplacementChevalier(t,x1,y1,x2,y2);}
    else if(t[x1][y1][0]=='F'){DeplacementFou(t,x1,y1,x2,y2);}
    else if(t[x1][y1][0]=='D'){DeplacementDame(t,x1,y1,x2,y2);}
    else if(t[x1][y1][0]=='R'){DeplacementRoi(t,x1,y1,x2,y2);}
    }

    else if(t[x1][y1][1]==t[x2][y2][1]){cout << "Piece alliee"; return 0;}

}

void Jeu::DeroulementJeu(){
int x1,y1,x2,y2,j=0;
int i =0;
P.AfficherPlateau();
while(j!=100){
    cout << endl << "x1 = ";
    cin >> x1;
    cout  << "y1 = ";
    cin >> y1;
    cout << "x2 = ";
    cin >> x2;
    cout << "y2 = ";
    cin >> y2;
    if(i%2==0){
    cout << "Tour des noirs";
    i += Deplacement(P.t,x1,y1,x2,y2,'N');
    }
    else if(i%2==1){
        cout << "Tour des blancs";
        i+=Deplacement(P.t,x1,y1,x2,y2,'B');
    }
j=j+1;
cout << endl << endl << "i= " <<i << endl;
P.AfficherPlateau();

}
}

Jeu::~Jeu(){

}
