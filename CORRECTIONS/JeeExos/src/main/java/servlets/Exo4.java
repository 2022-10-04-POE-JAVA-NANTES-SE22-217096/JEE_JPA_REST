package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import first.Auteur;
import first.Livre;

/**
 * Servlet implementation class Exo4
 */
@WebServlet("/Exo4")
public class Exo4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//localhost:8080/JeeExos/Exo4?auteurNom=Edgar&auteurPrenom=Poe&auteurTelephone=0606060606&auteurEmail=edgar@poe.fr&livreTitre=Ceci est un test&livreNbPages=80&livreCategorie=Testy

		String auteurNom = request.getParameter("auteurNom");
		String auteurPrenom = request.getParameter("auteurPrenom");
		String auteurTelephone = request.getParameter("auteurTelephone");
		String auteurEmail = request.getParameter("auteurEmail");
		String livreTitre = request.getParameter("livreTitre");
		String livreNbPages = request.getParameter("livreNbPages");
		String livreCategorie = request.getParameter("livreCategorie");
		
		int nbPages = Integer.parseInt(livreNbPages);
		
		Auteur auteur = new Auteur(auteurNom, auteurPrenom, auteurTelephone, auteurEmail);
		Livre livre = new Livre(auteur, livreTitre, nbPages, livreCategorie);
		
		request.setAttribute("auteur", auteur);
		request.setAttribute("livre", livre);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/Exo4.jsp").forward(request, response);
	}


}
