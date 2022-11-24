package fr.cleverdev.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "livre")
public class Livre {

	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY )
	private Long id;

	@Column(nullable = false, length = 50)
	private String titre;

	@Column(nullable = false, name="nb_pages")
	private int nbPages;

	@Column(length = 20)
	private String categorie;

	@ManyToOne( fetch=FetchType.LAZY )
	private Auteur auteur;

	@OneToOne( fetch=FetchType.LAZY )
	private Couverture couverture;

	@ManyToMany(cascade={CascadeType.MERGE})
	@JoinTable(
		name="livre_genre",
		joinColumns = { @JoinColumn(name="livre_id") },
		inverseJoinColumns = {@JoinColumn(name="genre_id") }
	)
	private List<Genre> genres = new ArrayList<>();

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

	public Couverture getCouverture() {
		return couverture;
	}

	public void setCouverture(Couverture couverture) {
		this.couverture = couverture;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public void addGenre(Genre genre) {
		this.genres.add(genre);
		genre.getLivres().add(this);
	}

	public void removeGenre(Genre genre) {
		this.genres.remove(genre);
		genre.getLivres().remove(this);
	}


	@Override
	public String toString() {
		return getId() + " : " + getTitre() + " de " + getAuteur().getPrenom() + " " + getAuteur().getNom() + " / " + getNbPages() + " pages / Catégorie : " + getCategorie();
	}


}
