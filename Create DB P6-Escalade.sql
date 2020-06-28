
CREATE TABLE Roles (
                utilisateur_id VARBINARY NOT NULL,
                role VARCHAR NOT NULL,
                PRIMARY KEY (utilisateur_id)
);


CREATE TABLE Utilisateur (
                utilisateur_id VARBINARY NOT NULL,
                nom VARCHAR NOT NULL,
                prenom VARCHAR NOT NULL,
                pseudo VARCHAR NOT NULL,
                mot_de_passe VARCHAR NOT NULL,
                email VARCHAR NOT NULL,
                active BOOLEAN NOT NULL,
                credentials_non_expired BOOLEAN NOT NULL,
                account_non_locked BOOLEAN NOT NULL,
                account_non_expired BOOLEAN NOT NULL,
                PRIMARY KEY (utilisateur_id)
);


CREATE TABLE Spot (
                spot_id VARBINARY NOT NULL,
                nom VARCHAR NOT NULL,
                departement VARCHAR NOT NULL,
                tagOfficiel BOOLEAN NOT NULL,
                utilisateur_id VARBINARY NOT NULL,
                PRIMARY KEY (spot_id)
);


CREATE TABLE Commentaire (
                commentaire_id VARBINARY NOT NULL,
                verbatim VARCHAR NOT NULL,
                spot_id VARBINARY NOT NULL,
                utilisateur_id VARBINARY NOT NULL,
                PRIMARY KEY (commentaire_id)
);


CREATE TABLE Topo (
                topo_id VARBINARY NOT NULL,
                nom VARCHAR NOT NULL,
                description VARCHAR NOT NULL,
                lieu VARCHAR NOT NULL,
                date_parution VARCHAR NOT NULL,
                statut BOOLEAN NOT NULL,
                spot_id VARBINARY NOT NULL,
                utilisateur_id VARBINARY NOT NULL,
                PRIMARY KEY (topo_id)
);


CREATE TABLE Reservation (
                reservation_id VARBINARY NOT NULL,
                statut VARCHAR NOT NULL,
                topo_id VARBINARY NOT NULL,
                utilisateur_id VARBINARY NOT NULL,
                PRIMARY KEY (reservation_id)
);


CREATE TABLE Secteur (
                secteur_id VARBINARY NOT NULL,
                nom VARCHAR NOT NULL,
                spot_id VARBINARY NOT NULL,
                PRIMARY KEY (secteur_id)
);


CREATE TABLE Voie (
                voie_id VARBINARY NOT NULL,
                nom VARCHAR NOT NULL,
                nombre_points VARCHAR NOT NULL,
                cotation VARCHAR NOT NULL,
                hauteur VARCHAR NOT NULL,
                secteur_id VARBINARY NOT NULL,
                PRIMARY KEY (voie_id)
);


CREATE TABLE Longueur (
                longueur_id VARBINARY NOT NULL,
                nom VARCHAR NOT NULL,
                cotation VARCHAR NOT NULL,
                voie_id VARBINARY NOT NULL,
                PRIMARY KEY (longueur_id)
);


ALTER TABLE Utilisateur ADD CONSTRAINT role_utilisateur_fk
FOREIGN KEY (utilisateur_id)
REFERENCES Roles (utilisateur_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Topo ADD CONSTRAINT utilisateur_topo_fk
FOREIGN KEY (utilisateur_id)
REFERENCES Utilisateur (utilisateur_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Commentaire ADD CONSTRAINT utilisateur_commentaire_fk
FOREIGN KEY (utilisateur_id)
REFERENCES Utilisateur (utilisateur_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Reservation ADD CONSTRAINT utilisateur_emprunt_fk
FOREIGN KEY (utilisateur_id)
REFERENCES Utilisateur (utilisateur_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Spot ADD CONSTRAINT utilisateur_spot_fk
FOREIGN KEY (utilisateur_id)
REFERENCES Utilisateur (utilisateur_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Secteur ADD CONSTRAINT spot_secteur_fk
FOREIGN KEY (spot_id)
REFERENCES Spot (spot_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Topo ADD CONSTRAINT spot_topo_fk
FOREIGN KEY (spot_id)
REFERENCES Spot (spot_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Commentaire ADD CONSTRAINT spot_commentaire_fk
FOREIGN KEY (spot_id)
REFERENCES Spot (spot_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Reservation ADD CONSTRAINT topo_emprunt_fk
FOREIGN KEY (topo_id)
REFERENCES Topo (topo_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Voie ADD CONSTRAINT secteur_voie_fk
FOREIGN KEY (secteur_id)
REFERENCES Secteur (secteur_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Longueur ADD CONSTRAINT voie_longueur_fk
FOREIGN KEY (voie_id)
REFERENCES Voie (voie_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;
