-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mar 24 Avril 2018 à 01:39
-- Version du serveur :  10.1.9-MariaDB
-- Version de PHP :  5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `booknews`
--

-- --------------------------------------------------------

--
-- Structure de la table `comment`
--

CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `thread_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `author_id` int(11) DEFAULT NULL,
  `body` longtext COLLATE utf8_unicode_ci NOT NULL,
  `ancestors` varchar(1024) COLLATE utf8_unicode_ci NOT NULL,
  `depth` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  `state` int(11) NOT NULL,
  `score` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `comment`
--

INSERT INTO `comment` (`id`, `thread_id`, `author_id`, `body`, `ancestors`, `depth`, `created_at`, `state`, `score`) VALUES
(1, '1', 1, 'Super ce bundle', '', 0, '2018-03-27 18:57:36', 0, 0),
(2, '1', 3, 'Lourd ce bundle', '', 0, '2018-04-01 16:33:41', 0, 0),
(3, '1', 3, 'J''avoue il est très pratique', '1', 1, '2018-04-01 16:34:14', 0, 0),
(4, '5', 3, 'Très pratique ce  bundle', '', 0, '2018-04-01 16:36:11', 0, 0),
(5, '5', 3, 'Il peut te permettre de définir tes tags très facilement', '4', 1, '2018-04-01 16:37:22', 0, 0),
(6, '7', 3, 'c''était le feu', '', 0, '2018-04-01 16:39:20', 0, 0),
(7, '7', 3, 'j''avoue il a mis le feu', '6', 1, '2018-04-01 16:42:56', 0, 0),
(8, '7', 3, 'Je recommande fortement d''assister aux autres concers', '', 0, '2018-04-01 16:43:39', 0, 0),
(9, '7', 3, 'J''ai hate de participer à un autre de ces concerts', '8', 1, '2018-04-01 16:44:19', 0, 0),
(10, '7', 1, 'c''était plus que le feu je confirme', '6/7', 2, '2018-04-11 01:37:49', 0, 0),
(11, '7', 1, 'C''était 3h mémorable', '', 0, '2018-04-11 01:41:58', 0, 0),
(12, '7', 1, 'Tu as raté quelque chose d''historique', '8/9', 2, '2018-04-11 01:45:32', 0, 0),
(13, '7', 1, 'on y sera la prochaine fois aussi', '8', 1, '2018-04-11 01:46:55', 0, 0),
(14, '11', 4, 'Dommage qu''ils aient perdu', '', 0, '2018-04-23 21:17:52', 0, 0),
(15, '7', 4, 'C''était le feu', '', 0, '2018-04-23 21:18:45', 0, 0),
(16, '8', 4, 'J''ai hate', '', 0, '2018-04-23 21:21:27', 0, 0),
(17, '8', 1, 'oui moi de plus alpha', '16', 1, '2018-04-23 21:22:42', 0, 0),
(18, '8', 1, 'ça va être lourd', '', 0, '2018-04-23 21:23:20', 0, 0),
(19, '8', 1, 'ça va être lourd', '', 0, '2018-04-23 21:23:29', 0, 0),
(20, '8', 3, 'Surtout avec la blessure de neymar, il va revenir avec force', '19', 1, '2018-04-23 21:25:30', 0, 0),
(21, '8', 3, 'Surtout avec la blessure de neymar, il va revenir avec force', '19', 1, '2018-04-23 21:25:38', 0, 0),
(22, '8', 3, 'En plus le ballon d''or il va vraiment se jouer à ce niveau', '', 0, '2018-04-23 21:26:12', 0, 0),
(23, '8', 5, 'Moi je pense que le brazil ne pourra rien faire sans neymar', '', 0, '2018-04-23 21:28:32', 0, 0),
(24, '8', 5, 'très lourd', '18', 1, '2018-04-23 21:28:56', 0, 0),
(25, '8', 5, 'Ah on ne sait jamais karim, il risque d''avoir des surprises', '19/21', 2, '2018-04-23 21:29:54', 0, 0),
(26, '8', 5, 'J''ai hate surtout que Messi soulève cette coupe', '16/17', 2, '2018-04-23 21:30:45', 0, 0),
(27, '8', 5, 'Moi je pense plutôt serré parce que entre Messi et CR7 c''est le ballon d''or qui va les séparer', '19', 1, '2018-04-23 21:32:20', 0, 0),
(28, '8', 5, 'Et puis karim pour neymar c''est toujours pas sur qu''il reviendra', '19/20', 2, '2018-04-23 21:33:05', 0, 0),
(29, '8', 4, 'Non ça c''est ton point de vue, moi je pense qu''ils pourront j''ai confiance', '23', 1, '2018-04-23 21:35:46', 0, 0),
(30, '8', 4, 'Vivement Juin', '', 0, '2018-04-23 21:36:09', 0, 0),
(31, '9', 4, 'Incroyable celui-ci', '', 0, '2018-04-23 21:37:24', 0, 0),
(32, '9', 1, 'Un peu louche l''histoire', '', 0, '2018-04-23 21:41:36', 0, 0),
(33, '9', 1, 'J''ai lu le scénario mais je n''ai toujours pas capté alpha', '31', 1, '2018-04-23 21:42:19', 0, 0),
(34, '9', 3, 'oui j''avoue c''est chelou Rymkaysh', '32', 1, '2018-04-23 21:44:11', 0, 0),
(35, '9', 3, 'Comme le titre le dit c''dst une histoire vraiment drôle', '', 0, '2018-04-23 21:45:04', 0, 0),
(36, '9', 3, 'Oui il a vraiment un fil qui le perturbe sa tête', '31', 1, '2018-04-23 21:46:14', 0, 0),
(37, '9', 3, 'Je confirme', '31', 1, '2018-04-23 21:48:21', 0, 0),
(38, '9', 5, 'Mdrrrrrrrrrr', '', 0, '2018-04-23 21:49:56', 0, 0),
(39, '9', 5, 'lol c''est exactement ça karim', '35', 1, '2018-04-23 21:50:21', 0, 0),
(40, '9', 5, 'Oui j''avoue', '32', 1, '2018-04-23 21:51:11', 0, 0),
(41, '9', 5, 'Très incroyable', '31', 1, '2018-04-23 21:51:29', 0, 0),
(42, '9', 5, 'Il n''est pas complètement clean', '31/36', 2, '2018-04-23 21:52:19', 0, 0),
(43, '1', 5, 'Très efficace', '2', 1, '2018-04-23 21:53:03', 0, 0),
(44, '1', 5, 'Très important', '1/3', 2, '2018-04-23 21:58:08', 0, 0),
(45, '1', 5, 'Pour ceux qui veulent intégrer le système d''authentification à partir de facebook , je vous le recommende', '', 0, '2018-04-23 21:59:26', 0, 0),
(46, '10', 4, 'J''ai trouvé de très intéressantes offres', '', 0, '2018-04-23 22:01:17', 0, 0),
(47, '28', 3, 'Lilwayne c''est un fou', '', 0, '2018-04-24 01:03:24', 0, 0),
(48, '28', 3, 'A Chaue fois qu''il sort un son il tue', '', 0, '2018-04-24 01:03:49', 0, 0),
(49, '28', 3, 'Il n''a que des classiques', '', 0, '2018-04-24 01:04:03', 0, 0),
(50, '28', 3, 'J''ai grandi avec ses musiques', '', 0, '2018-04-24 01:04:16', 0, 0),
(51, '28', 1, 'Pareil Karim il a fait mon adolescence', '50', 1, '2018-04-24 01:05:19', 0, 0),
(52, '28', 1, 'Ouais je pense a son John avec Rick ross en 2010', '49', 1, '2018-04-24 01:06:25', 0, 0),
(53, '28', 1, 'C''est un démon', '47', 1, '2018-04-24 01:07:54', 0, 0),
(54, '28', 5, 'Je pense que c''est réciproque pour toutes les personnes de notre génération', '50', 1, '2018-04-24 01:09:41', 0, 0),
(55, '28', 5, 'c''est ouf', '49', 1, '2018-04-24 01:10:13', 0, 0),
(56, '28', 5, 'Et ça depuis touts les temps', '48', 1, '2018-04-24 01:11:44', 0, 0),
(57, '28', 5, 'Weezy baby', '47/53', 2, '2018-04-24 01:12:08', 0, 0),
(58, '28', 4, 'J''ai grave kiffé Karim', '50', 1, '2018-04-24 01:13:26', 0, 0),
(59, '28', 4, 'ça au moins c''est clair Jeff', '50/54', 2, '2018-04-24 01:13:49', 0, 0),
(60, '28', 4, 'Lourd comme tous les temps, il me met à l''aise', '50/51', 2, '2018-04-24 01:14:48', 0, 0),
(61, '28', 4, 'Plus fou que lui y''a pas', '49/55', 2, '2018-04-24 01:15:27', 0, 0),
(62, '21', 4, 'C''est sa meilleure saison cette année', '', 0, '2018-04-24 01:16:35', 0, 0),
(63, '21', 4, 'Il est plus qu''en forme', '', 0, '2018-04-24 01:16:50', 0, 0),
(64, '21', 4, 'Il déchire tout sur son passage', '', 0, '2018-04-24 01:17:06', 0, 0),
(65, '21', 1, 'Il fait partir des meilleurs butteurs d''Europe', '', 0, '2018-04-24 01:18:32', 0, 0),
(66, '21', 1, 'Peu de personnes peuvent s''arrêter devant lui actuellement', '64', 1, '2018-04-24 01:19:22', 0, 0),
(67, '21', 1, 'J''espère qu''ils briseront tout en ligue des champions', '63', 1, '2018-04-24 01:23:33', 0, 0),
(68, '21', 1, 'j''avoue', '62', 1, '2018-04-24 01:23:50', 0, 0),
(69, '21', 3, 'oui exactement à quelque buts d''écarts avec CR7', '65', 1, '2018-04-24 01:25:39', 0, 0),
(70, '21', 3, 'Il est grave chaud', '64', 1, '2018-04-24 01:25:59', 0, 0),
(71, '21', 3, '2 plus précisément en terme de but Rimkaysh', '64/66', 2, '2018-04-24 01:26:35', 0, 0),
(72, '21', 3, 'Il pète la forme', '63', 1, '2018-04-24 01:27:01', 0, 0),
(73, '21', 3, 'Oui je pense avec la ROMA c''est une victoire assurée d''avance', '63/67', 2, '2018-04-24 01:29:01', 0, 0),
(74, '21', 3, 'Fuego', '62/68', 2, '2018-04-24 01:29:17', 0, 0),
(75, '14', 3, 'Ressoude les liens avec les STATES', '', 0, '2018-04-24 01:31:00', 0, 0),
(76, '14', 3, 'J''espère que c''est pour faire avancer le monde', '', 0, '2018-04-24 01:31:47', 0, 0),
(77, '14', 3, 'Les puissances mondiales', '', 0, '2018-04-24 01:32:08', 0, 0),
(78, '14', 5, 'Oui les dirigeants du monde', '77', 1, '2018-04-24 01:33:33', 0, 0),
(79, '14', 5, 'J''espère aussi que c''est pour la bonne cause', '76', 1, '2018-04-24 01:33:57', 0, 0),
(80, '14', 5, 'C''est une longue histoire ces deux liens', '75', 1, '2018-04-24 01:34:27', 0, 0),
(81, '14', 1, 'La puissance respecte la puissance', '77', 1, '2018-04-24 01:36:23', 0, 0),
(82, '14', 1, 'Oui Jeff, ils détiennent les clés', '77/78', 2, '2018-04-24 01:36:49', 0, 0),
(83, '14', 1, 'I hope too Karim', '76', 1, '2018-04-24 01:37:16', 0, 0),
(84, '14', 1, 'Pareil j''espère', '76/79', 2, '2018-04-24 01:37:37', 0, 0),
(85, '14', 1, 'ça ne date pas d''hier ça jeff', '75/80', 2, '2018-04-24 01:38:22', 0, 0);

-- --------------------------------------------------------

--
-- Structure de la table `oc_annonce`
--

CREATE TABLE `oc_annonce` (
  `id` int(11) NOT NULL,
  `image_id` int(11) DEFAULT NULL,
  `rubrique_id` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `url` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `content` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `published` tinyint(1) NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `nb_applications` int(11) NOT NULL,
  `thread_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `oc_annonce`
--

INSERT INTO `oc_annonce` (`id`, `image_id`, `rubrique_id`, `user_id`, `date`, `title`, `url`, `content`, `published`, `updated_at`, `nb_applications`, `thread_id`) VALUES
(1, 1, 5, 1, '2018-03-27 18:29:38', 'Facebook bundle', 'https://docs.oracle.com/javafx/2/drag_drop/jfxpub-drag_drop.htm', 'Intégrer facebook avec FosUserbundle', 1, NULL, 0, NULL),
(2, 2, 1, 2, '2018-03-27 19:12:59', 'Niska en concet', 'https://www.digitick.com/billet/display.php/d684d1a87f7d6ebee9fd2d750d522751a5863bf8/242931341/97533221/41963287/digitick242931341.pdf', 'Venez voir niska en concet à lyon-Villeurbanne', 1, NULL, 0, NULL),
(4, NULL, 5, 2, '2018-03-27 20:48:58', 'Tag BeelabTagBundle', 'https://github.com/Bee-Lab/BeelabTagBundle/blob/master/Resources/doc/index.md#1-installation', 'Gérer les tags avec symfony', 1, NULL, 0, NULL),
(5, NULL, 1, 2, '2018-03-27 20:50:54', 'Bundle tag FPNTagBundle', 'http://knpbundles.com/FabienPennequin/FPNTagBundle', 'Gestion des tags avec bundle', 1, '2018-03-27 20:51:58', 0, NULL),
(6, 3, 3, 1, '2018-03-30 00:04:35', 'Actualité politique', 'http://www.bfmtv.com/politique/une-chute-des-adherents-et-des-elus-au-front-national-le-parti-livre-sa-version-1407966.html', 'Ce qui se passe actuellement en france', 1, NULL, 0, NULL),
(7, 4, 1, 1, '2018-03-30 00:07:36', 'Niska à Lyon', 'https://www.infoconcert.com/ticket/concert-niska-villeurbanne/1072957.html', 'Retrouvez niska demain à lyon Villeurbanne', 1, '2018-04-23 23:06:35', 0, NULL),
(8, 5, 4, 1, '2018-03-30 00:14:19', 'Matchs amicaux coupe du monde', 'https://sport.francetvinfo.fr/football/mondial-2018-pour-clement-turpin-cela-recompense-un-travail-de-fond', 'Retrouvez les informations sur la france pour la coupe du monde 2018', 1, NULL, 0, NULL),
(9, 6, 2, 1, '2018-03-30 00:21:52', 'Drôle d''histoire', 'https://www.20minutes.fr/culture/2246127-20180329-video-quand-guitariste-scorpions-avoue-avoir-nasty-souvient-vraiment', 'Paranoïaque, Il avoue avoir été à Nasty mais il se le rappelle pas vraiment, lol très bizzare', 1, '2018-04-23 21:40:30', 0, NULL),
(10, 7, 5, 3, '2018-04-01 17:11:55', 'Recherche de stage', 'https://www.rhonealpesjob.com/emplois/recherche.html?l=lyon&f=Informatique_dev_hard&f=Informatique_dev&f=Informatique_syst_info&f=Informatique_syst_reseaux&c=Stage', 'Trouver ici des offres de stage', 1, NULL, 0, NULL),
(11, 8, 4, 4, '2018-04-23 21:15:54', 'Footbool', 'https://www.20minutes.fr/sport/football/2260251-20180423-appel-rejete-paok-salonique-bien-perdu-titre-grece-cause-president-gun', 'Triste réalité', 1, NULL, 0, NULL),
(12, 9, 3, 1, '2018-04-23 23:11:08', 'Pression du premier ministre arménien', 'http://www.france24.com/fr/20180423-armenie-serge-sarkissian-demission-premier-ministre-armenien-manifestations-militaires', 'Se sentant menacé le premier ministre arménien démissionne', 1, NULL, 0, NULL),
(13, 10, 3, 1, '2018-04-23 23:25:41', 'Fusillade à en Belgique', 'http://www.france24.com/fr/20180423-proces-belgique-salah-abdeslam-coupable-tentative-assassinat-policiers', 'Le terroriste BenAbdeslem condamné à 20ans de prison', 1, NULL, 0, NULL),
(14, 11, 3, 1, '2018-04-23 23:27:42', 'Président Macron en Belgique', 'http://www.france24.com/fr/20180423-proces-belgique-salah-abdeslam-coupable-tentative-assassinat-policiers', 'Le terroriste BenAbdeslem condamné à 20ans de prison', 1, NULL, 0, NULL),
(15, 12, 3, 1, '2018-04-23 23:31:19', 'Solidarité des migrants', 'http://observers.france24.com/fr/20180423-traversee-solidaire-migrants-hautes-alpes-il-fallait-repondre-identitaires', 'Devenez Observateur\r\nMot de passe oublié ?\r\nUne centaine de militants accompagnés de migrants ont traversé la frontière entre l’Italie et la France dimanche 22 avril, au lendemain d''une opération anti-migrants organisée par le groupuscule d’extrême droite', 1, NULL, 0, NULL),
(16, 13, 2, 1, '2018-04-23 23:36:01', 'L''arabie saudite s''ouvre au monde', 'http://www.france24.com/fr/20180421-arabie-saoudite-secteur-divertissement-business-hollywood-cinema-trente-cinq-ans', 'L''Arabie saoudite mise sur le secteur du divertissement et du cinéma en particulier.\r\nLa projection publique, cette semaine, d’un film en Arabie saoudite, est une première depuis trente-cinq ans. Elle annonce la volonté du nouveau Prince héritier de dével', 1, NULL, 0, NULL),
(17, 14, 2, 1, '2018-04-23 23:42:53', 'Blocage des universités', 'https://www.20minutes.fr/societe/2260311-20180423-blocage-universites-degats-estimes-plus-million-euros-frederic-vidal', 'Ces perturbations ont causé des  dégâts estimés à plus d''un million d''euros', 1, NULL, 0, NULL),
(18, 15, 2, 1, '2018-04-23 23:48:52', 'Une camionnette cause des morts', 'https://www.20minutes.fr/monde/2260295-20180423-canada-camionnette-fonce-pietons-toronto-9-personnes-tuees-16-blessees', 'Une camionnette fonce sur des piétons à Toronto et cause plusieurs mort', 1, NULL, 0, NULL),
(19, 16, 2, 1, '2018-04-23 23:54:28', 'Evènement Transbordeur', 'http://www.transbordeur.fr/agenda-2017-2018/ricard-live-music-isaac-delusion-mnnqns-jeu-26-avr-18/', 'Retrouver les prochains événements du Transbordeur', 1, NULL, 0, NULL),
(20, 17, 2, 1, '2018-04-23 23:57:18', 'Retrouver le rappeur Siboy à lyon', 'http://www.transbordeur.fr/agenda-2017-2018/siboy-x-trm-tour-ven-04-mai-18/', 'Le mystère plane autour Siboy. Derrière sa cagoule se cache un artiste beatmaker de haute volée. Il enflamme la toile avec des clips et des freestyles de haut niveau basé sur un egotrip bestial et féroce.', 1, NULL, 0, NULL),
(21, 18, 4, 5, '2018-04-24 00:07:46', 'Salah en feu', 'http://www.france24.com/fr/20180423-football-ligue-champions-mohamed-mo-salah-liverpool-as-roma-demi-finale-c1', 'Football\r\nLiverpool\r\nAS Roma\r\nSPORT\r\n\r\nLigue des champions : Salah, facteur X d''une demie indécise entre Liverpool et la Roma', 1, NULL, 0, NULL),
(22, 19, 4, 5, '2018-04-24 00:12:20', 'Messi le joueur le plus payé', 'http://www.france24.com/fr/20180423-football-footballeur-mieux-paye-2018-messi-neymar-cristiano-ronaldo', 'Même hors des pelouses, la rivalité entre Lionel Messi et Cristiano Ronaldo bat son plein. Sur la saison 2017-18, l''Argentin a devancé son adversaire portugais en matière de rémunération. Le Brésilien Neymar complète le podium.', 1, NULL, 0, NULL),
(23, 20, 4, 5, '2018-04-24 00:21:42', 'L''incroyable but de JR.Smith', 'https://www.20minutes.fr/sport/2259751-20180423-video-nba-ooooooh-incroyable-panier-rsmith-buzzer-depuis-propre-camp', 'Ça n’arrive pas tous les jours, alors autant en profiter. Alors que Cleveland mène déjà de trois points à la fin du premier quart-temps contre les Pacers et qu’il ne reste qu’une poignée de secondes à jouer - et donc pas assez pour lancer un contre -, l’a', 1, NULL, 0, NULL),
(24, 21, 4, 5, '2018-04-24 00:24:03', 'Strasbourg dans la course du titre', 'https://www.20minutes.fr/sport/2258375-20180421-strasbourg-route-titre-sig-dispute-finale-coupe-france-basket-samedi', 'A Paris-Bercy ce samedi (19h), les Strasbourgeois peuvent glaner un titre ce week-end, depuis leur dernier, dans la même compétition, en 2015.\r\nIls veulent aussi l''emporter pour aborder dans de bonnes conditions les play-offs, après cinq défaites de suite', 1, NULL, 0, NULL),
(25, 22, 4, 5, '2018-04-24 00:26:17', 'Le déclin de Tony parker', 'https://www.20minutes.fr/sport/2257551-20180419-difficile-voir-galerer-comme-ca-comment-fans-tony-parker-vivent-declin', 'Le meneur de jeu de Spurs ne joue presque plus dans une équipe en passe d’être balayée par Golden State au premier tour des playoffs…', 1, NULL, 0, NULL),
(26, 23, 4, 3, '2018-04-24 00:39:09', 'Clash entre Ronaldinho et Luis Fernandez', 'https://www.20minutes.fr/sport/football/2259935-20180423-psg-gachis-clash-qualite-entre-luis-fernandez-ronaldinho', 'Luis Fernandez et Ronaldinho, cela n’a jamais été l’amour fou. La ligue 1 se souvient encore des images de Ronnie remplaçant à Paris (qui à l’époque était loin d’avoir l’effectif actuel) en 2003, sous les ordres de Luis', 1, NULL, 0, NULL),
(27, 24, 1, 3, '2018-04-24 00:44:24', 'Lacrim sort une tuerie', 'https://www.youtube.com/watch?v=puyF6yN8xo8', 'Lacrim toujours au top', 1, NULL, 0, NULL),
(28, 25, 1, 3, '2018-04-24 00:48:02', 'Nouvelle tuerie de Lilwayne', 'https://www.youtube.com/watch?v=SZHoOLWtBdg', 'Une légende ne meurt jamais. Toujours au top Lilwayne à chaque sortie de son', 1, NULL, 0, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `oc_annonce_sous_rubrique`
--

CREATE TABLE `oc_annonce_sous_rubrique` (
  `annonce_id` int(11) NOT NULL,
  `sousrubrique_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `oc_annonce_sous_rubrique`
--

INSERT INTO `oc_annonce_sous_rubrique` (`annonce_id`, `sousrubrique_id`) VALUES
(1, 12),
(1, 13),
(2, 1),
(2, 3),
(2, 4),
(4, 8),
(4, 12),
(4, 13),
(5, 12),
(5, 13),
(6, 6),
(6, 7),
(7, 4),
(8, 10),
(9, 5),
(10, 12),
(11, 10),
(12, 7),
(13, 8),
(14, 7),
(15, 8),
(16, 6),
(17, 6),
(18, 6),
(19, 5),
(20, 4),
(21, 10),
(22, 10),
(23, 9),
(24, 9),
(25, 9),
(26, 10),
(27, 1),
(28, 1);

-- --------------------------------------------------------

--
-- Structure de la table `oc_image`
--

CREATE TABLE `oc_image` (
  `id` int(11) NOT NULL,
  `url` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `alt` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `oc_image`
--

INSERT INTO `oc_image` (`id`, `url`, `alt`) VALUES
(1, 'jpeg', 'PhotoGrid_1477848634209.jpg'),
(2, 'jpeg', '_20160606_145440.JPG'),
(3, 'jpeg', '_20170103_162550.JPG'),
(4, 'jpeg', '_20170103_162550.JPG'),
(5, 'jpeg', '_20160518_152814.JPG'),
(6, 'jpeg', '1623628_1516429815253188_8964451244560261082_n.jpg'),
(7, 'jpeg', '6tag_031115-141152.jpg'),
(8, 'jpeg', 'Snapchat-78687113.jpg'),
(9, 'jpeg', 'IMG_20140829_225952.JPG'),
(10, 'jpeg', 'IMG_3208475644138.jpeg'),
(11, 'jpeg', 'IMG_1513459360690.jpeg'),
(12, 'jpeg', 'IMG_5026365920076.jpeg'),
(13, 'jpeg', 'IMG_405492608230.jpeg'),
(14, 'jpeg', 'IMG_405492608230.jpeg'),
(15, 'jpeg', 'IMG_1123827212306.jpeg'),
(16, 'jpeg', 'IMG_1123827212306.jpeg'),
(17, 'jpeg', 'IMG_977239338613.jpeg'),
(18, 'jpeg', 'IMG_5347547990383.jpeg'),
(19, 'jpeg', 'IMG_5347547990383.jpeg'),
(20, 'jpeg', 'Screenshot_20160725-211428.png'),
(21, 'png', 'Screenshot_20170218-071843.png'),
(22, 'png', 'Screenshot_20170218-071843.png'),
(23, 'jpeg', 'received_1674108819298213.jpeg'),
(24, 'jpeg', 'received_1547121435330286.jpeg'),
(25, 'png', 'Screenshot_20160626-004951.png');

-- --------------------------------------------------------

--
-- Structure de la table `oc_rubriques`
--

CREATE TABLE `oc_rubriques` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `oc_rubriques`
--

INSERT INTO `oc_rubriques` (`id`, `name`) VALUES
(1, 'Divertissement'),
(2, 'Culture'),
(3, 'Politique'),
(4, 'Sport'),
(5, 'Science');

-- --------------------------------------------------------

--
-- Structure de la table `oc_sous_rubriques`
--

CREATE TABLE `oc_sous_rubriques` (
  `id` int(11) NOT NULL,
  `rubrique_id` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `oc_sous_rubriques`
--

INSERT INTO `oc_sous_rubriques` (`id`, `rubrique_id`, `name`) VALUES
(1, 1, 'Musique'),
(2, 1, 'Sortie'),
(3, 1, 'Spectacle'),
(4, 2, 'Concert'),
(5, 2, 'Evènement'),
(6, 2, 'Actualité'),
(7, 3, 'Gouvernementale'),
(8, 3, 'Autres'),
(9, 4, 'Basket'),
(10, 4, 'Football'),
(11, 4, 'Tennis'),
(12, 5, 'Informatique'),
(13, 5, 'Technologie'),
(14, 5, 'Biologie');

-- --------------------------------------------------------

--
-- Structure de la table `oc_user`
--

CREATE TABLE `oc_user` (
  `id` int(11) NOT NULL,
  `username` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `username_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `confirmation_token` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:array)',
  `author` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `oc_user`
--

INSERT INTO `oc_user` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `author`) VALUES
(1, 'Rimkaysh', 'rimkaysh', 'rimkaysh@gmail.com', 'rimkaysh@gmail.com', 1, 'lZRFsz1a3FhA8JsEd9AXmuhtfuCKUqJs2fjZfSJCHMo', 'Vy086St+g8I2hbCjCsvBO6Y4nSaSEQTZ2GMvuNqppxUcdeFdseaNm3BEM8pGJCEetlvM7JTlVFAN1CBPUkJxvA==', '2018-04-24 01:35:39', NULL, NULL, 'a:0:{}', NULL),
(2, 'Pascal', 'pascal', 'pascal@hotmail.com', 'pascal@hotmail.com', 1, 'ahWQCcmt0Sh2Mef5X3Y/oqVC0qxjBJNHgTdtsxkBSLM', 'O/N/ZBPfi0pRYKqMbXYRfpUANA0ycAlpZrfWoMKxJJHRvlm7u14ETrKoS1UL9mzW9BxkYVBvuzvkHoGFsfx41Q==', '2018-03-27 19:10:48', NULL, NULL, 'a:0:{}', NULL),
(3, 'Karim', 'karim', 'karim@gmail.com', 'karim@gmail.com', 1, 'HN0AbX96cLR22aRWfD/juncDY2YNQfWfmPuTscLFzh0', 'KZRF/cFxn+v3IfNLS/+AMUw9X0h6OWtjYbtKK1PC6ubD9b+wpfXL3YFBWygcpBKyQVMsH/xygYPWKdVIDmtRDg==', '2018-04-24 01:24:37', NULL, NULL, 'a:0:{}', NULL),
(4, 'Alpha', 'alpha', 'alpha1@gmail.com', 'alpha1@gmail.com', 1, 'z/redSyQK3omLQVDavIRFcoe/5mwZKget81ZsGhaI/w', 'OTWeK1wak9ArdAxbrP9U0tUAbPOd6TCwms7CmK+JsvBv5govH8oefFqh35JM2pIUQOHiHXmCe0NWWDgBVFEj9g==', '2018-04-24 01:12:36', NULL, NULL, 'a:0:{}', NULL),
(5, 'Jeff', 'jeff', 'Jeff@hotmail.com', 'jeff@hotmail.com', 1, 'nuAqfZwm3tkP6HYxBvqqeI0RzamoxzBiYI/gIfAE6s4', 'I9U1/p5uaWmKAN1EqBXAIWGeAMnCed957mPcBA2fbq0oQ+sXnZqnNwxEw06jHgO/jN7bOsRougO3VUvXwBY1pg==', '2018-04-24 01:32:26', NULL, NULL, 'a:0:{}', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `tag`
--

CREATE TABLE `tag` (
  `id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `slug` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `tagging`
--

CREATE TABLE `tagging` (
  `id` int(11) NOT NULL,
  `tag_id` int(11) DEFAULT NULL,
  `resource_type` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `resource_id` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `thread`
--

CREATE TABLE `thread` (
  `id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `permalink` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `is_commentable` tinyint(1) NOT NULL,
  `num_comments` int(11) NOT NULL,
  `last_comment_at` datetime DEFAULT NULL,
  `annonce_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `thread`
--

INSERT INTO `thread` (`id`, `permalink`, `is_commentable`, `num_comments`, `last_comment_at`, `annonce_id`) VALUES
('1', 'http://localhost:8080/Executable/Newsbook/web/app_dev.php/platform/annonce/1', 1, 6, '2018-04-23 21:59:26', NULL),
('10', 'http://localhost:8080/Executable/Newsbook/web/app_dev.php/platform/annonce/10', 1, 1, '2018-04-23 22:01:17', NULL),
('11', 'http://localhost:8080/Executable/Newsbook/Symfony/web/app_dev.php/platform/annonce/11', 1, 1, '2018-04-23 21:17:52', NULL),
('14', 'http://localhost:8080/Executable/Newsbook/Symfony/web/app_dev.php/platform/annonce/14', 1, 11, '2018-04-24 01:38:22', NULL),
('2', 'http://localhost:8080/Executable/Newsbook/web/app_dev.php/platform/annonce/2', 1, 0, NULL, NULL),
('21', 'http://localhost:8080/Executable/Newsbook/Symfony/web/app_dev.php/platform/annonce/21', 1, 13, '2018-04-24 01:29:17', NULL),
('28', 'http://localhost:8080/Executable/Newsbook/Symfony/web/app_dev.php/platform/annonce/28', 1, 15, '2018-04-24 01:15:27', NULL),
('3', 'http://localhost:8080/Executable/Newsbook/web/app_dev.php/platform/annonce/3', 1, 0, NULL, NULL),
('4', 'http://localhost:8080/Executable/Newsbook/web/app_dev.php/platform/annonce/4', 1, 0, NULL, NULL),
('5', 'http://localhost:8080/Executable/Newsbook/web/app_dev.php/platform/annonce/5', 1, 2, '2018-04-01 16:37:22', NULL),
('6', 'http://localhost:8080/Executable/Newsbook/web/app_dev.php/platform/annonce/6', 1, 0, NULL, NULL),
('7', 'http://localhost:8080/Executable/Newsbook/web/app_dev.php/platform/annonce/7', 1, 9, '2018-04-23 21:18:45', NULL),
('8', 'http://localhost:8080/Executable/Newsbook/web/app_dev.php/platform/annonce/8', 1, 15, '2018-04-23 21:36:09', NULL),
('9', 'http://localhost:8080/Executable/Newsbook/web/app_dev.php/platform/annonce/9', 1, 12, '2018-04-23 21:52:19', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `vote`
--

CREATE TABLE `vote` (
  `id` int(11) NOT NULL,
  `comment_id` int(11) DEFAULT NULL,
  `voter_id` int(11) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `value` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_9474526CE2904019` (`thread_id`),
  ADD KEY `IDX_9474526CF675F31B` (`author_id`);

--
-- Index pour la table `oc_annonce`
--
ALTER TABLE `oc_annonce`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQ_41B7271B2B36786B` (`title`),
  ADD UNIQUE KEY `UNIQ_41B7271B3DA5256D` (`image_id`),
  ADD UNIQUE KEY `UNIQ_41B7271BE2904019` (`thread_id`),
  ADD KEY `IDX_41B7271B3BD38833` (`rubrique_id`),
  ADD KEY `IDX_41B7271BA76ED395` (`user_id`);

--
-- Index pour la table `oc_annonce_sous_rubrique`
--
ALTER TABLE `oc_annonce_sous_rubrique`
  ADD PRIMARY KEY (`annonce_id`,`sousrubrique_id`),
  ADD KEY `IDX_D88D416F8805AB2F` (`annonce_id`),
  ADD KEY `IDX_D88D416FBEE02DA1` (`sousrubrique_id`);

--
-- Index pour la table `oc_image`
--
ALTER TABLE `oc_image`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `oc_rubriques`
--
ALTER TABLE `oc_rubriques`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `oc_sous_rubriques`
--
ALTER TABLE `oc_sous_rubriques`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_BC61CD823BD38833` (`rubrique_id`);

--
-- Index pour la table `oc_user`
--
ALTER TABLE `oc_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQ_7866CFC992FC23A8` (`username_canonical`),
  ADD UNIQUE KEY `UNIQ_7866CFC9A0D96FBF` (`email_canonical`),
  ADD UNIQUE KEY `UNIQ_7866CFC9C05FB297` (`confirmation_token`);

--
-- Index pour la table `tag`
--
ALTER TABLE `tag`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQ_389B7835E237E06` (`name`),
  ADD UNIQUE KEY `UNIQ_389B783989D9B62` (`slug`);

--
-- Index pour la table `tagging`
--
ALTER TABLE `tagging`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `tagging_idx` (`tag_id`,`resource_type`,`resource_id`),
  ADD KEY `IDX_A4AED123BAD26311` (`tag_id`);

--
-- Index pour la table `thread`
--
ALTER TABLE `thread`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQ_31204C838805AB2F` (`annonce_id`);

--
-- Index pour la table `vote`
--
ALTER TABLE `vote`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_5A108564F8697D13` (`comment_id`),
  ADD KEY `IDX_5A108564EBB4B8AD` (`voter_id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `comment`
--
ALTER TABLE `comment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=86;
--
-- AUTO_INCREMENT pour la table `oc_annonce`
--
ALTER TABLE `oc_annonce`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
--
-- AUTO_INCREMENT pour la table `oc_image`
--
ALTER TABLE `oc_image`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
--
-- AUTO_INCREMENT pour la table `oc_rubriques`
--
ALTER TABLE `oc_rubriques`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT pour la table `oc_sous_rubriques`
--
ALTER TABLE `oc_sous_rubriques`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT pour la table `oc_user`
--
ALTER TABLE `oc_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT pour la table `tag`
--
ALTER TABLE `tag`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `tagging`
--
ALTER TABLE `tagging`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `vote`
--
ALTER TABLE `vote`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `FK_9474526CE2904019` FOREIGN KEY (`thread_id`) REFERENCES `thread` (`id`),
  ADD CONSTRAINT `FK_9474526CF675F31B` FOREIGN KEY (`author_id`) REFERENCES `oc_user` (`id`);

--
-- Contraintes pour la table `oc_annonce`
--
ALTER TABLE `oc_annonce`
  ADD CONSTRAINT `FK_41B7271B3BD38833` FOREIGN KEY (`rubrique_id`) REFERENCES `oc_rubriques` (`id`),
  ADD CONSTRAINT `FK_41B7271B3DA5256D` FOREIGN KEY (`image_id`) REFERENCES `oc_image` (`id`),
  ADD CONSTRAINT `FK_41B7271BA76ED395` FOREIGN KEY (`user_id`) REFERENCES `oc_user` (`id`),
  ADD CONSTRAINT `FK_41B7271BE2904019` FOREIGN KEY (`thread_id`) REFERENCES `thread` (`id`);

--
-- Contraintes pour la table `oc_annonce_sous_rubrique`
--
ALTER TABLE `oc_annonce_sous_rubrique`
  ADD CONSTRAINT `FK_D88D416F8805AB2F` FOREIGN KEY (`annonce_id`) REFERENCES `oc_annonce` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_D88D416FBEE02DA1` FOREIGN KEY (`sousrubrique_id`) REFERENCES `oc_sous_rubriques` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `oc_sous_rubriques`
--
ALTER TABLE `oc_sous_rubriques`
  ADD CONSTRAINT `FK_BC61CD823BD38833` FOREIGN KEY (`rubrique_id`) REFERENCES `oc_rubriques` (`id`);

--
-- Contraintes pour la table `tagging`
--
ALTER TABLE `tagging`
  ADD CONSTRAINT `FK_A4AED123BAD26311` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`);

--
-- Contraintes pour la table `thread`
--
ALTER TABLE `thread`
  ADD CONSTRAINT `FK_31204C838805AB2F` FOREIGN KEY (`annonce_id`) REFERENCES `oc_annonce` (`id`);

--
-- Contraintes pour la table `vote`
--
ALTER TABLE `vote`
  ADD CONSTRAINT `FK_5A108564EBB4B8AD` FOREIGN KEY (`voter_id`) REFERENCES `oc_user` (`id`),
  ADD CONSTRAINT `FK_5A108564F8697D13` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
