#include <iostream>
#include <SFML/Graphics.hpp>
#include <time.h>
<<<<<<< HEAD
#include <string>
=======



>>>>>>> 3dd2830f552a1103633d0f7ed5a314ee2cb5ba8f
using namespace std;
using namespace sf;

//déclaration du plateau
RenderWindow  window;

// pour l'affichage du plateau
int size=56;
<<<<<<< HEAD

Sprite f[32]; // pour nos figures

int Plateau[8][8] =
    {-1,-2,-3,-4,-5,-3,-2,-1,
     -6,-6,-6,-6,-6,-6,-6,-6,
      0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0,
      6, 6, 6, 6, 6, 6, 6, 6,
      1, 2, 3, 4, 5, 3, 2, 1
    };
=======
Vector2f offset(28,28);

Sprite f[32]; // pour nos figures

////// signification ///////
/*  -1: la tour       1: la tour
    -2: le cavalier   2: le cavalier
    -3: le fou        3:le fou
    -4: la dame       4:la dame
    -5: le roi        5: le roi
*/
int Plateau[8][8] =
    {-1,-2,-3,-4,-5,-3,-2,-1,
	 -6,-6,-6,-6,-6,-6,-6,-6,
	  0, 0, 0, 0, 0, 0, 0, 0,
	  0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0,
	  0, 0, 0, 0, 0, 0, 0, 0,
	  6, 6, 6, 6, 6, 6, 6, 6,
	  1, 2, 3, 4, 5, 3, 2, 1};
// un pion ne doit pas manger son equipe
std::string position="";



////////////////  appel de fonctions  /////////////////


Vector2f Coordonner(char a, char b)
{
  int xi= int(a) - 97;
  int yi= 7-int(b) + 49;
  return  Vector2f(xi*size, yi*size);

}





// pour les mouvement et (manger un pion)
void move(std::string str)
{
  Vector2f oldPos= Coordonner(str[0],str[1]);
  Vector2f  newPos= Coordonner(str[2],str[3]);
  // pour toutes les pieces des deux équipes
  for(int i=0;i<32;i++)
     if(f[i].getPosition() == newPos)
     f[i].setPosition(-100,-100);

  for(int i=0;i<32;i++)
  if(f[i].getPosition()== oldPos) f[i].setPosition(newPos);

  //castling       //si le roi ne bouge pas
	if (str=="e1g1") if (position.find("e1")==-1) move("h1f1");
	if (str=="e8g8") if (position.find("e8")==-1) move("h8f8");
	if (str=="e1c1") if (position.find("e1")==-1) move("a1d1");
	if (str=="e8c8") if (position.find("e8")==-1) move("a8d8");

}


>>>>>>> 3dd2830f552a1103633d0f7ed5a314ee2cb5ba8f

// pour charger la position du plateau
void chargePosition()
{
  int l=0;
  for(int i=0; i<8; i++)
     for( int j=0; j<8;j++)
     {
       int p= Plateau[i][j];
       if(!p) continue;
<<<<<<< HEAD
       int x= abs(p)-1;
       int y= p>0?1:0;
=======
       int x = abs(p)-1;
       int y = p>0?1:0;
>>>>>>> 3dd2830f552a1103633d0f7ed5a314ee2cb5ba8f

       // on dessine un rectangle
       f[l].setTextureRect( IntRect(size*x,size*y,size,size));
       // pour positionné notre rectangle dans le tableau
       f[l].setPosition(size*j,size*i);
       l++;

<<<<<<< HEAD

     }
=======
     }

    for(int i=0;i<position.length(); i+=5)
       move(position.substr(i,4));

>>>>>>> 3dd2830f552a1103633d0f7ed5a314ee2cb5ba8f
}



<<<<<<< HEAD
=======
// pour avoir la position actuelle de chaque pion
std::string chessNote(Vector2f p)
{
  std::string s= "";
  s+= char(p.x/size+97);
  s+= char(7-p.y/size+49);
  return s;
}




>>>>>>> 3dd2830f552a1103633d0f7ed5a314ee2cb5ba8f
int main ()
{

    // pour créer la fenetre  et sf c'est pour un objet en sfml
<<<<<<< HEAD
    window.create(sf::VideoMode(600, 500), "SFML works!");
=======
    window.create(sf::VideoMode(504, 504), "SFML works!");
>>>>>>> 3dd2830f552a1103633d0f7ed5a314ee2cb5ba8f

    // pour modifier la position de la fenetre
    window.setPosition(sf::Vector2i(190, 90));

    // pour modifier la taille de notre fenetre
    //window.setSize(sf::Vector2u(700,700));

    // pour limiter le nombre d'image par seconde de notre fenetre
    window.setFramerateLimit(60);


    //declaration de la texture
<<<<<<< HEAD
    Texture t1,t2;
    t1.loadFromFile("data/pions.png");
    t2.loadFromFile("data/plateau.png");


    // on a son sprite du cavalier
    Sprite s(t1);
=======
    Texture t1,t2,t3;
    t1.loadFromFile("data/pions.png");
    t2.loadFromFile("data/plateau.png");
    t3.loadFromFile("data/CN.png");
    // on a son sprite des pions
    Sprite s(t1);

    //Sprite cavalier
    Sprite cav(t3);

>>>>>>> 3dd2830f552a1103633d0f7ed5a314ee2cb5ba8f
    //le sprite du plateau
    Sprite splateau(t2);

    for(int i=0; i<32; i++) // f c'est notre sprite
    {
<<<<<<< HEAD
=======
      // on pose la texture sur chaque case
>>>>>>> 3dd2830f552a1103633d0f7ed5a314ee2cb5ba8f
      f[i].setTexture(t1);
    }

    // Appel de notre fonctionS
    chargePosition();

<<<<<<< HEAD
    //le mouvement
    bool isMove=false;
    float dx=0, dy=0; // pour les directions
=======

    //le mouvement
    bool isMove=false;
    float dx=0, dy=0; // pour les directions
   Vector2f oldPos,newPos; // les positions des pions
    std::string str;

    int entierPiece=0;
>>>>>>> 3dd2830f552a1103633d0f7ed5a314ee2cb5ba8f

    // pour conserver la fenetre ouverte
    while (window.isOpen())
    {

        // pos prends la position de la souris
<<<<<<< HEAD
        Vector2i pos= Mouse::getPosition(window);
=======
        Vector2i pos= Mouse::getPosition(window)  - Vector2i(offset);
>>>>>>> 3dd2830f552a1103633d0f7ed5a314ee2cb5ba8f


        Event event;
        // c'est pour dire que si on a un evenement qui se produit
        while (window.pollEvent(event))
        {   // si on clique sur le bouton fermer
            if (event.type == sf::Event::Closed)
                window.close(); //on ferme la fenetre


<<<<<<< HEAD
           ///// glisser déposer//////

           if(event.type == Event::MouseButtonPressed) // on clique la souris
              if(event.key.code == Mouse::Left)
                 //si dans sprite on a la position de la souris
                if(s.getGlobalBounds().contains(pos.x,pos.y))
                {
                  isMove= true;
                  dx=pos.x - s.getPosition().x;
                  dy=pos.y - s.getPosition().y;
=======
            //////// le mouvement du Récule//////////
              if(event.type == Event::KeyPressed)
              if (event.key.code == Keyboard::BackSpace)
				{
				  if (position.length()>6)
				    position.erase(position.length()-6,5); chargePosition();
                }
           ///////// glisser déposer//////////

           if(event.type == Event::MouseButtonPressed) // on clique la souris
              if(event.key.code == Mouse::Left)
                 // pour le déplacement des pieces
                 for(int i=0;i<32;i++)
                 //si dans sprite on a la position de la souris
                if(f[i].getGlobalBounds().contains(pos.x,pos.y))
                {
                  isMove= true;
                  entierPiece=i;
                  // dx et dy c'est pour savoir la distance entre la souris et le pièce séléctionné
                  dx=pos.x - f[i].getPosition().x;
                  dy=pos.y - f[i].getPosition().y;
                  // la position avant un evenement clique
                  oldPos= f[i].getPosition();
>>>>>>> 3dd2830f552a1103633d0f7ed5a314ee2cb5ba8f
                }

           // si le butoon est relaché et que ce boutton est (le left) alors on arrete le déplacement de l'objet
           if(event.type == Event::MouseButtonReleased)
              if(event.key.code == Mouse::Left)
<<<<<<< HEAD
                 isMove= false;



}
          // si le mouvement est vraie donc on déplace notre sprite
         if(isMove) s.setPosition(pos.x-dx,pos.y-dy);
=======
                 {
                 isMove= false;
                 // pour savoir la position d'un pion et le mettre à jour(pour toujour mettre le pion dans une case)
                 Vector2f actu=f[entierPiece].getPosition() + Vector2f(size/2,size/2);
                 // pour ne pas avoir des arrondit et bien positionné dans une case
                 newPos= Vector2f(size*int(actu.x/size),size*int(actu.y/size) );
                 str = chessNote(oldPos)+chessNote(newPos);
                 move(str);
                 if(oldPos!=newPos) position+=str+"";
                 cout<<str<<endl;
                 f[entierPiece].setPosition(newPos);

                 }


}



    //////////////// VENIR BOUGER  ////////////////////////

    if (Keyboard::isKeyPressed(Keyboard::Space))//touche clavier espace
       {
         // par exemple
		 str = "d7d5";

         oldPos= Coordonner(str[0],str[1]);
         newPos =  Coordonner(str[2],str[3]);

         for(int i=0;i<32;i++)
         if (f[i].getPosition()==oldPos)
         entierPiece=i;

          ///////////aANIMATION//////////////

		 for(int j=0;j<50;j++)
		  {
			Vector2f actu = newPos- oldPos;
		    f[entierPiece].move(actu.x/50, actu.y/50);
			window.draw(splateau);
			for(int i=0;i<32;i++) f[i].move(offset);
			for(int i=0;i<32;i++) window.draw(f[i]); window.draw(f[entierPiece]);
			for(int i=0;i<32;i++) f[i].move(-offset);
			window.display();
		  }

		move(str);  position+=str+" ";
		f[entierPiece].setPosition(newPos);
        }






          // si le mouvement est vraie donc on déplace notre sprite
         if(isMove) f[entierPiece].setPosition(pos.x-dx,pos.y-dy);
>>>>>>> 3dd2830f552a1103633d0f7ed5a314ee2cb5ba8f


        /////////////pour déssiner nos élément dans notre fénetre. (tant que ma fenetre est ouverte)

        // pour actualiser notre fenetre , les objets
        window.clear();

        //dessiner
        window.draw(splateau);
<<<<<<< HEAD
        // pour charger toutes nos pions
        for(int i=0; i<32; i++)
        {
          window.draw(f[i]);
        }
=======
        for(int i=0;i<32;i++) f[i].move(offset);
        for(int i=0;i<32;i++) window.draw(f[i]); window.draw(f[entierPiece]);
	    for(int i=0;i<32;i++) f[i].move(-offset);

	    window.draw(cav);
>>>>>>> 3dd2830f552a1103633d0f7ed5a314ee2cb5ba8f
        //afficher le contenu de notre fenetre
        window.display();
    }


    return 0;
}



<<<<<<< HEAD





=======
////////////  LES FONCTIONS POUR LES DEPLACEMENT DES PIONS /////////


//Deplacement pour le dame
/*
int DeplacementDame(char t[8][8][3], int x1,int y1, int x2, int y2)
{
int i; int d=1;

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


*/
>>>>>>> 3dd2830f552a1103633d0f7ed5a314ee2cb5ba8f




