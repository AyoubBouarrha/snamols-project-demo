package fr.univbrest.dosi.spi.controller;

import java.util.List;

import fr.univbrest.dosi.spi.bean.*;
import fr.univbrest.dosi.spi.exception.SPIException;
import fr.univbrest.dosi.spi.exception.SpiExceptionCode;
import fr.univbrest.dosi.spi.service.EnseignantService;
import fr.univbrest.dosi.spi.service.UserService;
import fr.univbrest.dosi.spi.util.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import fr.univbrest.dosi.spi.service.EvaluationService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/evaluations")
public class EvaluationController {

	@Autowired
	private EvaluationService evaluationService;

	@Autowired
    private EnseignantService enseignantService;

    @Autowired
    UserService userService;

	@RequestMapping(method = RequestMethod.POST)
	public Evaluation addEvaluation(@RequestBody Evaluation evaluation,HttpServletRequest request) {

        if(Connection.currentUser(request) == null || !Connection.currentUser(request).getRole().equals("Prof"))
            throw  new SPIException(SpiExceptionCode.NOT_ENOUGH_RIGHT,"access denied");


		System.out.println(evaluation);
		try {
            return evaluationService.addEvaluation(evaluation);
		} catch (Exception e) {
			return null;
		}
	}

	@RequestMapping(method = RequestMethod.PUT)
	public boolean updateEvaluation(@RequestBody Evaluation evaluation,HttpServletRequest request) {

        if(Connection.currentUser(request) == null || !Connection.currentUser(request).getRole().equals("Prof"))
            throw  new SPIException(SpiExceptionCode.NOT_ENOUGH_RIGHT,"access denied");
		try {
			evaluationService.updateEvaluation(evaluation);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	@RequestMapping(value = "/{idevaluation}", method = RequestMethod.DELETE)
	public final boolean deleteEvaluation(@PathVariable(value = "idevaluation") final long idEvaluation,HttpServletRequest request) {

        if(Connection.currentUser(request) == null || !Connection.currentUser(request).getRole().equals("Prof"))
            throw  new SPIException(SpiExceptionCode.NOT_ENOUGH_RIGHT,"access denied");
		try {
			evaluationService.deleteEvaluation(idEvaluation);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}


	@RequestMapping(method = RequestMethod.GET)
	public final List<Evaluation> evaluations(HttpServletRequest request)  {
	    if(Connection.currentUser(request) == null || !Connection.currentUser(request).getRole().equals("Prof"))
	         throw  new SPIException(SpiExceptionCode.NOT_ENOUGH_RIGHT,"access denied");
        List<Evaluation> list =  (List<Evaluation>) evaluationService.listEvaluations();
        System.out.println(list.get(0));
        return list;
	}

	@RequestMapping(value = "/{idevaluation}")
	public final Evaluation getEvaluation(@PathVariable(value = "idevaluation") final long idevaluation,HttpServletRequest request) {

        if(Connection.currentUser(request) == null || !Connection.currentUser(request).getRole().equals("Prof"))
            throw  new SPIException(SpiExceptionCode.NOT_ENOUGH_RIGHT,"access denied");

		return evaluationService.getEvaluation(idevaluation);
	}



	@RequestMapping(value = "/existeva/{idevaluation}")
	public final Boolean existEvaluation(@PathVariable(value = "idevaluation") final long idevaluation,HttpServletRequest request) {


        if(Connection.currentUser(request) == null || !Connection.currentUser(request).getRole().equals("Prof"))
            throw  new SPIException(SpiExceptionCode.NOT_ENOUGH_RIGHT,"access denied");
		try {
			return evaluationService.existEvaluation(idevaluation);
		} catch (Exception e) {
			return false;
		}
	}


	@RequestMapping(value = "/getevadesignation/{designation}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public final List<Evaluation> getEvaluationByDesignation(@PathVariable(value = "designation") final String designation,HttpServletRequest request) {

        if(Connection.currentUser(request) == null || !Connection.currentUser(request).getRole().equals("Prof"))
            throw  new SPIException(SpiExceptionCode.NOT_ENOUGH_RIGHT,"access denied");
		return evaluationService.getEvaluationByDesination(designation);
	}


    @RequestMapping(value = "/getEvaByEnseignant/{noEnseignant}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public final List<Evaluation> getEvaluationByEnseignant(@PathVariable(value = "noEnseignant") final Long noEnseignant,HttpServletRequest request) {

        if(Connection.currentUser(request) == null || !Connection.currentUser(request).getRole().equals("Prof"))
            throw  new SPIException(SpiExceptionCode.NOT_ENOUGH_RIGHT,"access denied");
	    Enseignant enseignant = enseignantService.getEnseignant(noEnseignant);
        return evaluationService.findByEnseignant(enseignant);
    }








}
