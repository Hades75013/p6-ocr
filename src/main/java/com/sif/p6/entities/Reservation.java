package com.sif.p6.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Reservation implements Serializable{
	
	@Id@GeneratedValue
	private Long id;
	
	
	@ManyToOne
	@JoinColumn
	private Utilisateur utilisateur;
	
	@ManyToOne
	@JoinColumn
	private Topo topo;

	public Reservation() {
		super();
	}
	
	public Reservation(Long id, Utilisateur utilisateur, Topo topo) {
		super();
		this.id = id;
		this.utilisateur = utilisateur;
		this.topo = topo;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
