package fr.cleverdev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.cleverdev.model.Auteur;

public class AuteurDaoImpl implements AuteurDao {
	
	private static final String SQL_INSERT       = "INSERT INTO auteur(nom,prenom,telephone,email) VALUES(?,?,?,?)";
	private static final String SQL_SELECT       = "SELECT id,nom,prenom,telephone,email FROM auteur";
    private static final String SQL_SELECT_BY_ID = "SELECT id,nom,prenom,telephone,email FROM auteur WHERE id = ?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM auteur WHERE id = ? ";
	
	private static final String SQL_UPDATE = "UPDATE auteur SET nom=?, prenom=?, telephone=?, email=? WHERE id = ?";
	
	private DaoFactory factory;
	
	protected AuteurDaoImpl(DaoFactory factory) {
		this.factory = factory;
	}

	@Override
	public void creer(Auteur auteur) throws DaoException {
		Connection con=null;
		try {
			con = factory.getConnection();
			
			PreparedStatement pst = con.prepareStatement( SQL_INSERT, Statement.RETURN_GENERATED_KEYS );
			pst.setString( 1, auteur.getNom() );
			pst.setString( 2, auteur.getPrenom() );
			pst.setString( 3, auteur.getTelephone() );
			pst.setString( 4, auteur.getEmail() );
			
			int statut = pst.executeUpdate();

            if ( statut == 0 ) {
                throw new DaoException( "Echec cr�ation Auteur (aucun ajout)" );
            }
            ResultSet rsKeys = pst.getGeneratedKeys();
            if ( rsKeys.next() ) {
                auteur.setId( rsKeys.getLong( 1 ) );
            } else {
                throw new DaoException( "Echec cr�ation Auteur (ID non retourn�)" );
            }
            rsKeys.close();
			pst.close();
			
	    } catch(SQLException ex) {
	    	throw new DaoException("Echec cr�ation Auteur",ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		
	}
	
	
	@Override
	public void update(Auteur auteur) throws DaoException {
		Connection con=null;
		try {
			con = factory.getConnection();
			
			PreparedStatement pst = con.prepareStatement( SQL_UPDATE );
			pst.setString( 1, auteur.getNom() );
			pst.setString( 2, auteur.getPrenom() );
			pst.setString( 3, auteur.getTelephone() );
			pst.setString( 4, auteur.getEmail() );
			pst.setLong( 5, auteur.getId() );

			int statut = pst.executeUpdate();

            if ( statut == 0 ) {
                throw new DaoException( "Echec modification Auteur" );
            }
			pst.close();
			
	    } catch(SQLException ex) {
	    	throw new DaoException("Echec m Auteur",ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
	}


	@Override
	public Auteur trouver(long id) throws DaoException {
		Auteur            auteur=null;
		Connection        con=null;
		PreparedStatement pst=null;
		ResultSet         rs=null;
		try {
			  con = factory.getConnection();
			  pst = con.prepareStatement( SQL_SELECT_BY_ID );
			  pst.setLong(1, id);
		      rs  = pst.executeQuery();
		      if ( rs.next() ) {
		    	  auteur = map(rs);
		      } else {
		    	  throw new DaoException("Erreur de recherche BDD Auteur");
		      }
		      rs.close();
		      pst.close();
	    } catch(SQLException ex) {
	    	throw new DaoException("Erreur de recherche BDD Auteur", ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		return auteur;
	}

	@Override
	public List<Auteur> lister() throws DaoException {
		List<Auteur> listeAuteurs = new ArrayList<Auteur>();
		Connection   con=null;
		try {
			  con = factory.getConnection();
			  PreparedStatement pst = con.prepareStatement( SQL_SELECT );
		      ResultSet         rs  = pst.executeQuery();
		      while ( rs.next() ) {
		    	  listeAuteurs.add( map(rs) );
		      }
		      rs.close();
		      pst.close();
	    } catch(SQLException ex) {
	    	throw new DaoException("Erreur de lecture BDD Auteur", ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		return listeAuteurs;
	}

	@Override
	public void supprimer(long id) throws DaoException {
		Connection   con=null;
		try {
			  con = factory.getConnection();
			  PreparedStatement pst = con.prepareStatement( SQL_DELETE_BY_ID );
			  pst.setLong(1, id);
			  int statut = pst.executeUpdate();
			  if ( statut == 0 ) {
				  throw new DaoException("Erreur de suppression Auteur("+id+")");
			  }
		      pst.close();
	    } catch(SQLException ex) {
	    	throw new DaoException("Erreur de suppression BDD Auteur", ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		
	}
    /*
     * Mapping (correspondance) entre un ResultSet et un JavaBean
     * M�thode utilitaire (interne)
     */
    private static Auteur map( ResultSet resultSet ) throws SQLException {
        Auteur a = new Auteur();
        a.setId( resultSet.getLong( "id" ) );
        a.setNom( resultSet.getString( "nom" ) );
        a.setPrenom( resultSet.getString( "prenom" ) );
        a.setTelephone( resultSet.getString( "telephone" ) );
        a.setEmail( resultSet.getString( "email" ) );
        return a;
    }



}
