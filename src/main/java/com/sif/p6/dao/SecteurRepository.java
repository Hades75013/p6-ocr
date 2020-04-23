package com.sif.p6.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sif.p6.entities.Secteur;


public interface SecteurRepository extends JpaRepository<Secteur, Long>{
	
	@Query("select s from Secteur s where s.spot.id = :id")
	public List<Secteur> findSecteurBySpot(Long id);
	
}
