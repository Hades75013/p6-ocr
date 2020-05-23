package com.sif.p6.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.sif.p6.dao.UtilisateurRepository;
import com.sif.p6.entities.Utilisateur;

@Service("userService")
public class UserService implements UserDetailsService {

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UserService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String pseudo) {
    	Utilisateur utilisateur = utilisateurRepository.findByPseudo(pseudo);
    	
            return utilisateur;
        }
    
    }
    
    

    	
    
