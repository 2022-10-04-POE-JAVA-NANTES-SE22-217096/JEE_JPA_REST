package fr.cleverdev.servlets;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class TestFilter
 */
@WebFilter("/*")
public class AuthentificationFilter extends HttpFilter {

	private static final long serialVersionUID = 1L;
	

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		if(!request.getRequestURI().contains("/connexion") && !request.getRequestURI().contains("/inc/")) {
			boolean isConnected = false;
			
			if(request.getCookies() != null) {
				for(Cookie cookie : request.getCookies()) {
					if(cookie.getName().equals("authentificated")) {
						 isConnected = true;
						 break;
					}
				}
			}
			
			if(!isConnected) {
				response.sendRedirect( request.getContextPath() + "/connexion" );
				return;
			}
		}
		
		chain.doFilter(request, response);	
	}


}
