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
import com.sif.p6.dao.ReservationRepository;
import com.sif.p6.dao.SecteurRepository;
import com.sif.p6.dao.SpotRepository;
import com.sif.p6.dao.TopoRepository;
import com.sif.p6.dao.VoieRepository;

import com.sif.p6.entities.Commentaire;
import com.sif.p6.entities.Longueur;
import com.sif.p6.entities.ResaStatutEnum;
import com.sif.p6.entities.Reservation;
import com.sif.p6.entities.Secteur;
import com.sif.p6.entities.Spot;
import com.sif.p6.entities.Topo;
import com.sif.p6.entities.Utilisateur;
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

	@Autowired
	private ReservationRepository reservationRepository;


	/*Méthodes communes à l'utilisateur connecté (role User) et l'administrateur (role Admin)*/
	
	@RequestMapping(value="user/espaceperso")
	public String EspacePerso (Model model){
	         
		Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addAttribute("utilisateur", utilisateur);

		return "espaceperso";
	}
	
	
	@RequestMapping(value="user/espacepersomesspots")
	public String EspacePersoSpots (Model model){
	         
		Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addAttribute("utilisateur", utilisateur);
		model.addAttribute("listeSpotsUtilisateur", spotRepository.findSpotByUtilisateur(utilisateur.getId()));
	
		return "espacepersospots";
	}
	
	
	@RequestMapping(value="user/espacepersomestopos")
	public String EspacePersoTopos (Model model){
	         
		Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addAttribute("utilisateur", utilisateur);
		model.addAttribute("listeToposUtilisateur", topoRepository.findTopoByUtilisateur(utilisateur.getId()));
		
		return "espacepersotopos";
	}
	
	
	@RequestMapping(value="user/topoDetails")
	public String EspacePersoToposDetails (Model model, Long idTopo){
	         
		Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	
		model.addAttribute("utilisateur", utilisateur);
		model.addAttribute("topo", topoRepository.getOne(idTopo));
		
		return "espacepersotoposdetails";
	}
	

	@RequestMapping(value="user/espacepersotoposdemandes")
	public String EspacePersoToposDemandes (Model model){
	         
		Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addAttribute("utilisateur", utilisateur);
		model.addAttribute("listeReservationsUtilisateur", reservationRepository.findResaByUtilisateur(utilisateur.getId()));
	
		return "espacepersotoposdemandes";
	}
	
	
	@RequestMapping(value="user/espacepersoreservations")
	public String EspacePersoReservations (Model model){
	         
		Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		model.addAttribute("utilisateur", utilisateur);
		model.addAttribute("listeReservationsToposUtilisateur", reservationRepository.findResaByTopoByUtilisateur(utilisateur.getId()));
	
		return "espacepersoreservations";
	}

	
	@RequestMapping(value="/user/listespots")
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

	
	@RequestMapping(value="/user/rechercheParDepartement")
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
	
	
	@RequestMapping(value="/user/rechercheParTopoDispo")
	public String ResultatRechercheTopoDispo(Model model, 
		@RequestParam(name="page", defaultValue="0")int p, 
		@RequestParam(name="size", defaultValue="3")int s) {

		Page<Topo> pageSpotsByTopoDispo = topoRepository.findTopoByStatut( true, PageRequest.of(p, s));
		int[] pagesRechercheTopo = new int [pageSpotsByTopoDispo.getTotalPages()];

		model.addAttribute("listeSpotsTopo", pageSpotsByTopoDispo.getContent());
		model.addAttribute("pagesTopo",pagesRechercheTopo);
		model.addAttribute("pageCourante",p);
		model.addAttribute("size",s);

		return "resultatrecherchetopo";
	}

	
	@RequestMapping(value="/user/ajouterspot", method=RequestMethod.GET)
	public String ajouterSpot(Model model) {

		model.addAttribute("spot",new Spot());

		return "formspot";

	}
	
	
	@RequestMapping(value="user/saveajoutspot", method=RequestMethod.POST)
	public String sauverAjoutSpot(Model model, @Valid Spot spot, BindingResult bindingResult) {

		if(bindingResult.hasErrors()) {
			
			model.addAttribute("spot",spot);
			
			return "formspot";
		}	

		model.addAttribute("spot",spot);

		Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		spot.setUtilisateur(utilisateur);	

		spotRepository.save(spot);		

		return "redirect:/user/listespots";
  	}


	@RequestMapping(value="/user/modifierspot", method=RequestMethod.GET)
	public String modifierSpot(Model model, Long idSpot) {

		Optional<Spot> optionalspot=spotRepository.findById(idSpot);
		Spot spot = optionalspot.get();

		model.addAttribute("spot",spot);

		return "modifspot";
	}

	
	@RequestMapping(value="/user/savemodifspot", method=RequestMethod.POST)
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

	

	@RequestMapping(value="/user/supprimerspot", method=RequestMethod.GET)
	public String supprimerSpot (Long idSpot) {

		spotRepository.deleteById(idSpot);

		return "redirect:/user/espacepersomesspots";
	}

	

	@RequestMapping(value="/user/ajoutersecteur", method=RequestMethod.GET)
	public String ajouterSecteur(Model model, Long idSpot) { 

		model.addAttribute("secteur",new Secteur());
		model.addAttribute("listeSecteurs", secteurRepository.findSecteurBySpot(idSpot));
		model.addAttribute("idSpot", idSpot);

		return "formsecteur";
	}

	

	

	@RequestMapping(value="/user/saveajoutsecteur", method=RequestMethod.POST)
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

		return "redirect:/user/ajoutersecteur?idSpot="+idSpot;
	}

	
	@RequestMapping(value="/user/ajoutervoie", method=RequestMethod.GET)
	public String ajouterVoie(Model model, Long idSecteur) { 

		model.addAttribute("voie", new Voie());
		model.addAttribute("listeVoies", voieRepository.findVoieBySecteur(idSecteur));
		model.addAttribute("idSecteur", idSecteur);
		
		return "formvoie";
	}


	@RequestMapping(value="/user/saveajoutvoie", method=RequestMethod.POST)
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

		return "redirect:/user/ajoutervoie?idSecteur="+idSecteur;
	}

	
	@RequestMapping(value="/user/ajouterlongueur", method=RequestMethod.GET)
	public String ajouterLongueur(Model model, Long idVoie) { 

		model.addAttribute("longueur", new Longueur());
		model.addAttribute("listeLongueurs", longueurRepository.findLongueurByVoie(idVoie));
		model.addAttribute("idVoie", idVoie);

		return "formlongueur";
	}


	@RequestMapping(value="/user/saveajoutlongueur", method=RequestMethod.POST)
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

			return "redirect:/user/ajouterlongueur?idVoie="+idVoie;
	}

	

	@RequestMapping(value="/user/ajoutercommentaire", method=RequestMethod.GET)
	public String ajouterCommentaire(Model model, Long idSpot) { 

		model.addAttribute("commentaire", new Commentaire());
		model.addAttribute("listeCommentaires", commentaireRepository.findCommentaireBySpot(idSpot));
		model.addAttribute("idSpot", idSpot);

		return "formcommentaire";
	}


	@RequestMapping(value="/user/saveajoutcommentaire", method=RequestMethod.POST)
	public String sauverAjoutCommentaire(Model model, Long idSpot, @Valid Commentaire commentaire, BindingResult bindingResult) {

		if(bindingResult.hasErrors()) {

			model.addAttribute("listeCommentaires", commentaireRepository.findCommentaireBySpot(idSpot));
			model.addAttribute("commentaire",commentaire);		
			model.addAttribute("idSpot",idSpot);		

			return "formcommentaire";
		}

			model.addAttribute("listeCommentaires", commentaireRepository.findCommentaireBySpot(idSpot));
			model.addAttribute("commentaire",commentaire);			
			model.addAttribute("idSpot",idSpot);		

			Spot spot = spotRepository.getOne(idSpot);
			commentaire.setSpot(spot);

			Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			commentaire.setUtilisateur(utilisateur);

			commentaireRepository.save(commentaire);

			return "redirect:/user/ajoutercommentaire?idSpot="+idSpot;
	}

	
	@RequestMapping(value="/user/ajoutertopo", method=RequestMethod.GET)
	public String ajouterTopo(Model model, Long idSpot) { 

		model.addAttribute("topo", new Topo());
		model.addAttribute("listeTopos", topoRepository.findTopoBySpot(idSpot));
		model.addAttribute("idSpot", idSpot);

		return "formtopo";
	}


	@RequestMapping(value="/user/saveajouttopo", method=RequestMethod.POST)
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

			return "redirect:/user/ajoutertopo?idSpot="+idSpot;
	}

	
	@RequestMapping(value="/user/modifiertopo", method=RequestMethod.GET)
	public String modifierTopo(Model model, Long idTopo) {

		Optional<Topo> optionaltopo= topoRepository.findById(idTopo);
		Topo topo = optionaltopo.get();

		model.addAttribute("topo",topo);

		return "modiftopo";
	}

	
	@RequestMapping(value="/user/savemodiftopo", method=RequestMethod.POST)
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

	
	@RequestMapping(value="/user/supprimertopo", method=RequestMethod.GET)
	public String supprimerTopo (Long idTopo) {

		topoRepository.deleteById(idTopo);

		return "redirect:/user/espaceperso";
	}

	
	@RequestMapping(value="/user/statuttopo" , method=RequestMethod.GET)
	public String statutTopo (Model model, Long idTopo){

		Optional<Topo> optionaltopo=topoRepository.findById(idTopo);
		Topo topo = optionaltopo.get();

		topo.setStatut(!topo.isStatut());	

		topoRepository.save(topo);

		return "redirect:/user/espacepersomestopos";
	}
	

	@RequestMapping(value="/user/demandertopo", method=RequestMethod.GET)
	public String demanderTopo(Model model, Long idTopo) { 
		
		Reservation reservation = new Reservation();
		reservation.setStatut(ResaStatutEnum.EN_COURS);
		
		Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		reservation.setUtilisateur(utilisateur);
		
		Topo topo = topoRepository.getOne(idTopo);
		reservation.setTopo(topo);

		reservationRepository.save(reservation);
		
		model.addAttribute("reservation", reservation);
			
		return "confirmationdemandetopo";
	}

	
	@RequestMapping(value="/user/resaOk", method=RequestMethod.GET)
	public String AccepterReservation(Model model, Long idReservation) { 
				
		Reservation reservation = reservationRepository.getOne(idReservation);
		reservation.getTopo().setStatut(false);
		topoRepository.save(reservation.getTopo());
		
	
		reservation.setStatut(ResaStatutEnum.ACCEPTEE);		
		reservationRepository.save(reservation);

		model.addAttribute("reservation", reservation);	
		
		return "confirmationresaok";
	}
	
	
	@RequestMapping(value="/user/refusResa", method=RequestMethod.GET)
	public String RefuserReservation(Model model, Long idReservation) { 
		
		Reservation reservation = reservationRepository.getOne(idReservation);	
		reservation.setStatut(ResaStatutEnum.REFUSEE);
		
		reservationRepository.save(reservation);

		model.addAttribute("reservation", reservation);
			
		return "confirmationrefusresa";
	}
	
	
	@RequestMapping(value="/user/deconnection")
	public String Deconnection (){

		 SecurityContextHolder.clearContext();	 

		 return "redirect:/connection"; 
	}

}