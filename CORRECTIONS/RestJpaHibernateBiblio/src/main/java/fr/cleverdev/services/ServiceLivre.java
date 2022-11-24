package fr.cleverdev.services;

import com.google.gson.JsonObject;

import fr.cleverdev.dao.DaoException;
import fr.cleverdev.dao.impl.DaoAuteur;
import fr.cleverdev.dao.impl.DaoCouverture;
import fr.cleverdev.dao.impl.DaoGenre;
import fr.cleverdev.dao.impl.DaoLivre;
import fr.cleverdev.models.Auteur;
import fr.cleverdev.models.Couverture;
import fr.cleverdev.models.Genre;
import fr.cleverdev.models.Livre;
import fr.cleverdev.utils.Utils;

public class ServiceLivre {

	private DaoLivre dao;
	private DaoAuteur daoAuteur;
	private DaoCouverture daoCouverture;
	private DaoGenre daoGenre;

	public ServiceLivre() {
		dao = new DaoLivre();
		daoAuteur = new DaoAuteur();
		daoCouverture = new DaoCouverture();
		daoGenre = new DaoGenre();
	}


	public String find(long id) throws ServiceException {
		Livre livre;

		try {
			livre = dao.find(id);
		} catch (DaoException e) {
			throw new ServiceException("Le livre n'existe pas. Id : "+id);
		}

		return Utils.getSuperJson().toJson(livre);
	}


	public String list() throws ServiceException {
		return Utils.getSuperJson().toJson(dao.list());
	}

	public void create(JsonObject data) throws ServiceException {
		String titre = null, nbPages = null, idAuteur = null, idCouverture = null;
		Auteur auteur = null;
		Couverture couverture = null;

		try {
			titre = Utils.getStringParameter(data, "titreLivre", false, 2, 255);
			nbPages = Utils.getStringParameter(data, "nbPagesLivre", false,1, 255, "^\\d+$");
			idAuteur = Utils.getStringParameter(data, "idAuteur", true, 0, 50, "^\\d+$");
			idCouverture = Utils.getStringParameter(data, "idCouverture", true, 0, 50, "^\\d+$");

			if(idAuteur != null) {
				auteur = daoAuteur.find(Long.parseLong(idAuteur));
				if(auteur == null)
					throw new ServiceException("L'auteur n'existe pas. Id : "+idAuteur);
			}

			if(idCouverture != null) {
				couverture = daoCouverture.find(Long.parseLong(idCouverture));
				if(couverture == null)
					throw new ServiceException("La couverture n'existe pas. Id : "+idCouverture);

				if(couverture.getLivre() != null)
					throw new ServiceException("La couverture est déja associé au livre d'id : "+couverture.getLivre().getId());
			}

			Livre livre = new Livre();
			livre.setTitre(titre);
			livre.setNbPages(Integer.parseInt(nbPages));
			livre.setAuteur(auteur);
			livre.setCouverture(couverture);

			dao.create(livre);


		} catch (DaoException e) {
			throw new ServiceException("Erreur DAO.");
		}
	}

	public void update(JsonObject data) throws ServiceException {
		String id = null, titre = null, nbPages = null, idAuteur = null, idCouverture = null;
		Auteur auteur = null;
		Couverture couverture = null;

		try {
			id = Utils.getStringParameter(data, "idLivre", false, 0, 50, "^\\d+$");
			titre = Utils.getStringParameter(data, "titreLivre", false, 2, 255);
			nbPages = Utils.getStringParameter(data, "nbPagesLivre", false,1, 255, "^\\d+$");
			idAuteur = Utils.getStringParameter(data, "idAuteur", true, 0, 50, "^\\d+$");
			idCouverture = Utils.getStringParameter(data, "idCouverture", true, 0, 50, "^\\d+$");


			if(idAuteur != null) {
				auteur = daoAuteur.find(Long.parseLong(idAuteur));
				if(auteur == null)
					throw new ServiceException("L'auteur n'existe pas. Id : "+idAuteur);
			}

			if(idCouverture != null) {
				couverture = daoCouverture.find(Long.parseLong(idCouverture));
				if(couverture == null)
					throw new ServiceException("La couverture n'existe pas. Id : "+idCouverture);

				if(couverture.getLivre() != null && couverture.getLivre().getId() != Long.parseLong(id))
					throw new ServiceException("La couverture est déja associé au livre d'id : "+couverture.getLivre().getId());
			}


			Livre livre = dao.find(Long.parseLong(id));
			if(livre == null)
				throw new ServiceException("Le livre n'existe pas. Id : "+id);

			livre.setTitre(titre);
			livre.setNbPages(Integer.parseInt(nbPages));
			livre.setAuteur(auteur);
			livre.setCouverture(couverture);

			dao.update(livre);
		} catch(NumberFormatException e) {
			throw new ServiceException("Le format du paramétre idLivre n'est pas bon.");
		} catch (DaoException e) {
			throw new ServiceException("Erreur DAO.");
		}
	}

	public void delete(long id) throws ServiceException {
		try {
			dao.delete(id);
		} catch (DaoException e) {
			throw new ServiceException("Le livre n'existe pas. Id : "+id);
		}
	}


	public void addGenre(long idLivre, long idGenre) throws ServiceException {
		Livre livre;
		try {
			livre = dao.find(idLivre);
		} catch (DaoException e1) {
			throw new ServiceException("Le livre n'existe pas. Id : "+idLivre);
		}

		Genre genre;
		try {
			genre = daoGenre.find(idGenre);
		} catch (DaoException e1) {
			throw new ServiceException("Le genre n'existe pas. Id : "+idGenre);
		}

		if(livre.getGenres().contains(genre))
			throw new ServiceException("Le genre est déja associé au livre.");


		try {
			livre.addGenre(genre);
			dao.update(livre);
		} catch (DaoException e) {
			throw new ServiceException("Erreur DAO.");
		}
	}

	public void removeGenre(long idLivre, long idGenre) throws ServiceException {
		Livre livre;
		try {
			livre = dao.find(idLivre);
		} catch (DaoException e1) {
			throw new ServiceException("Le livre n'existe pas. Id : "+idLivre);
		}

		Genre genre;
		try {
			genre = daoGenre.find(idGenre);
		} catch (DaoException e1) {
			throw new ServiceException("Le genre n'existe pas. Id : "+idGenre);
		}

		if(!livre.getGenres().contains(genre))
			throw new ServiceException("Le genre n'est pas associé au livre.");

		try {
			livre.removeGenre(genre);
			dao.update(livre);
		} catch (DaoException e) {
			throw new ServiceException("Erreur DAO.");
		}
	}

}
