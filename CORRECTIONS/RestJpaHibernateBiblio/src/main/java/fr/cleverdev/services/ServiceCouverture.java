package fr.cleverdev.services;

import com.google.gson.JsonObject;

import fr.cleverdev.dao.DaoException;
import fr.cleverdev.dao.impl.DaoCouverture;
import fr.cleverdev.dao.impl.DaoLivre;
import fr.cleverdev.models.Couverture;
import fr.cleverdev.models.Livre;
import fr.cleverdev.utils.Utils;

public class ServiceCouverture {

	private DaoCouverture dao;
	private DaoLivre daoLivre;

	public ServiceCouverture() {
		dao = new DaoCouverture();
		daoLivre = new DaoLivre();
	}


	public String find(long id) throws ServiceException {
		Couverture couverture;

		try {
			couverture = dao.find(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}

		if(couverture == null)
			throw new ServiceException("La couverture n'existe pas. Id : "+id);

		return Utils.getSuperJson().toJson(couverture);
	}


	public String list() throws ServiceException {
		return Utils.getSuperJson().toJson(dao.list());
	}

	public void create(JsonObject data) throws ServiceException {
		String description = null, idLivre = null;
		Livre livre = null;

		try {
			description = Utils.getStringParameter(data, "descriptionCouverture", false, 2, 255);
			idLivre = Utils.getStringParameter(data, "idLivre", true, 0, 50, "^\\d+$");

			if(idLivre != null) {
				livre = daoLivre.find(Long.parseLong(idLivre));
				if(livre == null)
					throw new ServiceException("Le livre n'existe pas. Id : "+idLivre);

				if(livre.getCouverture() != null)
					throw new ServiceException("Le livre est déja associé é la couverture d'id : "+livre.getCouverture().getId());
			}

			Couverture couverture = new Couverture();
			couverture.setDescription(description);
			couverture.setLivre(livre);

			dao.create(couverture);

			if(livre != null) {
				livre.setCouverture(couverture);
				daoLivre.update(livre);
			}
		} catch (DaoException e) {
			throw new ServiceException("Erreur DAO.");
		}
	}

	public void update(JsonObject data) throws ServiceException {
		String id = null, description = null, idLivre = null;
		Livre livre = null;

		try {
			id = Utils.getStringParameter(data, "idCouverture", false, 0, 50, "^\\d+$");
			description = Utils.getStringParameter(data, "descriptionCouverture", false, 2, 255);
			idLivre = Utils.getStringParameter(data, "idLivre", true, 0, 50, "^\\d+$");

			Couverture couverture = dao.find(Long.parseLong(id));
			if(couverture == null)
				throw new ServiceException("La couverture n'existe pas. Id : "+id);


			if(idLivre != null) {
				livre = daoLivre.find(Long.parseLong(idLivre));
				if(livre == null)
					throw new ServiceException("Le livre n'existe pas. Id : "+idLivre);

				if(livre.getCouverture() != null && livre.getCouverture().getId() != Long.parseLong(id))
					throw new ServiceException("Le livre est déja associé é la couverture d'id : "+livre.getCouverture().getId());
			} else {
				if(couverture.getLivre() != null) {
					Livre livreOld = couverture.getLivre();
					livreOld.setCouverture(null);
					daoLivre.update(livreOld);
				}
			}

			couverture.setDescription(description);
			couverture.setLivre(livre);

			dao.update(couverture);


			if(livre != null) {
				livre.setCouverture(couverture);
				daoLivre.update(livre);
			}

		} catch(NumberFormatException e) {
			throw new ServiceException("Le format du paramétre idCouverture n'est pas bon.");
		} catch (DaoException e) {
			throw new ServiceException("Erreur DAO.");
		}
	}

	public void delete(long id) throws ServiceException {
		try {
			dao.delete(id);
		} catch (DaoException e) {
			throw new ServiceException("La couverture n'existe pas. Id : "+id);
		}
	}

}
