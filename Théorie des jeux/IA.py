from random import randint

import chateau

class IA:           #Ici nous allons créer toutes les stratégies que nous allons utiliser
    
    nb_pierre_envoye=0

    def __init__(self,strategie):
        self.strategie=strategie

# Une fonction de stratégie par chateau

    #Choix de la stratégie en fonction du choix de l'utilisateur

    def choixStrategie(self,jeu,chateau):
        if(self.strategie == 1):
            print("bien rentrer")
            return self.StrategieAgressive(jeu.grille,chateau)
        elif self.strategie == 2:
            return self.StrategiePrudente()
        elif self.strategie == 3:
            return self.StrategieAleatoire(chateau)
        elif self.strategie == 4:
            if(chateau.nom=="Chateau 1"):
                return self.StrategieContreAttaque(jeu.grille,chateau,jeu.chateau2)
            elif(chateau.nom=="Chateau 2"):
                return self.StrategieContreAttaque(jeu.grille,chateau,jeu.chateau1)

    # def choixStrategie2(self,jeu,chateau):
    #     if(self.strategie == 1):
    #         return self.StrategieAgressive1(jeu.grille,chateau)
    #     elif self.strategie == 2:
    #         print("Bien rentrer")
    #         return self.StrategiePrudente()
    #     elif self.strategie == 3:
    #         return self.StrategieAleatoire(self,chateau)

    # Stratégie Agressive

    def StrategieAgressive (self, grille, chateau):
        nb_pierre = chateau.pierre
        if chateau.nom == "Chateau 2":
            distance = (grille.index("troll") - grille.index("Chateau 1"))
        elif chateau.nom == "Chateau 1":
            distance = (grille.index("Chateau 2") - grille.index("troll"))
        print("LA DISTANCE : ", distance)
        if distance == 1:
            self.nb_pierre_envoye = chateau.pierre
        elif distance > 1:
            self.nb_pierre_envoye = int(nb_pierre / distance)
        if self.nb_pierre_envoye < 1 or chateau.pierre == 1:
            self.nb_pierre_envoye = 1

        print(self.nb_pierre_envoye)

        return self.nb_pierre_envoye

    # def StrategieAgressive2(self,grille,chateau):
    #     nb_pierre = chateau.pierre
    #     distance= (grille.index("troll")-grille.index("Chateau 1"))
    #     print("LA DISTANCE : ",distance)
    #     self.nb_pierre_envoye = int(nb_pierre/distance)
    #     print(self.nb_pierre_envoye)
    #     return self.nb_pierre_envoye

    #Stratégie Aleatoire

    def StrategieAleatoire(self,chateau):
        self.nb_pierre_envoye= randint(1,chateau.pierre)
        print(" ALEA : ",self.nb_pierre_envoye)
        return self.nb_pierre_envoye

    # Stratégie Contre Attaque


    # A DISCUTER ET CORRIGER
    def StrategieContreAttaque(self,grille,chateau,chateauAdverse): # Problème si adversaire envoie toute les pièces au porte du chateau2 mais marche pour chateau 1
        if(chateau.nom=="Chateau 1"): #On est le chateau 1 on regarde la distance du troll à notre chateau
            distance= (grille.index("troll")-grille.index("Chateau 1"))
        elif(chateau.nom=="Chateau 2"): #On est le chateau 2 on regarde la distance du troll à notre chateau
            distance= (grille.index("Chateau 2")-grille.index("troll"))

        if(distance==1 and chateau.pierre >= (chateauAdverse.pierre +1)):
            print("Contre Attaque Activé")
            self.nb_pierre_envoye=chateauAdverse.pierre+1
        elif(distance>1):
            self.nb_pierre_envoye=1
        print("Distance Contre attaque :",distance)
        print("Contre Attaque : ",self.nb_pierre_envoye)
        return self.nb_pierre_envoye



    #Strategie Prudente

    def StrategiePrudente(self):
        self.nb_pierre_envoye=3
        return self.nb_pierre_envoye

