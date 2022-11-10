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
@WebServlet("/Exo4_2")
public class Exo4_2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//localhost:8080/JeeExos/Exo4?auteurNom=Edgar&auteurPrenom=Poe&auteurTelephone=0606060606&auteurEmail=edgar@poe.fr&livreTitre=Ceci est un test&livreNbPages=80&livreCategorie=Testy		
		
		try {
			Auteur auteur = new Auteur(request.getParameter("auteurNom"), request.getParameter("auteurPrenom"), request.getParameter("auteurTelephone"), request.getParameter("auteurEmail"));
			Livre livre = new Livre(auteur, request.getParameter("livreTitre"), Integer.parseInt(request.getParameter("livreNbPages")), request.getParameter("livreCategorie"));
			
			request.setAttribute("auteur", auteur);
			request.setAttribute("livre", livre);
		} catch(NumberFormatException e) {
			System.out.println("Ce n'est pas un nombre !");
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/Exo4_2.jsp").forward(request, response);
	}


}
