Bienvenue dans le README

Nom du jeu : CHESSMASTER
Code : C++/SFML (interface graphique)
Projet : Jeux d'echec deux joueurs jouable sur un terminal.

Modifications:

	-Dernière version sortie le 23 avril 2018.

Utilisation:

	-Ce jeu ne nécessite qu'un compilateur.
	-Vous pouvez utiliser codeblocks pour démarrer le jeu.
	-Les règles du jeu sont identiques au jeu original.

-------------------------------------------------------------------------

Espace Codeurs:

	-4 classes principales (Jeu | Joueur | Pieces | Plateau )
	-1 classe SFML ( éventuellement si on a le temps )
	-2 main : un main pour le mode texte et un pour le sfml 
	-2 Dossiers : include, src (.cpp/.h)
	-1 Makefile
	-1 dossier data avec les photos format PNG

Diagramme des classes:

	-réalisation du diagramme avec le logiciel libre DIA

			-----------
			| Jeu_txt |	
			-----------
			     ?
	--------	 ----------	  --------
	|Joueur|  <----	 |  Jeu   | <---- | SFML |
	--------	 ----------	  --------
	   |		 |   |
     	   |	     	 |   ?
	---------  <-----/ ---------
        |Pieces |  <-------|Plateau|   
	---------	   ---------


à propos:

	Nous avons tester une première version du jeu sans les classes afin
	de vérifier si le jeu était fonctionnel puis nous avons implémenté
	le code dans 4 classes pour le rendre plus lisible et modifiable.
	Des modifications ont été nécessaire pour concevoir la dernière fonction
	de mise en echec du roi.

-----------------------------------------------------------------------------

Credits:

	Projet réalisé par 4 étudiants dans le cadre d'une licence informatique.
	
