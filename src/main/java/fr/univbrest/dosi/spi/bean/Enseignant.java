package fr.univbrest.dosi.spi.bean;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the ENSEIGNANT database table.
 *
 */
@Entity
@NamedQuery(name="Enseignant.findAll", query="SELECT e FROM Enseignant e")
public class Enseignant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NO_ENSEIGNANT")
	private long noEnseignant;

	private String adresse;

	@Column(name="CODE_POSTAL")
	private String codePostal;

	@Column(name="EMAIL_PERSO")
	private String emailPerso;

	@Column(name="EMAIL_UBO")
	private String emailUbo;

	private String mobile;

	private String nom;

	private String pays;

	private String prenom;

	private String sexe;

	private String telephone;

	@Column(name="TYPE")
	private String type;

	private String ville;

	//bi-directional many-to-one association to Droit
	@OneToMany(mappedBy="enseignant")
	@JsonIgnore
	private List<Droit> droits;

	//bi-directional many-to-one association to ElementConstitutif
	@OneToMany(mappedBy="enseignant")
	@JsonIgnore
	private List<ElementConstitutif> elementConstitutifs;

	//bi-directional many-to-one association to Evaluation
	@OneToMany(mappedBy="enseignant")
	@JsonIgnore
	private List<Evaluation> evaluations;

	//bi-directional many-to-one association to Promotion
	@OneToMany(mappedBy="enseignant")
	@JsonIgnore
	private List<Promotion> promotions;

	//bi-directional many-to-one association to Question
	@OneToMany(mappedBy="enseignant")
	@JsonIgnore
	private List<Question> questions;

	//bi-directional many-to-one association to Rubrique
	@OneToMany(mappedBy="enseignant")
	@JsonIgnore
	private List<Rubrique> rubriques;

	//bi-directional many-to-one association to UniteEnseignement
	@OneToMany(mappedBy="enseignant")
	@JsonIgnore
	private List<UniteEnseignement> uniteEnseignements;

	public Enseignant() {
	}

	public long getNoEnseignant() {
		return this.noEnseignant;
	}

	public void setNoEnseignant(long noEnseignant) {
		this.noEnseignant = noEnseignant;
	}

	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCodePostal() {
		return this.codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getEmailPerso() {
		return this.emailPerso;
	}

	public void setEmailPerso(String emailPerso) {
		this.emailPerso = emailPerso;
	}

	public String getEmailUbo() {
		return this.emailUbo;
	}

	public void setEmailUbo(String emailUbo) {
		this.emailUbo = emailUbo;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPays() {
		return this.pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getSexe() {
		return this.sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVille() {
		return this.ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public List<Droit> getDroits() {
		return this.droits;
	}

	public void setDroits(List<Droit> droits) {
		this.droits = droits;
	}

	public Droit addDroit(Droit droit) {
		getDroits().add(droit);
		droit.setEnseignant(this);

		return droit;
	}

	public Droit removeDroit(Droit droit) {
		getDroits().remove(droit);
		droit.setEnseignant(null);

		return droit;
	}

	public List<ElementConstitutif> getElementConstitutifs() {
		return this.elementConstitutifs;
	}

	public void setElementConstitutifs(List<ElementConstitutif> elementConstitutifs) {
		this.elementConstitutifs = elementConstitutifs;
	}

	public ElementConstitutif addElementConstitutif(ElementConstitutif elementConstitutif) {
		getElementConstitutifs().add(elementConstitutif);
		elementConstitutif.setEnseignant(this);

		return elementConstitutif;
	}

	public ElementConstitutif removeElementConstitutif(ElementConstitutif elementConstitutif) {
		getElementConstitutifs().remove(elementConstitutif);
		elementConstitutif.setEnseignant(null);

		return elementConstitutif;
	}

	public List<Evaluation> getEvaluations() {
		return this.evaluations;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}

	public Evaluation addEvaluation(Evaluation evaluation) {
		getEvaluations().add(evaluation);
		evaluation.setEnseignant(this);

		return evaluation;
	}

	public Evaluation removeEvaluation(Evaluation evaluation) {
		getEvaluations().remove(evaluation);
		evaluation.setEnseignant(null);

		return evaluation;
	}

	public List<Promotion> getPromotions() {
		return this.promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}

	public Promotion addPromotion(Promotion promotion) {
		getPromotions().add(promotion);
		promotion.setEnseignant(this);

		return promotion;
	}

	public Promotion removePromotion(Promotion promotion) {
		getPromotions().remove(promotion);
		promotion.setEnseignant(null);

		return promotion;
	}

	public List<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Question addQuestion(Question question) {
		getQuestions().add(question);
		question.setEnseignant(this);

		return question;
	}

	public Question removeQuestion(Question question) {
		getQuestions().remove(question);
		question.setEnseignant(null);

		return question;
	}

	public List<Rubrique> getRubriques() {
		return this.rubriques;
	}

	public void setRubriques(List<Rubrique> rubriques) {
		this.rubriques = rubriques;
	}

	public Rubrique addRubrique(Rubrique rubrique) {
		getRubriques().add(rubrique);
		rubrique.setEnseignant(this);

		return rubrique;
	}

	public Rubrique removeRubrique(Rubrique rubrique) {
		getRubriques().remove(rubrique);
		rubrique.setEnseignant(null);

		return rubrique;
	}

	public List<UniteEnseignement> getUniteEnseignements() {
		return this.uniteEnseignements;
	}

	public void setUniteEnseignements(List<UniteEnseignement> uniteEnseignements) {
		this.uniteEnseignements = uniteEnseignements;
	}

	public UniteEnseignement addUniteEnseignement(UniteEnseignement uniteEnseignement) {
		getUniteEnseignements().add(uniteEnseignement);
		uniteEnseignement.setEnseignant(this);

		return uniteEnseignement;
	}

	public UniteEnseignement removeUniteEnseignement(UniteEnseignement uniteEnseignement) {
		getUniteEnseignements().remove(uniteEnseignement);
		uniteEnseignement.setEnseignant(null);

		return uniteEnseignement;
	}

    @Override
    public String toString() {
        return "Enseignant{" +
            "noEnseignant=" + noEnseignant +
            ", nom='" + nom + '\'' +
            ", prenom='" + prenom + '\'' +
            '}';
    }
}
