package fr.cleverdev.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cleverdev.dao.LivreDao;
import fr.cleverdev.dao.AuteurDao;
import fr.cleverdev.dao.DaoException;
import fr.cleverdev.dao.DaoFactory;
import fr.cleverdev.model.Livre;

/**
 * Servlet implementation class AjouterLivre
 */
@WebServlet("/ajouterLivre")
public class AjouterLivre extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private LivreDao livreDao;
	private AuteurDao auteurDao;
	
    public AjouterLivre() {
        super();
        livreDao = DaoFactory.getInstance().getLivreDao();
        auteurDao = DaoFactory.getInstance().getAuteurDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("auteurs", auteurDao.lister());
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterLivre.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long idAuteur = Long.parseLong(request.getParameter("auteurLivre"));
		String titre = request.getParameter("titreLivre");
		int nbPages = Integer.parseInt(request.getParameter("nbPagesLivre"));
		String categorie = request.getParameter("categorieLivre");
	
		try {
			Livre livre = new Livre();
			livre.setTitre(titre);
			livre.setNbPages(nbPages);
			livre.setCategorie(categorie);
			livre.setAuteur(auteurDao.trouver(idAuteur));
			
			livreDao.creer(livre);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect( request.getContextPath() + "/listeLivres" );		
	}

}
