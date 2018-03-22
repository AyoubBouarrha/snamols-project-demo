package fr.univbrest.dosi.spi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univbrest.dosi.spi.bean.Question;
import fr.univbrest.dosi.spi.bean.RubriqueEvaluation;
import fr.univbrest.dosi.spi.service.QuestionService;
import fr.univbrest.dosi.spi.service.RubriqueEvaluationService;

@RestController
@RequestMapping("/rubrique-eval")
public class RubriqueEvaluationControlleur {

	RubriqueEvaluationService buisness ; 
	
	@Autowired
	public  RubriqueEvaluationControlleur (RubriqueEvaluationService  buisness)
	{
		this.buisness = buisness ; 
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<RubriqueEvaluation> getAllQuestions()
	{
		return buisness.getAllRubriqueEvaluation();
	}
	
	@RequestMapping(method = RequestMethod.GET , value="/{id}")
	public RubriqueEvaluation getSingleRubriqueEvaluation(@PathVariable("id") String id)
	{
		return buisness.getSingleRubriqueEvaluation(Long.parseLong(id, 10));
	}
	
	@RequestMapping(method = RequestMethod.DELETE , value="/{id}")
	public void deleteRubriqueEvaluation(@PathVariable("id") String id)
	{
		 buisness.deleteRubriqueEvaluation(Long.parseLong(id, 10));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public RubriqueEvaluation addRubriqueEvaluation(@RequestBody RubriqueEvaluation rubrique)
	{
		return buisness.addRubriqueEvaluation(rubrique) ; 
	}
	

	@RequestMapping(method = RequestMethod.PUT)
	public RubriqueEvaluation  updateRubriqueEvaluation(@RequestBody RubriqueEvaluation rubrique)
	{
		return buisness.updateRubriqueEvaluation(rubrique);
	}
	
	
	
	 
}
