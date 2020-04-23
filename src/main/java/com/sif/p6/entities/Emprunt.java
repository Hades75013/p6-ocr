package com.sif.p6.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Emprunt implements Serializable{
	
	@Id@GeneratedValue
	private Long id;
	
	private Date dateDebut;
	
	private Date dateFin;
	
	@ManyToOne
	@JoinColumn
	private Utilisateur utilisateur;
	
	@ManyToOne
	@JoinColumn
	private Topo topo;

	public Emprunt() {
		super();
	}
	
	public Emprunt(Long id, Date dateDebut, Date dateFin, Utilisateur utilisateur, Topo topo) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.utilisateur = utilisateur;
		this.topo = topo;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Topo getTopo() {
		return topo;
	}

	public void setTopo(Topo topo) {
		this.topo = topo;
	}
	
	
}
