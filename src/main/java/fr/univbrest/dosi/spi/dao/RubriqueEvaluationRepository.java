package fr.univbrest.dosi.spi.dao;

import org.springframework.data.repository.CrudRepository;

import fr.univbrest.dosi.spi.bean.*;

import java.util.List;

public interface RubriqueEvaluationRepository  extends CrudRepository <RubriqueEvaluation , Long>{
    List<RubriqueEvaluation> findByEvaluation (Evaluation evaluation);
}
