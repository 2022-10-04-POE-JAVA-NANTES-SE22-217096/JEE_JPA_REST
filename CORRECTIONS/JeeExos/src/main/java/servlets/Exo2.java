package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Exo2
 */
@WebServlet("/exo2")
public class Exo2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("isGood") != null) {
			String result = null;
			
			if(request.getParameter("isGood").equals("0")) {
				result = "Ce n'est pas bon !";
			} else {
				result = "C'est bon !";
			}
			request.setAttribute("result", result);
		}

		
		this.getServletContext().getRequestDispatcher("/WEB-INF/Exo2.jsp").forward(request, response);
	}

}
