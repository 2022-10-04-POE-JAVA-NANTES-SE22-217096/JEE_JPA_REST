package fr.cleverdev.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "auteur")
public class Auteur {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long   id;

	@Column(nullable = false, length = 20)
    private String nom;

	@Column(length = 20)
    private String prenom;

	@Column(nullable = false, length = 10)
    private String telephone;

	@Column(length =  60)
    private String email;

	@OneToMany(mappedBy = "auteur")
	private List<Livre> livres = new ArrayList<>();


    public Auteur() {
    }

    public Auteur(String nom, String prenom, String telephone, String email) {
    	this.nom = nom;
    	this.prenom = prenom;
    	this.telephone = telephone;
    	this.email = email;
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
	public String getPrenom() {
		return this.prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public List<Livre> getLivres() {
		return livres;
	}

	public void setLivres(List<Livre> livres) {
		this.livres = livres;
	}

	@Override
	public String toString() {
		return getId() + " : " + getNom() + " " + getPrenom() + " - " + getTelephone() + " / " + getEmail();
	}


	@Override
	public boolean equals(Object obj) {
		if(((Auteur) obj).getId() != this.id) {
			return false;
		}

		return true;
	}




}
