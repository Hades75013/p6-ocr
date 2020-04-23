package com.sif.p6.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sif.p6.entities.Emprunt;


public interface EmpruntRepository extends JpaRepository<Emprunt, Long>{

	
}
