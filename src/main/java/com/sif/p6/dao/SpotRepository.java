package com.sif.p6.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sif.p6.entities.Spot;

public interface SpotRepository extends JpaRepository<Spot, Long>{

	@Query("select s from Spot s where s.nom like :x")
	public Page<Spot> rechercherByNom (@Param("x")String mc, Pageable pageable);

	
}
