package fr.cleverdev.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.cleverdev.dao.AuteurDao;
import fr.cleverdev.dao.DaoException;
import fr.cleverdev.dao.DaoFactory;
import fr.cleverdev.model.Auteur;

/**
 * Servlet implementation class AjouterAuteur
 */
@WebServlet("/ajouterAuteur")
public class AjouterAuteur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private AuteurDao auteurDao;
	
    public AjouterAuteur() {
        super();
        auteurDao = DaoFactory.getInstance().getAuteurDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterAuteur.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, String> erreurs = new HashMap<String, String>();
		
		String nom = request.getParameter("nomAuteur");
		String prenom = request.getParameter("prenomAuteur");
		String telephone = request.getParameter("telephoneAuteur");
		String email = request.getParameter("emailAuteur");
		
		
		//Ajout des contrôles
		if(nom != null) {
			if(nom.length() < 2 || nom.length() > 20) {
				erreurs.put("nomAuteur", "Un nom d'auteur doit contenir entre 2 et 20 caractères.");
			}
		} else {
			erreurs.put("nomAuteur", "Merci d'entrer un nom d'auteur.");
		}
		
		if(prenom != null) {
			if(prenom.length() > 20) {
				erreurs.put("prenomAuteur", "Un prénom d'auteur doit avoir maximum 20 caractères.");
			}
		}
		
		if(telephone != null) {
			if(telephone.length() > 10) {
				erreurs.put("telephoneAuteur", "Un numéro de téléphone doit avoir maximum 10 caractères.");
			}
			if(!telephone.matches("^\\d+$")) {
				erreurs.put("telephoneAuteur", "Un numéro de téléphone doit contenir uniquement des chiffres.");
			}
		} else {
			erreurs.put("telephoneAuteur", "Merci d'entrer un numéro de téléphone.");
		}
		
		if(email != null) {
			if(email.length() > 60) {
				erreurs.put("emailAuteur", "Un email doit avoir maximum 60 caractères.");
			}
			if(!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				erreurs.put("emailAuteur", "Merci d'entrer une adresse email valide.");
			}
		}
		
		Auteur auteur = new Auteur();
		auteur.setNom(nom);
		auteur.setPrenom(prenom);
		auteur.setTelephone(telephone);
		auteur.setEmail(email);
		
		if(erreurs.isEmpty()) {
			try {
				auteurDao.creer(auteur);
				request.getSession().setAttribute("confirmMessage", "L'auteur a bien été ajouté !");
				
				response.sendRedirect( request.getContextPath() + "/listeAuteurs" );
			} catch (DaoException e) {
				e.printStackTrace();
			}
		} else {
			request.setAttribute("auteur", auteur);
			request.setAttribute("erreurs", erreurs);

			this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterAuteur.jsp").forward(request, response);
		}		
	}

}
