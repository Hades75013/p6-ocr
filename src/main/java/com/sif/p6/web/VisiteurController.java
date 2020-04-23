package com.sif.p6.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sif.p6.dao.RoleRepository;
import com.sif.p6.dao.SpotRepository;
import com.sif.p6.dao.UtilisateurRepository;
import com.sif.p6.entities.Role;
import com.sif.p6.entities.Spot;
import com.sif.p6.entities.Utilisateur;

@Controller
public class VisiteurController {

	@Autowired
	private SpotRepository spotRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@RequestMapping(value="home")
	public String Home (){
		return "layout";
	}
	
	@RequestMapping(value="spots")
	public String Spotsadmin(Model model, 
		 @RequestParam(name="page", defaultValue="0")int p, 
		 @RequestParam(name="size", defaultValue="3")int s,
		 @RequestParam(name="motCle", defaultValue="")String mc) {
			
		Page<Spot> pageSpotsByNom = spotRepository.rechercherByNom("%"+mc+"%",PageRequest.of(p, s));
		int[] pages = new int [pageSpotsByNom.getTotalPages()];
		
		
		model.addAttribute("listeSpots", pageSpotsByNom.getContent());
		model.addAttribute("pages",pages);
		model.addAttribute("pageCourante",p);
		model.addAttribute("size",s);
		model.addAttribute("motCle",mc);

		return "spots";
	}
	
	
	@RequestMapping(value="inscription")
	public String ajouterUtilisateur(Model model) {
		model.addAttribute("utilisateur",new Utilisateur());
		
		return "forminscription";
	}
	
	@RequestMapping(value="saveUser", method=RequestMethod.POST)
	public String sauverUtilisateur(Model model, @Valid Utilisateur utilisateur, BindingResult bindingResult) {
		model.addAttribute("utilisateur",utilisateur);
		if(bindingResult.hasErrors()) {
			return "forminscription";
		}
		
		Role role = roleRepository.getOne(85l);
		utilisateur.setRole(role);

		utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
		
		utilisateurRepository.save(utilisateur);
		
		return "confirmationcreationcompte";
	}
	
	@RequestMapping(value="connection")
	public String Connection (Model model, Utilisateur utilisateur){
		model.addAttribute("utilisateur",utilisateur);

		return "formconnection";
	}
	
	
	@RequestMapping(value="connectionUser")
	public String ConnectionUser (Model model, @Valid Utilisateur utilisateur, BindingResult bindingResult){
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("utilisateur",utilisateur);

			return "formconnection";
		}
		
		return "redirect:/user/espaceperso";
		
	}
	
	@RequestMapping(value="espaceperso")
	public String EspacePersoRefuse (){
		return "formconnection";
	}
	
	

}

