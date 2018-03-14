package fr.univbrest.dosi.spi.bean;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the REPONSE_EVALUATION database table.
 * 
 */
@Entity
@Table(name="REPONSE_EVALUATION")
@NamedQuery(name="ReponseEvaluation.findAll", query="SELECT r FROM ReponseEvaluation r")
public class ReponseEvaluation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_REPONSE_EVALUATION")
	private long idReponseEvaluation;

	private String commentaire;

	private String nom;

	private String prenom;

	//bi-directional many-to-one association to Etudiant
	@ManyToOne
	@JoinColumn(name="NO_ETUDIANT")
	private Etudiant etudiant;

	//bi-directional many-to-one association to Evaluation
	@ManyToOne
	@JoinColumn(name="ID_EVALUATION")
	private Evaluation evaluation;

	//bi-directional many-to-one association to ReponseQuestion
	@OneToMany(mappedBy="reponseEvaluation")
	@JsonIgnore
	private List<ReponseQuestion> reponseQuestions;

	public ReponseEvaluation() {
	}

	public long getIdReponseEvaluation() {
		return this.idReponseEvaluation;
	}

	public void setIdReponseEvaluation(long idReponseEvaluation) {
		this.idReponseEvaluation = idReponseEvaluation;
	}

	public String getCommentaire() {
		return this.commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Etudiant getEtudiant() {
		return this.etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public Evaluation getEvaluation() {
		return this.evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	public List<ReponseQuestion> getReponseQuestions() {
		return this.reponseQuestions;
	}

	public void setReponseQuestions(List<ReponseQuestion> reponseQuestions) {
		this.reponseQuestions = reponseQuestions;
	}

	public ReponseQuestion addReponseQuestion(ReponseQuestion reponseQuestion) {
		getReponseQuestions().add(reponseQuestion);
		reponseQuestion.setReponseEvaluation(this);

		return reponseQuestion;
	}

	public ReponseQuestion removeReponseQuestion(ReponseQuestion reponseQuestion) {
		getReponseQuestions().remove(reponseQuestion);
		reponseQuestion.setReponseEvaluation(null);

		return reponseQuestion;
	}

}