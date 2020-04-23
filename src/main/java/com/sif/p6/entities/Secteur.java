package com.sif.p6.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;


@SuppressWarnings("serial")
@Entity
public class Secteur implements Serializable{

	@Id@GeneratedValue
	private Long id;
	@Size(min=2)
	private String nom;
	
	@OneToMany
	private Set<Voie> voies;
	
	@ManyToOne
	@JoinColumn
	private Spot spot;
	
	public Secteur() {
		super();
	}

	public Secteur(Long id, @Size(min = 2) String nom, Set<Voie> voies, Spot spot) {
		super();
		this.id = id;
		this.nom = nom;
		this.voies = voies;
		this.spot = spot;
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

	public Set<Voie> getVoies() {
		return voies;
	}

	public void setVoies(Set<Voie> voies) {
		this.voies = voies;
	}

	public Spot getSpot() {
		return spot;
	}

	public void setSpot(Spot spot) {
		this.spot = spot;
	}

	
}
