Attention : aggrandisser la fenetre de ce fichier !!!

---------------------------------------------------------------------------------------INSTALLATION------------------------------------------------------------------------------------------
Attention : a lire juqu'a la fin !!!

							
							Etape qu'il faut pour lancé le site web et navigué dans les différentes pages

Exigence : 
-Avoir le serveur web Xampp installé sur votre ordinateur
-Installer git bash sur windows
-Installer composer avec les commandes:
sous linux: sudo apt-get install composer
sous windows: se placer dans répertoire xampp/htdocs/ puis exécuter la commande:
				php -r "eval('?>'.file_get_contents('http://getcomposer.org/installer'));"

Etape à suivre :
-Que vous soyez sur windows ou sur linux aller dans votre dossier d'installation de xampp et placé vous dans le répertoire htdocs comme suit :
/xampp/htdocs/

-Cloner le projet de la forge dans ce dossier (htdocs)
			
			- Se rendre dans le dossier Symfony puis exécuter la commande sours git bash: "php composer.phar update" qui va mettra à jour toutes les bundles disponibles dans composer.json et créera les répetoires vendor.

			*** Quand les chammps des paramètres sortiront pour créer la base de données. 
			Appyer sur ENTREE pour ldes deux premiers paramètres. Et pour le 3, il nous propose un nom par défaut 
			qui est Symfony que vous remplacerez par BookNews qui est le nom de notre BD puis ENTREE.
			Et ENTREE à chaque suggestion pour tous les autres paramètres
			
																			OU
			
			-Se rendre dans le dossier Symfony puis dezipper le dossier vendor et var qui se trouvent dedans puis sortir du dossier.

			SI VOUS AVEZ DES ERREURS OU PROBLEMES : Dezipper le fichier NewsBook.zip ouvrer le fichier puis continuer sur les etapes suivantes
			                 				

-Aller dans phpmyadmin et creer une nouvelle base de donnée avec pour nom: BookNews et d'interclassement: utf16_general_ci 

-Se rendre dans le dossier Modelisation puis ouvrir le fichier basededonnees.sql, copier le contenu du code et l'executé sur phpmyadmin dans la base de données booknews. 
		Maintenant vous avez la base de données du site qui marche  

-Ouvrir votre navigateur puis taper cette url pour acceder au site web (vous accederez directement à la page d'acceuil) : 
 	http://localhost:8080/NewsBook/Symfony/web/app_dev.php/platform
				
				ou
	http://localhost/NewsBook/Symfony/web/app_dev.php/platform
	

En resumé : Maintenant vous pouvez naviguer dans le site et coLnsulter toutes les pages. Mais vous ne pouvez rien modifier. Pour pouvoir ajouter une publication
		et faire des modification vous devez vous inscrire via le lien creer un compte qui se trouve sur la barre du menu a droite.



----------------------------------------------------------------------------------------ORGANISATION------------------------------------------------------------------------------------------
Attention : pour bien comprendre tout le fonctionnement et les dependances vous devez lire cette partie avec attention !!!
NB

Le projet symfony est reparti dans plusieurs dossier dont :

app : repertoire dans le quel on definit les configuration pour les nouveaux bundles à installer, la base de donnée, la gestion de la sécurité, droit et contrôle,
	le routing pour les bundles installés, les services.
	les fichiers importants dans ce dossier sont : config/routing.yml, config/parameters.yml, config/config.yml, config/security.yml


bin : repertoire dans le quel on se place pour executer nos commande via le terminal
	Ex pour creer la base de donnée on a utiliser la commande : C:\xampp\htdocs\Symfony>php bin/console doctrine:database:create


src : repertoire le plus important dans le quel se trouve le code source de toute nos pages dedans se trouve les dossiers de 3 bundles :

	- CoreBundle : qui gere le layout general, l'affichage global du front end du site (src\OC\CoreBundle\Resources\views\layout.html.twig)

	- PlatformBundle : c'est la qu'on gere le modele mvc 

		-PlatformBundle\Entity : dossier dans le quel se trouve le modele

		-PlatformBundle\Ressources\views : dossier dans le quel on gere la vue, dans ce dossier ce trouve le 2eme layout qui herite du layout général qui s'occupe de la vue
						   de chaque page et le dossier Annonces qui contient la vue de chaque page du site.

		-PlatformBundle\Ressources\config : dossier dans le quel on gère les url de nos pages (comment on les définit et comment on y accède). Qui se trouve dans le fichier
						    routing.yml

		-PlatformBundle\Controller : dossier dans le quel on gere le controleur

		-PlatformBundle\Form : dossier dans le quel on gere nos differents formulaire

		-PlatformBundle\Repository : dossier dans le quel certaines fonctions importantes qu'on appel dans le controleur

	- UserBundle : bundle qui gère le service utilisateur qui herite du bundle FOSUserBundle qui se trouve dans le vendor (Symfony/vendor)


var : repertoire concernant le cache


vendor : repertoire qui contient toutes les bibiliothèques externes, tout les bundle telechargé par symfony et les bundles que nous avons installé, 
	 c'est un dossier qui gère tous les outils de symfony. 
	 normalement pour avoir ce dossier on a utilisé le fichier composer.json (Symfony/composer.json) pour mettre le libellé dans la partie require, ensuite faire la 
	 commande composer.phar update qui permet d'installer maintenant les bundles et les placer dans le repertoire vendor.
	 NB : la forge n'uplodait pas notre fichier vendor, c'est pour cela qu'on la zippé et comme ça vous aurez tous le bundles directement installé. Ceci vous evitera d'installer
	 les bundles un a un avec la commande.


web : c'est le repertoire qui contient les fichiers destinés aux visiteurs, les fichiers css,... Il contient le fichier app_dev.php qui est le controleur frontal coté developpeur

		 
NB : pour 

OPTION : nous avons installer le terminal "gitbash" qui nous a servi d'executer toute nos commandes.




----------------------------------------------------------------------------------------RESULTAT------------------------------------------------------------------------------------------

VOIR LE RAPPORT DANS LA PARTIE 4 - BILAN 