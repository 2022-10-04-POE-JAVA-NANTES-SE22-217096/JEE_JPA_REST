package fr.cleverdev.model;

public class Auteur {

	private Long   id;
    private String nom;
    private String prenom;
    private String telephone;
    private String email;

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
