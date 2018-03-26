package fr.univbrest.dosi.spi.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.univbrest.dosi.spi.bean.QuestionEvaluation;
import fr.univbrest.dosi.spi.bean.RubriqueEvaluation;

public interface QuestionEvaluationRepository extends CrudRepository<QuestionEvaluation, Long> {

	List<QuestionEvaluation> findByIntitule(String intitule);
	List<QuestionEvaluation> findByRubriqueEvaluation(RubriqueEvaluation rubriqueEvaluation);
}
