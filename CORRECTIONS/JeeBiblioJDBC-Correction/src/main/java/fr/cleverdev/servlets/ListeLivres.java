package fr.cleverdev.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cleverdev.dao.DaoException;
import fr.cleverdev.dao.DaoFactory;
import fr.cleverdev.dao.LivreDao;

/**
 * Servlet implementation class ListeAuteurs
 */
@WebServlet("/listeLivres")
public class ListeLivres extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LivreDao livreDao;

    public ListeLivres() {
        super();
        livreDao = DaoFactory.getInstance().getLivreDao();
    }


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			request.setAttribute("livres", livreDao.lister());
		} catch (DaoException e) {
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/listeLivres.jsp").forward(request, response);

	}


}
