package fr.univbrest.dosi.spi.controller;

import java.util.ArrayList;
import java.util.List;

import fr.univbrest.dosi.spi.service.EvaluationService;
import fr.univbrest.dosi.spi.service.RubriqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univbrest.dosi.spi.bean.Evaluation;
import fr.univbrest.dosi.spi.bean.Question;
import fr.univbrest.dosi.spi.bean.QuestionEvaluation;
import fr.univbrest.dosi.spi.bean.Rubrique;
import fr.univbrest.dosi.spi.bean.RubriqueEvaluation;
import fr.univbrest.dosi.spi.service.QuestionEvaluationService;
import fr.univbrest.dosi.spi.service.RubriqueEvaluationService;

@RestController
@RequestMapping("/questionEvaluation")
public class QuestionEvaluationController {

    private QuestionEvaluationService questionEvaluationService;
    private RubriqueEvaluationService rubriqueEvaluationService;
    private EvaluationService evaluationService;
    private RubriqueService rubriqueService;

    public QuestionEvaluationController(QuestionEvaluationService questionEvaluationService, RubriqueEvaluationService rubriqueEvaluationService, EvaluationService evaluationService, RubriqueService rubriqueService) {
        this.questionEvaluationService = questionEvaluationService;
        this.rubriqueEvaluationService = rubriqueEvaluationService;
        this.evaluationService = evaluationService;
        this.rubriqueService = rubriqueService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public boolean addQuestionEvaluation(@RequestBody final QuestionEvaluation questionEvaluation) {
        try {
            questionEvaluationService.addQuestionEvaluation(questionEvaluation);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final boolean deleteQuestionEvaluation(@PathVariable(value = "id") final long id) {
        try {
            questionEvaluationService.deleteQuestionEvaluation(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @RequestMapping(value = "notAffectedQuestion/evaluation/{idEvaluation}/rubrique/{idRubrique}")
    public List<Question> getQuestionsNotAffected(@PathVariable(value = "idEvaluation") int idEvaluation, @PathVariable(value = "idRubrique") int idRubrique) {
        return questionEvaluationService.getQuestionsNotAffectedByRubriqueEvaluation(rubriqueEvaluationService.findByEvaluationAndRubrique(evaluationService.getEvaluation(idEvaluation), rubriqueService.getRubrique(idRubrique)).get(0));
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<QuestionEvaluation> getAllQuestionsEvaluations() {
        return questionEvaluationService.getAllQuestionsEvaluations();
    }


    @RequestMapping(value = "/{idquestionEvaluation}")
    public final QuestionEvaluation getQuestionEvaluation(@PathVariable(value = "idquestionEvaluation") final long idQuestionEvaluation) {
        return questionEvaluationService.getQuestionEvaluation(idQuestionEvaluation);
    }


    @RequestMapping(value = "affectedQuestion/evaluation/{idEvaluation}/rubrique/{idRubrique}")
    public List<QuestionEvaluation> getQuestionsAffected(@PathVariable(value = "idEvaluation") int idEvaluation, @PathVariable(value = "idRubrique") int idRubrique) {

        return  questionEvaluationService.getQuestionEvaluationByRubriqueEvaluation(rubriqueEvaluationService.findByEvaluationAndRubrique(evaluationService.getEvaluation(idEvaluation), rubriqueService.getRubrique(idRubrique)).get(0));
        /*List<Question> questionList = new ArrayList<Question>();
        System.out.println("####################---------------------------------------\n\n\n\n");
        for (QuestionEvaluation questionEvaluation : list) {
            questionList.add(questionEvaluation.getQuestion());
        }
        return questionList;*/
    }


}
