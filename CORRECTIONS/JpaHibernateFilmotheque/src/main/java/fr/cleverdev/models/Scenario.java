package fr.cleverdev.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "scenario")
public class Scenario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long   id;

	@Column(nullable = false)
    private String titre;

	@Column(nullable = false)
    private String description;

	@OneToOne( mappedBy="scenario" )
	private Film film;

    public Scenario() {
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

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

}
