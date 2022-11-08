import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;


public class LauncherLivre {

	public static void main(String[] args) {

		//Récupérer 1e livre
		/*try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/biblio", "root", "");
			
			PreparedStatement ps = con.prepareStatement("Select * From Livre Where id = ?");
			ps.setInt(1, 3);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int id = rs.getInt("id");
				int idAuteur = rs.getInt("id_auteur");
				String titre = rs.getString("titre");
				int nbPages = rs.getInt("nb_pages");
				String categorie = rs.getString("categorie");
				
				System.out.println("Livre " + id + " : " + titre + " " + nbPages + " pages / " + categorie + " / Auteur : "+ idAuteur);
			}
			
			
			rs.close();
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} */
		
		//Récupérer tous les livres
		/*try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/biblio", "root", "");
			
			PreparedStatement ps = con.prepareStatement("Select * From Livre");
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				int idAuteur = rs.getInt("id_auteur");
				String titre = rs.getString("titre");
				int nbPages = rs.getInt("nb_pages");
				String categorie = rs.getString("categorie");
				
				System.out.println("Livre " + id + " : " + titre + " " + nbPages + " pages / " + categorie + " / Auteur : "+ idAuteur);
			}
			
			
			rs.close();
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} */
		
		//Insertion livre
		/*try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/biblio", "root", "");
			
			PreparedStatement ps = con.prepareStatement("Insert into Livre(id_auteur, titre, nb_pages, categorie) Values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, 1);
			ps.setString(2, "Le Silmarion");
			ps.setInt(3, 456);
			ps.setString(4, "Fantaisie");
			
			int statut = ps.executeUpdate();
		
			
			if(statut > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				int id = -1;
				if(rs.next()) {
					id = rs.getInt(1);
				}
				System.out.println("Le livre bien créer avec l'id : " + id);
			}
			
			
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}*/
		
		
		//Modification livre
		/*try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/biblio", "root", "");
			
			PreparedStatement ps = con.prepareStatement("Update Livre Set id_auteur=?, titre=?, nb_pages=?, categorie=? Where id = ?");
			ps.setInt(1, 1);
			ps.setString(2, "Le Silmarion");
			ps.setInt(3, 456);
			ps.setString(4, "Fantaisie");
			ps.setInt(5, 2);
			
			int statut = ps.executeUpdate();
			
			if(statut > 0) {
				System.out.println("Le livre a bien été modifié");
			}
			
			
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}*/
		
		
		//Suppresion livre
		/*try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/biblio", "root", "");
			
			PreparedStatement ps = con.prepareStatement("Delete from Livre Where id = ?");
			ps.setInt(1, 2);
			
			int statut = ps.executeUpdate();
			
			if(statut > 0) {
				System.out.println("Le livre a bien été supprimé");
			}
			
			
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}*/

	}

}
