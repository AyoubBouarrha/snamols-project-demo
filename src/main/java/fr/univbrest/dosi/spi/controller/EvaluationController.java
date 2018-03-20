package fr.univbrest.dosi.spi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univbrest.dosi.spi.bean.Enseignant;
import fr.univbrest.dosi.spi.bean.Evaluation;
import fr.univbrest.dosi.spi.service.EvaluationService;

@RestController
@RequestMapping("/evaluations")
public class EvaluationController {

	@Autowired
	private EvaluationService evaluationService;

	@RequestMapping(method = RequestMethod.POST)
	public boolean addEvaluation(@RequestBody Evaluation evaluation) {
		System.out.println("evaluation");
		/*Enseignant ens = new Enseignant();
        ens.setNoEnseignant(6L);
        evaluation.setEnseignant(ens);*/
		System.out.println(evaluation);
		try {
			evaluationService.addEvaluation(evaluation);
			return true;
		} catch (Exception e) {
			System.out.println("eeeeee");
			e.printStackTrace();
			return false;
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
