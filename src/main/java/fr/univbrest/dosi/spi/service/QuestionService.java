package fr.univbrest.dosi.spi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univbrest.dosi.spi.bean.Question;
import fr.univbrest.dosi.spi.dao.QuestionRepository;

@Service
public class QuestionService {

	QuestionRepository repos ; 
	
	
	@Autowired
	public QuestionService ( QuestionRepository repos )
	{
		this.repos = repos ; 
	}
	
   public List<Question> getAllQuestions ()
   {
	   return ( List<Question> ) repos.findAll() ; 
   }
   
   
   public  Question getSingleQuestion (Long id)
   {
	   return repos.findOne(id) ; 
   }
   
   
   public void  deleteQuestion (Long id)
   {
	   repos.delete(id);
   }
   
   public Question addQuestion (Question question)
   {
	  return  repos.save(question);
   }
   
   public Question updateQuestion (Question question)
   {
	  return repos.save(question);
   }
   
   
	
}
