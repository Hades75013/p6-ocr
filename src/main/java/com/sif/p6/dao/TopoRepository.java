package com.sif.p6.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sif.p6.entities.Topo;

public interface TopoRepository extends JpaRepository<Topo, Long>{

	@Query("select t from Topo t where t.spot.id = :id")
	public List<Topo> findTopoBySpot(Long id);
	
	
	@Query("select t from Topo t where t.utilisateur.id = :id")
	public List<Topo> findTopoByUtilisateur(Long id);

}
