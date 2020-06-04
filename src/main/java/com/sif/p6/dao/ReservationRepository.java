package com.sif.p6.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sif.p6.entities.Reservation;


public interface ReservationRepository extends JpaRepository<Reservation, Long>{

	@Query("select r from Reservation r where r.topo.utilisateur.id = :id")
	List <Reservation> findResaByTopoByUtilisateur(Long id);
	
	@Query("select r from Reservation r where r.utilisateur.id = :id")
	List <Reservation> findResaByUtilisateur(Long id);

	
}
