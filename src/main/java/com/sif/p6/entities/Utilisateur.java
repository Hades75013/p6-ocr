package com.sif.p6.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@SuppressWarnings("serial")
@Entity
public class Utilisateur implements Serializable{
	
	@Id@GeneratedValue
	private Long id;
	
	@NotNull
	@Size(min=2)
	private String nom;
	
	@NotNull
	@Size(min=2)
	private String prenom;
	
	@NotNull
	private String pseudo;
	
	@NotNull
	@Size(min=5)
	private String motDePasse;
	
	@NotNull
	private String email;
	
	@NotNull
	private boolean active;

	@OneToMany
	private Set<Topo> topo;
	
	@OneToMany
	private Set<Commentaire> commentaire;
	
	@OneToMany
	private Set<Emprunt> emprunt;
	
	@ManyToOne
	@JoinColumn
	private Role role;
	
	
	public Utilisateur() {
		super();
	}

	public Utilisateur(Long id, @NotNull @Size(min = 2) String nom, @NotNull @Size(min = 2) String prenom,
			@NotNull String pseudo, @NotNull @Size(min = 5) String motDePasse, @NotNull String email,
			@NotNull boolean active, Set<Topo> topo, Set<Commentaire> commentaire, Set<Emprunt> emprunt, Role role) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
		this.email = email;
		this.active = active;
		this.topo = topo;
		this.commentaire = commentaire;
		this.emprunt = emprunt;
		this.role = role;
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

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<Topo> getTopo() {
		return topo;
	}

	public void setTopo(Set<Topo> topo) {
		this.topo = topo;
	}

	public Set<Commentaire> getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(Set<Commentaire> commentaire) {
		this.commentaire = commentaire;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<Emprunt> getEmprunt() {
		return emprunt;
	}

	public void setEmprunt(Set<Emprunt> emprunt) {
		this.emprunt = emprunt;
	}
	
	
	
}
