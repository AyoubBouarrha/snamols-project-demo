package fr.univbrest.dosi.spi.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the REPONSE_QUESTION database table.
 * 
 */
@Entity
@Table(name="REPONSE_QUESTION")
@NamedQuery(name="ReponseQuestion.findAll", query="SELECT r FROM ReponseQuestion r")
public class ReponseQuestion implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ReponseQuestionPK id;

	private BigDecimal positionnement;

	//bi-directional many-to-one association to QuestionEvaluation
	@ManyToOne
	@JoinColumn(name="ID_QUESTION_EVALUATION",insertable=false, updatable=false)
	private QuestionEvaluation questionEvaluation;

	//bi-directional many-to-one association to ReponseEvaluation
	@ManyToOne
	@JoinColumn(name="ID_REPONSE_EVALUATION",insertable=false, updatable=false)
	private ReponseEvaluation reponseEvaluation;

	public ReponseQuestion() {
	}

	public ReponseQuestionPK getId() {
		return this.id;
	}

	public void setId(ReponseQuestionPK id) {
		this.id = id;
	}

	public BigDecimal getPositionnement() {
		return this.positionnement;
	}

	public void setPositionnement(BigDecimal positionnement) {
		this.positionnement = positionnement;
	}

	public QuestionEvaluation getQuestionEvaluation() {
		return this.questionEvaluation;
	}

	public void setQuestionEvaluation(QuestionEvaluation questionEvaluation) {
		this.questionEvaluation = questionEvaluation;
	}

	public ReponseEvaluation getReponseEvaluation() {
		return this.reponseEvaluation;
	}

	public void setReponseEvaluation(ReponseEvaluation reponseEvaluation) {
		this.reponseEvaluation = reponseEvaluation;
	}

}