package com.sif.p6.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sif.p6.dao.SpotRepository;
import com.sif.p6.dao.TopoRepository;
import com.sif.p6.entities.Spot;
import com.sif.p6.entities.Topo;
import com.sif.p6.entities.Utilisateur;

@Controller
public class UtilisateurController {

	@Autowired
	private SpotRepository spotRepository;
	
	@Autowired
	private TopoRepository topoRepository;
	
	@RequestMapping(value="/user/home")
	public String Home (){
		return "layout";
	}
	
	@RequestMapping(value="/user/spots")
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

		return "spotsuser";
	}
	
	
	@RequestMapping(value="/user/ajouterspot", method=RequestMethod.GET)
	public String ajouterSpot(Model model) {
		model.addAttribute("spot",new Spot());
		
		return "formspot";
	}
	
	@RequestMapping(value="/user/saveajoutspot", method=RequestMethod.POST)
	public String sauverAjoutSpot(Model model, @Valid Spot spot, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("spot",spot);
			return "formspot";
		}
		
		model.addAttribute("spot",spot);
		spotRepository.save(spot);
		return "confirmationajoutspot";
  	}
	
	@RequestMapping(value="/user/ajoutertopo", method=RequestMethod.GET)
	public String ajouterTopo(Model model) {
		model.addAttribute("topo",new Topo());
		
		return "formtopo";
	}
	
	@RequestMapping(value="/user/saveajouttopo", method=RequestMethod.POST)
	public String sauverAjoutTopo(Model model, @Valid Topo topo, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("topo",topo);
			return "formtopo";
		}
		
		model.addAttribute("topo",topo);
		topoRepository.save(topo);
		return "confirmationajouttopo";
  	}
	
	@RequestMapping(value="user/connectionUser")
	public String ConnectionUser (Model model, Utilisateur utilisateur){
		model.addAttribute("utilisateur",utilisateur);
		
		return "redirect:/user/espaceperso";
		
	}
	
	@RequestMapping(value="/user/espaceperso")
	public String EspacePerso (Model model,Utilisateur utilisateur){
		model.addAttribute("utilisateur",utilisateur);
		model.addAttribute("topo", new Topo());
		model.addAttribute("listeTopos", topoRepository.findAll());
		
		
		
		return "espaceperso";
	}
	
	
}