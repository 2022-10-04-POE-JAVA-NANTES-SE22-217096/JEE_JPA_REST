package fr.cleverdev.dao;

import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

public abstract class DaoObject<T> {

	private DaoFactory factory;
	private Class<T> className;

	public DaoObject(Class<T> className) {
		this.factory = DaoFactory.getInstance();
		this.className = className;
	}


	public T find(long id) {
		T object = null;

		try {
			object = factory.getEntityManager().find(className, id);

		} catch(NoResultException e) {
			e.printStackTrace();
		} finally {
			factory.releaseEntityManager();
		}

		return object;
	}

	public List<T> list() {
		List<T> objects = null;

		try {
			objects = factory.getEntityManager().createQuery("FROM "+className.getCanonicalName(), className).getResultList();
		} finally {
			factory.releaseEntityManager();
		}

		return objects;
	}

	public void create(T object) throws DaoException {
		EntityTransaction trans = null;

		try {
			trans = factory.getEntityManager().getTransaction();
			trans.begin();

			factory.getEntityManager().persist(object);

			trans.commit();
		} catch(Exception e) {
			if(trans != null)
				trans.rollback();

			e.printStackTrace();
			throw new DaoException("Erreur DAO");
		} finally {
			factory.releaseEntityManager();
		}
	}

	public void update(T object) throws DaoException {
		EntityTransaction trans = null;

		try {
			trans = factory.getEntityManager().getTransaction();
			trans.begin();

			factory.getEntityManager().merge(object);

			trans.commit();
		} catch(Exception e) {
			if(trans != null)
				trans.rollback();

			e.printStackTrace();
			throw new DaoException("Erreur DAO");
		} finally {
			factory.releaseEntityManager();
		}
	}

	public void delete(long id) throws DaoException {
		EntityTransaction trans = null;

		try {
			trans = factory.getEntityManager().getTransaction();
			trans.begin();

			T object = factory.getEntityManager().find(className, id);

			factory.getEntityManager().remove(object);

			trans.commit();
		} catch(NoResultException e) {
			throw new DaoException("Erreur DAO");
		} catch(Exception e) {
			if(trans != null)
				trans.rollback();

			e.printStackTrace();
			throw new DaoException("Erreur DAO");
		} finally {
			factory.releaseEntityManager();
		}
	}


	public DaoFactory getFactory() {
		return factory;
	}
}
