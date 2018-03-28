package fr.univbrest.dosi.spi.controller;

import java.util.List;

import fr.univbrest.dosi.spi.bean.Evaluation;
import fr.univbrest.dosi.spi.bean.Rubrique;
import fr.univbrest.dosi.spi.exception.SPIException;
import fr.univbrest.dosi.spi.exception.SpiExceptionCode;
import fr.univbrest.dosi.spi.service.EvaluationService;
import fr.univbrest.dosi.spi.util.Connection;
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

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rubrique-eval")
public class RubriqueEvaluationControlleur {

	RubriqueEvaluationService buisness ;

    EvaluationService buisnessEva ;


    @Autowired
    public RubriqueEvaluationControlleur(RubriqueEvaluationService buisness, EvaluationService buisnessEva) {
        this.buisness = buisness;
        this.buisnessEva = buisnessEva;
    }

    @RequestMapping(method = RequestMethod.GET)
	public List<RubriqueEvaluation> getAllRubriqueEvaluations(HttpServletRequest request)
	{
        if(Connection.currentUser(request) == null || !Connection.currentUser(request).getRole().equals("Prof"))
            throw  new SPIException(SpiExceptionCode.NOT_ENOUGH_RIGHT,"access denied");
		return buisness.getAllRubriqueEvaluation();
	}

	@RequestMapping(method = RequestMethod.GET , value="/{id}")
	public RubriqueEvaluation getSingleRubriqueEvaluation(@PathVariable("id") String id,HttpServletRequest request)
	{
        if(Connection.currentUser(request) == null || !Connection.currentUser(request).getRole().equals("Prof"))
            throw  new SPIException(SpiExceptionCode.NOT_ENOUGH_RIGHT,"access denied");
		return buisness.getSingleRubriqueEvaluation(Long.parseLong(id));
	}

	@RequestMapping(method = RequestMethod.DELETE , value="/{id}")
	public boolean deleteRubriqueEvaluation(@PathVariable("id") String id,HttpServletRequest request)
	{
        if(Connection.currentUser(request) == null || !Connection.currentUser(request).getRole().equals("Prof"))
            throw  new SPIException(SpiExceptionCode.NOT_ENOUGH_RIGHT,"access denied");
	    try {
            buisness.deleteRubriqueEvaluation(Long.parseLong(id));
            return true;
        }
        catch (Exception e){
	        return false;
        }
	}

	@RequestMapping(method = RequestMethod.POST)
	public RubriqueEvaluation addRubriqueEvaluation(@RequestBody RubriqueEvaluation rubrique,HttpServletRequest request)
	{
        if(Connection.currentUser(request) == null || !Connection.currentUser(request).getRole().equals("Prof"))
            throw  new SPIException(SpiExceptionCode.NOT_ENOUGH_RIGHT,"access denied");
		return buisness.addRubriqueEvaluation(rubrique) ;
	}


	@RequestMapping(method = RequestMethod.PUT)
	public RubriqueEvaluation  updateRubriqueEvaluation(@RequestBody RubriqueEvaluation rubrique,HttpServletRequest request)
	{
        if(Connection.currentUser(request) == null || !Connection.currentUser(request).getRole().equals("Prof"))
            throw  new SPIException(SpiExceptionCode.NOT_ENOUGH_RIGHT,"access denied");
		return buisness.updateRubriqueEvaluation(rubrique);

	}

    @RequestMapping(method = RequestMethod.GET , value="evaluation/{idEvaluation}")
    public List<RubriqueEvaluation> getRubriqueEvaluationsByIdEvluation(@PathVariable("idEvaluation") Long idEvaluation,HttpServletRequest request)
    {
        if(Connection.currentUser(request) == null || !Connection.currentUser(request).getRole().equals("Prof"))
            throw  new SPIException(SpiExceptionCode.NOT_ENOUGH_RIGHT,"access denied");
        System.out.println("--------------------------------------------------------"+idEvaluation);
        System.out.println(buisnessEva.getEvaluation(idEvaluation));
        return buisness.getRubriqueEvaluationsByIdEvluation(buisnessEva.getEvaluation(idEvaluation));
    }

    @RequestMapping(value = "notAffectedRubrique/evaluation/{idEvaluation}")
    public List<Rubrique> getQuestionsNotAffected(@PathVariable(value = "idEvaluation") int idEvaluation, HttpServletRequest request) {
        if(Connection.currentUser(request) == null || !Connection.currentUser(request).getRole().equals("Prof"))
            throw  new SPIException(SpiExceptionCode.NOT_ENOUGH_RIGHT,"access denied");
        return buisness.getRubriquesNotAffectedByEvaluation(buisnessEva.getEvaluation(idEvaluation));
    }





}
