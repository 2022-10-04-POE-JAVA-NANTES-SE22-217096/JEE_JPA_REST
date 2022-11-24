package fr.cleverdev.services;

import com.google.gson.JsonObject;

import fr.cleverdev.dao.DaoException;
import fr.cleverdev.dao.impl.DaoAuteur;
import fr.cleverdev.models.Auteur;
import fr.cleverdev.utils.Utils;

public class ServiceAuteur {

	private DaoAuteur daoAuteur;

	public ServiceAuteur() {
		super();
		daoAuteur = new DaoAuteur();
	}

	public String find(long id) throws ServiceException {
		Auteur auteur;

		try {
			auteur = daoAuteur.find(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}

		return Utils.getSuperJson().toJson(auteur);
	}

	public String list() {
		return Utils.getSuperJson().toJson(daoAuteur.list());
	}

	public void create(JsonObject data) throws ServiceException {

		try {
			//Récupération des informations de l'auteur depuis l'objet JSON
			String nom = Utils.getStringParameter(data, "nom", false, 2, 20);
			String prenom = Utils.getStringParameter(data, "prenom", true, 0, 20);
			String telephone = Utils.getStringParameter(data, "telephone", false, 0, 10, "^\\d+$");
			String email= Utils.getStringParameter(data, "email", true, 0, 60, "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)");

			//Création de l'auteur
			Auteur auteur = new Auteur();
			auteur.setNom(nom);
			auteur.setPrenom(prenom);
			auteur.setTelephone(telephone);
			auteur.setEmail(email);

			//Sauvegarde de l'auteur
			daoAuteur.create(auteur);
		} catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void update(JsonObject data) throws ServiceException {
		try {
			String id = Utils.getStringParameter(data, "nom", false, 0, 50);
			String nom = Utils.getStringParameter(data, "nom", false, 2, 20);
			String prenom = Utils.getStringParameter(data, "prenom", true, 0, 20);
			String telephone = Utils.getStringParameter(data, "telephone", false, 0, 10, "^\\d+$");
			String email= Utils.getStringParameter(data, "email", true, 0, 60, "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)");

			//Création de l'auteur
			Auteur auteur = daoAuteur.find(Long.parseLong(id));
			auteur.setNom(nom);
			auteur.setPrenom(prenom);
			auteur.setTelephone(telephone);
			auteur.setEmail(email);

			//Sauvegarde de l'auteur
			daoAuteur.update(auteur);
		} catch(NumberFormatException e) {
			throw new ServiceException("Le format du paramètre id n'est pas bon.");
		} catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void delete(long id) throws ServiceException {
		try {
			daoAuteur.delete(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}




}
