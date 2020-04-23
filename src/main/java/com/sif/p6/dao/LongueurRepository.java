package com.sif.p6.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sif.p6.entities.Longueur;


public interface LongueurRepository extends JpaRepository<Longueur, Long>{

	@Query("select l from Longueur l where l.voie.id = :id")
	public List<Longueur> findLongueurByVoie(Long id);
}
