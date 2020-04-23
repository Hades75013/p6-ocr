package com.sif.p6.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sif.p6.entities.Voie;


public interface VoieRepository extends JpaRepository<Voie, Long>{

	@Query("select v from Voie v where v.secteur.id = :id")
	public List<Voie> findVoieBySecteur(Long id);
	
	
}
