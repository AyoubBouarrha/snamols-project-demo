package fr.univbrest.dosi.spi.bean;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the QUESTION database table.
 *
 */
@Entity
@NamedQuery(name="Question.findAll", query="SELECT q FROM Question q")
public class Question implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_QUESTION")
    // --Une Sequence doit être obligatoirement crée pour génerer un idQuestion
    // (script de la sequence : "create sequence qes_seq start with 100 increment by 1 nomaxvalue;")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen-cq")
    @SequenceGenerator(name = "gen-cq", sequenceName = "qes_seq")
	private long idQuestion;

	@Column(name="ID_QUALIFICATIF")
	private java.math.BigDecimal idQualificatif;

	private String intitule;

	@Column(name="TYPE")
	private String type;

	//bi-directional many-to-one association to Enseignant
	@ManyToOne
	@JoinColumn(name="NO_ENSEIGNANT")
	private Enseignant enseignant;

	//bi-directional many-to-one association to QuestionEvaluation
	@OneToMany(mappedBy="question")
	@JsonIgnore
	private List<QuestionEvaluation> questionEvaluations;

	//bi-directional many-to-one association to RubriqueQuestion
	@OneToMany(mappedBy="question")
	@JsonIgnore
	private List<RubriqueQuestion> rubriqueQuestions;

	public Question() {
	}

	public long getIdQuestion() {
		return this.idQuestion;
	}

	public void setIdQuestion(long idQuestion) {
		this.idQuestion = idQuestion;
	}

	public java.math.BigDecimal getIdQualificatif() {
		return this.idQualificatif;
	}

	public void setIdQualificatif(java.math.BigDecimal idQualificatif) {
		this.idQualificatif = idQualificatif;
	}

	public String getIntitule() {
		return this.intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Enseignant getEnseignant() {
		return this.enseignant;
	}

	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}

	public List<QuestionEvaluation> getQuestionEvaluations() {
		return this.questionEvaluations;
	}

	public void setQuestionEvaluations(List<QuestionEvaluation> questionEvaluations) {
		this.questionEvaluations = questionEvaluations;
	}

	public QuestionEvaluation addQuestionEvaluation(QuestionEvaluation questionEvaluation) {
		getQuestionEvaluations().add(questionEvaluation);
		questionEvaluation.setQuestion(this);

		return questionEvaluation;
	}

	public QuestionEvaluation removeQuestionEvaluation(QuestionEvaluation questionEvaluation) {
		getQuestionEvaluations().remove(questionEvaluation);
		questionEvaluation.setQuestion(null);

		return questionEvaluation;
	}

	public List<RubriqueQuestion> getRubriqueQuestions() {
		return this.rubriqueQuestions;
	}

	public void setRubriqueQuestions(List<RubriqueQuestion> rubriqueQuestions) {
		this.rubriqueQuestions = rubriqueQuestions;
	}

	public RubriqueQuestion addRubriqueQuestion(RubriqueQuestion rubriqueQuestion) {
		getRubriqueQuestions().add(rubriqueQuestion);
		rubriqueQuestion.setQuestion(this);

		return rubriqueQuestion;
	}

	public RubriqueQuestion removeRubriqueQuestion(RubriqueQuestion rubriqueQuestion) {
		getRubriqueQuestions().remove(rubriqueQuestion);
		rubriqueQuestion.setQuestion(null);

		return rubriqueQuestion;
	}

}
