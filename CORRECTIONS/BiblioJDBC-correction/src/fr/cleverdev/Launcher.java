package fr.cleverdev;

import java.util.Scanner;

import fr.cleverdev.dao.AuteurDao;
import fr.cleverdev.dao.DaoException;
import fr.cleverdev.dao.DaoFactory;
import fr.cleverdev.dao.LivreDao;
import fr.cleverdev.model.Auteur;
import fr.cleverdev.model.Livre;

public class Launcher {

	public static void main(String[] args) throws DaoException {

		System.out.println("\n----------Gestion de la Bibliothèque----------");
		
	    Scanner sc = new Scanner(System.in);
	    AuteurDao auteurDao = DaoFactory.getInstance().getAuteurDao();
	    LivreDao livreDao = DaoFactory.getInstance().getLivreDao();
	    
	    boolean isReady = true;	    
	    
	    do {
	      System.out.println("\n----MENU----\n"
	      		+ "1 - Lister les auteurs\n"
	      		+ "2 - Afficher le détails d'un auteur\n"
	      		+ "3 - Créer un auteur\n"
	      		+ "4 - Modifier un auteur\n"
	      		+ "5 - Supprimer un auteur\n"
	      		+ "6 - Lister les livres\n"
	      		+ "7 - Afficher le détails d'un livre\n"
	      		+ "8 - Créer un livre\n"
	      		+ "9 - Modifier un livre\n"
	      		+ "10 - Supprimer un livre\n"
	      		+ "Tapez 'q' pour quitter");
	      String response = sc.nextLine(); 
	      if (response.equals("q")) {
	    	  isReady = false;
	      } else {
	    	  try {
		        switch(Integer.parseInt(response)) {
		        	case 1:
		        		System.out.println("\n----Liste des auteurs----\n");
		        		for(Auteur a: auteurDao.lister()) {
		        			System.out.println(a.getId() + " : " + a.getPrenom() + " " + a.getNom());
		        		}
		        		break;
		        	case 2:
		        		System.out.println("\n----Détails d'un auteur----\n");
		        		System.out.println("Quel est l'ID de l'auteur recherché ?");
		        		response = sc.nextLine();
		        		try {
		        			Auteur a = auteurDao.trouver(Long.parseLong(response));
		        			System.out.println(a);
		        		} catch(NumberFormatException e) {
		        			System.out.println("Vous devez entrer un nombre !");
		        		} catch(DaoException e) {
		        			System.out.println("Auteur non trouvé...");
		        		}
		        		break;
		        	case 3:
		        		System.out.println("\n----Création d'un auteur----\n");
		        		try {
			        		System.out.println("Quel est le nom de l'auteur ?");
			        		String nom = sc.nextLine();
			        		System.out.println("Quel est le prenom de l'auteur ?");
			        		String prenom = sc.nextLine();
			        		System.out.println("Quel est le telephone de l'auteur ?");
			        		String telephone = sc.nextLine();
			        		System.out.println("Quel est l'email de l'auteur ?");
			        		String mail = sc.nextLine();
			        		
		        			auteurDao.creer(new Auteur(nom, prenom, telephone, mail));
		        			System.out.println("Auteur crée !");
		        		} catch(DaoException e) {
		        			System.out.println("Erreur : Auteur non crée...");
		        		}
		        		break;
		        	case 4:
		        		System.out.println("\n----Modification d'un auteur----\n");
		        		System.out.println("Quel est l'ID de l'auteur que vous souhaitez modifier ?");
		        		response = sc.nextLine();
		        		try {
			        		System.out.println("Quel est le nom de l'auteur ?");
			        		String nom = sc.nextLine();
			        		System.out.println("Quel est le prenom de l'auteur ?");
			        		String prenom = sc.nextLine();
			        		System.out.println("Quel est le telephone de l'auteur ?");
			        		String telephone = sc.nextLine();
			        		System.out.println("Quel est l'email de l'auteur ?");
			        		String mail = sc.nextLine();
			        		
			        		Auteur a = auteurDao.trouver(Long.parseLong(response));
			        		a.setNom(nom);
			        		a.setPrenom(prenom);
			        		a.setTelephone(telephone);
			        		a.setEmail(mail);
			        		
		        			auteurDao.update(a);
		        			System.out.println("Auteur modifié !");
		        		} catch(NumberFormatException e) {
		        			System.out.println("Vous devez entrer un nombre !");
		        		} catch(DaoException e) {
		        			System.out.println("Erreur : Auteur non modifié...");
		        		}
		        		break;
		        	case 5:
		        		System.out.println("\n----Suppression d'un auteur----\n");
		        		System.out.println("Quel est l'ID de l'auteur recherché ?");
		        		response = sc.nextLine();
		        		try {
		        			auteurDao.supprimer(Long.parseLong(response));
		        			System.out.println("Auteur supprimé !");
		        		} catch(NumberFormatException e) {
		        			System.out.println("Vous devez entrer un nombre !");
		        		} catch(DaoException e) {
		        			System.out.println("Erreur lors de la suppression...");
		        		}
		        		break;
		        	case 6:
		        		System.out.println("\n----Liste des livres----\n");
		        		for(Livre l: livreDao.lister()) {
		        			System.out.println(l.getId() + " : " + l.getTitre() + " de " + l.getAuteur().getPrenom() + " " +  l.getAuteur().getNom());
		        		}
		        		break;
		        	case 7:
		        		System.out.println("\n----Détails d'un livre----\n");
		        		System.out.println("Quel est l'ID u livre recherché ?");
		        		response = sc.nextLine();
		        		try {
		        			Livre l = livreDao.trouver(Long.parseLong(response));
		        			System.out.println(l);
		        		} catch(NumberFormatException e) {
		        			System.out.println("Vous devez entrer un nombre !");
		        		} catch(DaoException e) {
		        			System.out.println("Livre non trouvé...");
		        		}
		        		break;
		        	case 8:
		        		System.out.println("\n----Création d'un livre----\n");
		        		try {
			        		System.out.println("Quel est le nom du livre ?");
			        		String nom = sc.nextLine();
			        		System.out.println("Quel est l'id de l'auteur du livre ?");
			        		long idAuteur = Long.parseLong(sc.nextLine());
			        		System.out.println("Quel est le nombre de page du livre ? (entrez un nombre)");
			        		int nbPages = Integer.parseInt(sc.nextLine());
			        		System.out.println("Quel est la catégorie du livre ?");
			        		String categorie = sc.nextLine();
			        		
		        			livreDao.creer(new Livre(auteurDao.trouver(idAuteur), nom, nbPages, categorie));
		        			System.out.println("Livre crée !");
		        		} catch(NumberFormatException e) {
		        			System.out.println("Vous devez entrer un nombre !");
		        		} catch(DaoException e) {
		        			System.out.println("Erreur : Livre non crée...");
		        		}
		        		break;
		        	case 9:
		        		System.out.println("\n----Modification d'un livre----\n");
		        		System.out.println("Quel est l'ID de l'auteur que vous souhaitez modifier ?");
		        		response = sc.nextLine();
		        		try {
			        		System.out.println("Quel est le nom du livre ?");
			        		String nom = sc.nextLine();
			        		System.out.println("Quel est l'id de l'auteur du livre ?");
			        		long idAuteur = Long.parseLong(sc.nextLine());
			        		System.out.println("Quel est le nombre de page du livre ? (entrez un nombre)");
			        		int nbPages = Integer.parseInt(sc.nextLine());
			        		System.out.println("Quel est la catégorie du livre ?");
			        		String categorie = sc.nextLine();
			        		
			        		Livre l = livreDao.trouver(Long.parseLong(response));
			        		l.setAuteur(auteurDao.trouver(idAuteur));
			        		l.setTitre(nom);
			        		l.setNbPages(nbPages);
			        		l.setCategorie(categorie);
			        		
		        			livreDao.update(l);
		        			System.out.println("Livre modifié !");
		        		} catch(NumberFormatException e) {
		        			System.out.println("Vous devez entrer un nombre !");
		        		} catch(DaoException e) {
		        			System.out.println("Erreur : Livre non crée...");
		        		}
		        		break;
		        	case 10:
		        		System.out.println("\n----Suppression d'un livre----\n");
		        		System.out.println("Quel est l'ID du livre recherché ?");
		        		response = sc.nextLine();
		        		try {
		        			livreDao.supprimer(Long.parseLong(response));
		        			System.out.println("Livre supprimé !");
		        		} catch(NumberFormatException e) {
		        			System.out.println("Vous devez entrer un nombre !");
		        		} catch(DaoException e) {
		        			System.out.println("Erreur lors de la suppression...");
		        		}
		        		break;
		        	default:
		        		System.out.println("Je n'ai pas compris votre demande...");
		        		break;
		        }
	    	  } catch(NumberFormatException e) {
	    		  System.out.println("Vous devez entrer un nombre ou 'q' pour quitter !");
	    	  }
	      }
	    
	    } while(isReady);
	    sc.close();
	   

	    System.out.println("\n--------------------GOOD BYE--------------------\n");
	    
	}

}
