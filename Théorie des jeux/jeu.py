import chateau
import troll
import sys
import os
import IA

def cls(): print("\n" * 100)


class jeu:
    IA1 = IA.IA(0) # Nous dirons quelle stratégie va utiliser chaque IA
    IA2 = IA.IA(0)   # Par exemple si IA1 = 1 on utilisera la stratégie agressive etc.
    nb_cases = 0  # nombre de case dans le jeu
    nb_pierres = 0  # nombre de pierre
    grille = []  # grille
    chateau1 = chateau.chateau(0,"Chateau 1")  # chateau1
    chateau2 = chateau.chateau(0,"Chateau 2")  # chateau 2
    troll = troll.troll(0)  # troll

    # def __init__(self, cases, pierres):         #constructeur
    # Grille et initialisation de la grille
    # _________________________________________________________________________________________________________________________

    def reinitialise_pierre(self): #Reinitialise le nb de pierre envoyer a 0
        self.chateau1.nb_pierre_envoyee = 0
        self.chateau2.nb_pierre_envoyee = 0

    def init(self, cases, pierres):
        self.nb_cases = cases
        self.nb_pierres = pierres
        self.init_grille()
        self.troll.emplacement = self.place_troll() - 1  # tableau commence à 0
        self.chateau1.pierre = self.nb_pierres
        self.chateau2.pierre = self.nb_pierres
        print(self.affiche_grille())
        print("Init fini")
        # print("TROLL : ",self.grille.index("troll"))
        # print("Chateau 2",self.grille.index("Chateau 2"))
        # difference=self.grille.index("Chateau 2")-self.grille.index("troll")
        # print(difference)
    def initIA(self):
        print("Stratégie IA 1 ? Choisissez une des stratégie suivantes : ")
        print("a. Aggresive")
        print("b. Prudente ")
        print("c. Aléatoire ")
        print("d. Contre-Attaque ")
        nb = input("Votre choix ?")
        while nb != 'a' and nb != 'b' and nb != 'c' and nb != 'd':
            nb = input("Votre choix ?")
        if nb == 'a':
            self.IA1.strategie=1
        elif nb == 'b':
            self.IA1.strategie=2
        elif nb == 'c':
            self.IA1.strategie=3
        elif nb == 'd':
            self.IA1.strategie=4
        print("Stratégie IA 2 ? Choisissez une des stratégie suivantes : ")
        print("a. Aggresive")
        print("b. Prudente ")
        print("c. Aléatoire ")
        print("d. Contre-Attaque ")
        nb = input("Votre choix ?")
        while nb != 'a' and nb != 'b' and nb != 'c' and nb != 'd':
            nb = input("Votre choix ?")
        if nb == 'a':
            self.IA2.strategie=1
        elif nb == 'b':
            self.IA2.strategie=2
        elif nb == 'c':
            self.IA2.strategie=3
        elif nb == 'd':
            self.IA2.strategie=4

            # def init_grille(self,grille):                       #on initialise la grille avec le chateau troll et des 0 quand c'est vide
    #     i=1
    #     i=self.init_grille1(i,grille)
    #     i=self.init_troll(i,grille)
    #     self.init_grille2(i,grille)
    #
    #
    # def init_grille1(self,i, grille):                   #La première partie de la grille jusqu'au troll
    #     grille.append("Chateau 1")
    #     i=i+1
    #     while i < self.place_troll():
    #         grille.append(0)
    #         i = i+1
    #     return i
    #
    # def init_grille2(self,i,grille):                    #Du troll jusqu'a la fin de la grille
    #     while i < self.nb_cases:
    #         grille.append(0)
    #         i = i+1
    #     grille.append("Chateau 2")

    def init_grille(self):  # on initialise la grille avec le chateau troll et des 0 quand c'est vide
        print("Rentrer")
        self.grille.append("Chateau 1")
        i = 2
        while i < self.nb_cases:
            if i == self.place_troll():
                self.grille.append("troll")
                i = i + 1
            else:
                self.grille.append(0)
                i = i + 1
        self.grille.append("Chateau 2")

        # def init_troll(self,i,grille):                      #Positionnement du troll

    #     grille.append("troll")
    #     i = i+1
    #     return i

    def affiche_grille(self):  # Affichage de la grille
        print(self.grille)

    def place_troll(self):
        return int((self.nb_cases / 2) + 0.5)  # L'indice ou est posé le troll au début

    # Le jeu
    # _______________________________________________________________________________________________________________________

    def compare_pierre(self):
        if self.chateau1.nb_pierre_envoyee < self.chateau2.nb_pierre_envoyee:
            return 1
        elif self.chateau1.nb_pierre_envoyee > self.chateau2.nb_pierre_envoyee:
            return 2
        else:
            return 3

    def lancer_pierre(self):  # La fonction pour le lancer de pierre
        print("| Attaque du Chateau 1 |")
        print("Nombre de pierres restantes : ", self.chateau1.pierre)
        self.chateau1.nb_pierre_envoyee = int( input(" Nombre de pierre à envoyer ?"))  # Si on entre plus de pierres qu'on en a on refait
        while self.chateau1.nb_pierre_envoyee > self.chateau1.pierre or self.chateau1.nb_pierre_envoyee < 1:
            self.chateau1.nb_pierre_envoyee = int(input(" Nombre de pierre à envoyer ?"))
        self.chateau1.pierre = self.chateau1.pierre - self.chateau1.nb_pierre_envoyee
        #cls()
        print("| Attaque du Chateau 2 |")
        print("Nombre de pierres restantes : ", self.chateau2.pierre)
        self.chateau2.nb_pierre_envoyee = int( input(" Nombre de pierre à envoyer ?"))  # Si on entre plus de pierres qu'on en a on refait
        while self.chateau2.nb_pierre_envoyee > self.chateau2.pierre or self.chateau2.nb_pierre_envoyee < 1:
            self.chateau2.nb_pierre_envoyee = int(input(" Nombre de pierre à envoyer ?"))
        self.chateau2.pierre = self.chateau2.pierre - self.chateau2.nb_pierre_envoyee
       # cls()

    def lancer_pierre_ia(self):
        print("| Attaque du Chateau 1 |")
        print("Nombre de pierres restantes : ", self.chateau1.pierre)
        self.chateau1.nb_pierre_envoyee = self.IA1.choixStrategie(self,self.chateau1)  # Si on entre plus de pierres qu'on en a on refait

        print("| Attaque du Chateau 2 |")
        print("Nombre de pierres restantes : ", self.chateau2.pierre)
        self.chateau2.nb_pierre_envoyee = self.IA2.choixStrategie(self,self.chateau2) # Si on entre plus de pierres qu'on en a on refait
        self.chateau1.pierre = self.chateau1.pierre - self.chateau1.nb_pierre_envoyee
        self.chateau2.pierre = self.chateau2.pierre - self.chateau2.nb_pierre_envoyee

    def deplace_troll(self):  # La fonction de déplacement du troll sur la grille

        if self.compare_pierre() == 3:  # Si les 2 balancent le même nombre de pierre..
            self.reinitialise_pierre()
            self.affiche_grille()
            print("3 vOILA")

        elif self.compare_pierre() == 1:
            self.grille[self.troll.emplacement] = 0  # On met un 0 la ou était le troll
            self.troll.emplacement = self.troll.emplacement - 1  # On change l'emplacement du troll
            self.grille[self.troll.emplacement] = "troll"  # On met au nouvel emplacement sur la grille le troll
            self.reinitialise_pierre()
            self.affiche_grille()
            print("1 vOILA")

        elif self.compare_pierre() == 2:  # Pareil mais dans l'autre sens
            self.grille[self.troll.emplacement] = 0
            self.troll.emplacement = self.troll.emplacement + 1
            self.grille[self.troll.emplacement] = "troll"
            self.reinitialise_pierre()
            self.affiche_grille()
            print("2 vOILA")


    def testnb_pierre(self):  # Le test pour le nombre de pierres de chaque chateau
        if self.chateau1.pierre == 0 and self.chateau2.pierre != 0:
            self.jetpierre1par1(self.chateau2)
        if self.chateau2.pierre == 0 and self.chateau1.pierre != 0:
            self.jetpierre1par1(self.chateau1)
        return 0

    def jetpierre1par1(self, chateau):  # Si l'un des chateaux tombe à court de pierre on les envoie 1 par 1
        while chateau.pierre != 0 and self.victoire2() != 1:
            chateau.nb_pierre_envoyee = 1
            chateau.pierre = chateau.pierre - 1
            print("Nombre de PIERRE ENVOYER : ",chateau.nb_pierre_envoyee)
            self.deplace_troll()
          #  print(self.grille)

    def plusdepierres(self):
        if self.troll.emplacement + 1 < self.place_troll():
            print("Victoire chateau 2")
            return 2
        if self.troll.emplacement + 1 > self.place_troll():
            print("Victoire chateau 1")
            return 1
        if self.troll.emplacement + 1 == self.place_troll():
            print("Match Nul")
            return 0

    def victoire2(self):  # Les conditions de victoire pour le jet 1 par 1
        if self.grille[0] == "troll":  # Solution la plus simple pour ne pas afficher 2 fois
            return 1
        if self.grille[-1] == "troll":
            return 1
        if self.chateau1.pierre == 0 and self.chateau2.pierre == 0:  # Si les 2 n'ont plus de pierre
            return 1

    def victoire(self):  # Les conditions de victoire
        if self.grille[0] == "troll":
            print("Victoire chateau 2")
            return 2
        elif self.grille[-1] == "troll":  # Le premier à partir de la fin
            print("Victoire chateau 1")
            return 1
        elif self.chateau1.pierre == 0 and self.chateau2.pierre == 0:  # Si les 2 n'ont plus de pierre
            return self.plusdepierres()
        else:
            return -1
            #return 1

    # Le Menu
    # _________________________________________________________________________________________________________________________
    def menu(self):
        print("Choisissez votre mode de Jeu:")
        print("1.Mode 1 vs 1")
        print("2.Mode IA")
        print("3.Test IA x100")
        print("4.Quitter")
        
        nb = int(input("Votre choix ?"))
        while nb != 1 and nb != 2 and nb != 3 and nb!=4:
            nb = int(input("Votre choix ?"))
        return nb

    def menuConfig(self):
        self.nb_cases = self.menuCases()
        self.nb_pierres = self.menuPierres()

    def menuCases(self):
        print("1. Nombre de cases ?")
        print("a.7 cases")
        print("b.15 cases")
        nb = input("Votre choix ?")
        while nb != 'a' and nb != 'b':
            nb = input("Votre choix ?")
        if nb == 'a':
            nb = 7
        else:
            nb = 15
        return nb

    def menuPierres(self):
        print("1.Nombre de pierres ?")
        print("a.15 pierres")
        print("b.30 pierres")
        nb = input("Votre choix ?")
        while nb != 'a' and nb != 'b':
            nb = input("Votre choix ?")
        if nb == 'a':
            nb = 15
        else:
            nb = 30
        return nb

    def jeu(self):

        nb = self.menu()
        print(self.grille)
        if nb == 1:
            self.menuConfig()
            cls()
            self.jeuHumain()
        elif nb == 2:
            self.menuConfig()
            cls()
            self.jeuIA()
        elif nb == 3:                   #IA 100 fois
            self.menuConfig()
            cls()
            self.jeuIA100()
        else:
            sys.exit(0)

    #Fonction Globales de jeu IA humain et 100x IA_______________________________________________

    def jeuHumain(self):  # La fonction de jeu
        self.init(self.nb_cases, self.nb_pierres)
        while (self.victoire() == -1):
            self.lancer_pierre()
            self.deplace_troll()
            print("JEU")
            self.testnb_pierre()
        return self.victoire()

    def jeuIA(self):
        self.init(self.nb_cases, self.nb_pierres)
        print("Pierre IA1 :",self.IA1.choixStrategie(self,self.chateau1))
        print("Pierre IA2 :",self.IA2.choixStrategie(self,self.chateau2))
        self.initIA()
        while (self.victoire() == -1):
            self.lancer_pierre_ia()
            self.deplace_troll()
            self.testnb_pierre()
       # self.IA1.StrategiePrudente(self) Idée pour strategie prudente
    
    def jeuIA100(self):
        i = 0
        victoiresJ1 = 0
        victoiresJ2 = 0
        MatchsNuls = 0
        print("Pierre IA1 :",self.IA1.choixStrategie(self,self.chateau1))
        print("Pierre IA2 :",self.IA2.choixStrategie(self,self.chateau2))
        self.initIA()
        while i<100:            #Changer victoire 1 et victoire 2
            self.grille = []
            self.init(self.nb_cases, self.nb_pierres)
            while (self.victoire() == -1):
                self.lancer_pierre_ia()
                self.deplace_troll()
                self.testnb_pierre()
            resultat = self.victoire()
            if resultat == 1:
                victoiresJ1 = victoiresJ1 +1
            elif resultat == 2:
                victoiresJ2 = victoiresJ2 +1
            elif resultat == 0:
                MatchsNuls = MatchsNuls +1
            i = i+1
        cls()
        print("Victoires J1" , victoiresJ1)
        print("Victoires J2" , victoiresJ2)
        print("Match Nuls" , MatchsNuls)


        return 0

