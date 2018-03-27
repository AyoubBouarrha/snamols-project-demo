package fr.univbrest.dosi.spi.dao;

import java.util.List;

import fr.univbrest.dosi.spi.bean.Enseignant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;

import fr.univbrest.dosi.spi.bean.Evaluation;

public interface EvaluationRepository extends CrudRepository<Evaluation, Long>{

	List<Evaluation> findByDesignation(@Param("designation") String designation);

	List<Evaluation> findByEnseignant (Enseignant enseignant);

}
