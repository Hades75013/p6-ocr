package com.sif.p6.web;

import java.util.Optional;
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
import com.sif.p6.dao.VoieRepository;
import com.sif.p6.entities.Commentaire;
import com.sif.p6.entities.Longueur;
import com.sif.p6.entities.Secteur;
import com.sif.p6.entities.Spot;
import com.sif.p6.entities.Voie;

@Controller
public class AdminController {

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
	
	@RequestMapping(value="/admin/home")
	public String Home (){
		return "layout";
	}
	
	@RequestMapping(value="/admin/spots")
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

		return "spotsadmin";
	}
	
	@RequestMapping(value="/admin/ajouterspot", method=RequestMethod.GET)
	public String ajouterSpot(Model model) {
		model.addAttribute("spot",new Spot());
		
		return "formspot";
	}
	
	@RequestMapping(value="/admin/saveajoutspot", method=RequestMethod.POST)
	public String sauverAjoutSpot(Model model, @Valid Spot spot, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("spot",spot);
			return "formspot";
		}
		
		model.addAttribute("spot",spot);
		spotRepository.save(spot);
		return "confirmationajoutspot";
  	}
	
	@RequestMapping(value="/admin/modifierspot", method=RequestMethod.GET)
	public String modifierSpot(Model model, Long id) {
		Optional<Spot> optionalspot=spotRepository.findById(id);
		Spot spot = optionalspot.get();
		model.addAttribute("spot",spot);
		return "modifspot";
	}
	
	@RequestMapping(value="/admin/savemodifspot", method=RequestMethod.POST)
	public String sauverModifSpot(Model model, @Valid Spot spot, Long id, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
	   	 	model.addAttribute("spot",spot);
			return "modifspot";
		}
		
		model.addAttribute("spot",spot);
		spotRepository.save(spot);
		return "confirmationmodifspot";
	}
	
	@RequestMapping(value="/admin/supprimerspot", method=RequestMethod.GET)
	public String supprimerSpot (Long id, int page, int size, String motCle) {
		spotRepository.deleteById(id);
	
		return "redirect:/admin/spots?page="+page+"&size="+size+"&motCle="+motCle;
	}
	
	@RequestMapping(value="/admin/ajoutersecteur", method=RequestMethod.GET)
	public String ajouterSecteur(Model model, Long id) { 
		model.addAttribute("secteur",new Secteur());
		model.addAttribute("listeSecteurs", secteurRepository.findSecteurBySpot(id));
		model.addAttribute("idSpot", id);
		return "formsecteur";
	}
	
	
	@RequestMapping(value="/admin/saveajoutsecteur", method=RequestMethod.POST)
	public String sauverAjoutSecteur(Model model, Long id, @Valid Secteur secteur, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("listeSecteurs",secteurRepository.findSecteurBySpot(id));
			model.addAttribute("secteur",secteur);
			
			return "formsecteur";
		}
		
		model.addAttribute("listeSecteurs",secteurRepository.findSecteurBySpot(id));
		model.addAttribute("secteur",secteur);
		Spot spot = spotRepository.getOne(id);
		secteur.setSpot(spot);
		secteurRepository.save(secteur);
		
		return "confirmationajoutsecteur";
	}
	
	@RequestMapping(value="/admin/modifiersecteur", method=RequestMethod.GET)
	public String modifierSecteur(Model model, Long id) {
		Optional<Secteur> optionalsecteur= secteurRepository.findById(id);
		Secteur secteur = optionalsecteur.get();
		model.addAttribute("secteur",secteur);
		return "modifsecteur";
	}
	
	@RequestMapping(value="/admin/savemodifsecteur", method=RequestMethod.POST)
	public String sauverModifSecteur(Model model, @Valid Secteur secteur, Long id, BindingResult bindingResult) {
   		model.addAttribute("secteur",secteur);
		if(bindingResult.hasErrors()) {
	   	 	return "modifsecteur";
		}
		
		Secteur secteurModifiee = secteurRepository.getOne(id);
		secteurModifiee.setNom(secteur.getNom());
		secteurRepository.save(secteurModifiee);

			
			return "confirmationmodifsecteur";
	}
	
	@RequestMapping(value="/admin/supprimersecteur", method=RequestMethod.GET)
	public String supprimerSecteur (Long idSecteur,Long idSpot ) {
		secteurRepository.deleteById(idSecteur);
	
		return "redirect:/admin/ajoutersecteur?id="+idSpot;
	}
	
	@RequestMapping(value="/admin/ajoutervoie", method=RequestMethod.GET)
	public String ajouterVoie(Model model, Long id) { 
		model.addAttribute("voie", new Voie());
		model.addAttribute("listeVoies", voieRepository.findVoieBySecteur(id));
		model.addAttribute("idSecteur", id);
		
		return "formvoie";
	}
	
	
	@RequestMapping(value="/admin/saveajoutvoie", method=RequestMethod.POST)
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
	
	@RequestMapping(value="/admin/modifiervoie", method=RequestMethod.GET)
	public String modifierVoie(Model model, Long id) {
		Optional<Voie> optionalvoie= voieRepository.findById(id);
		Voie voie = optionalvoie.get();
		model.addAttribute("voie",voie);
		return "modifvoie";
	}
	
	@RequestMapping(value="/admin/savemodifvoie", method=RequestMethod.POST)
	public String sauverModifVoie(Model model, @Valid Voie voie, Long id, BindingResult bindingResult) {
   		model.addAttribute("voie",voie);
		if(bindingResult.hasErrors()) {
	   	 	return "modifvoie";
		}
		
		Voie voieModifiee = voieRepository.getOne(id);
		voieModifiee.setNom(voie.getNom());
		voieModifiee.setHauteur(voie.getHauteur());
		voieModifiee.setNombrePoints(voie.getNombrePoints());
		voieModifiee .setCotation(voie.getCotation());
		voieRepository.save(voieModifiee);

			
			return "confirmationmodifvoie";
	}
	
	@RequestMapping(value="/admin/supprimervoie", method=RequestMethod.GET)
	public String supprimerVoie (Long idVoie, Long idSecteur) {
		voieRepository.deleteById(idVoie);
		return "redirect:/admin/ajoutervoie?id="+idSecteur;
	}
	
	
	@RequestMapping(value="/admin/ajouterlongueur", method=RequestMethod.GET)
	public String ajouterLongueur(Model model, Long id) { 
		model.addAttribute("longueur", new Longueur());
		model.addAttribute("listeLongueurs", longueurRepository.findLongueurByVoie(id));
		model.addAttribute("idVoie", id);
		return "formlongueur";
	}

	@RequestMapping(value="/admin/saveajoutlongueur", method=RequestMethod.POST)
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
	
	@RequestMapping(value="/admin/modifierlongueur", method=RequestMethod.GET)
	public String modifierLongueur(Model model, Long id) {
		Optional<Longueur> optionallongueur= longueurRepository.findById(id);
		Longueur longueur = optionallongueur.get();
		model.addAttribute("longueur",longueur);
		return "modiflongueur";
	}
	
	@RequestMapping(value="/admin/savemodiflongueur", method=RequestMethod.POST)
	public String sauverModifLongueur(Model model, @Valid Longueur longueur, Long id, BindingResult bindingResult) {
   		model.addAttribute("longueur",longueur);
		if(bindingResult.hasErrors()) {
	   	 	return "modiflongueur";
		}
		
		Longueur longueurModifiee = longueurRepository.getOne(id);
		longueurModifiee.setNom(longueur.getNom());
		longueurModifiee .setCotation(longueur.getCotation());			
		longueurRepository.save(longueurModifiee);

			
			return "confirmationmodiflongueur";
	}
	
	@RequestMapping(value="/admin/supprimerlongueur", method=RequestMethod.GET)
	public String supprimerLongueur (Long idLongueur, Long idVoie) {
		longueurRepository.deleteById(idLongueur);
		return "redirect:/admin/ajouterlongueur?id="+idVoie;
	}
	
	
	@RequestMapping(value="/admin/ajoutercommentaire", method=RequestMethod.GET)
	public String ajouterCommentaire(Model model, Long id) { 
		model.addAttribute("commentaire", new Commentaire());
		model.addAttribute("listeCommentaires", commentaireRepository.findCommentaireBySpot(id));
		model.addAttribute("idSpot", id);
		return "formcommentaire";
	}

	@RequestMapping(value="/admin/saveajoutcommentaire", method=RequestMethod.POST)
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
	
	@RequestMapping(value="/admin/modifiercommentaire", method=RequestMethod.GET)
	public String modifierCommentaire(Model model, Long id) {
		Optional<Commentaire> optionalcommentaire= commentaireRepository.findById(id);
		Commentaire commentaire = optionalcommentaire.get();
		model.addAttribute("commentaire",commentaire);
		return "modifcommentaire";
	}
	
	@RequestMapping(value="/admin/savemodifcommentaire", method=RequestMethod.POST)
	public String sauverModifCommentaire(Model model, @Valid Commentaire commentaire, Long id, BindingResult bindingResult) {
   		model.addAttribute("commentaire",commentaire);
		if(bindingResult.hasErrors()) {
	   	 	return "modifcommentaire";
		}
		
		Commentaire commentaireModifie = commentaireRepository.getOne(id);
		commentaireModifie.setVerbatim(commentaire.getVerbatim());
				
		commentaireRepository.save(commentaireModifie);

			
			return "confirmationmodifcommentaire";
	}
	
	@RequestMapping(value="/admin/supprimercommentaire", method=RequestMethod.GET)
	public String supprimerCommentaire (Long idCommentaire, Long idSpot) {
		commentaireRepository.deleteById(idCommentaire);
		return "redirect:/admin/ajoutercommentaire?id="+idSpot;
	}
	
	
	
	
	
	@RequestMapping(value="/403")
	public String accesRefuse (){
		return "403";
	}
	
}
