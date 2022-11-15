package fr.cleverdev.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cleverdev.dao.LivreDao;
import fr.cleverdev.dao.DaoException;
import fr.cleverdev.dao.DaoFactory;


@WebServlet("/supprimerLivre")
public class SupprimerLivre extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LivreDao livreDao;

    public SupprimerLivre() {
        super();
        livreDao = DaoFactory.getInstance().getLivreDao();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			long id = Long.parseLong(request.getParameter("id"));
			livreDao.supprimer(id);
			
			//Ajout d'un élément dans la session
			request.getSession().setAttribute("confirmMessage", "L'auteur a bien été supprimé !");
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect( request.getContextPath() + "/listeLivres" );
	}


}
