package com.sif.p6.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;


@SuppressWarnings("serial")
@Entity
public class Longueur implements Serializable {

	@Id@GeneratedValue
	private Long id;
	
	@Size(min=2)
	private String nom;
	
	@Size(min=2)
	private String cotation;
	
	@ManyToOne
	@JoinColumn
	private Voie voie;
	
	
	public Longueur() {
		super();
	}

	public Longueur(Long id, @Size(min = 2) String nom, @Size(min = 2) String cotation, Voie voie) {
		super();
		this.id = id;
		this.nom = nom;
		this.cotation = cotation;
		this.voie = voie;
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
	
	public String getCotation() {
		return cotation;
	}

	public void setCotation(String cotation) {
		this.cotation = cotation;
	}

	public Voie getVoie() {
		return voie;
	}

	public void setVoie(Voie voie) {
		this.voie = voie;
	}
	
}
