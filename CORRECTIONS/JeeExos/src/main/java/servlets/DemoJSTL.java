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
 * Servlet implementation class DemoJSTL
 */
@WebServlet("/demoJSTL")
public class DemoJSTL extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Exo 1 avec JSTL
		List<String> fruits = new ArrayList<String>(Arrays.asList("Poire", "Pomme", "Orange", "Banane"));
		request.setAttribute("fruits", fruits);
		
		//Exo 2 avec JSTL
		request.setAttribute("isGood", request.getParameter("isGood"));
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/DemoJSTL.jsp").forward(request, response);
	}


}
