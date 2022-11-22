package fr.cleverdev.servlets;


import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cleverdev.models.Auteur;

/**
 * Servlet implementation class Launcher
 */
@WebServlet("/Exemples")
public class Exemples extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EntityManagerFactory entityManagerFactory;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Exemples() {
        super();
        entityManagerFactory = Persistence.createEntityManagerFactory("biblio");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		EntityManager entityManager = entityManagerFactory.createEntityManager();

		//Création Entité
		EntityTransaction entityTransaction = null;
		try {
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			Auteur newAuteur = new Auteur();
			newAuteur.setNom("Toto");
			newAuteur.setTelephone("0606060606");
			entityManager.persist(newAuteur); //Insert into Auteur(nom) Values ("Toto");
			newAuteur.setPrenom("060606060"); // UPDATE auteur SET prenom="0606060606gdfgfdgfdg" WHERE id = 1

			Auteur newAuteur2 = new Auteur();
			newAuteur2.setNom("Test");
			newAuteur2.setTelephone("0606060606");
			entityManager.persist(newAuteur2);

			entityTransaction.commit();
		} catch(Exception e) {
			e.printStackTrace();
			if(entityTransaction!=null)
				entityTransaction.rollback();
		}

		
		//Récupération et détachement auteur
		Auteur auteur = entityManager.find(Auteur.class, 1L); //Select * From Auteur Where id=1;
		entityManager.detach(auteur);

		
		//Modification entité
		entityTransaction = null;
		try {
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			auteur.setNom("NEWTest");

			Auteur auteurMerged = entityManager.merge(auteur); //Sauvegarde auteur
			
			entityTransaction.commit();
		} catch(Exception e) {
			e.printStackTrace();
			if(entityTransaction!=null)
				entityTransaction.rollback();
		}

		//Récupération et détachement auteur
		auteur = entityManager.find(Auteur.class, 2L); //Select * From Auteur Where id=1;
		entityManager.detach(auteur);
		
		
		//Suppression entité
		entityTransaction = null;
		try {
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();

			Auteur auteurMerged = entityManager.merge(auteur);
			//entityManager.remove(auteurMerged);

			entityTransaction.commit();
		} catch(Exception e) {
			e.printStackTrace();
			if(entityTransaction!=null)
				entityTransaction.rollback();
		}


		//Récupération d'éléments
		//Liste élément
		Query query = entityManager.createQuery("From Auteur"); //Ou "Select a From Auteur a"
		List<Auteur> auteurs = query.getResultList();
		for(Auteur a : auteurs) {
			System.out.println(a.getNom());
		}
		
		
		//Récupérer un élément avec paramètre
		query = entityManager.createQuery("From Auteur Where id=:id"); //Ou "Select a From Auteur a"
		query.setParameter("id", 1L); 
		Auteur auteur2 = (Auteur) query.getSingleResult();
		System.out.println(auteur2.getNom());
		
		
		//Requete en SQL (pas conseillé !!!)
		auteurs = entityManager.createNativeQuery("Select * From Auteur", Auteur.class).getResultList();
		for(Auteur a : auteurs) {
			System.out.println(a.getNom());
		}

		entityManager.close();
	}

}
