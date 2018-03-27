package fr.univbrest.dosi.spi.controller;

import java.util.List;

import fr.univbrest.dosi.spi.exception.SPIException;
import fr.univbrest.dosi.spi.exception.SpiExceptionCode;
import fr.univbrest.dosi.spi.util.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univbrest.dosi.spi.bean.Question;
import fr.univbrest.dosi.spi.service.QuestionService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/questions")
public class QuestionController {

	private QuestionService buisness ;

	@Autowired
	public  QuestionController (QuestionService  buisness)
	{
		this.buisness = buisness ;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Question> getAllQuestions()
	{
		return buisness.getAllQuestions() ;
	}

	@RequestMapping(method = RequestMethod.GET , value="/{id}")
	public Question getSingleQuestion(@PathVariable("id") String id)
	{
		return buisness.getSingleQuestion(Long.parseLong(id, 10));
	}

	@RequestMapping(method = RequestMethod.DELETE , value="/{id}")
	public boolean deleteQuestion(@PathVariable("id") String id,HttpServletRequest request)
	{

        if(Connection.currentUser(request) == null || !Connection.currentUser(request).getRole().equals("Admin"))
            throw  new SPIException(SpiExceptionCode.NOT_ENOUGH_RIGHT,"access denied");
	    try {
            buisness.deleteQuestion(Long.parseLong(id, 10));
            return true;
        }
        catch(Exception e){
            return false;
        }
	}

	@RequestMapping(method = RequestMethod.POST)
	public Question  addQuestion(@RequestBody Question question,HttpServletRequest request)
	{

        if(Connection.currentUser(request) == null || !Connection.currentUser(request).getRole().equals("Admin"))
            throw  new SPIException(SpiExceptionCode.NOT_ENOUGH_RIGHT,"access denied");
		return buisness.addQuestion(question);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Question  updateQuestion(@RequestBody Question question,HttpServletRequest request)
	{

        if(Connection.currentUser(request) == null || !Connection.currentUser(request).getRole().equals("Admin"))
            throw  new SPIException(SpiExceptionCode.NOT_ENOUGH_RIGHT,"access denied");
		return buisness.updateQuestion(question);
	}


}



