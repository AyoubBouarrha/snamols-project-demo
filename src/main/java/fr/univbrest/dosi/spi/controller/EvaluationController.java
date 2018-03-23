package fr.univbrest.dosi.spi.controller;

import java.util.List;

import fr.univbrest.dosi.spi.bean.Promotion;
import fr.univbrest.dosi.spi.bean.PromotionPK;
import fr.univbrest.dosi.spi.service.EnseignantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import fr.univbrest.dosi.spi.bean.Enseignant;
import fr.univbrest.dosi.spi.bean.Evaluation;
import fr.univbrest.dosi.spi.service.EvaluationService;

@RestController
@RequestMapping("/evaluations")
public class EvaluationController {

	@Autowired
	private EvaluationService evaluationService;

	@Autowired
    private EnseignantService enseignantService;

	@RequestMapping(method = RequestMethod.POST)
	public Evaluation addEvaluation(@RequestBody Evaluation evaluation) {

		System.out.println(evaluation);
		try {
            return evaluationService.addEvaluation(evaluation);
		} catch (Exception e) {
			return null;
		}
		//return true;
	}

	@RequestMapping(method = RequestMethod.PUT)
	public boolean updateEvaluation(@RequestBody Evaluation evaluation) {
		try {
			evaluationService.updateEvaluation(evaluation);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	@RequestMapping(value = "/{idevaluation}", method = RequestMethod.DELETE)
	public final boolean deleteEvaluation(@PathVariable(value = "idevaluation") final long idEvaluation) {
		try {
			evaluationService.deleteEvaluation(idEvaluation);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}


	@RequestMapping(method = RequestMethod.GET)
	public final List<Evaluation> evaluations() {
        List<Evaluation> list =  (List<Evaluation>) evaluationService.listEvaluations();
        System.out.println(list.get(0));
        return list;
	}

	@RequestMapping(value = "/{idevaluation}")
	public final Evaluation getEvaluation(@PathVariable(value = "idevaluation") final long idevaluation) {
		return evaluationService.getEvaluation(idevaluation);
	}



	@RequestMapping(value = "/existeva/{idevaluation}")
	public final Boolean existEvaluation(@PathVariable(value = "idevaluation") final long idevaluation) {
		try {
			return evaluationService.existEvaluation(idevaluation);
		} catch (Exception e) {
			return false;
		}
	}


	@RequestMapping(value = "/getevadesignation/{designation}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public final List<Evaluation> getEvaluationByDesignation(@PathVariable(value = "designation") final String designation) {
		return evaluationService.getEvaluationByDesination(designation);
	}

	public EvaluationService getEvaluationService() {
		return evaluationService;
	}



}
