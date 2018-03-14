package fr.univbrest.dosi.spi.dao;

import org.springframework.data.repository.CrudRepository;

import fr.univbrest.dosi.spi.bean.Question;

public interface QuestionRepository  extends CrudRepository<Question , Long>

{
	

}
