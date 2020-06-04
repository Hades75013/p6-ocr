package com.sif.p6.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sif.p6.entities.Spot;

public interface SpotRepository extends JpaRepository<Spot, Long>{

	@Query("select s from Spot s where s.nom like :x")
	public Page<Spot> rechercherByNom (@Param("x")String mc, Pageable pageable);
	
	@Query("select s from Spot s where s.departement like :x")
	public Page<Spot> rechercherByDepartement (@Param("x")String mcDep, Pageable pageable);
	
	@Query("select s from Spot s where s.utilisateur.id = :id")
	public List<Spot> findSpotByUtilisateur(Long id);

	/*@Query("select s from Spot s join fetch s.topos t where t.statut = :statut")
	public Page<Spot> rechercherByTopoDispo (Boolean statut,Pageable pageable);
	*/
}
