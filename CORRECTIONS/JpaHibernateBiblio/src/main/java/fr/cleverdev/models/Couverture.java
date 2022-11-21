package fr.cleverdev.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "couverture")
public class Couverture {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long   id;

	@Column(nullable = false)
    private String description;

	@OneToOne( mappedBy="couverture" )
	private Livre livre;

    public Couverture() {
    }


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}



	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Livre getLivre() {
		return livre;
	}


	public void setLivre(Livre livre) {
		this.livre = livre;
	}


	@Override
	public boolean equals(Object obj) {
		if(((Couverture) obj).getId() != this.id) {
			return false;
		}

		return true;
	}




}
