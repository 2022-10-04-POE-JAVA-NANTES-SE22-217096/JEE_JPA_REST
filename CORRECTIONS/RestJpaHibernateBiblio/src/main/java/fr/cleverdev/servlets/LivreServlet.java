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
import fr.cleverdev.services.ServiceLivre;
import fr.cleverdev.utils.Utils;

/**
 * Servlet implementation class LivreServlet
 */
@WebServlet("/livre")
public class LivreServlet extends HttpServlet {
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
			String idLivre = request.getParameter("idLivre");
			if(idLivre != null) {
				Long id = Long.parseLong(idLivre);
				if(id > 0) {
					responseContent = new ServiceLivre().find(id);
					contentType = "application/json";
				} else {
					responseStatus = 400;
					responseContent = "Erreur : L'idLivre doit �tre strictement sup�rieur � 0.";
				}
			} else {
				responseContent = new ServiceLivre().list();
				contentType = "application/json";
			}
		} catch(NumberFormatException e) {
			responseStatus = 400;
			responseContent = "Erreur : Le format du param�tre idLivre n'est pas bon.";
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

			new ServiceLivre().create(data);

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

			new ServiceLivre().update(data);

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
			String idLivre = request.getParameter("idLivre");
			if(idLivre != null) {
				Long id = Long.parseLong(idLivre);
				if(id > 0) {
					new ServiceLivre().delete(id);
					responseContent = "Suppression livre OK.";
				} else {
					responseStatus = 400;
					responseContent = "Erreur : L'idLivre doit �tre strictement sup�rieur � 0.";
				}
			} else {
				responseStatus = 400;
				responseContent = "Erreur : Le param�tre idLivre est obligatoire.";
			}
		} catch(ServiceException e) {
			responseStatus = 400;
			responseContent = "Erreur : " +e.getMessage();
		} catch(NumberFormatException e) {
			responseStatus = 400;
			responseContent = "Erreur : Le format du param�tre idLivre n'est pas bon.";
		} catch(Exception e) {
			e.printStackTrace();
			responseStatus = 500;
			responseContent = "Erreur : Erreur serveur.";
		}

		response.setContentType(contentType);
		response.setStatus(responseStatus);
		response.getWriter().write(responseContent);
	}


	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		if (request.getMethod().equalsIgnoreCase("PATCH")){

			String responseContent="Ok", contentType = "text";
			int responseStatus = 200;

			try {
				String action = request.getParameter("action");
				if(action != null && (action.equals("addGenre") || action.equals("removeGenre"))) {
					String idLivre = request.getParameter("idLivre");
					String idGenre = request.getParameter("idGenre");
					if(idLivre != null && idGenre != null) {

						Long idLivreParse = Long.parseLong(idLivre);
						Long idGenreParse = Long.parseLong(idGenre);

						if(idLivreParse > 0 && idGenreParse > 0) {

							if(action.equals("addGenre")) {
								new ServiceLivre().addGenre(idLivreParse, idGenreParse);
								responseContent = "Le genre a bien �t� ajout� au livre.";
							} else {
								new ServiceLivre().removeGenre(idLivreParse, idGenreParse);
								responseContent = "Le genre a bien �t� supprim� du livre.";
							}
						} else {
							responseStatus = 400;
							responseContent = "Erreur : L'idLivre et l'idGenre doit �tre strictement sup�rieur � 0.";
						}
					} else {
						responseStatus = 400;
						responseContent = "Erreur : Le param�tre idLivre et idGenre est obligatoire.";
					}
				} else {
					responseStatus = 400;
					responseContent = "Erreur : Le param�tre action est obligatoire. 2 valeurs possibles : addGenre et removeGenre";
				}
			} catch(NumberFormatException e) {
				responseStatus = 400;
				responseContent = "Erreur : Le format du param�tre idLivre ou idGenre n'est pas bon.";
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

        } else {
            super.service(request, response);
        }
	}

}
