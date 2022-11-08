package fr.cleverdev;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.cleverdev.dao.AuteurDao;
import fr.cleverdev.dao.AuteurDaoImpl;
import fr.cleverdev.dao.DaoException;
import fr.cleverdev.dao.DaoFactory;
import fr.cleverdev.model.Auteur;

public class Launcher {

	public static void main(String[] args) throws DaoException {

		Connection con = null;
		Statement sta = null;
		ResultSet rs = null;
		List<Auteur> listeAuteurs = new ArrayList<Auteur>();
	
		AuteurDao auteurDao = DaoFactory.getInstance().getAuteurDao();
		List<Auteur> listAuteurs;
		Auteur tmp;
		
		/*try {
			listAuteurs = auteurDao.lister();
			
		    Iterator<Auteur> i = listAuteurs.iterator();
		    while (i.hasNext()) {
		      System.out.println(i.next());
		    }
		} catch (DaoException e) {
			e.printStackTrace();
		}*/


		/*try {
			auteurDao.creer(new Auteur("Zweig", "Stefan", "0660606060", "stefan.zweig@lejoueurdechecs.de"));
			
			listAuteurs = auteurDao.lister();
			
		    Iterator<Auteur> i = listAuteurs.iterator();
		    while (i.hasNext()) {
		      System.out.println(i.next());
		    }
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		try {
			tmp = auteurDao.trouver(1);
			System.out.println(tmp);
			
			tmp.setPrenom("Bob");
			auteurDao.miseAJour(tmp);
			System.out.println(tmp);
			
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*try {
			auteurDao.supprimer(5);
			auteurDao.supprimer(6);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}

}
