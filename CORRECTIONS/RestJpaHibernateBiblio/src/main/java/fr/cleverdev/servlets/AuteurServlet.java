package fr.cleverdev.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import fr.cleverdev.dao.DaoException;
import fr.cleverdev.dao.impl.DaoAuteur;
import fr.cleverdev.models.Auteur;


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
		
		Gson gson = new Gson();
		
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

	}

	
	@Override //Modification auteur
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	
	@Override //Suppression auteur
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
    
    
    

}
