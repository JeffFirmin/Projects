Attention : aggrandisser la fenetre de ce fichier !!!

---------------------------------------------------------------------------------------INSTALLATION------------------------------------------------------------------------------------------
Attention : a lire juqu'a la fin !!!

							
							Etape qu'il faut pour lanc� le site web et navigu� dans les diff�rentes pages

Exigence : 
-Avoir le serveur web Xampp install� sur votre ordinateur
-Installer git bash sur windows
-Installer composer avec les commandes:
sous linux: sudo apt-get install composer
sous windows: se placer dans r�pertoire xampp/htdocs/ puis ex�cuter la commande:
				php -r "eval('?>'.file_get_contents('http://getcomposer.org/installer'));"

Etape � suivre :
-Que vous soyez sur windows ou sur linux aller dans votre dossier d'installation de xampp et plac� vous dans le r�pertoire htdocs comme suit :
/xampp/htdocs/

-Cloner le projet de la forge dans ce dossier (htdocs)
			
			- Se rendre dans le dossier Symfony puis ex�cuter la commande sours git bash: "php composer.phar update" qui va mettra � jour toutes les bundles disponibles dans composer.json et cr�era les r�petoires vendor.

			*** Quand les chammps des param�tres sortiront pour cr�er la base de donn�es. 
			Appyer sur ENTREE pour ldes deux premiers param�tres. Et pour le 3, il nous propose un nom par d�faut 
			qui est Symfony que vous remplacerez par BookNews qui est le nom de notre BD puis ENTREE.
			Et ENTREE � chaque suggestion pour tous les autres param�tres
			
																			OU
			
			-Se rendre dans le dossier Symfony puis dezipper le dossier vendor et var qui se trouvent dedans puis sortir du dossier.

			SI VOUS AVEZ DES ERREURS OU PROBLEMES : Dezipper le fichier NewsBook.zip ouvrer le fichier puis continuer sur les etapes suivantes
			                 				

-Aller dans phpmyadmin et creer une nouvelle base de donn�e avec pour nom: BookNews et d'interclassement: utf16_general_ci 

-Se rendre dans le dossier Modelisation puis ouvrir le fichier basededonnees.sql, copier le contenu du code et l'execut� sur phpmyadmin dans la base de donn�es booknews. 
		Maintenant vous avez la base de donn�es du site qui marche  

-Ouvrir votre navigateur puis taper cette url pour acceder au site web (vous accederez directement � la page d'acceuil) : 
 	http://localhost:8080/NewsBook/Symfony/web/app_dev.php/platform
				
				ou
	http://localhost/NewsBook/Symfony/web/app_dev.php/platform
	

En resum� : Maintenant vous pouvez naviguer dans le site et coLnsulter toutes les pages. Mais vous ne pouvez rien modifier. Pour pouvoir ajouter une publication
		et faire des modification vous devez vous inscrire via le lien creer un compte qui se trouve sur la barre du menu a droite.



----------------------------------------------------------------------------------------ORGANISATION------------------------------------------------------------------------------------------
Attention : pour bien comprendre tout le fonctionnement et les dependances vous devez lire cette partie avec attention !!!
NB

Le projet symfony est reparti dans plusieurs dossier dont :

app : repertoire dans le quel on definit les configuration pour les nouveaux bundles � installer, la base de donn�e, la gestion de la s�curit�, droit et contr�le,
	le routing pour les bundles install�s, les services.
	les fichiers importants dans ce dossier sont : config/routing.yml, config/parameters.yml, config/config.yml, config/security.yml


bin : repertoire dans le quel on se place pour executer nos commande via le terminal
	Ex pour creer la base de donn�e on a utiliser la commande : C:\xampp\htdocs\Symfony>php bin/console doctrine:database:create


src : repertoire le plus important dans le quel se trouve le code source de toute nos pages dedans se trouve les dossiers de 3 bundles :

	- CoreBundle : qui gere le layout general, l'affichage global du front end du site (src\OC\CoreBundle\Resources\views\layout.html.twig)

	- PlatformBundle : c'est la qu'on gere le modele mvc 

		-PlatformBundle\Entity : dossier dans le quel se trouve le modele

		-PlatformBundle\Ressources\views : dossier dans le quel on gere la vue, dans ce dossier ce trouve le 2eme layout qui herite du layout g�n�ral qui s'occupe de la vue
						   de chaque page et le dossier Annonces qui contient la vue de chaque page du site.

		-PlatformBundle\Ressources\config : dossier dans le quel on g�re les url de nos pages (comment on les d�finit et comment on y acc�de). Qui se trouve dans le fichier
						    routing.yml

		-PlatformBundle\Controller : dossier dans le quel on gere le controleur

		-PlatformBundle\Form : dossier dans le quel on gere nos differents formulaire

		-PlatformBundle\Repository : dossier dans le quel certaines fonctions importantes qu'on appel dans le controleur

	- UserBundle : bundle qui g�re le service utilisateur qui herite du bundle FOSUserBundle qui se trouve dans le vendor (Symfony/vendor)


var : repertoire concernant le cache


vendor : repertoire qui contient toutes les bibilioth�ques externes, tout les bundle telecharg� par symfony et les bundles que nous avons install�, 
	 c'est un dossier qui g�re tous les outils de symfony. 
	 normalement pour avoir ce dossier on a utilis� le fichier composer.json (Symfony/composer.json) pour mettre le libell� dans la partie require, ensuite faire la 
	 commande composer.phar update qui permet d'installer maintenant les bundles et les placer dans le repertoire vendor.
	 NB : la forge n'uplodait pas notre fichier vendor, c'est pour cela qu'on la zipp� et comme �a vous aurez tous le bundles directement install�. Ceci vous evitera d'installer
	 les bundles un a un avec la commande.


web : c'est le repertoire qui contient les fichiers destin�s aux visiteurs, les fichiers css,... Il contient le fichier app_dev.php qui est le controleur frontal cot� developpeur

		 
NB : pour 

OPTION : nous avons installer le terminal "gitbash" qui nous a servi d'executer toute nos commandes.




----------------------------------------------------------------------------------------RESULTAT------------------------------------------------------------------------------------------

VOIR LE RAPPORT DANS LA PARTIE 4 - BILAN 