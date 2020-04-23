package com.sif.p6.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
public class Role implements Serializable{
	
	@Id@GeneratedValue
	private Long id;
	
	@NotNull
	private String nom;
	
	@OneToMany
	private Set<Utilisateur> utilisateur;
	
	
	public Role() {
		super();
	}

	public Role(Long id, @NotNull String nom, Set<Utilisateur> utilisateur) {
		super();
		this.id = id;
		this.nom = nom;
		this.utilisateur = utilisateur;
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

	public Role(String nom) {
		super();
		this.nom = nom;
	}

	public Set<Utilisateur> getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Set<Utilisateur> utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	
	
}
