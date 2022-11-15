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


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			request.setAttribute("auteurs", auteurDao.lister());
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		
		//Exemple Cookie (stocké côté client)
		//Création du cookie
		/*Cookie cookie = new Cookie("nomCookie", "valeurCookie"); //Création du cookie avec 1er paramètre qui est la clé et second la valeur
		cookie.setMaxAge(3600); //Fixer la durée d'expiration du cookie à 3600 secondes soit 1h
		response.addCookie(cookie); //Ajout du cookie à la réponse
		//Récupération du cookie
		if(request.getCookies() != null) {
			for(Cookie c : request.getCookies()) {
				if(c.getName().equals("nomCookie")) {
					System.out.println(c.getValue());
				}
			}
		}*/
		
		
		//Exemple Session
		//Ajout d'un élément dans la session
		/*request.getSession().setAttribute("confirmMessage", "L'auteur a bien été modifié !");
		//Lecture de la session
		if(request.getSession().getAttribute("confirmMessage") != null) {//On verifie si l'attribut existe
			String confirmMessage = request.getSession().getAttribute("confirmMessage").toString();
			request.setAttribute("confirmMessage", confirmMessage);
			request.getSession().removeAttribute("confirmMessage");//On efface l'attribut de la session
		}*/

		this.getServletContext().getRequestDispatcher("/WEB-INF/listeAuteurs.jsp").forward(request, response);

		request.getSession().removeAttribute("confirmMessage");
	}


}
