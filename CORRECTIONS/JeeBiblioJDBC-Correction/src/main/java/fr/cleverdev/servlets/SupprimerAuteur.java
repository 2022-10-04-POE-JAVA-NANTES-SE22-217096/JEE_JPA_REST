package fr.cleverdev.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cleverdev.dao.AuteurDao;
import fr.cleverdev.dao.DaoException;
import fr.cleverdev.dao.DaoFactory;


@WebServlet("/supprimerAuteur")
public class SupprimerAuteur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AuteurDao auteurDao;

    public SupprimerAuteur() {
        super();
        auteurDao = DaoFactory.getInstance().getAuteurDao();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			long id = Long.parseLong(request.getParameter("id"));
			auteurDao.supprimer(id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect( request.getContextPath() + "/listeAuteurs" );
	}


}
