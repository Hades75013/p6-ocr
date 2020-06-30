# P6-Openclassroom : Les amis de l'escalade

## Recueil des besoins client 
  
### Objet
  Création d’un site communautaire autour de l’escalade.   
  
### Contexte  
  Avec l’objectif de fédérer les licenciés, l’association “Les amis de l’escalade” souhaite développer sa présence en ligne. 
  À ce titre, plusieurs axes d’amélioration ont été identifiés dont la création d’un site communautaire.  
  Ce site aura pour but la mise en relation et le partage d’informations. Il permettra de donner de la visibilité à l’association 
  afin d’encourager des grimpeurs indépendants à y adhérer. 
### Liste des fonctionnalités   
  - F1 : Un utilisateur doit pouvoir consulter les informations des sites d’escalades (secteurs, voies, longueurs, etc.).    
  - F2 : Un utilisateur doit pouvoir faire une recherche à l’aide de plusieurs critères pour trouver un site de grimpe et 
  consulter le résultat de cette recherche. Les critères peuvent être le lieu, la cotation, le nombre de secteurs, etc.    
  - F3 : Un utilisateur doit pouvoir s’inscrire gratuitement sur le site.    
  - F4 : Un utilisateur connecté doit pouvoir partager des informations sur un site d’escalade (secteurs, voies, longueurs, etc.).    
  - F5 : Un utilisateur connecté doit pouvoir laisser des commentaires sur les pages des sites d’escalade.    
  - F6 : Un membre de l'association doit pouvoir taguer un site d’escalade enregistré sur la plateforme 
  comme « Officiel Les amis de l’escalade ».    
  - F7 : Un membre de l'association doit pouvoir modifier et supprimer un commentaire.    
  - F8 : Un utilisateur connecté doit pouvoir enregistrer dans son espace personnel les topos qu’ils possèdent et préciser 
  si ces derniers sont disponibles pour être prêtés ou non.  Un topo est défini par un nom, une description, un lieu et une date de parution.    
  - F9 : Un utilisateur connecté doit pouvoir consulter la liste des topos disponibles par d’autres utilisateurs et faire 
  une demande de réservation. La réservation n’inclut pas les notions de date de début et date de fin. 
  - F10 : Un utilisateur connecté doit pouvoir accepter une demande de réservation. Si une réservation est acceptée, 
  automatiquement le topo devient indisponible. L’utilisateur connecté pourra ensuite de nouveau changer le statut du topo en « disponible ».   
  La plateforme se contente de mettre en contact les deux parties pour le prêt d’un topo (par échange de coordonnées). 
  
### Liste des contraintes fonctionnelles :    
  - C1 : Le vocabulaire de l’escalade doit être utilisé : site, spot, voie, longueur, grimpeur, point ou spit, cotation, topo.    
  - C2 : Le site doit être responsive.    
  - C3 : Le site doit être sécurisé


## Développement

Cette application a été développé avec :
- Eclipse
- Java 11
- Tomcat 9
- MySQL (version 5.7)
- le framework Spring 


## Déploiement

* La source du projet est hérbergée sur GitHub à l'adresse : https://github.com/Hades75013/p6-ocr
* Le projet est géré par Apache Maven.
* L'application se builde par Maven au format .war.

Les frameworks utilisés sont : 
* Spring MVC/ SpringBoot
* Bootstrap

* La base de données est déployée sur un serveur MySQL.
* L'application est déployée sur un serveur Apache Tomcat v9


## Base de données

* Pour la base de données, vous trouverez toutes les infos utiles dans le fichier "application.properties"
* Lancer le script "Create DB P6-Escalade.sql" pour créer la bdd.
* Enfin lancer le script Jeu_de_demo_p6-escalade pour insérer les données.

  Utilisateurs créés au préalable : 
* Un utilisateur lambda (aucun droit particulier) : 
  Pseudo : User
  Mot de passe : user
* Un utilisateur avec les droits "Membre de l'association" : 
  Pseudo : Admin
  Mot de passe : admin
