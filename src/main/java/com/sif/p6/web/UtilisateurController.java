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

import com.sif.p6.dao.CommentaireRepository;
import com.sif.p6.dao.LongueurRepository;
import com.sif.p6.dao.SecteurRepository;
import com.sif.p6.dao.SpotRepository;
import com.sif.p6.dao.TopoRepository;
import com.sif.p6.dao.VoieRepository;
import com.sif.p6.entities.Commentaire;
import com.sif.p6.entities.Longueur;
import com.sif.p6.entities.Secteur;
import com.sif.p6.entities.Spot;
import com.sif.p6.entities.Topo;
import com.sif.p6.entities.Voie;

@Controller
public class UtilisateurController {

	@Autowired
	private SpotRepository spotRepository;
	
	@Autowired
	private SecteurRepository secteurRepository;
	
	@Autowired
	private VoieRepository voieRepository;
	
	@Autowired
	private LongueurRepository longueurRepository;
	
	@Autowired
	private CommentaireRepository commentaireRepository;
	
	@Autowired
	private TopoRepository topoRepository;
	
	
	
	@RequestMapping(value="/user/home")
	public String Home (){
		return "layout";
	}
	
	@RequestMapping(value="/user/espaceperso")
	public String EspacePerso (Model model,Long id){
		model.addAttribute("topo", new Topo());
		model.addAttribute("listeTopos", topoRepository.findAll());
		
		return "espaceperso";
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
	
	
	@RequestMapping(value="/user/ajoutersecteur", method=RequestMethod.GET)
	public String ajouterSecteur(Model model, Long id) { 
		model.addAttribute("secteur",new Secteur());
		model.addAttribute("listeSecteurs", secteurRepository.findSecteurBySpot(id));
		model.addAttribute("idSpot", id);
		return "formsecteur";
	}
	
	
	@RequestMapping(value="/user/saveajoutsecteur", method=RequestMethod.POST)
	public String sauverAjoutSecteur(Model model, Long id, @Valid Secteur secteur, BindingResult bindingResult) {
		
		model.addAttribute("listeSecteurs",secteurRepository.findSecteurBySpot(id));
		model.addAttribute("secteur",secteur);
		
		if(bindingResult.hasErrors()) {
			return "formsecteur";
		}
		
		Spot spot = spotRepository.getOne(id);
		secteur.setSpot(spot);
		secteurRepository.save(secteur);
		
		return "confirmationajoutsecteur";
	}
	
	
	@RequestMapping(value="/user/ajoutervoie", method=RequestMethod.GET)
	public String ajouterVoie(Model model, Long id) { 
		model.addAttribute("voie", new Voie());
		model.addAttribute("listeVoies", voieRepository.findVoieBySecteur(id));
		model.addAttribute("idSecteur", id);
		
		return "formvoie";
	}
	
	
	@RequestMapping(value="/user/saveajoutvoie", method=RequestMethod.POST)
	public String sauverAjoutVoie(Model model, Long id, @Valid Voie voie, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("listeVoies", voieRepository.findVoieBySecteur(id));
			model.addAttribute("voie",voie);
			
			return "formvoie";
		}
		
		model.addAttribute("listeVoies", voieRepository.findVoieBySecteur(id));
		model.addAttribute("voie",voie);
		Secteur secteur = secteurRepository.getOne(id);
		voie.setSecteur(secteur);
		voieRepository.save(voie);
		
		return "confirmationajoutvoie";
	}
	
	@RequestMapping(value="/user/ajouterlongueur", method=RequestMethod.GET)
	public String ajouterLongueur(Model model, Long id) { 
		model.addAttribute("longueur", new Longueur());
		model.addAttribute("listeLongueurs", longueurRepository.findLongueurByVoie(id));
		model.addAttribute("idVoie", id);
		return "formlongueur";
	}

	@RequestMapping(value="/user/saveajoutlongueur", method=RequestMethod.POST)
	public String sauverAjoutLongueur(Model model, Long id, @Valid Longueur longueur, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("listeLongueurs", longueurRepository.findLongueurByVoie(id));
			model.addAttribute("longueur",longueur);
			return "formlongueur";
		}
			model.addAttribute("listeLongueurs", longueurRepository.findLongueurByVoie(id));
			model.addAttribute("longueur",longueur);
			Voie voie = voieRepository.getOne(id);
			longueur.setVoie(voie);
			longueurRepository.save(longueur);
			return "confirmationajoutlongueur";
	}
	
	@RequestMapping(value="/user/ajoutercommentaire", method=RequestMethod.GET)
	public String ajouterCommentaire(Model model, Long id) { 
		model.addAttribute("commentaire", new Commentaire());
		model.addAttribute("listeCommentaires", commentaireRepository.findCommentaireBySpot(id));
		model.addAttribute("idSpot", id);
		return "formcommentaire";
	}

	@RequestMapping(value="/user/saveajoutcommentaire", method=RequestMethod.POST)
	public String sauverAjoutCommentaire(Model model, Long id, @Valid Commentaire commentaire, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("listeCommentaires", commentaireRepository.findCommentaireBySpot(id));
			model.addAttribute("commentaire",commentaire);
			return "formcommentaire";
		}
			model.addAttribute("listeCommentaires", commentaireRepository.findCommentaireBySpot(id));
			model.addAttribute("commentaire",commentaire);
			Spot spot = spotRepository.getOne(id);
			commentaire.setSpot(spot);
			commentaireRepository.save(commentaire);
			return "confirmationajoutcommentaire";
	}
	
	
	
}