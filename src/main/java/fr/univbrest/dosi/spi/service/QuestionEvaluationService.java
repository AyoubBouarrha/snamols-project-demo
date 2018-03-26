package fr.univbrest.dosi.spi.service;

import java.util.ArrayList;
import java.util.List;

import fr.univbrest.dosi.spi.dao.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univbrest.dosi.spi.bean.Question;
import fr.univbrest.dosi.spi.bean.QuestionEvaluation;
import fr.univbrest.dosi.spi.bean.RubriqueEvaluation;
import fr.univbrest.dosi.spi.dao.QuestionEvaluationRepository;

@Service
public class QuestionEvaluationService {
    @Autowired
    private QuestionEvaluationRepository questionEvaluationRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public final QuestionEvaluation addQuestionEvaluation(final QuestionEvaluation questionEvaluation) throws Exception {
        if (questionEvaluationRepository.exists(questionEvaluation.getIdQuestionEvaluation())) {
            throw new Exception("la question que vous souhaitez ajouter exsite déja ");
        }
        return questionEvaluationRepository.save(questionEvaluation);
    }


    public final void deleteQuestionEvaluation(final long idQuestionEvaluation) throws Exception {
        if (questionEvaluationRepository.exists(idQuestionEvaluation)) {
            try {
                questionEvaluationRepository.delete(idQuestionEvaluation);
            } catch (Exception c) {
                throw new Exception("Cant delete QuestionEvaluation");
            }
        } else {
            throw new Exception("QuestionEvaluation not found");
        }

    }


    public final QuestionEvaluation getQuestionEvaluation(final long idQuestionEvaluation) {
        return questionEvaluationRepository.findOne(idQuestionEvaluation);
    }


    public final List<QuestionEvaluation> getQuestionEvaluationByIntitule(final String intitule) {
        return questionEvaluationRepository.findByIntitule(intitule);
    }

    public final List<QuestionEvaluation> getQuestionEvaluationByRubriqueEvaluation(RubriqueEvaluation rubriqueEvaluation) {
        return questionEvaluationRepository.findByRubriqueEvaluation(rubriqueEvaluation);
    }


    public final List<Question> getQuestionsNotAffectedByRubriqueEvaluation(RubriqueEvaluation rubriqueEvaluation) {

        List<Question> listNotAffectedQst = new ArrayList<Question>();
        List<QuestionEvaluation> listAffectedQstEva = questionEvaluationRepository.findByRubriqueEvaluation(rubriqueEvaluation);
        List<Question> listAllQst = (List<Question>) questionRepository.findAll();

        List<Question> listQuestionsAffected = new ArrayList<Question>();


        for (QuestionEvaluation qstEvaAffected : listAffectedQstEva)
            listQuestionsAffected.add(qstEvaAffected.getQuestion());

        for (Question question : listAllQst)
        {
            if(!listQuestionsAffected.contains(question))
                listNotAffectedQst.add(question);
        }

        return listNotAffectedQst;
    }


    public List<QuestionEvaluation> getAllQuestionsEvaluations() {
        return (List<QuestionEvaluation>) questionEvaluationRepository.findAll();
    }

    /**
     * @return getter
     */
    public final QuestionEvaluationRepository getQuestionEvaluationRepository() {
        return questionEvaluationRepository;
    }


    public final void setQuestionEvaluationRepository(final QuestionEvaluationRepository questionEvaluationRepository) {
        this.questionEvaluationRepository = questionEvaluationRepository;
    }

}
