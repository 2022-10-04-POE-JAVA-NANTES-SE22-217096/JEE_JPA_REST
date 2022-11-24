package fr.cleverdev.services;

import com.google.gson.JsonObject;

import fr.cleverdev.dao.DaoException;
import fr.cleverdev.dao.impl.DaoGenre;
import fr.cleverdev.models.Genre;
import fr.cleverdev.utils.Utils;

public class ServiceGenre {

	private DaoGenre dao;

	public ServiceGenre() {
		dao = new DaoGenre();
	}


	public String find(long id) throws ServiceException {
		Genre genre;

		try {
			genre = dao.find(id);
		} catch (DaoException e) {
			throw new ServiceException("Le genre n'existe pas. Id : "+id);
		}

		return Utils.getSuperJson().toJson(genre);
	}


	public String list() throws ServiceException {
		return Utils.getSuperJson().toJson(dao.list());
	}

	public void create(JsonObject data) throws ServiceException {
		String nom = null;

		try {
			nom = Utils.getStringParameter(data, "nomGenre", false, 2, 255);

			Genre genre = new Genre();
			genre.setNom(nom);

			dao.create(genre);
		} catch (DaoException e) {
			throw new ServiceException("Erreur DAO.");
		}
	}

	public void update(JsonObject data) throws ServiceException {
		String id = null, nom = null;

		try {
			id = Utils.getStringParameter(data, "idGenre", false, 0, 50, "^\\d+$");
			nom = Utils.getStringParameter(data, "nomGenre", false, 2, 255);

			Genre genre = dao.find(Long.parseLong(id));
			if(genre == null)
				throw new ServiceException("Le genre n'existe pas. Id : "+id);

			genre.setNom(nom);

			dao.update(genre);
		} catch(NumberFormatException e) {
			throw new ServiceException("Le format du paramétre idGenre n'est pas bon.");
		} catch (DaoException e) {
			throw new ServiceException("Erreur DAO.");
		}
	}

	public void delete(long id) throws ServiceException {
		try {
			dao.delete(id);
		} catch (DaoException e) {
			throw new ServiceException("Le genre n'existe pas. Id : "+id);
		}
	}

}
