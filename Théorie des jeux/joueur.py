class Joueur:

    def yes(self):

        return False


    def lancer_pierre(self):  # La fonction pour le lancer de pierre
        print("nombre de pierres restantes", chateau.pierre)
        chateau.nb_pierre_envoyee = int(
            input("Nombre de pierre à envoyer ?"))  # Si on entre plus de pierres qu'on en a on refait
        while chateau.nb_pierre_envoyee > chateau.pierre or chateau.nb_pierre_envoyee < 1:
            chateau.nb_pierre_envoyee = int(input("Nombre de pierre à envoyer ?"))
        chateau.pierre = chateau.pierre - chateau.nb_pierre_envoyee