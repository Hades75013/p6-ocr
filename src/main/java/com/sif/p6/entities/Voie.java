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
public class Voie implements Serializable{

	@Id@GeneratedValue
	private Long id;
	@Size(min=2)
	private String nom;
	@Size(min=2)
	private String hauteur;
	
	private String nombrePoints;
	
	private String cotation;
	
	@OneToMany(mappedBy="voie")
	private Set<Longueur> longueurs;
	
	@ManyToOne
	@JoinColumn
	private Secteur secteur;

	public Voie() {
		super();
	}

	

	public Voie(Long id, @Size(min = 2) String nom, @Size(min = 2) String hauteur, String nombrePoints, String cotation,
			Set<Longueur> longueurs, Secteur secteur) {
		super();
		this.id = id;
		this.nom = nom;
		this.hauteur = hauteur;
		this.nombrePoints = nombrePoints;
		this.cotation = cotation;
		this.longueurs = longueurs;
		this.secteur = secteur;
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

	
	public String getHauteur() {
		return hauteur;
	}

	public void setHauteur(String hauteur) {
		this.hauteur = hauteur;
	}

	public String getNombrePoints() {
		return nombrePoints;
	}

	public void setNombrePoints(String nombrePoints) {
		this.nombrePoints = nombrePoints;
	}
	
	public String getCotation() {
		return cotation;
	}

	public void setCotation(String cotation) {
		this.cotation = cotation;
	}

	public Set<Longueur> getLongueurs() {
		return longueurs;
	}

	public void setLongueurs(Set<Longueur> longueurs) {
		this.longueurs = longueurs;
	}

	public Secteur getSecteur() {
		return secteur;
	}

	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}
	
	
}
