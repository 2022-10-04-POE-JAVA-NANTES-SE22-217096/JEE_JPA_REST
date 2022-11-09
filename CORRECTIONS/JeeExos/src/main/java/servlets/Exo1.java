package servlets;

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
 * Servlet implementation class Exo1
 */
@WebServlet("/exo1")
public class Exo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<String> fruits = new ArrayList<String>(Arrays.asList("Poire", "Pomme", "Orange", "Banane"));
		
		request.setAttribute("liste", fruits);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/Exo1.jsp").forward(request, response);
	}


}
