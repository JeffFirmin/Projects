Bienvenue dans le README

Nom du jeu : CHESSMASTER
Code : C++/SFML (interface graphique)
Projet : Jeux d'echec deux joueurs jouable sur un terminal.

Modifications:

	-Derni�re version sortie le 23 avril 2018.

Utilisation:

	-Ce jeu ne n�cessite qu'un compilateur.
	-Vous pouvez utiliser codeblocks pour d�marrer le jeu.
	-Les r�gles du jeu sont identiques au jeu original.

-------------------------------------------------------------------------

Espace Codeurs:

	-4 classes principales (Jeu | Joueur | Pieces | Plateau )
	-1 classe SFML ( �ventuellement si on a le temps )
	-2 main : un main pour le mode texte et un pour le sfml 
	-2 Dossiers : include, src (.cpp/.h)
	-1 Makefile
	-1 dossier data avec les photos format PNG

Diagramme des classes:

	-r�alisation du diagramme avec le logiciel libre DIA

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


� propos:

	Nous avons tester une premi�re version du jeu sans les classes afin
	de v�rifier si le jeu �tait fonctionnel puis nous avons impl�ment�
	le code dans 4 classes pour le rendre plus lisible et modifiable.
	Des modifications ont �t� n�cessaire pour concevoir la derni�re fonction
	de mise en echec du roi.

-----------------------------------------------------------------------------

Credits:

	Projet r�alis� par 4 �tudiants dans le cadre d'une licence informatique.
	
