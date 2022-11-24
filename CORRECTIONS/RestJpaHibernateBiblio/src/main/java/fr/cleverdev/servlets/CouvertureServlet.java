package fr.cleverdev.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import fr.cleverdev.services.ServiceCouverture;
import fr.cleverdev.services.ServiceException;
import fr.cleverdev.utils.Utils;

/**
 * Servlet implementation class CouvertureServlet
 */
@WebServlet("/couverture")
public class CouvertureServlet extends HttpServlet {
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
			String idCouverture = request.getParameter("idCouverture");
			if(idCouverture != null) {
				Long id = Long.parseLong(idCouverture);
				if(id > 0) {
					responseContent = new ServiceCouverture().find(id);
					contentType = "application/json";
				} else {
					responseStatus = 400;
					responseContent = "Erreur : L'idCouverture doit �tre strictement sup�rieur � 0.";
				}
			} else {
				responseContent = new ServiceCouverture().list();
				contentType = "application/json";
			}
		} catch(NumberFormatException e) {
			responseStatus = 400;
			responseContent = "Erreur : Le format du param�tre idCouverture n'est pas bon.";
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

			new ServiceCouverture().create(data);

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
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String responseContent="Ok", contentType = "text";
		int responseStatus = 200;

		try {
			JsonObject data = Utils.getJsonFromBuffer(request);

			new ServiceCouverture().update(data);

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
			String idCouverture = request.getParameter("idCouverture");
			if(idCouverture != null) {
				Long id = Long.parseLong(idCouverture);
				if(id > 0) {
					new ServiceCouverture().delete(id);
					responseContent = "Suppression couverture OK.";
				} else {
					responseStatus = 400;
					responseContent = "Erreur : L'idCouverture doit �tre strictement sup�rieur � 0.";
				}
			} else {
				responseStatus = 400;
				responseContent = "Erreur : Le param�tre idCouverture est obligatoire.";
			}
		} catch(ServiceException e) {
			responseStatus = 400;
			responseContent = "Erreur : " +e.getMessage();
		} catch(NumberFormatException e) {
			responseStatus = 400;
			responseContent = "Erreur : Le format du param�tre idCouverture n'est pas bon.";
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
