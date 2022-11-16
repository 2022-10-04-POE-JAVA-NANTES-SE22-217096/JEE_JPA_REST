package fr.cleverdev.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Configuration;

/**
 * Servlet implementation class Connection
 */
@WebServlet("/connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		if(login.equals(Configuration.getConfig("user_login")) && password.equals(Configuration.getConfig("user_password"))) {
			Cookie cookie = new Cookie("authentificated", "connected");
			cookie.setMaxAge(3600);
			
			response.addCookie(cookie);
			response.sendRedirect( request.getContextPath() + "/" );
		} else {
			request.setAttribute("loginMessage", "Identifiants incorrects...");
			this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
		}
		
		
	}

}
