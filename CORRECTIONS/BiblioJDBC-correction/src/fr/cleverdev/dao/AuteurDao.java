package fr.cleverdev.dao;

import java.util.List;

import fr.cleverdev.model.Auteur;

public interface AuteurDao {

	void         creer( Auteur auteur ) throws DaoException;

    Auteur       trouver( long id ) throws DaoException;

    List<Auteur> lister() throws DaoException;

    void         supprimer( long id ) throws DaoException;

    void         update( Auteur auteur ) throws DaoException;

    
}
