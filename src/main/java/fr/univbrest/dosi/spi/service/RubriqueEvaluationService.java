package fr.univbrest.dosi.spi.service;

import java.util.ArrayList;
import java.util.List;

import fr.univbrest.dosi.spi.bean.*;
import fr.univbrest.dosi.spi.dao.RubriqueRepository;
import fr.univbrest.dosi.spi.exception.SPIException;
import fr.univbrest.dosi.spi.exception.SpiExceptionCode;
import fr.univbrest.dosi.spi.util.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univbrest.dosi.spi.dao.RubriqueEvaluationRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Service
public class RubriqueEvaluationService {


	private RubriqueEvaluationRepository repos ;

	private RubriqueRepository rubriqueRepository;



    public RubriqueEvaluationService(RubriqueEvaluationRepository repos, RubriqueRepository rubriqueRepository) {
        this.repos = repos;
        this.rubriqueRepository = rubriqueRepository;
    }

    public List<RubriqueEvaluation> getAllRubriqueEvaluation ()
	{
		return (List<RubriqueEvaluation>) this.repos.findAll() ;
	}

	public RubriqueEvaluation addRubriqueEvaluation(RubriqueEvaluation rubrique)
	{
		return this.repos.save(rubrique) ;
	}

	public void deleteRubriqueEvaluation (Long id )
	{
		this.repos.delete(id);
	}

	public RubriqueEvaluation getSingleRubriqueEvaluation ( Long id  )
	{
		return this.repos.findOne(id) ;
	}

	public RubriqueEvaluation updateRubriqueEvaluation ( RubriqueEvaluation rubrique )
	{
		return this.repos.save(rubrique) ;
	}


    public final List<Rubrique> getRubriquesNotAffectedByEvaluation(Evaluation evaluation) {

        List<Rubrique> listNotAffectedRub = new ArrayList<Rubrique>();
        List<RubriqueEvaluation> listAffectedRubEva = repos.findByEvaluation(evaluation);
        List<Rubrique> listAllRub = (List<Rubrique>) rubriqueRepository.findAll();

        List<Rubrique> listRubriquesAffected = new ArrayList<Rubrique>();


        for (RubriqueEvaluation rubEvaAffected : listAffectedRubEva)
            listRubriquesAffected.add(rubEvaAffected.getRubrique());

        for (Rubrique rubrique : listAllRub)
        {
            if(!listRubriquesAffected.contains(rubrique))
                listNotAffectedRub.add(rubrique);
        }

        return listNotAffectedRub;
    }


	public List<RubriqueEvaluation> getRubriqueEvaluationsByIdEvluation (Evaluation evaluation){
	    return this.repos.findByEvaluation(evaluation);
    }

    public List<RubriqueEvaluation> findByEvaluationAndRubrique (Evaluation evaluation , Rubrique rubrique){
	    return this.repos.findByEvaluationAndRubrique(evaluation , rubrique);
    }






}
