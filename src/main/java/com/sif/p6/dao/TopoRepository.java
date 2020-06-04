package com.sif.p6.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sif.p6.entities.Topo;

public interface TopoRepository extends JpaRepository<Topo, Long>{

	@Query("select t from Topo t where t.spot.id = :id")
	public List<Topo> findTopoBySpot(Long id);
	
	@Query("select t from Topo t where t.utilisateur.id = :id")
	public List<Topo> findTopoByUtilisateur(Long id);
	
	/*@Query("select t from Topo t where t.reservation.id = :id")
	public Topo findTopoByResa(Long id);
	*/
	@Query("select t from Topo t where t.statut = :statut")
	public Page<Topo> findTopoByStatut(Boolean statut,Pageable pageable);

}
