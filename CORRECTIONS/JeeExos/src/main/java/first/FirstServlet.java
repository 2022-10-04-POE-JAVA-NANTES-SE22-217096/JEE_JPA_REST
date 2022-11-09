package first;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/first")
public class FirstServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//request.getParameter("id")
		
		//request.setAttribute("tata", 25);
		
		/*request.setAttribute("toto", 80);
		request.setAttribute("tata", 25);
		
		Auteur auteur = new Auteur("Edgar", "Poe", "0606060606", "edgar@poe.fr");
		Livre livre = new Livre(auteur, "Testy", 40, "REsfdf");
		request.setAttribute("test", livre);
		
		System.out.println(livre.getId());*/
		
		List<String> liste = new ArrayList<String>(Arrays.asList("Test", "Pitre", "Banane", "Exo"));
		request.setAttribute("maListe", liste);
		

		this.getServletContext().getRequestDispatcher("/WEB-INF/first.jsp").forward(request, response);
	}

}
