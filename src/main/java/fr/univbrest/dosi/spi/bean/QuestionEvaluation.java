package fr.univbrest.dosi.spi.bean;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the QUESTION_EVALUATION database table.
 *
 */
@Entity
@Table(name="QUESTION_EVALUATION")
@NamedQuery(name="QuestionEvaluation.findAll", query="SELECT q FROM QuestionEvaluation q")
public class QuestionEvaluation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_QUESTION_EVALUATION")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen-qe")
    @SequenceGenerator(name = "gen-qe", sequenceName = "qe_seq")
	private long idQuestionEvaluation;

	@Column(name="ID_QUALIFICATIF")
	private BigDecimal idQualificatif;

	private String intitule;

	private BigDecimal ordre;

	//bi-directional many-to-one association to Question
	@ManyToOne
	@JoinColumn(name="ID_QUESTION",insertable=true, updatable=true)
	private Question question;

	//bi-directional many-to-one association to RubriqueEvaluation
	@ManyToOne
	@JoinColumn(name="ID_RUBRIQUE_EVALUATION",insertable=true, updatable=true)
	//@JsonIgnore
	private RubriqueEvaluation rubriqueEvaluation;

	//bi-directional many-to-one association to ReponseQuestion
	@OneToMany(mappedBy="questionEvaluation")
	@JsonIgnore
	private List<ReponseQuestion> reponseQuestions;

	public QuestionEvaluation() {
	}

	public long getIdQuestionEvaluation() {
		return this.idQuestionEvaluation;
	}

	public void setIdQuestionEvaluation(long idQuestionEvaluation) {
		this.idQuestionEvaluation = idQuestionEvaluation;
	}

	public BigDecimal getIdQualificatif() {
		return this.idQualificatif;
	}

	public void setIdQualificatif(BigDecimal idQualificatif) {
		this.idQualificatif = idQualificatif;
	}

	public String getIntitule() {
		return this.intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public BigDecimal getOrdre() {
		return this.ordre;
	}

	public void setOrdre(BigDecimal ordre) {
		this.ordre = ordre;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public RubriqueEvaluation getRubriqueEvaluation() {
		return this.rubriqueEvaluation;
	}

	public void setRubriqueEvaluation(RubriqueEvaluation rubriqueEvaluation) {
		this.rubriqueEvaluation = rubriqueEvaluation;
	}

	public List<ReponseQuestion> getReponseQuestions() {
		return this.reponseQuestions;
	}

	public void setReponseQuestions(List<ReponseQuestion> reponseQuestions) {
		this.reponseQuestions = reponseQuestions;
	}

	public ReponseQuestion addReponseQuestion(ReponseQuestion reponseQuestion) {
		getReponseQuestions().add(reponseQuestion);
		reponseQuestion.setQuestionEvaluation(this);

		return reponseQuestion;
	}

	public ReponseQuestion removeReponseQuestion(ReponseQuestion reponseQuestion) {
		getReponseQuestions().remove(reponseQuestion);
		reponseQuestion.setQuestionEvaluation(null);

		return reponseQuestion;
	}

}
