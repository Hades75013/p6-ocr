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
public class Topo implements Serializable{
	
	@Id@GeneratedValue
	private Long id;
	
	@Size(min=2)
	private String nom;
	
	
	private String description;
	
	@Size(min=2)
	private String lieu;
	
	@Size(min=2)
	private String dateParution;
	
	private boolean statut;
	
	@ManyToOne
	@JoinColumn
	private Spot spot;
	
	@ManyToOne
	@JoinColumn
	private Utilisateur utilisateur;
	
	@OneToMany(mappedBy="topo")
	private Set<Reservation> reservation;
	
	public Topo() {
		super();
	}

	

	public Topo(Long id, @Size(min = 2) String nom, String description, String lieu, String dateParution,
			boolean statut, Spot spot, Utilisateur utilisateur, Set<Reservation> reservation) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.lieu = lieu;
		this.dateParution = dateParution;
		this.statut = statut;
		this.spot = spot;
		this.utilisateur = utilisateur;
		this.reservation = reservation;
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
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public String getDateParution() {
		return dateParution;
	}

	public void setDateParution(String dateParution) {
		this.dateParution = dateParution;
	}

	public boolean isStatut() {
		return statut;
	}

	public void setStatut(boolean statut) {
		this.statut = statut;
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

	public Set<Reservation> getReservation() {
		return reservation;
	}

	public void setReservation(Set<Reservation> reservation) {
		this.reservation = reservation;
	}
	
	
	

}
