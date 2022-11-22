package fr.cleverdev.servlets;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cleverdev.dao.DaoException;
import fr.cleverdev.dao.impl.DaoAuteur;
import fr.cleverdev.models.Auteur;

/**
 * Servlet implementation class Launcher
 */
@WebServlet("/ExemplesAvecDao")
public class ExemplesAvecDao extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExemplesAvecDao() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			Auteur newAuteur = new Auteur();
			newAuteur.setNom("Toto");
			newAuteur.setTelephone("0606060606");
			new DaoAuteur().create(newAuteur);
			
			newAuteur.setPrenom("060606060");
			new DaoAuteur().update(newAuteur);

			Auteur newAuteur2 = new Auteur();
			newAuteur2.setNom("Test");
			newAuteur2.setTelephone("0606060606");
			new DaoAuteur().create(newAuteur2);
			
			
			Auteur auteur = new DaoAuteur().find(1L);
			
			auteur.setNom("NEWTest");
			new DaoAuteur().update(auteur);
			
			new DaoAuteur().delete(2L);	

			List<Auteur> auteurs =  new DaoAuteur().list();
			for(Auteur a : auteurs) {
				System.out.println(a.getNom());
			}


			Auteur auteur2 = new DaoAuteur().find(1L);
			System.out.println(auteur2.getNom());
		} catch(DaoException e) {
			e.printStackTrace();
		}


	}

}
