package fr.cleverdev.dao;

import java.util.List;

import fr.cleverdev.model.Livre;


public interface LivreDao {

	void         creer( Livre livre ) throws DaoException;

	Livre       trouver( long id ) throws DaoException;

    List<Livre> lister() throws DaoException;

    void         supprimer( long id ) throws DaoException;

    void         update( Livre livre ) throws DaoException;

}
