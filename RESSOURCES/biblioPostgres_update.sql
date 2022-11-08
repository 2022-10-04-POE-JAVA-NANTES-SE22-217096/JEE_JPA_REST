CREATE SEQUENCE Page_id_seq;

CREATE TABLE Page (
 id         integer NOT NULL DEFAULT nextval('Page_id_seq'),
 id_livre  INT ,
 texte      VARCHAR( 500 ) NOT NULL ,
 nb_lignes   INT NOT NULL ,
 police  VARCHAR( 20 ),
 PRIMARY KEY ( id ) ,
 CONSTRAINT fk_id_livre    
    FOREIGN KEY (id_livre)  
    REFERENCES livre(id)  
    ON DELETE SET NULL    
);

ALTER SEQUENCE Page_id_seq
OWNED BY Page.id;

INSERT INTO Page (id_livre, texte, nb_lignes, police) VALUES
(1, 'Un, Dos, Tres', 7800, 'Arial'),
(1, 'Un, Deux, Trois', 6400, 'Time New Roman'),
(2, 'One, Two, Three', 65, 'Arial'),
(2, 'Mäuse, Gitarren, Delphine', 1200, 'Arial Black'),
(3, 'Bir, Iki, Üç', 5400, 'Comic Sans'),
(4, 'Eén, Twee, Drie', 1840, 'MS'),
(4, 'Adin, Dva, Tri', 3500, 'Time New Roman'),
(5, 'Um, Dois, Três', 8420, 'Arial');