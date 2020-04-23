package com.sif.p6.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sif.p6.entities.Commentaire;


public interface CommentaireRepository extends JpaRepository<Commentaire, Long>{
	
	@Query("select c from Commentaire c where c.spot.id = :id")
	public List<Commentaire> findCommentaireBySpot(Long id);

	
}