package com.sif.p6.web;

import java.util.Optional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.sif.p6.entities.Emprunt;
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
	
		
	@RequestMapping(value="/admin/home")
	public String Home (){
		return "layout";
	}
	
	@RequestMapping(value="/admin/espaceperso")
	public String EspacePerso (Model model){
		
		Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		model.addAttribute("topo", new Topo());
		model.addAttribute("utilisateur", utilisateur);
		model.addAttribute("listeTopos", topoRepository.findTopoByUtilisateur(utilisateur.getId()));
		model.addAttribute("listeSpots", spotRepository.findSpotByUtilisateur(utilisateur.getId()));
		
		return "espaceperso";
	}
	
	@RequestMapping(value="/admin/deconnection")
	public String Deconnection (){
		 SecurityContextHolder.clearContext();
		 
		 return "redirect:/connection"; 
	}
	
	@RequestMapping(value="/admin/inscription")
	public String AdminInscription (){
		 
		 return "admininscription"; 
	}
	@RequestMapping(value="/admin/connection")
	public String AdminConnection (){
		 
		 return "adminconnection"; 
	}
	
	@RequestMapping(value="/admin/spots")
	public String spotsAdmin(Model model, 
		 @RequestParam(name="page", defaultValue="0")int p, 
		 @RequestParam(name="size", defaultValue="3")int s,
		 @RequestParam(name="motCle", defaultValue="")String mc) {
			
		Page<Spot> pageSpotsByNom = spotRepository.rechercherByNom("%"+mc+"%",PageRequest.of(p, s));
		int[] pagesSpot = new int [pageSpotsByNom.getTotalPages()];

		
		model.addAttribute("listeSpots", pageSpotsByNom.getContent());
		model.addAttribute("pages",pagesSpot);
		model.addAttribute("pageCourante",p);
		model.addAttribute("size",s);
		model.addAttribute("motCle",mc);


		return "spotsadmin";
	}
	
	@RequestMapping(value="/admin/rechercheParDepartement")
	public String ResultatRechercheDepartement(Model model, 
		 @RequestParam(name="page", defaultValue="0")int p, 
		 @RequestParam(name="size", defaultValue="3")int s,
		 @RequestParam(name="motCleDep", defaultValue="")String mcDep) {
			
		Page<Spot> pageSpotsByDepartement = spotRepository.rechercherByDepartement("%"+mcDep+"%",PageRequest.of(p, s));
		int[] pagesRechercheDep = new int [pageSpotsByDepartement.getTotalPages()];

		
		
		model.addAttribute("listeSpotsDepartement", pageSpotsByDepartement.getContent());
		model.addAttribute("pagesDep",pagesRechercheDep);
		model.addAttribute("pageCourante",p);
		model.addAttribute("size",s);
		model.addAttribute("motCle",mcDep);


		return "resultatrecherchedepartement";
	}
	
	/*
	@RequestMapping(value="/admin/rechercheParTopoDispo")
	public String ResultatRechercheTopoDispo(Model model, 
		 @RequestParam(name="page", defaultValue="0")int p, 
		 @RequestParam(name="size", defaultValue="3")int s) {
			
		Page<Spot> pageSpotsByTopoDispo = spotRepository.rechercherByTopoDispo( true, PageRequest.of(p, s));
		int[] pagesRechercheTopo = new int [pageSpotsByTopoDispo.getTotalPages()];
		
		model.addAttribute("listeSpotsTopo", pageSpotsByTopoDispo.getContent());
		model.addAttribute("pagesTopo",pagesRechercheTopo);
		model.addAttribute("pageCourante",p);
		model.addAttribute("size",s);


		return "resultatrecherchetopo";
	}
	*/
	
	
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
		
		Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		spot.setUtilisateur(utilisateur);
		
		spotRepository.save(spot);
		
		return "confirmationajoutspot";
  	}
	
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
		return "confirmationmodifspot";
	}
	
	@RequestMapping(value="/admin/supprimerspot", method=RequestMethod.GET)
	public String supprimerSpot (Long idSpot, int page, int size, String motCle) {
		spotRepository.deleteById(idSpot);
	
		return "redirect:/admin/spots?page="+page+"&size="+size+"&motCle="+motCle;
	}
	
	@RequestMapping(value="/admin/ajoutersecteur", method=RequestMethod.GET)
	public String ajouterSecteur(Model model, Long idSpot) { 
		model.addAttribute("secteur",new Secteur());
		model.addAttribute("listeSecteurs", secteurRepository.findSecteurBySpot(idSpot));
		model.addAttribute("idSpot", idSpot);
		return "formsecteur";
	}
	
	
	@RequestMapping(value="/admin/saveajoutsecteur", method=RequestMethod.POST)
	public String sauverAjoutSecteur(Model model, Long idSpot, @Valid Secteur secteur, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("listeSecteurs",secteurRepository.findSecteurBySpot(idSpot));
			model.addAttribute("secteur",secteur);
			
			return "formsecteur";
		}
		
		model.addAttribute("listeSecteurs",secteurRepository.findSecteurBySpot(idSpot));
		model.addAttribute("secteur",secteur);
		Spot spot = spotRepository.getOne(idSpot);
		secteur.setSpot(spot);
		secteurRepository.save(secteur);
		
		return "confirmationajoutsecteur";
	}
	
	@RequestMapping(value="/admin/modifiersecteur", method=RequestMethod.GET)
	public String modifierSecteur(Model model, Long idSecteur) {
		Optional<Secteur> optionalsecteur= secteurRepository.findById(idSecteur);
		Secteur secteur = optionalsecteur.get();
		model.addAttribute("secteur",secteur);
		return "modifsecteur";
	}
	
	@RequestMapping(value="/admin/savemodifsecteur", method=RequestMethod.POST)
	public String sauverModifSecteur(Model model, @Valid Secteur secteur, Long idSecteur, BindingResult bindingResult) {
   		model.addAttribute("secteur",secteur);
		if(bindingResult.hasErrors()) {
	   	 	return "modifsecteur";
		}
		
		Secteur secteurModifiee = secteurRepository.getOne(idSecteur);
		secteurModifiee.setNom(secteur.getNom());
		secteurRepository.save(secteurModifiee);

			
			return "confirmationmodifsecteur";
	}
	
	@RequestMapping(value="/admin/supprimersecteur", method=RequestMethod.GET)
	public String supprimerSecteur (Long idSecteur,Long idSpot ) {
		secteurRepository.deleteById(idSecteur);
	
		return "redirect:/admin/ajoutersecteur?idSpot="+idSpot;
	}
	
	@RequestMapping(value="/admin/ajoutervoie", method=RequestMethod.GET)
	public String ajouterVoie(Model model, Long idSecteur) { 
		model.addAttribute("voie", new Voie());
		model.addAttribute("listeVoies", voieRepository.findVoieBySecteur(idSecteur));
		model.addAttribute("idSecteur", idSecteur);
		
		return "formvoie";
	}
	
	
	@RequestMapping(value="/admin/saveajoutvoie", method=RequestMethod.POST)
	public String sauverAjoutVoie(Model model, Long idSecteur, @Valid Voie voie, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("listeVoies", voieRepository.findVoieBySecteur(idSecteur));
			model.addAttribute("voie",voie);
			
			return "formvoie";
		}
		
		model.addAttribute("listeVoies", voieRepository.findVoieBySecteur(idSecteur));
		model.addAttribute("voie",voie);
		Secteur secteur = secteurRepository.getOne(idSecteur);
		voie.setSecteur(secteur);
		voieRepository.save(voie);
		
		return "confirmationajoutvoie";
	}
	
	@RequestMapping(value="/admin/modifiervoie", method=RequestMethod.GET)
	public String modifierVoie(Model model, Long idVoie) {
		Optional<Voie> optionalvoie= voieRepository.findById(idVoie);
		Voie voie = optionalvoie.get();
		model.addAttribute("voie",voie);
		return "modifvoie";
	}
	
	@RequestMapping(value="/admin/savemodifvoie", method=RequestMethod.POST)
	public String sauverModifVoie(Model model, @Valid Voie voie, Long idVoie, BindingResult bindingResult) {
   		model.addAttribute("voie",voie);
		if(bindingResult.hasErrors()) {
	   	 	return "modifvoie";
		}
		
		Voie voieModifiee = voieRepository.getOne(idVoie);
		voieModifiee.setNom(voie.getNom());
		voieModifiee.setHauteur(voie.getHauteur());
		voieModifiee.setNombrePoints(voie.getNombrePoints());
		voieModifiee.setCotation(voie.getCotation());
		voieRepository.save(voieModifiee);

			
			return "confirmationmodifvoie";
	}
	
	@RequestMapping(value="/admin/supprimervoie", method=RequestMethod.GET)
	public String supprimerVoie (Long idVoie, Long idSecteur) {
		voieRepository.deleteById(idVoie);
		return "redirect:/admin/ajoutervoie?idSecteur="+idSecteur;
	}
	
	
	@RequestMapping(value="/admin/ajouterlongueur", method=RequestMethod.GET)
	public String ajouterLongueur(Model model, Long idVoie) { 
		model.addAttribute("longueur", new Longueur());
		model.addAttribute("listeLongueurs", longueurRepository.findLongueurByVoie(idVoie));
		model.addAttribute("idVoie", idVoie);
		return "formlongueur";
	}

	@RequestMapping(value="/admin/saveajoutlongueur", method=RequestMethod.POST)
	public String sauverAjoutLongueur(Model model, Long idVoie, @Valid Longueur longueur, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("listeLongueurs", longueurRepository.findLongueurByVoie(idVoie));
			model.addAttribute("longueur",longueur);
			return "formlongueur";
		}
			model.addAttribute("listeLongueurs", longueurRepository.findLongueurByVoie(idVoie));
			model.addAttribute("longueur",longueur);
			Voie voie = voieRepository.getOne(idVoie);
			longueur.setVoie(voie);
			longueurRepository.save(longueur);
			return "confirmationajoutlongueur";
	}
	
	@RequestMapping(value="/admin/modifierlongueur", method=RequestMethod.GET)
	public String modifierLongueur(Model model, Long idLongueur) {
		Optional<Longueur> optionallongueur= longueurRepository.findById(idLongueur);
		Longueur longueur = optionallongueur.get();
		model.addAttribute("longueur",longueur);
		return "modiflongueur";
	}
	
	@RequestMapping(value="/admin/savemodiflongueur", method=RequestMethod.POST)
	public String sauverModifLongueur(Model model, @Valid Longueur longueur, Long idLongueur, BindingResult bindingResult) {
   		model.addAttribute("longueur",longueur);
		if(bindingResult.hasErrors()) {
	   	 	return "modiflongueur";
		}
		
		Longueur longueurModifiee = longueurRepository.getOne(idLongueur);
		longueurModifiee.setNom(longueur.getNom());
		longueurModifiee .setCotation(longueur.getCotation());			
		longueurRepository.save(longueurModifiee);

			
			return "confirmationmodiflongueur";
	}
	
	@RequestMapping(value="/admin/supprimerlongueur", method=RequestMethod.GET)
	public String supprimerLongueur (Long idLongueur, Long idVoie) {
		longueurRepository.deleteById(idLongueur);
		return "redirect:/admin/ajouterlongueur?idVoie="+idVoie;
	}
	
	
	@RequestMapping(value="/admin/ajoutercommentaire", method=RequestMethod.GET)
	public String ajouterCommentaire(Model model, Long idSpot) { 
		model.addAttribute("commentaire", new Commentaire());
		model.addAttribute("listeCommentaires", commentaireRepository.findCommentaireBySpot(idSpot));
		model.addAttribute("idSpot", idSpot);

		return "formcommentaire";
	}

	@RequestMapping(value="/admin/saveajoutcommentaire", method=RequestMethod.POST)
	public String sauverAjoutCommentaire(Model model, Long idSpot, @Valid Commentaire commentaire, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("listeCommentaires", commentaireRepository.findCommentaireBySpot(idSpot));
			model.addAttribute("commentaire",commentaire);
			model.addAttribute("idSpot", idSpot);

			return "formcommentaire";
		}
			model.addAttribute("listeCommentaires", commentaireRepository.findCommentaireBySpot(idSpot));
			model.addAttribute("commentaire",commentaire);
			model.addAttribute("idSpot", idSpot);

			
			Spot spot = spotRepository.getOne(idSpot);
			commentaire.setSpot(spot);
			
			Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			commentaire.setUtilisateur(utilisateur);
			
			commentaireRepository.save(commentaire);
			
			return "confirmationajoutcommentaire";
	}
	
	@RequestMapping(value="/admin/modifiercommentaire", method=RequestMethod.GET)
	public String modifierCommentaire(Model model, Long idCommentaire) {
		Optional<Commentaire> optionalcommentaire= commentaireRepository.findById(idCommentaire);
		Commentaire commentaire = optionalcommentaire.get();
		model.addAttribute("commentaire",commentaire);
		return "modifcommentaire";
	}
	
	@RequestMapping(value="/admin/savemodifcommentaire", method=RequestMethod.POST)
	public String sauverModifCommentaire(Model model, @Valid Commentaire commentaire, Long idCommentaire, BindingResult bindingResult) {
   		model.addAttribute("commentaire",commentaire);
		if(bindingResult.hasErrors()) {
	   	 	return "modifcommentaire";
		}
		
		Commentaire commentaireModifie = commentaireRepository.getOne(idCommentaire);
		commentaireModifie.setVerbatim(commentaire.getVerbatim());
				
		Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		commentaire.setUtilisateur(utilisateur);
		
		commentaireRepository.save(commentaireModifie);

			
			return "confirmationmodifcommentaire";
	}
	
	@RequestMapping(value="/admin/supprimercommentaire", method=RequestMethod.GET)
	public String supprimerCommentaire (Long idCommentaire, Long idSpot) {
		commentaireRepository.deleteById(idCommentaire);
		return "redirect:/admin/ajoutercommentaire?idSpot="+idSpot;
	}
	
	@RequestMapping(value="/admin/ajoutertopo", method=RequestMethod.GET)
	public String ajouterTopo(Model model, Long idSpot) { 
		model.addAttribute("topo", new Topo());
		model.addAttribute("listeTopos", topoRepository.findTopoBySpot(idSpot));
		model.addAttribute("idSpot", idSpot);
		
		return "formtopo";
	}

	@RequestMapping(value="/admin/saveajouttopo", method=RequestMethod.POST)
	public String sauverAjoutTopo(Model model, Long idSpot, @Valid Topo topo, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("listeTopos", topoRepository.findTopoBySpot(idSpot));
			model.addAttribute("idSpot", idSpot);

			return "formtopo";
		}
		
			model.addAttribute("listeTopos", topoRepository.findTopoBySpot(idSpot));
			model.addAttribute("idSpot", idSpot);

			Spot spot = spotRepository.getOne(idSpot);
			topo.setSpot(spot);
			
			Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			topo.setUtilisateur(utilisateur);
			
			topoRepository.save(topo);
			
			return "confirmationajouttopo";
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

		return "confirmationmodiftopo";
	}
	
	@RequestMapping(value="/admin/supprimertopo", method=RequestMethod.GET)
	public String supprimerTopo (Long idTopo) {
		topoRepository.deleteById(idTopo);
		return "redirect:/admin/espaceperso";
	}
	
	
	@RequestMapping(value="/admin/taguerspot")
	public String taguerSpot(Model model,Long idSpot) {
		
		Optional<Spot> optionalspot=spotRepository.findById(idSpot);
		Spot spot = optionalspot.get();
		spot.setTag(!spot.isTag());
		
		spotRepository.save(spot);		

		return "confirmationmodiftag";
	}
	
	@RequestMapping(value="/admin/statuttopo" , method=RequestMethod.GET)
	public String statutTopo (Model model, Long idTopo){
		
		Optional<Topo> optionaltopo=topoRepository.findById(idTopo);
		Topo topo = optionaltopo.get();
		topo.setStatut(!topo.isStatut());
		
		topoRepository.save(topo);
		
		
		return "confirmationmodifdispotopo";
	}
	
	@RequestMapping(value="/admin/demandertopo", method=RequestMethod.GET)
	public String demanderTopo(Model model, Long idTopo, Topo topo) { 
		model.addAttribute("topo", topo);
		model.addAttribute("emprunt", new Emprunt());
		model.addAttribute("idTopo", idTopo);
		
		return "demandetopo";
	}
	
	
	
}
