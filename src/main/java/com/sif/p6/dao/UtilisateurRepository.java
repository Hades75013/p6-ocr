package com.sif.p6.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sif.p6.entities.Utilisateur;


public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{
	
	Utilisateur findByPseudo(String pseudo);
	
}
