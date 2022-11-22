package fr.cleverdev.dao;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityTransaction;

import fr.cleverdev.models.Auteur;

public class ExempleAuteurDaoImpl {

	private DaoFactory factory;

	protected ExempleAuteurDaoImpl() {
		this.factory = DaoFactory.getInstance();
	}

	
	public Auteur trouver(long id) throws DaoException {
		Auteur            auteur=null;
		try {
			auteur = factory.getEntityManager().find(Auteur.class, id);
		} catch(Exception e) {
			throw new DaoException("ErreurDAO");
		} finally {
			factory.releaseEntityManager();
		}
		return auteur;
	}

	public List<Auteur> lister() throws DaoException {
		List<Auteur> listeAuteurs = new ArrayList<>();
		try {
			listeAuteurs = factory.getEntityManager().createQuery("From Auteur", Auteur.class).getResultList();
		} catch(Exception e) {
			throw new DaoException("ErreurDAO");
		} finally {
			factory.releaseEntityManager();
		}
		return listeAuteurs;
	}
	

	
	public void save(Auteur auteur) throws DaoException { //Remplace Create et Update
		EntityTransaction entityTransaction = null;
		try {
			entityTransaction = factory.getEntityManager().getTransaction();
			entityTransaction.begin();
			
			if(auteur.getId() != null ) {
				factory.getEntityManager().merge(auteur);
			} else {
				factory.getEntityManager().persist(auteur);
			}
			
			entityTransaction.commit();
		} catch(Exception e) {
			if(entityTransaction != null)
				entityTransaction.rollback();
			e.printStackTrace();
			
			throw new DaoException("ErreurDAO");
		} finally {
			factory.releaseEntityManager();
		}
	}
	

	public void create(Auteur auteur) throws DaoException {
		EntityTransaction entityTransaction = null;
		try {
			entityTransaction = factory.getEntityManager().getTransaction();
			entityTransaction.begin();
			
			factory.getEntityManager().persist(auteur);
			
			entityTransaction.commit();
		} catch(Exception e) {
			if(entityTransaction != null)
				entityTransaction.rollback();
			e.printStackTrace();
			
			throw new DaoException("ErreurDAO");
		} finally {
			factory.releaseEntityManager();
		}
	}

	public void update(Auteur auteur) throws DaoException {
		EntityTransaction entityTransaction = null;
		try {
			entityTransaction = factory.getEntityManager().getTransaction();
			entityTransaction.begin();
			
			factory.getEntityManager().merge(auteur);
			
			entityTransaction.commit();
		} catch(Exception e) {
			if(entityTransaction != null)
				entityTransaction.rollback();
			e.printStackTrace();
			
			throw new DaoException("ErreurDAO");
		} finally {
			factory.releaseEntityManager();
		}
	}

	public void supprimer(long id) throws DaoException {
		EntityTransaction entityTransaction = null;
		try {
			entityTransaction = factory.getEntityManager().getTransaction();
			entityTransaction.begin();
			
			Auteur auteur = factory.getEntityManager().find(Auteur.class, id);
			factory.getEntityManager().remove(auteur);
			
			entityTransaction.commit();
		} catch(Exception e) {
			if(entityTransaction != null)
				entityTransaction.rollback();
			e.printStackTrace();
			
			throw new DaoException("ErreurDAO");
		} finally {
			factory.releaseEntityManager();
		}
	}




}
