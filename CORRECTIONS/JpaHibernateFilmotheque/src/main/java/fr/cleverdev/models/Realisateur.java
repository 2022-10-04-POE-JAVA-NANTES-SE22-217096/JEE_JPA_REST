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
@Table(name = "realisateur")
public class Realisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long   id;

	@Column(nullable = false)
    private String nom;

	@Column(nullable = false)
    private String prenom;

    private int age;

    private String pays;

	@OneToMany(mappedBy = "realisateur")
	private List<Film> films = new ArrayList<>();


    public Realisateur() {
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
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getPays() {
		return pays;
	}


	public void setPays(String pays) {
		this.pays = pays;
	}


	public List<Film> getFilms() {
		return films;
	}


	public void setFilms(List<Film> films) {
		this.films = films;
	}





}
