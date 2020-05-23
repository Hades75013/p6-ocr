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
public class Spot implements Serializable{
	@Id@GeneratedValue
	private Long id;
	@NotNull
	@Size(min=2)
	private String nom;
	@NotNull
	@Size(min=2)
	private String departement;
	@NotNull
	private boolean tag;
	
	@ManyToOne
	@JoinColumn
	private Utilisateur utilisateur;
	
	@OneToMany
	private Set<Secteur> secteurs;
	
	@OneToMany
	private Set<Topo> topos;
	
	@OneToMany
	private Set<Commentaire> commentaires;
	
	
	public Spot() {
		super();
	}
	
	
	public Spot(Long id, @NotNull @Size(min = 2) String nom, @NotNull @Size(min = 2) String departement,
			@NotNull boolean tag, Set<Secteur> secteurs, Set<Topo> topos, Set<Commentaire> commentaires) {
		super();
		this.id = id;
		this.nom = nom;
		this.departement = departement;
		this.tag = tag;
		this.secteurs = secteurs;
		this.topos = topos;
		this.commentaires = commentaires;
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
	public String getDepartement() {
		return departement;
	}
	public void setDepartement(String departement) {
		this.departement = departement;
	}
	
	public boolean isTag() {
		return tag;
	}

	public void setTag(boolean tag) {
		this.tag = tag;
	}
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Set<Secteur> getSecteurs() {
		return secteurs;
	}

	public void setSecteurs(Set<Secteur> secteurs) {
		this.secteurs = secteurs;
	}

	public Set<Topo> getTopos() {
		return topos;
	}

	public void setTopos(Set<Topo> topos) {
		this.topos = topos;
	}

	public Set<Commentaire> getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(Set<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}
	
	
	
}
