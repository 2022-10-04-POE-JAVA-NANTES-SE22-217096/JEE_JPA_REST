package fr.cleverdev.model;

public class Livre {

	private Long id;
	private Auteur auteur;
	private String titre;
	private int nbPages;
	private String categorie;
	
	public Livre() {
		
	}
	
	public Livre(Auteur auteur, String titre, int nbPages, String categorie) {
		this.auteur = auteur;
		this.titre = titre;
		this.nbPages = nbPages;
		this.categorie = categorie;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Auteur getAuteur() {
		return auteur;
	}

	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public int getNbPages() {
		return nbPages;
	}

	public void setNbPages(int nbPages) {
		this.nbPages = nbPages;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	@Override
	public String toString() {
		return getId() + " : " + getTitre() + " de " + getAuteur().getPrenom() + " " + getAuteur().getNom() + " / " + getNbPages() + " pages / Catégorie : " + getCategorie();
	}
	
	
}
