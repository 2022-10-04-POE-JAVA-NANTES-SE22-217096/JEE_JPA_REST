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

/**
 * Servlet implementation class ListeAuteurs
 */
@WebServlet("/listeAuteurs")
public class ListeAuteurs extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AuteurDao auteurDao;

    public ListeAuteurs() {
        super();
        auteurDao = DaoFactory.getInstance().getAuteurDao();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			request.setAttribute("auteurs", auteurDao.lister());
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/listeAuteurs.jsp").forward(request, response);
		
	}


}
