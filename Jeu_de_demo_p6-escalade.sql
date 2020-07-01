-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: p6-escalade
-- ------------------------------------------------------
-- Server version	5.7.29-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `commentaire`
--

LOCK TABLES `commentaire` WRITE;
/*!40000 ALTER TABLE `commentaire` DISABLE KEYS */;
INSERT INTO `commentaire` VALUES (162,'Un gros travail de nettoyage a été fait aussi bien sur le chemin d’approche, 10 mn depuis le parking, que sur les voies. Bravo et merci à toute l’équipe ! ',155,153),(163,'Sur la voie \"L\'entonnoir\", il semble qu’il y ait un écart assez énorme entre le 4ème et 5ème anneau ce qui rend la voie assez exposée, me trompe-je ? \r\n',155,153),(180,'Attention , je pense qu’il y a une erreur de disposition du secteur dièdre. En fait se trouve entre le secteur gauche et le secteur de la croix, et non à droite du secteur de la croix. A part ca ce secteur est très sympa!',169,153),(181,'Le secteur dièdre est bien à l’extrémité droite du site (cf. topo)',169,154),(198,'Les Pyrales de buis sont arrivées sur site, j’ai pu le constater hier (07/09/2019). A voir comment cela évolue, mais les buis n’avaient pas l’air en très grande forme :/ ',183,153),(199,'Une belle surprise que cette ancienne carrière au rocher étonnant, bien adhérent. Les parties sommitales sont un peu délitées mais l’ensemble reste bien compact. Secteur permettant des escalades variées, dalles, surplomb, arrêtes, dièdres … ',183,153),(217,'Il n y a plus que la voie à l\'extrême gauche qui n\'a qu\'un seul point au relais et la voie facile de droite. J ai tout doublé au printemps 2017. \r\nSinon l environnement a bien changé car la pyrale a tout dévasté.',201,154),(235,'Spot avec un pas de bloc très dur à la sortie de la première conque + pas de bloc très dur en sortie, voie non réalisée.',218,154),(251,'Une dulfer éducative en première partie. Rester concentré pour la sortie...Joli spot abordable !',236,154),(260,'Petit spot au calme, assez court, mais avec un joli rocher gris bien raide. Exposé Sud (bien dégagé des arbres)...\r\nPetit pas de dalle au départ puis une suite de jolis mouvements en dévers.',201,153),(261,'Rocher un peu délicat dans le bas, bien meilleur ensuite. Départ possible en bas du socle. Très joli système de fissures à droite du monolithe.',218,153),(262,'Joli spot technique, difficile a vue... l’idée est de rester au mieux dans l’axe des points... Sortie sur le relais de gauche... De jolis mouvements dans un profil parfois déversant...',236,153);
/*!40000 ALTER TABLE `commentaire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (272),(272),(272),(272),(272),(272),(272),(272);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `longueur`
--

LOCK TABLES `longueur` WRITE;
/*!40000 ALTER TABLE `longueur` DISABLE KEYS */;
INSERT INTO `longueur` VALUES (160,'6b','La Vingt',158),(161,'6a','La 4L du facteur',158),(166,'7c','Longueur des buis',164),(167,'7c','Longueur vire',164),(176,'5c','Longueur du Dièdre',172),(177,'6a','Longueur de la Longuepée',172),(178,'6b','Longueur du Sommet',173),(179,'4c','Longueur large',173),(188,'8c','Longueur du Diable',186),(189,'8c','Longueur Ouest ',186),(190,'6a','Longueur Grande Raie',187),(191,'8c','Longueur des Froussards',187),(194,'5a','Longueur 1',192),(195,'5b','Longueur 2',192),(196,'6c','Longueur Meyer',193),(197,'6c','Longueur Domet',193),(206,'3b','la Crête de Coq',204),(207,'3b','La Dent de la Rancune',204),(208,'3a','L\'Aiguillette',205),(209,'3a','La Rotonde',205),(212,'3b','Les Trois Becs',210),(213,'3b','Le Clap',210),(214,'3b','Le Glandasse',211),(215,'3b','L\'Archiane',211),(223,'7a','Longueur des Braves',221),(224,'7a','Longueur du Requin',221),(225,'7c','Longueur A-Pic',222),(226,'7c','Les Baroudeurs',222),(229,'7b','Les Babos',227),(232,'7b','Longueur de la Chamade',228),(231,'7b','Longueur secouée',227),(233,'7b','Longueur Est',228),(241,'5c','Les Petits Goulets',239),(242,'5c','Les Endormis',239),(243,'6c','Les Pinets',240),(244,'6c','Les Bossons',240),(247,'7c','Les Trois Rivières',245),(248,'7a','Saint Florent',245),(249,'7c','Sainte-Croix',246),(250,'7c','La Rochette ',246);
/*!40000 ALTER TABLE `longueur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (253,200,154,'ACCEPTEE'),(254,216,153,'ACCEPTEE'),(255,252,153,'EN_COURS'),(256,168,154,'EN_COURS'),(257,182,154,'REFUSEE'),(259,234,153,'REFUSEE'),(270,168,154,'ACCEPTEE');
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (153,'ADMIN'),(154,'USER'),(263,'USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `secteur`
--

LOCK TABLES `secteur` WRITE;
/*!40000 ALTER TABLE `secteur` DISABLE KEYS */;
INSERT INTO `secteur` VALUES (156,'Le Toit',155),(157,'Fond de combe',155),(170,'Secteur du haut',169),(171,'Secteur du plateau',169),(184,'Secteur gauche',183),(185,'Secteur droite',183),(202,'Secteur Blocs Roses',201),(203,'Secteur Pierres Posées',201),(219,'La Traversée des Roches',218),(220,'Secteur Vernet',218),(237,'Secteur du Loup',236),(238,'Les Dentelles',236);
/*!40000 ALTER TABLE `secteur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `spot`
--

LOCK TABLES `spot` WRITE;
/*!40000 ALTER TABLE `spot` DISABLE KEYS */;
INSERT INTO `spot` VALUES (155,'Hautes-Alpes','Ailefroide',153,_binary ''),(169,'Loire-Atlantique','Clisson',153,_binary '\0'),(183,'Rhône-Alpes','Le Désert des Froussards',153,_binary ''),(201,'Auvergne','Le Capucin',154,_binary ''),(218,'Drôme','Les Roches Qui Dansent',154,_binary '\0'),(236,'Bouches-du-Rhône','Les Calanques',154,_binary '\0');
/*!40000 ALTER TABLE `spot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `topo`
--

LOCK TABLES `topo` WRITE;
/*!40000 ALTER TABLE `topo` DISABLE KEYS */;
INSERT INTO `topo` VALUES (168,'25/02/2017','Préparez-vous à plonger dans un océan de granit d’une qualité constante, caractérisé par son grain féroce, qui le rend agréablement adhérent quand il s’agit de se tenir sur les prises, mais quelque peu abrasif pour la peau des doigts. Tout est là, des blocs dépassant rarement 4 m de haut, aux grandes parois dalleuses où vous embarquez pour des voyages de 400 m d’escalade.','Hautes-Alpes','Topo Ailefroide',_binary '',155,153),(182,'13/07/2018','Clisson étant somme toute encore assez proche de la Bretagne, la principale roche que l’on trouve ici reste le bon vieux granit breton. Moins agressif que celui qui règne sur la côte nord de la Bretagne, il faudra cependant apprendre à l’apprivoiser afin de taper un maximum d’essais sur les nombreux passages du site. ','Loire-Atlantique','Topo Clisso',_binary '',169,153),(200,'18/06/2016','Le rocher est bien compact et on n’y voit pas tant de prises que ça. Avec une majorité de préhensions en bi ou en tritendu, il est tout de même conseillé de venir avec un bon potentiel \"doigts\", ainsi qu’avec des doigts relativement neufs (le rocher a tendance à brouter les doigts rapidement).','Rhône-Alpes','Topo Désert des Froussards',_binary '\0',183,153),(216,'11/05/2019','Ce secteur calcaire offre des blocs, parfois hauts et équipés, à explorer avec une corde ou un crash pad selon ses envies de bloc ou de couenne. Il y a toutes les orientations et tous les profils en termes d’inclinaison, la tendance générale étant à la dalle et au mur vertical avec réglettes et fissures.','Auvergne','Topo Le Capucin',_binary '\0',201,154),(234,'11/05/2020','Des gros cailloux de granite posés en vrac, ça donne des passages verticaux, surplombants ou déversants, avec des réglettes et des plats. Le sol est assez plat - Crash pad en réception.\r\n','Drôme','Topo Les Roches Qui Dansent',_binary '',218,154),(252,'08/06/2020','Le caillou ressemble à la fameuse dolomie des gorges du Tarn voisines. Il offre essentiellement de la dalle verticale, du bombé, quelques dévers et surplombs, du très facile, du un peu plus dur… et même du encore plus dur! Vous trouverez ainsi des voies typées bloc et des voies plus longues et techniques, ainsi que des grandes voies \"école\" sur du rocher sculpté de réglettes, trous et fissures.','Bouches-du-Rhône','Topo Les Calanques',_binary '',236,154),(269,'date test','description test','lieu test','topo test ',_binary '\0',183,154);
/*!40000 ALTER TABLE `topo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `utilisateur`
--

LOCK TABLES `utilisateur` WRITE;
/*!40000 ALTER TABLE `utilisateur` DISABLE KEYS */;
INSERT INTO `utilisateur` VALUES (153,_binary '',_binary '',_binary '',_binary '','admin@gmail.com','$2a$10$NOxgBXGVuw4Puq3mlIUn1eAXrMlC0YbO8Q/76zASQBFaPpMV1BNUy','ADMIN','Admin','Admin'),(154,_binary '',_binary '',_binary '',_binary '','user@gmail.com','$2a$10$xvJ.fycBttjHw/kKIBl5SetQRFAlNtUfBH4IRNbkcwb/uaFfGo0FG','USER','User','User');
/*!40000 ALTER TABLE `utilisateur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `voie`
--

LOCK TABLES `voie` WRITE;
/*!40000 ALTER TABLE `voie` DISABLE KEYS */;
INSERT INTO `voie` VALUES (158,'4a','245m','L\'inconnu','18',156),(159,'6b','225m','L\'entonnoir','14',156),(164,'5b','110m','Voie du balcon','8',157),(165,'7c','180m','Voie Border line','10',157),(172,'4c','80m','Voie Bi-doigt','6',170),(173,'5c','120m','Voie du haut','8',170),(174,'5c','120m','Voie bord de route','4',171),(175,'5b','155m','Voie de la Croix','7',171),(186,'8c','132m','Voie Grand Mur','17',184),(187,'7c','140m','Voie Le Puit','12',184),(192,'5c','55m','Voie Tueur à gaz','11',185),(193,'6a','40m','Voie des Acolytes','9',185),(204,'3c','15m','Voie Usine','4',202),(205,'3a','10m','Voie de la Cave','2',202),(210,'3b','18m','Voie de l\'Accroche','5',203),(211,'3b','12m','Voie du Mandarin','3',203),(221,'7a','50m','Voie Argenton','16',219),(222,'7a','30m','Voie Annot','11',219),(227,'7b','18m','Voie de la Motte','7',220),(228,'7b','10m','Voie du Crépuscule','5',220),(239,'5c','25m','Les Eaux-Claires','14',237),(240,'6c','35m','Les Gorges Du Loup','17',237),(245,'7a','78m','L\'Auberge Espagnole','12',238),(246,'7c','52m','Les Traverses','10',238);
/*!40000 ALTER TABLE `voie` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-02  0:25:04
