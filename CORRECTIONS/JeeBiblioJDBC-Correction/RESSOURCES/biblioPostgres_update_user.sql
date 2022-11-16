CREATE SEQUENCE Utilisateur_id_seq;

CREATE TABLE Utilisateur (
 id         integer NOT NULL DEFAULT nextval('Utilisateur_id_seq'),
 login      VARCHAR( 50 ) NOT NULL UNIQUE,
 nom        VARCHAR( 100 ),
 prenom     VARCHAR( 100 ),
 password   VARCHAR( 500 ),
 PRIMARY KEY ( id ) 
);

ALTER SEQUENCE Utilisateur_id_seq
OWNED BY Utilisateur.id;