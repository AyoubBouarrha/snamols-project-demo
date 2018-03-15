package fr.univbrest.dosi.spi.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univbrest.dosi.spi.bean.Qualificatif;

import fr.univbrest.dosi.spi.service.QualificatifService;


@RestController

public class QualificatifController {

	@Autowired
	private QualificatifService qualificatifService;
	
	@RequestMapping(value = "/qualificatif", method = RequestMethod.POST, consumes = { "application/json;charset=UTF-8" }, produces = { "application/json;charset=UTF-8" })
	public final String addQualificatif(@RequestBody final Qualificatif qualificatif) {
		// this.checkDroits(TypeDroit.CREATE);
		try {
			qualificatifService.addQualificatif(qualificatif);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "le qualificatif " + qualificatif.getMinimal()+ " " + qualificatif.getMaximal()+ " est ajouté";
	}

	
	@RequestMapping(value = "/deleteQualificatif/{idqualificatif}", method = RequestMethod.DELETE)
	public final boolean deleteQualificatif(@PathVariable(value = "idqualificatif") final long idqualificatif) {
		// this.checkDroits(TypeDroit.DELETE);
		try {
			qualificatifService.deleteQualificatif(idqualificatif);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}

	
	@RequestMapping("/qual")
	public final Iterable<Qualificatif> qualificatif() {
		// Iterable<Enseignant> enseignants = enseignantService.listens();
		/*
		 * for(Enseignant ens : enseignants){ System.out.println("OK traitement "+ ens.getNom()); }
		 */
		// this.checkDroits(TypeDroit.SELECT);
		return qualificatifService.listens();
	}

	
	@RequestMapping(value = "/existqual/{idqualificatif}")
	public final Boolean existRubrique(@PathVariable(value = "idqualificatif") final long idQualificatif) {
		try {
			return qualificatifService.existQualificatif(idQualificatif);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
	}

	
	@RequestMapping(value = "/getqual/{idqualificatif}")
	public final Qualificatif getQualificatif(@PathVariable(value = "idqualificatif") final long idQualificatif) {
		// this.checkDroits(TypeDroit.SELECT);
		return qualificatifService.getQualificatif(idQualificatif);
	}

	

	public QualificatifService getQualificatifService() {
		return qualificatifService;
	}



}

