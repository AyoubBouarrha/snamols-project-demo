package fr.univbrest.dosi.spi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univbrest.dosi.spi.bean.Rubrique;
import fr.univbrest.dosi.spi.service.RubriqueService;

@RestController

public class RubriqueController {

	@Autowired
	private RubriqueService rubriqueService;
	
	@RequestMapping(value = "/rubriqueEnseignant", method = RequestMethod.POST, consumes = { "application/json;charset=UTF-8" }, produces = { "application/json;charset=UTF-8" })
	public final String addRubrique(@RequestBody final Rubrique rubrique) {
		// this.checkDroits(TypeDroit.CREATE);
		try {
			rubriqueService.addRubrique(rubrique);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "la rubrique " + rubrique.getDesignation() + " " + rubrique.getOrdre() + " est ajouté";
	}

	
	@RequestMapping(value = "/deleteRubrique/{idrubrique}", method = RequestMethod.DELETE)
	public final boolean deleteRubrique(@PathVariable(value = "idrubrique") final long idRubrique) {
		// this.checkDroits(TypeDroit.DELETE);
		try {
			rubriqueService.deleteRubrique(idRubrique);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}

	
	@RequestMapping("/rub")
	public final Iterable<Rubrique> rubrique() {
		// Iterable<Enseignant> enseignants = enseignantService.listens();
		/*
		 * for(Enseignant ens : enseignants){ System.out.println("OK traitement "+ ens.getNom()); }
		 */
		// this.checkDroits(TypeDroit.SELECT);
		return rubriqueService.listens();
	}

	
	@RequestMapping(value = "/existrub/{idrubrique}")
	public final Boolean existRubrique(@PathVariable(value = "idrubrique") final long idRubrique) {
		try {
			return rubriqueService.existRubrique(idRubrique);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
	}

	
	@RequestMapping(value = "/getrub/{idrubrique}")
	public final Rubrique getRubrique(@PathVariable(value = "idrubrique") final long idRubrique) {
		// this.checkDroits(TypeDroit.SELECT);
		return rubriqueService.getRubrique(idRubrique);
	}

	
	// @RequestMapping(value ="/getrub/{id}")
	@RequestMapping(value = "/getrubdesignation/{designation}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public final List<Rubrique> getRubriqueByDesignation(@PathVariable(value = "designation") final String designation) {
		// this.checkDroits(TypeDroit.SELECT);
		return rubriqueService.getRubriqueByDesination(designation);
	}

	public RubriqueService getRubriqueService() {
		return rubriqueService;
	}



}
