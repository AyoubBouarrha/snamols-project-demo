package fr.univbrest.dosi.spi.service;

import java.util.List;

import fr.univbrest.dosi.spi.bean.Evaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univbrest.dosi.spi.bean.RubriqueEvaluation;
import fr.univbrest.dosi.spi.dao.RubriqueEvaluationRepository;

@Service
public class RubriqueEvaluationService {


	private RubriqueEvaluationRepository repos ;

	@Autowired
	public RubriqueEvaluationService ( RubriqueEvaluationRepository repos )
	{
		this.repos = repos ;
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

	public List<RubriqueEvaluation> getRubriqueEvaluationsByIdEvluation (Evaluation evaluation){
	    return this.repos.findByEvaluation(evaluation);
    }

}
