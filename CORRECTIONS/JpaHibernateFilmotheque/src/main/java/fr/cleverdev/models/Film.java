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
@Table(name = "film")
public class Film {

	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY )
	private Long id;

	@Column(nullable = false)
	private String titre;

	private String description;

	private long dureeMinutes;

	private String genre;

	@ManyToOne( optional = false, fetch=FetchType.LAZY, cascade=CascadeType.ALL )
	private Realisateur realisateur;

	@OneToOne( optional = false, fetch=FetchType.LAZY )
	private Scenario scenario;

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
		name="film_acteur",
		joinColumns = { @JoinColumn(name="film_id") },
		inverseJoinColumns = {@JoinColumn(name="acteur_id") }
	)
	private List<Acteur> acteurs = new ArrayList<>();


	public Film() {

	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public long getDureeMinutes() {
		return dureeMinutes;
	}


	public void setDureeMinutes(long dureeMinutes) {
		this.dureeMinutes = dureeMinutes;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public Realisateur getRealisateur() {
		return realisateur;
	}


	public void setRealisateur(Realisateur realisateur) {
		this.realisateur = realisateur;
	}


	public Scenario getScenario() {
		return scenario;
	}


	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}


	public List<Acteur> getActeurs() {
		return acteurs;
	}


	public void setActeurs(List<Acteur> acteurs) {
		this.acteurs = acteurs;
	}



}
