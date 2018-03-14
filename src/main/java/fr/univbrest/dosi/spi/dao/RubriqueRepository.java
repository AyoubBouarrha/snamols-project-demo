package fr.univbrest.dosi.spi.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.univbrest.dosi.spi.bean.Enseignant;
import fr.univbrest.dosi.spi.bean.Rubrique;

public interface RubriqueRepository extends CrudRepository<Rubrique, Long> {

	List<Rubrique> findByDesignation(String designation);
}
