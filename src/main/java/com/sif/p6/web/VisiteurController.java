package com.sif.p6.web;

import java.util.ArrayList;
import java.util.Collection;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sif.p6.dao.SpotRepository;
import com.sif.p6.dao.UtilisateurRepository;
import com.sif.p6.entities.Spot;
import com.sif.p6.entities.Utilisateur;
import com.sif.p6.security.RoleEnum;

@Controller
public class VisiteurController {

	@Autowired
	private SpotRepository spotRepository;
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	
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
		
		Collection<RoleEnum> role = new ArrayList<RoleEnum>();
		role.add(RoleEnum.USER);
		utilisateur.setRoles(role);
		utilisateurRepository.save(utilisateur);
		
		return "confirmationcreationcompte";
	}
	
	@RequestMapping(value="connection")
	public String Connection (){
		
		return "formconnection";
	}
	
	@RequestMapping(value="deconnection")
	public String Deconnection (){
		 
		 return "formconnection"; 
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView loginGet() {
		
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return new ModelAndView("redirect:/espaceperso");
        }
        return new ModelAndView("connection");
    }
	
	@RequestMapping(value="espaceperso")
	public String AccesRefuse (){
		
		return "formconnection";
	}
	
}

