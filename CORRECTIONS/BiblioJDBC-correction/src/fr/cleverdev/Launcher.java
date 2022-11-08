package fr.cleverdev;

import java.util.Iterator;
import java.util.List;

import fr.cleverdev.dao.AuteurDao;
import fr.cleverdev.dao.DaoException;
import fr.cleverdev.dao.DaoFactory;
import fr.cleverdev.dao.LivreDao;
import fr.cleverdev.model.Auteur;
import fr.cleverdev.model.Livre;

public class Launcher {

	public static void main(String[] args) {

		LivreDao livreDao = DaoFactory.getInstance().getLivreDao();
		List<Livre> listLivres;
		
		/*try {
			listLivres = livreDao.lister();
			
		    Iterator<Livre> i = listLivres.iterator();
		    while (i.hasNext()) {
		      System.out.println(i.next());
		    }
		} catch (DaoException e) {
			e.printStackTrace();
		}*/
		
		AuteurDao auteurDao = DaoFactory.getInstance().getAuteurDao();
		
		try {
			Auteur tmp = auteurDao.trouver(2);
			System.out.println(tmp);
			
			tmp.setPrenom("Joe");
			tmp.setEmail("gonzalves@gmail.c");
			auteurDao.update(tmp);
			
			tmp = auteurDao.trouver(2);
			System.out.println(tmp);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		
		/*List<Auteur> listAuteurs;
		Auteur tmp;
		
		try {
			listAuteurs = auteurDao.lister();
			
		    Iterator<Auteur> i = listAuteurs.iterator();
		    while (i.hasNext()) {
		      System.out.println(i.next());
		    }
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		try {
			auteurDao.creer(new Auteur("Zweig", "Stefan", "0660606060", "stefan.zweig@lejoueurdechecs.de"));
			
			listAuteurs = auteurDao.lister();
			
		    Iterator<Auteur> i = listAuteurs.iterator();
		    while (i.hasNext()) {
		      System.out.println(i.next());
		    }
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			tmp = auteurDao.trouver(1);
			System.out.println(tmp);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}

}
