package fr.cleverdev.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import fr.cleverdev.dao.DaoException;
import fr.cleverdev.dao.impl.DaoAuteur;
import fr.cleverdev.dao.impl.DaoCouverture;
import fr.cleverdev.dao.impl.DaoGenre;
import fr.cleverdev.dao.impl.DaoLivre;
import fr.cleverdev.models.Auteur;
import fr.cleverdev.models.Couverture;
import fr.cleverdev.models.Genre;
import fr.cleverdev.models.Livre;
import fr.cleverdev.utils.Utils;


@WebServlet("/livre")
public class LivreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DaoLivre daoLivre;
	private DaoAuteur daoAuteur;
	private DaoCouverture daoCouverture;
	private DaoGenre daoGenre;
	
    public LivreServlet() {
        super();
        daoLivre = new DaoLivre();
        daoAuteur = new DaoAuteur();
        daoCouverture = new DaoCouverture();
        daoGenre = new DaoGenre();
    }


	@Override //Récupération livre
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		
		int responseStatus = 200;
		String response = "Ok";
		String contentType = "text";
		
		Gson gson = Utils.getSuperJson();
		
		try {
			String idLivre = req.getParameter("id");
			if(idLivre != null) {
				Livre livre = daoLivre.find(Long.parseLong(idLivre));
				response = gson.toJson(livre);
			} else {
				List<Livre> livres = daoLivre.list();
				response = gson.toJson(livres);
			}
			contentType = "application/json";
		} catch(NumberFormatException e) {
			response = "Le paramètre id n'est pas bon.";
			responseStatus = 400;
		} catch(DaoException e) {
			response = "L'livre n'a pas été trouvé.";
			responseStatus = 404;
		}
		
		resp.setContentType(contentType);
		resp.setStatus(responseStatus);
		resp.getWriter().write(response);
	}


	@Override //Création livre
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		
		int responseStatus = 200;
		String response = "Ok";
		String contentType = "text";
		
		try {
				
			//Récupération du body de la requête sous forme de String
			StringBuffer buffer = new StringBuffer();
			String line = null, body = "";
			BufferedReader reader = req.getReader();
			while((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			body = buffer.toString();
			
			//Récupération d'un objet JAVA représentant un JSON
			JsonObject data = JsonParser.parseString(body).getAsJsonObject();
			
			//Récupération des informations de l'livre depuis l'objet JSON
			String titre = data.get("titre").getAsString();
			int nbPages = data.get("nbPages").getAsInt();
			String categorie = data.get("categorie").getAsString();
			
			long idAuteur = data.get("idAuteur").getAsLong();
			Auteur auteur = daoAuteur.find(idAuteur);
			
			long idCouverture = data.get("idCouverture").getAsLong();
			Couverture couverture = daoCouverture.find(idCouverture);
			
			List<Genre> genres = new ArrayList<Genre>();
			JsonArray a = data.get("genres").getAsJsonArray();
            for(JsonElement j : a) {
                long idGenre = j.getAsJsonObject().get("id").getAsLong();
                genres.add(daoGenre.find(idGenre));
            }
			
			//Création de l'livre
			Livre livre = new Livre();
			livre.setTitre(titre);
			livre.setNbPages(nbPages);
			livre.setCategorie(categorie);
			livre.setAuteur(auteur);
			livre.setCouverture(couverture);
			livre.setGenres(genres);

			//Sauvegarde de l'livre
			daoLivre.create(livre);
		} catch (JsonSyntaxException e) {
			response = "Erreur : Le format des données n'est pas bon, veuillez utiliser du JSON.";
			responseStatus = 400;
		} catch (DaoException e) {
			e.printStackTrace();
			response = "Erreur serveur.";
			responseStatus = 500;
		}
		
		resp.setContentType(contentType);
		resp.setStatus(responseStatus);
		resp.getWriter().write(response);
	}

	
	@Override //Modification livre
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		
		int responseStatus = 200;
		String response = "Ok";
		String contentType = "text";
		
		try {
				
			//Récupération du body de la requête sous forme de String
			StringBuffer buffer = new StringBuffer();
			String line = null, body = "";
			BufferedReader reader = req.getReader();
			while((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			body = buffer.toString();
			
			//Récupération d'un objet JAVA représentant un JSON
			JsonObject data = JsonParser.parseString(body).getAsJsonObject();
			
			//Récupération des informations de l'livre depuis l'objet JSON
			long id = data.get("id").getAsLong();
			String titre = data.get("titre").getAsString();
			int nbPages = data.get("nbPages").getAsInt();
			String categorie = data.get("categorie").getAsString();
			
			long idAuteur = data.get("idAuteur").getAsLong();
			Auteur auteur = daoAuteur.find(idAuteur);
			
			long idCouverture = data.get("idCouverture").getAsLong();
			Couverture couverture = daoCouverture.find(idCouverture);
			
			List<Genre> genres = new ArrayList<Genre>();
			JsonArray a = data.get("genres").getAsJsonArray();
            for(JsonElement j : a) {
                long idGenre = j.getAsJsonObject().get("id").getAsLong();
                genres.add(daoGenre.find(idGenre));
            }
			
			//Création de l'livre
			Livre livre = daoLivre.find(id);
			livre.setTitre(titre);
			livre.setNbPages(nbPages);
			livre.setCategorie(categorie);
			livre.setAuteur(auteur);
			livre.setCouverture(couverture);
			livre.setGenres(genres);

			//Sauvegarde de l'livre
			daoLivre.update(livre);
		} catch (JsonSyntaxException e) {
			response = "Erreur : Le format des données n'est pas bon, veuillez utiliser du JSON.";
			responseStatus = 400;
		} catch (DaoException e) {
			e.printStackTrace();
			response = "Erreur serveur.";
			responseStatus = 500;
		}
		
		resp.setContentType(contentType);
		resp.setStatus(responseStatus);
		resp.getWriter().write(response);
	}

	
	@Override //Suppression livre
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		
		int responseStatus = 200;
		String response = "Ok";
		String contentType = "text";
		
		try {
			String idLivre = req.getParameter("id");
			daoLivre.delete(Long.parseLong(idLivre));
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
