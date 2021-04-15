## Gestion de Projet et Génie Logiciel, M1, département informatique, Lyon 1
# Application StopCovid 
par Jeff Firmin-Pignot et David Daily

### Statut du projet

Le projet est entièrement terminé et fonctionne avec toutes les fonctionnalités.

### Consignes d'installation et de lancement

Pour installer lancer le projet, il faut utiliser Maven. Pour installer Maven, cf. [ce guide d'installation](https://maven.apache.org/install.html)
 
Une fois Maven installé, aller dans le dossier /stopcovid et depuis un terminal faire: <br/>
- `mvn install` pour installer les dépendances <br/>
- `mvn clean` pour supprimer les anciens exécutables <br/>
- `mvn test` pour lancer les tests <br/>
- `mvn compile` pour compiler <br/>
- `mvn exec:java` pour lancer l'application. NB: il faut avoir fait exécuter le goal `compile` pour que `mvn exec:java` fonctionne. Les goals `test` et `install` font exécuter `compile` pendant leur opération, mais `clean` ne le fait pas.  

La dernière version du projet ainsi que le diagramme UML se trouve dans le dossier /stopcovid. <br/>
Le diagramme UML final a été fait avec l'outil intégré à IntelliJ, un .png a été mis en cas de problème d'ouverture. On a également travaillé avec l'application web Lucidchart pour des diagrammes intermédiaires. <br/>
Le rapport se situe à la racine. C'est `rapport.pdf`. <br/>


### Fonctionnalités bonus:

- Mode nuit, passage dynamique entre modes d'affichages clair et nuit. <br/>
- Bouton infection changeant pour permettre de remettre un utilisateur sain. <br/>
- Réinitialisation de l'application. <br/>
- Lancement des simulations aléatoires de rencontres et d'infections. <br/>
- Traduction française de l'interface. <br/>

