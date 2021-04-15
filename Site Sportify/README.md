# SPORTIFY 

Le guide de configuration de l'environnement de travail est orienté pour Linux, cependant certaines personnes aux seins de l'équipe ont travaillé sous windows. 
Pour travailler sous windows nous vous conseillons d'installer le *sous-système Windows pour Linux* : [WSL]. 

## Télécharger le projet 
***

Créez un nouveau repertoire de travail. 
Ouvrez un terminal dans ce repertoire et faite un clone du projet :  

```sh
git clone https://forge.univ-lyon1.fr/p1711842/projet_mif10_grp13.git
```

## Configuration d'une Base de Donnée local
***

Pour pouvoir travailler sur le projet **sportify** et tester les fonctionnalités, il vous faudra creer un base de donnée local. Vous pourrez ainsi créer/supprimer des comptes à votre guise pour tester les nouvelles implémentations selon vos besoins. 

Commencez par installer **postgresql** : 
```sh
sudo apt install postgresql
```
Si vous rencontrez des soucis voici un guide plus détaillé : [Installer Postgresql].
Démarrez le serveur postgres : 
```sh
sudo service postgresql start
```

Placez vous dans le repertoire du projet. Vous devez maintenant créer un utilisateur qui sera propriétaire de votre BD. 
```sh
sudo su postgres 
psql -U postgres
```
Si vous rencontrez des problèmes lorsque vous souhaitez lancer ou vous connecter au serveur postgres, vérifier que le port dans le fichier `postgresql.conf` est bien **5432**. 
Si les problèmes persistent, vérifier les METHODs dans le fichier `pg_hba.conf`, mettez tout à **md5** sauf pour le LOGIN postgres qui doit être à **trust**.
Vous êtes désormais dans le terminal de psql. Creez un utilisateur ainsi :
```sh
=\# CREATE USER <nom_utilisateur>;
=\# ALTER ROLE <nom_utilisateur> WITH CREATEBD;
=\# ALTER USER <nom_utilisateur> WITH ENCRYPYED PASSWORD 'motdepasse';
=\# ALTER ROLE <nom_utilisateur> WITH SUPERUSER;
```
Creez une Base de donnée : 
```sh
=\# CREATE DATABASE <nom_base_de_donnee> OWNER <nom_utilisateur>;
```
Vous pouvez vérifier que l'utilisateur a bien été créé avec `\du`et la base de donnée `\l`. Puis quittez avec `\q` ou `exit`.
Connectez-vous à cette nouvelle base de donnée : 
```sh
psql -h localhost -U <nom_utilisateur> <nom_base_de_donnee>
```
Executez le fichier sql qui est fourni dans ``> sportify/src/main/resources/sportify_database_1.sql``.
```sh
=\# \i sportify_database_1.sql
```
Pour vérifier la création des tables avec `\d`. Ici vous pouvez également faire des requêtes SQL pour vérifier le contenu de la base (les comptes inscrits ou club par exemple). A partir de là, votre base de donnée est prête. Mais il reste une étape, ouvrez le fichier `persistance.xml` qui se trouve dans le repertoire `sportify/src/main/resources/META-INF`. Vous devez modifier les lignes suivantes : 

- **url** : remplacez dans la value de l'url `sportify_db` par le nom de votre base de donnée.
- **user** : remplacez la value `sportify` par le nom de votre utilisateur de postgres.
- **password** : remplacez la value `p2020623` par le mot de passe de votre utilisateur postgres.

:warning: N'ajoutez pas le `persistance.xml` lors de vos commit ! (Vous pouvez l'ajouter au .gitignore) :warning:

Votre base de donnée est prête !

## La compilation 
***

Le projet utilise l'outil **Maven** pour la compilation. Installez maven avec : 

```sh
apt install maven
```

Le projet est décrit dans le fichier `pom.xml`. Vous devez vous placez dans la repertoire *sportify* pour le compiler. 
  
- `mvn clean package` compile le projet et génère un fichier Web ARchive (*.war* qui nous sera utile plus tard)
- `mvn tests` lance les tests

## Configuration du serveur
***

### Installation de tomcat
Bon... pour l'instant vous avez un base de donnée, un projet qui compile correctement. Il serait temps de configurer un serveur pour vous permettre de visualiser l'application dans un navigateur non ?

Rendez-vous sur le site de tomcat [Tomcat], et téléchargez la version du core qui correspond à votre configuration. 
Décompressez simplement l'archive où vous le souhaitez (en dehors du clone du projet).
Pour démarrer Tomcat, lancez le script `startup.sh` ou `startup.bat` dans bin. Pour l'arrêter, `shutdown.sh` ou `shutdown.bat` dans le même répertoire.

Testez le fonctionnement de votre serveur à l'adresse http://localhost:8080/.

### Configuration de tomcat
Vous allez modifier la configuration (répertoire `conf` de votre installation tomcat) pour :

Ajouter deux utilisateurs managers dans le fichier tomcat-users.xml : 
- L'un vous servira à accéder à l'interface Web de configuration (http://localhost:8080/manager/html).
- L'autre permettra à votre IDE de contrôler le serveur à l'aide de scripts.

Modifier l'encodage par défaut (ISO-8859-1) utilisé pour décoder les paramètres des requêtes :
- Dans le fichier **Server.xml**, rajouter à l'élément Connector les attributs URIEncoding="UTF-8" et useBodyEncodingForURI="true".
- Dans le fichier **web.xml**, décommenter les deux blocs qui spécifient le CharacterEncodingFilter. (ne pas oublier de décommenter à la fois le bloc `<filter>` et le bloc `<filter-mapping>`).

Redémarrez Tomcat et connectez-vous en tant qu'utilisateur administrateur à l'interface d'administration, pour voir si cela fonctionne. 

### configurez votre IDE
Configurez votre IDE pour qu'il puisse également démarrer et arrêter Tomcat. Nous prendrons l'exemple avec Eclipse. Les autres IDEs se configurent plus ou moins de la même façon.  

Allez dans la barre d'outils `show view > other > servers`, un onglet devrait s'ouvrir, cliquez sur le liens pour creer un nouveau serveur. Selectionnez `Tomcat v9.0` et ajoutez le chemin vers le repertoire tomcat que nous venons de décompresser. 

Vous avez désormais un serveur qui apparait dans l'onglet. Double cliquez sur ce serveur. Vous êtes alors sur l'overview, allez dans `modules`, puis `Add External Web Module...`, et dans `Document base` ajoutez le chemin vers le repertoire `sportify/target/sportify` du projet. Validez. 

Pour Eclipse plus en détails : [Configurer tomcat sur Eclipse]. (Partie 1 & 4)


## Déploiement 
***

Le déploiement sur la VM se fait automatiquement à chaque Push. 

Vous pouvez y accédez ici : `http://192.168.74.211:8080/v3/`

***
 L'équipe de SPORTIFY vous remercie.
***




[Configurer tomcat sur Eclipse]: <http://blog.paumard.org/tutoriaux/eclipse-tomcat/>
[Tomcat]: <https://tomcat.apache.org/download-90.cgi>
[WSL]: <https://docs.microsoft.com/fr-fr/windows/wsl/install-win10>
[Installer Postgresql]: <https://computingforgeeks.com/install-postgresql-12-on-ubuntu/>