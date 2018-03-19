package fr.univbrest.dosi.spi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univbrest.dosi.spi.bean.Qualificatif;
import fr.univbrest.dosi.spi.bean.Rubrique;
import fr.univbrest.dosi.spi.service.RubriqueService;

@RestController
@RequestMapping("/rubriques")
public class RubriqueController {

	@Autowired
	private RubriqueService rubriqueService;
	
	@RequestMapping(method = RequestMethod.POST)
	public boolean addRubrique(@RequestBody final Rubrique rubrique) {
		try {
			rubriqueService.addRubrique(rubrique);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public boolean updateRubrique(@RequestBody Rubrique rubrique) {
		try {
			rubriqueService.updateRubrique(rubrique);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	
	@RequestMapping(value = "/{idrubrique}", method = RequestMethod.DELETE)
	public final boolean deleteRubrique(@PathVariable(value = "idrubrique") final long idRubrique) {
		try {
			rubriqueService.deleteRubrique(idRubrique);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}

	
	@RequestMapping(method = RequestMethod.GET)
	public final Iterable<Rubrique> rubrique() {
		return rubriqueService.listRubriques();
	}
	
	@RequestMapping(value = "/{idrubrique}")
	public final Rubrique getRubrique(@PathVariable(value = "idrubrique") final long idRubrique) {
		return rubriqueService.getRubrique(idRubrique);
	}


	
	@RequestMapping(value = "/existrub/{idrubrique}")
	public final Boolean existRubrique(@PathVariable(value = "idrubrique") final long idRubrique) {
		try {
			return rubriqueService.existRubrique(idRubrique);
		} catch (Exception e) {
			return false;
		}
	}
	
	
	@RequestMapping(value = "/getrubdesignation/{designation}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public final List<Rubrique> getRubriqueByDesignation(@PathVariable(value = "designation") final String designation) {
		return rubriqueService.getRubriqueByDesination(designation);
	}

	public RubriqueService getRubriqueService() {
		return rubriqueService;
	}



}
