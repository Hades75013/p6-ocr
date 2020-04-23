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
public class Commentaire implements Serializable {

	@Id@GeneratedValue
	private Long id;
	@Size(max=200)
	private String verbatim;
	
	@ManyToOne
	@JoinColumn
	private Spot spot;
	
	@ManyToOne
	@JoinColumn
	private Utilisateur utilisateur;
	
	public Commentaire() {
		super();
	
	}

	public Commentaire(Long id, @Size(max = 200) String verbatim, Spot spot, Utilisateur utilisateur) {
		super();
		this.id = id;
		this.verbatim = verbatim;
		this.spot = spot;
		this.utilisateur = utilisateur;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVerbatim() {
		return verbatim;
	}

	public void setVerbatim(String verbatim) {
		this.verbatim = verbatim;
	}

	public Spot getSpot() {
		return spot;
	}

	public void setSpot(Spot spot) {
		this.spot = spot;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	
}
