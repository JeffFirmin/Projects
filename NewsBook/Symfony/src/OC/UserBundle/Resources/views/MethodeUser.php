<?php
// Dans un contrôleur :

// Pour récupérer le service UserManager du bundle
$userManager = $this->get('fos_user.user_manager');

// Pour charger un utilisateur
$user = $userManager->findUserBy(array('username' => 'winzou'));

// Pour modifier un utilisateur
$user->setEmail('cetemail@nexiste.pas');
$userManager->updateUser($user); // Pas besoin de faire un flush avec l'EntityManager, cette méthode le fait toute seule !

// Pour supprimer un utilisateur
$userManager->deleteUser($user);

// Pour récupérer la liste de tous les utilisateurs
$users = $userManager->findUsers();