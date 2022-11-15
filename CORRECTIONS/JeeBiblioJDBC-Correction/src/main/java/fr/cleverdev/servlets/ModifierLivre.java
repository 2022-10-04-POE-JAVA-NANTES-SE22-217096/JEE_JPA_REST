package fr.cleverdev.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cleverdev.dao.LivreDao;
import fr.cleverdev.dao.AuteurDao;
import fr.cleverdev.dao.DaoException;
import fr.cleverdev.dao.DaoFactory;
import fr.cleverdev.model.Auteur;
import fr.cleverdev.model.Livre;

/**
 * Servlet implementation class AjouterLivre
 */
@WebServlet("/modifierLivre")
public class ModifierLivre extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private LivreDao livreDao;
	private AuteurDao auteurDao;
	
    public ModifierLivre() {
        super();
        livreDao = DaoFactory.getInstance().getLivreDao();
        auteurDao = DaoFactory.getInstance().getAuteurDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			long id = Long.parseLong(request.getParameter("id"));
			request.setAttribute("livre", livreDao.trouver(id));
			request.setAttribute("auteurs", auteurDao.lister());
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/modifierLivre.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, String> erreurs = new HashMap<String, String>();
		
		String titre = request.getParameter("titreLivre");
		String categorie = request.getParameter("categorieLivre");
		long id = Long.parseLong(request.getParameter("id"));
	
		if(titre != null) {
			if(titre.length() < 2 || titre.length() > 50) {
				erreurs.put("titreLivre", "Un titre doit contenir entre 2 et 50 caractères.");
			}
		} else {
			erreurs.put("titreLivre", "Merci d'entrer un titre.");
		}
		
		if(categorie != null) {
			if(categorie.length() > 20) {
				erreurs.put("categorieLivre", "Une catégorie doit avoir au maximum 20 caractères.");
			}
		}
		
		int nbPages = 0;
		try {
			nbPages = Integer.parseInt(request.getParameter("nbPagesLivre"));
		} catch (NumberFormatException e) {
			erreurs.put("nbPagesLivre", "Merci d'entre un nombre de page (valeur numérique).");
		}

		Auteur auteur = null;
		try {
			long idAuteur = Long.parseLong(request.getParameter("auteurLivre"));
			auteur = auteurDao.trouver(idAuteur);
		} catch (NumberFormatException e) {
			erreurs.put("auteurLivre", "Erreur l'id n'est pas valide.");
		} catch (DaoException e) {
			erreurs.put("auteurLivre", "Erreur l'auteur n'existe pas.");
		}
		
		
		Livre livre = null;
		try {
			livre = livreDao.trouver(id);
		} catch (DaoException e1) {
			livre = new Livre();
			erreurs.put("livre", "Erreur le livre n'existe pas.");
		}
		
		livre.setTitre(titre);
		livre.setNbPages(nbPages);
		livre.setCategorie(categorie);
		livre.setAuteur(auteur);
		
		
		if(erreurs.isEmpty()) {
			try {
				livreDao.update(livre);
				
				request.getSession().setAttribute("confirmMessage", "Le livre a bien été modifié !");
			} catch (DaoException e) {
				e.printStackTrace();
			}
			
			response.sendRedirect( request.getContextPath() + "/listeLivres" );
		} else {
			try {
				request.setAttribute("auteurs", auteurDao.lister());
			} catch (DaoException e) {
				e.printStackTrace();
			}
			request.setAttribute("livre", livre);
			request.setAttribute("erreurs", erreurs);

			this.getServletContext().getRequestDispatcher("/WEB-INF/modifierLivre.jsp").forward(request, response);
		}	
	}

}
