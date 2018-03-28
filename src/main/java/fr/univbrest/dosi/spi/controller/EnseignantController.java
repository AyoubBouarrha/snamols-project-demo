package fr.univbrest.dosi.spi.controller;

import java.util.List;

import fr.univbrest.dosi.spi.exception.SPIException;
import fr.univbrest.dosi.spi.exception.SpiExceptionCode;
import fr.univbrest.dosi.spi.util.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univbrest.dosi.spi.bean.Enseignant;
import fr.univbrest.dosi.spi.bean.Promotion;
import fr.univbrest.dosi.spi.bean.UniteEnseignement;
import fr.univbrest.dosi.spi.bean.User;
import fr.univbrest.dosi.spi.service.EnseignantService;
import fr.univbrest.dosi.spi.service.PromotionService;
import fr.univbrest.dosi.spi.service.UniteEnseignementService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author DOSI
 *
 */

@RestController
public class EnseignantController {
	/**
	 *
	 */
	@Autowired
	private EnseignantService enseignantService;

	/**
	 *
	 */
	@Autowired
	private PromotionService promotionService;

	/**
	 *
	 */
	@Autowired
	private UniteEnseignementService uniteEnseignementService;

	/*
	 * private enum TypeDroit { CREATE, DELETE, MODIFY, SELECT, }
	 *
	 * /** Service de gestion des enseignants
	 */
	@Autowired
	EnseignantService ensService;

	// private final Map<TypeDroit, List<Role>> mapDroits = new HashMap<EnseignantController.TypeDroit, List<Role>>();

	/**
	 * User
	 */
	@Autowired
	User user;

	/*
	 * public EnseignantController() { this.configure(); }
	 *
	 * private void checkDroits(final TypeDroit typeDroit) { if (!this.mapDroits.get(typeDroit).contains(this.user.getRoles())) { throw new SPIException(SpiExceptionCode.NOT_ENOUGH_RIGHT,
	 * "L'utilisateur n'a pas les droits suffisants pour effectuer l'action demandée"); } }
	 *
	 * private void configure() { this.mapDroits.put(TypeDroit.CREATE, Arrays.asList(Role.ADMIN, Role.PROF)); this.mapDroits.put(TypeDroit.SELECT, Arrays.asList(Role.ETUDIANT, Role.ADMIN, Role.PROF));
	 * this.mapDroits.put(TypeDroit.MODIFY, Arrays.asList(Role.ADMIN, Role.PROF)); this.mapDroits.put(TypeDroit.DELETE, Arrays.asList(Role.ADMIN)); }
	 */

	/**
	 *
	 * @param enseignant
	 *            l'entité de l'enseignant
	 * @return le message d'ajout
	 */
	// @RequestMapping(value="/ajouterEnseignant" , headers="Accept=application/json", method=RequestMethod.POST)
	@RequestMapping(value = "/ajouterEnseignant", method = RequestMethod.POST, consumes = { "application/json;charset=UTF-8" }, produces = { "application/json;charset=UTF-8" })
	public final boolean addEnseignant(@RequestBody final Enseignant enseignant,HttpServletRequest request) {
		// this.checkDroits(TypeDroit.CREATE);

        if(Connection.currentUser(request) == null || !Connection.currentUser(request).getRole().equals("Admin"))
            throw  new SPIException(SpiExceptionCode.NOT_ENOUGH_RIGHT,"access denied");
        try {
            enseignantService.addEnseignant(enseignant);

            return true;
        }
        catch (Exception e){
            return false;
        }

	}

	/**
	 *
	 * @param noEnseignant
	 *            l'id de l'enseignant
	 */
	@RequestMapping(value = "/deleteEnseignant/{noenseignant}")
	public final boolean deleteEnseignant(@PathVariable(value = "noenseignant") final Long noEnseignant,HttpServletRequest request) {
		// this.checkDroits(TypeDroit.DELETE);

        if(Connection.currentUser(request) == null || !Connection.currentUser(request).getRole().equals("Admin"))
            throw  new SPIException(SpiExceptionCode.NOT_ENOUGH_RIGHT,"access denied");
        try {
            enseignantService.deleteEnseignant(noEnseignant);
            return true;
        }
        catch (Exception e){
            return false;

        }

	}

	/**
	 *
	 * @return liste des enseignant
	 */
	@RequestMapping("/ens")
	public final Iterable<Enseignant> enseignant() {
		// Iterable<Enseignant> enseignants = enseignantService.listens();
		/*
		 * for(Enseignant ens : enseignants){ System.out.println("OK traitement "+ ens.getNom()); }
		 */
		// this.checkDroits(TypeDroit.SELECT);
		return enseignantService.listens();
	}

	/**
	 *
	 * @param noEnseignant
	 *            l'id de l'enseignant
	 * @return un boolean
	 */
	@RequestMapping(value = "/existens/{noenseignant}")
	public final Boolean existEnseignant(@PathVariable(value = "noenseignant") final Long noEnseignant) {
		return enseignantService.existEnseignant(noEnseignant);
	}

	/**
	 *
	 * @param noEnseignant
	 *            l'id de l'enseignant
	 * @return un enseignant
	 */
	@RequestMapping(value = "/getens/{noenseignant}")
	public final Enseignant getEnseignant(@PathVariable(value = "noenseignant") final Long noEnseignant) {
		// this.checkDroits(TypeDroit.SELECT);
		return enseignantService.getEnseignant(noEnseignant);
	}

	/**
	 *
	 * @param nom
	 *            de recherche pour un enseignant
	 * @return list des enseignant ayant le parmetre nom
	 */
	// @RequestMapping(value ="/getens/{id}")
	@RequestMapping(value = "/getensnom/{nom}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public final List<Enseignant> getEnseignantByNom(@PathVariable(value = "nom") final String nom) {
		// this.checkDroits(TypeDroit.SELECT);
		return enseignantService.getEnseignantByNom(nom);
	}

	public EnseignantService getEnseignantService() {
		return enseignantService;
	}



	public PromotionService getPromotionService() {
		return promotionService;
	}



	public UniteEnseignementService getUniteEnseignementService() {
		return uniteEnseignementService;
	}

	public void setEnseignantService(final EnseignantService enseignantService) {
		this.enseignantService = enseignantService;
	}

	public void setPromotionService(final PromotionService promotionService) {
		this.promotionService = promotionService;
	}

	public void setUniteEnseignementService(final UniteEnseignementService uniteEnseignementService) {
		this.uniteEnseignementService = uniteEnseignementService;
	}

	/**
	 *
	 * @param enseignant
	 *            objet
	 * @return message de modification
	 */
	// @RequestMapping(value="/ajouterEnseignant" , headers="Accept=application/json", method=RequestMethod.POST)
	@RequestMapping(value = "/updateEnseignant", method = RequestMethod.PUT, consumes = { "application/json;charset=UTF-8" }, produces = { "application/json;charset=UTF-8" })
	public final boolean updateEnseignant(@RequestBody final Enseignant enseignant,HttpServletRequest request) {

        if(Connection.currentUser(request) == null || !Connection.currentUser(request).getRole().equals("Admin"))
            throw  new SPIException(SpiExceptionCode.NOT_ENOUGH_RIGHT,"access denied");
		// this.checkDroits(TypeDroit.MODIFY);
        try {
            enseignantService.updateEnseignant(enseignant);
            return true;

        }catch (Exception e){
            return false;
        }
	}
}
