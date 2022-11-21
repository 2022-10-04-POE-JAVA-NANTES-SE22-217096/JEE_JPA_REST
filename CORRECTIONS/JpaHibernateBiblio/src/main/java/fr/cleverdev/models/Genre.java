package fr.cleverdev.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "genre")
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long   id;
	
	@Column(nullable = false)
    private String nom;
    
	@ManyToMany( mappedBy="genres" )
	private List<Livre> livres = new ArrayList<Livre>();

    public Genre() {
    }


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public List<Livre> getLivres() {
		return livres;
	}


	public void setLivres(List<Livre> livres) {
		this.livres = livres;
	}


	@Override
	public boolean equals(Object obj) {
		if(((Genre) obj).getId() != this.id) {
			return false;
		}

		return true;
	}




}
