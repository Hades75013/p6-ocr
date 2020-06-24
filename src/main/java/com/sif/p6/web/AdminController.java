package com.sif.p6.web;



import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
import com.sif.p6.entities.Utilisateur;
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

	@Autowired
	private TopoRepository topoRepository;


	@RequestMapping(value="/admin/modifierspot", method=RequestMethod.GET)
	public String modifierSpot(Model model, Long idSpot) {

		Optional<Spot> optionalspot=spotRepository.findById(idSpot);
		Spot spot = optionalspot.get();

		model.addAttribute("spot",spot);

		return "modifspot";
	}

	
	@RequestMapping(value="/admin/savemodifspot", method=RequestMethod.POST)
	public String sauverModifSpot(Model model, @Valid Spot spot, BindingResult bindingResult) {	

		if(bindingResult.hasErrors()) {

	   	 	model.addAttribute("spot",spot);

			return "modifspot";
		}

		model.addAttribute("spot",spot);

		Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		spot.setUtilisateur(utilisateur);

		spotRepository.save(spot);

		return "redirect:/user/listespots";
	}

	

	@RequestMapping(value="/admin/supprimerspot", method=RequestMethod.GET)
	public String supprimerSpot (Long idSpot, int page, int size) {

		spotRepository.deleteById(idSpot);

		return "redirect:/user/listespots?page="+page+"&size="+size;
	}

	
	@RequestMapping(value="/admin/modifiersecteur", method=RequestMethod.GET)
	public String modifierSecteur(Model model, Long idSecteur) {

		Optional<Secteur> optionalsecteur= secteurRepository.findById(idSecteur);
		Secteur secteur = optionalsecteur.get();

		model.addAttribute("secteur",secteur);
		model.addAttribute("idSpot",secteur.getSpot().getId());

		return "modifsecteur";
	}

	
	@RequestMapping(value="/admin/savemodifsecteur", method=RequestMethod.POST)
	public String sauverModifSecteur(Model model, @Valid Secteur secteur, Long idSecteur, Long idSpot, BindingResult bindingResult) {

   		model.addAttribute("secteur",secteur);
		model.addAttribute("idSpot",idSpot);

		if(bindingResult.hasErrors()) {

	   	 	return "modifsecteur";
		}	

		Secteur secteurModifiee = secteurRepository.getOne(idSecteur);
		secteurModifiee.setNom(secteur.getNom());

		secteurRepository.save(secteurModifiee);
			
		return "redirect:/user/ajoutersecteur?idSpot="+idSpot;
	}

	

	@RequestMapping(value="/admin/supprimersecteur", method=RequestMethod.GET)
	public String supprimerSecteur (Long idSecteur,Long idSpot ) {

		secteurRepository.deleteById(idSecteur);	

		return "redirect:/user/ajoutersecteur?idSpot="+idSpot;

	}


	@RequestMapping(value="/admin/modifiervoie", method=RequestMethod.GET)
	public String modifierVoie(Model model, Long idVoie) {

		Optional<Voie> optionalvoie= voieRepository.findById(idVoie);
		Voie voie = optionalvoie.get();

		model.addAttribute("voie",voie);
		model.addAttribute("idSecteur",voie.getSecteur().getId());

		return "modifvoie";
	}

	
	@RequestMapping(value="/admin/savemodifvoie", method=RequestMethod.POST)
	public String sauverModifVoie(Model model, @Valid Voie voie, Long idVoie, Long idSecteur, BindingResult bindingResult) {

   		model.addAttribute("voie",voie);
		model.addAttribute("idSecteur",idSecteur);

		if(bindingResult.hasErrors()) {

	   	 	return "modifvoie";
		}

		Voie voieModifiee = voieRepository.getOne(idVoie);
		voieModifiee.setNom(voie.getNom());
		voieModifiee.setHauteur(voie.getHauteur());
		voieModifiee.setNombrePoints(voie.getNombrePoints());
		voieModifiee.setCotation(voie.getCotation());

		voieRepository.save(voieModifiee);

		return "redirect:/user/ajoutervoie?idSecteur="+idSecteur;
	}

	

	@RequestMapping(value="/admin/supprimervoie", method=RequestMethod.GET)
	public String supprimerVoie (Long idVoie, Long idSecteur) {

		voieRepository.deleteById(idVoie);

		return "redirect:/user/ajoutervoie?idSecteur="+idSecteur;
	}

	
	@RequestMapping(value="/admin/modifierlongueur", method=RequestMethod.GET)
	public String modifierLongueur(Model model, Long idLongueur) {

		Optional<Longueur> optionallongueur= longueurRepository.findById(idLongueur);
		Longueur longueur = optionallongueur.get();

		model.addAttribute("longueur",longueur);
		model.addAttribute("idVoie",longueur.getVoie().getId());

		return "modiflongueur";
	}

	

	@RequestMapping(value="/admin/savemodiflongueur", method=RequestMethod.POST)
	public String sauverModifLongueur(Model model, @Valid Longueur longueur, Long idLongueur, Long idVoie, BindingResult bindingResult) {

   		model.addAttribute("longueur",longueur);
   		model.addAttribute("idVoie",idVoie);

		if(bindingResult.hasErrors()) {

	   	 	return "modiflongueur";
		}

		Longueur longueurModifiee = longueurRepository.getOne(idLongueur);
		longueurModifiee.setNom(longueur.getNom());
		longueurModifiee .setCotation(longueur.getCotation());			

		longueurRepository.save(longueurModifiee);

		return "redirect:/user/ajouterlongueur?idVoie="+idVoie;

	}

	
	@RequestMapping(value="/admin/supprimerlongueur", method=RequestMethod.GET)
	public String supprimerLongueur (Long idLongueur, Long idVoie) {

		longueurRepository.deleteById(idLongueur);

		return "redirect:/user/ajouterlongueur?idVoie="+idVoie;

	}

	
	@RequestMapping(value="/admin/modifiercommentaire", method=RequestMethod.GET)
	public String modifierCommentaire(Model model, Long idCommentaire) {

		Optional<Commentaire> optionalcommentaire= commentaireRepository.findById(idCommentaire);
		Commentaire commentaire = optionalcommentaire.get();

		model.addAttribute("commentaire",commentaire);
		model.addAttribute("idSpot",commentaire.getSpot().getId());

		return "modifcommentaire";
	}

	
	@RequestMapping(value="/admin/savemodifcommentaire", method=RequestMethod.POST)
	public String sauverModifCommentaire(Model model, @Valid Commentaire commentaire, Long idCommentaire, Long idSpot, BindingResult bindingResult) {

   		model.addAttribute("commentaire",commentaire);
		model.addAttribute("idSpot",idSpot);

		if(bindingResult.hasErrors()) {

	   	 	return "modifcommentaire";
		}

		Commentaire commentaireModifie = commentaireRepository.getOne(idCommentaire);
		commentaireModifie.setVerbatim(commentaire.getVerbatim());

		Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		commentaire.setUtilisateur(utilisateur);

		commentaireRepository.save(commentaireModifie);		

		return "redirect:/user/ajoutercommentaire?idSpot="+idSpot;
	}

	
	@RequestMapping(value="/admin/supprimercommentaire", method=RequestMethod.GET)
	public String supprimerCommentaire (Long idCommentaire, Long idSpot) {

		commentaireRepository.deleteById(idCommentaire);

		return "redirect:/user/ajoutercommentaire?idSpot="+idSpot;
	}


	@RequestMapping(value="/admin/modifiertopo", method=RequestMethod.GET)
	public String modifierTopo(Model model, Long idTopo) {

		Optional<Topo> optionaltopo= topoRepository.findById(idTopo);
		Topo topo = optionaltopo.get();

		model.addAttribute("topo",topo);

		return "modiftopo";
	}

	
	@RequestMapping(value="/admin/savemodiftopo", method=RequestMethod.POST)
	public String sauverModifTopo(Model model, @Valid Topo topo, Long idTopo, BindingResult bindingResult) {

   		model.addAttribute("topo",topo);

		if(bindingResult.hasErrors()) {

	   	 	return "modiftopo";
		}

		Topo topoModifie = topoRepository.getOne(idTopo);
		topoModifie.setNom(topo.getNom());
		topoModifie.setLieu(topo.getLieu());
		topoModifie.setDateParution(topo.getDateParution());
		topoModifie.setDescription(topo.getDescription());

		Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		topo.setUtilisateur(utilisateur);
				
		topoRepository.save(topoModifie);
		
		return "redirect:/user/espacepersomestopos";
	}

	
	@RequestMapping(value="/admin/supprimertopo", method=RequestMethod.GET)
	public String supprimerTopo (Long idTopo) {

		topoRepository.deleteById(idTopo);

		return "redirect:/user/espaceperso";
	}

	
	@RequestMapping(value="/admin/taguerspot")
	public String taguerSpot(Model model,Long idSpot) {

		Optional<Spot> optionalspot=spotRepository.findById(idSpot);
		Spot spot = optionalspot.get();
		spot.setTagOfficiel(!spot.isTagOfficiel());

		spotRepository.save(spot);		
		
		return "redirect:/user/listespots";
	}

	

	
}