import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;


public class LauncherAuteur {

	public static void main(String[] args) {

		//Récupérer 1 auteur
		/*try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/biblio", "root", "");
			
			PreparedStatement ps = con.prepareStatement("Select * From Auteur Where id = ?");
			ps.setInt(1, 3);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int id = rs.getInt("id");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String telephone = rs.getString("telephone");
				String email = rs.getString("email");
				
				System.out.println("Auteur " + id + " : " + nom + " " + prenom + " / " + telephone + " / "+ email);
			}
			
			
			rs.close();
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} */
		
		//Récupérer tous les auteurs
		/*try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/biblio", "root", "");
			
			PreparedStatement ps = con.prepareStatement("Select * From Auteur");
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String telephone = rs.getString("telephone");
				String email = rs.getString("email");
				
				System.out.println("Auteur " + id + " : " + nom + " " + prenom + " / " + telephone + " / "+ email);
			}
			
			
			rs.close();
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} */
		
		//Insertion auteur
		/*try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/biblio", "root", "");
			
			PreparedStatement ps = con.prepareStatement("Insert into Auteur(nom, prenom, telephone, email) Values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, "Sanji");
			ps.setString(2, "Vinsmoke");
			ps.setString(3, "0660606060");
			ps.setString(4, "sanji@op.jp");
			
			int statut = ps.executeUpdate();
		
			
			if(statut > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				int id = -1;
				if(rs.next()) {
					id = rs.getInt(1);
				}
				System.out.println("L'auteur bien créer avec l'id : " + id);
			}
			
			
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}*/
		
		
		//Modification auteur
		/*try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/biblio", "root", "");
			
			PreparedStatement ps = con.prepareStatement("Update Auteur Set nom=?, prenom=?, telephone=?, email=? Where id = ?");
			ps.setString(1, "Sanji");
			ps.setString(2, "Vinsmoke");
			ps.setString(3, "0660606060");
			ps.setString(4, "sanji@op.jp");
			ps.setInt(5, 2);
			
			int statut = ps.executeUpdate();
			
			if(statut > 0) {
				System.out.println("L'auteur a bien été modifié");
			}
			
			
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}*/
		
		
		//Suppresion auteur
		/*try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/biblio", "root", "");
			
			PreparedStatement ps = con.prepareStatement("Delete from Auteur Where id = ?");
			ps.setInt(1, 2);
			
			int statut = ps.executeUpdate();
			
			if(statut > 0) {
				System.out.println("L'auteur a bien été supprimé");
			}
			
			
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}*/
		
		
		
	}

}
