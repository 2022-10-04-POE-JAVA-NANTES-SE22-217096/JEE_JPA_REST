package fr.cleverdev.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import fr.cleverdev.services.ServiceException;
import fr.cleverdev.services.ServiceGenre;
import fr.cleverdev.utils.Utils;

/**
 * Servlet implementation class GenreServlet
 */
@WebServlet("/genre")
public class GenreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String responseContent="Ok", contentType = "text";
		int responseStatus = 200;

		try {
			String idGenre = request.getParameter("idGenre");
			if(idGenre != null) {
				Long id = Long.parseLong(idGenre);
				if(id > 0) {
					responseContent = new ServiceGenre().find(id);
					contentType = "application/json";
				} else {
					responseStatus = 400;
					responseContent = "Erreur : L'idGenre doit �tre strictement sup�rieur � 0.";
				}
			} else {
				responseContent = new ServiceGenre().list();
				contentType = "application/json";
			}
		} catch(NumberFormatException e) {
			responseStatus = 400;
			responseContent = "Erreur : Le format du param�tre idGenre n'est pas bon.";
		} catch(ServiceException e) {
			responseStatus = 400;
			responseContent = "Erreur : " +e.getMessage();
		} catch(Exception e) {
			e.printStackTrace();
			responseStatus = 500;
			responseContent = "Erreur : Erreur serveur.";
		}

		response.setContentType(contentType);
		response.setStatus(responseStatus);
		response.getWriter().write(responseContent);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String responseContent="Ok", contentType = "text";
		int responseStatus = 200;

		try {
			JsonObject data = Utils.getJsonFromBuffer(request);

			new ServiceGenre().create(data);

		} catch(JsonSyntaxException e) {
			responseStatus = 400;
			responseContent = "Erreur : Le format des donn�es n'est pas bon, veuillez utiliser du JSON.";
		} catch(ServiceException e) {
			responseStatus = 400;
			responseContent = "Erreur : " +e.getMessage();
		} catch(Exception e) {
			e.printStackTrace();
			responseStatus = 500;
			responseContent = "Erreur : Erreur serveur.";
		}

		response.setContentType(contentType);
		response.setStatus(responseStatus);
		response.getWriter().write(responseContent);	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String responseContent="Ok", contentType = "text";
		int responseStatus = 200;

		try {
			JsonObject data = Utils.getJsonFromBuffer(request);

			new ServiceGenre().update(data);

		} catch(JsonSyntaxException e) {
			responseStatus = 400;
			responseContent = "Erreur : Le format des donn�es n'est pas bon, veuillez utiliser du JSON.";
		} catch(ServiceException e) {
			responseStatus = 400;
			responseContent = "Erreur : " +e.getMessage();
		} catch(Exception e) {
			e.printStackTrace();
			responseStatus = 500;
			responseContent = "Erreur : Erreur serveur.";
		}

		response.setContentType(contentType);
		response.setStatus(responseStatus);
		response.getWriter().write(responseContent);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String responseContent="", contentType = "text";
		int responseStatus = 200;

		try {
			String idGenre = request.getParameter("idGenre");
			if(idGenre != null) {
				Long id = Long.parseLong(idGenre);
				if(id > 0) {
					new ServiceGenre().delete(id);
					responseContent = "Suppression genre OK.";
				} else {
					responseStatus = 400;
					responseContent = "Erreur : L'idGenre doit �tre strictement sup�rieur � 0.";
				}
			} else {
				responseStatus = 400;
				responseContent = "Erreur : Le param�tre idGenre est obligatoire.";
			}
		} catch(ServiceException e) {
			responseStatus = 400;
			responseContent = "Erreur : " +e.getMessage();
		} catch(NumberFormatException e) {
			responseStatus = 400;
			responseContent = "Erreur : Le format du param�tre idGenre n'est pas bon.";
		} catch(Exception e) {
			e.printStackTrace();
			responseStatus = 500;
			responseContent = "Erreur : Erreur serveur.";
		}

		response.setContentType(contentType);
		response.setStatus(responseStatus);
		response.getWriter().write(responseContent);
	}

}
