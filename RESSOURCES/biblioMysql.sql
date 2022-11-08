CREATE TABLE Auteur (
 id        integer NOT NULL AUTO_INCREMENT,
 nom       VARCHAR( 20 ) NOT NULL ,
 prenom    VARCHAR( 20 ) ,
 telephone VARCHAR( 10 ) NOT NULL,
 email     VARCHAR( 60 ) ,
 PRIMARY KEY ( id )
);

CREATE TABLE Livre (
 id         integer NOT NULL AUTO_INCREMENT,
 id_auteur  INT ,
 titre      VARCHAR( 50 ) NOT NULL ,
 nb_pages   INT     NOT NULL ,
 categorie  VARCHAR( 20 ),
 PRIMARY KEY ( id ) ,
 CONSTRAINT fk_id_auteur    
    FOREIGN KEY (id_auteur)  
    REFERENCES auteur(id)  
    ON DELETE SET NULL    
);

INSERT INTO Auteur (prenom,nom,telephone,email) VALUES
('Bruce', 'Eckel','0605040302', 'thinking@me.net'),
('Antonio', 'Goncalves', '0102030405', null),
('Petter', 'Haggar', '0655443322', 'petharg@hotmail.com'),
('Claude', 'Delannoy', '0677889900', 'claude@delanooy.com');

INSERT INTO Livre (id_auteur, titre, nb_pages, categorie) VALUES
(1, 'Thinking in Java', 320, 'java'),
(1, 'Thinking in C++', 640, 'cpp'),
(2, 'Les cahiers du programmeur Java EE', '240', 'java'),
(2, 'Beginning Java EE 7', 120, 'javaee'),
(3, 'Mieux programmer en Java', 540, 'java'),
(4, 'Exercices en Java', 184, 'java'),
(4, 'Initiation à la programmation', 350, 'algo'),
(4, 'C++ Guide complet', 842, 'cpp');

