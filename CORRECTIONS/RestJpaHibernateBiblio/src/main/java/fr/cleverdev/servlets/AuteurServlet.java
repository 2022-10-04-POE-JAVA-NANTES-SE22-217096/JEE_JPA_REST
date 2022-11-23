package fr.cleverdev.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import fr.cleverdev.dao.DaoException;
import fr.cleverdev.dao.impl.DaoAuteur;
import fr.cleverdev.models.Auteur;
import fr.cleverdev.utils.Utils;


@WebServlet("/auteur")
public class AuteurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DaoAuteur daoAuteur;
	
    public AuteurServlet() {
        super();
        daoAuteur = new DaoAuteur();
    }


	@Override //Récupération auteur
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		
		int responseStatus = 200;
		String response = "Ok";
		String contentType = "text";
		
		Gson gson = Utils.getSuperJson();
		
		try {
			String idAuteur = req.getParameter("id");
			if(idAuteur != null) {
				Auteur auteur = daoAuteur.find(Long.parseLong(idAuteur));
				response = gson.toJson(auteur);
			} else {
				List<Auteur> auteurs = daoAuteur.list();
				response = gson.toJson(auteurs);
			}
			contentType = "application/json";
		} catch(NumberFormatException e) {
			response = "Le paramètre id n'est pas bon.";
			responseStatus = 400;
		} catch(DaoException e) {
			response = "L'auteur n'a pas été trouvé.";
			responseStatus = 404;
		}
		
		resp.setContentType(contentType);
		resp.setStatus(responseStatus);
		resp.getWriter().write(response);
	}


	@Override //Création auteur
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		
		int responseStatus = 200;
		String response = "Ok";
		String contentType = "text";
		
		try {
			//Récupération du body de la requête sous forme de String
			StringBuffer buffer = new StringBuffer();
			String line = null;
			BufferedReader reader = req.getReader();
			while((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			
			//Récupération d'un objet JAVA représentant un JSON
			JsonObject data = JsonParser.parseString(buffer.toString()).getAsJsonObject();
			
			//Récupération des informations de l'auteur depuis l'objet JSON
			String nom = data.get("nom").getAsString();
			String prenom = data.get("prenom").getAsString();
			String telephone = data.get("telephone").getAsString();
			String email = data.get("email").getAsString();
			
			//Création de l'auteur
			Auteur auteur = new Auteur();
			auteur.setNom(nom);
			auteur.setPrenom(prenom);
			auteur.setTelephone(telephone);
			auteur.setEmail(email);

			//Sauvegarde de l'auteur
			daoAuteur.create(auteur);
		} catch (DaoException e) {
			e.printStackTrace();
			response = "Erreur serveur.";
			responseStatus = 500;
		}
		
		
		resp.setContentType(contentType);
		resp.setStatus(responseStatus);
		resp.getWriter().write(response);
	}

	
	@Override //Modification auteur
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	
	@Override //Suppression auteur
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		
		int responseStatus = 200;
		String response = "Ok";
		String contentType = "text";
		
		try {
			String idAuteur = req.getParameter("id");
			daoAuteur.delete(Long.parseLong(idAuteur));
		} catch(NumberFormatException e) {
			response = "Le paramètre id n'est pas bon.";
			responseStatus = 400;
		} catch(DaoException e) {
			response = "Erreur serveur.";
			responseStatus = 500;
		}
		
		resp.setContentType(contentType);
		resp.setStatus(responseStatus);
		resp.getWriter().write(response);
	}
    
    
    

}
