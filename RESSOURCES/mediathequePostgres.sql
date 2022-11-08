CREATE SEQUENCE Realisateur_id_seq;

CREATE TABLE Realisateur (
 id         integer NOT NULL DEFAULT nextval('Realisateur_id_seq'),
 nom       VARCHAR( 100 ) NOT NULL ,
 prenom    VARCHAR( 100 ) NOT NULL ,
 age   INT NOT NULL ,
 pays  VARCHAR( 200 ),
 PRIMARY KEY ( id )  
);

ALTER SEQUENCE Realisateur_id_seq
OWNED BY Realisateur.id;



CREATE SEQUENCE Film_id_seq;

CREATE TABLE Film (
 id         integer NOT NULL DEFAULT nextval('Film_id_seq'),
 id_realisateur  INT ,
 titre       VARCHAR( 100 ) NOT NULL ,
 duree_minutes   INT NOT NULL ,
 genre  VARCHAR( 20 ),
 PRIMARY KEY ( id ) ,
 CONSTRAINT fk_id_realisateur   
    FOREIGN KEY (id_realisateur)  
    REFERENCES Realisateur(id)  
    ON DELETE SET NULL    
);

ALTER SEQUENCE Film_id_seq
OWNED BY Film.id;



INSERT INTO Realisateur (nom, prenom, age, pays) VALUES
('Eastwood', 'Clint', 92, 'Etats-Unis'),
('Spielberg', 'Steven', 75, null),
('Von Trier', 'Lars', 66, 'Danemark'),
('Achour', 'Mouloud', 41, 'France');

INSERT INTO Film (id_realisateur, titre, duree_minutes, genre) VALUES
(1, 'Gran Torino', 112, 'Drame'),
(1, 'Invictus', 134, 'Drame'),
(2, 'Jurassic Park', 128, 'Science-fiction'),
(2, 'Arrête-moi si tu peux', 141, 'Comédie dramatique'),
(3, 'Antichrist', 108, 'Drame'),
(3, 'Melancholia', 130, 'Drame'),
(4, 'Les méchants', 84, 'Comédie');
