package fr.univbrest.dosi.spi.bean;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the ELEMENT_CONSTITUTIF database table.
 *
 */
@Entity
@Table(name="ELEMENT_CONSTITUTIF")
@NamedQuery(name="ElementConstitutif.findAll", query="SELECT e FROM ElementConstitutif e")
public class ElementConstitutif implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ElementConstitutifPK id;

	private String description;

	private String designation;

	@Column(name="NBH_CM")
	private BigDecimal nbhCm;

	@Column(name="NBH_TD")
	private BigDecimal nbhTd;

	@Column(name="NBH_TP")
	private BigDecimal nbhTp;

	//bi-directional many-to-one association to Enseignant
	@ManyToOne
	@JoinColumn(name="NO_ENSEIGNANT")
    @JsonIgnore
	private Enseignant enseignant;

	//bi-directional many-to-one association to UniteEnseignement
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CODE_FORMATION", referencedColumnName="CODE_FORMATION",insertable=false, updatable=false),
		@JoinColumn(name="CODE_UE", referencedColumnName="CODE_UE",insertable=false, updatable=false)
		})
    @JsonIgnore
	private UniteEnseignement uniteEnseignement;

	//bi-directional many-to-one association to Evaluation
	@OneToMany(mappedBy="elementConstitutif")
    @JoinColumnsOrFormulas(value ={
        @JoinColumnOrFormula(column = @JoinColumn(name = "CODE_FORMATION", referencedColumnName = "CODE_FORMATION")),
        @JoinColumnOrFormula(column = @JoinColumn(name = "CODE_UE", referencedColumnName = "CODE_UE")),
        @JoinColumnOrFormula(column = @JoinColumn(name = "CODE_EC", referencedColumnName = "CODE_EC"))
    })
	@JsonIgnore
	private List<Evaluation> evaluations;

	public ElementConstitutif() {
	}

	public ElementConstitutifPK getId() {
		return this.id;
	}

	public void setId(ElementConstitutifPK id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public BigDecimal getNbhCm() {
		return this.nbhCm;
	}

	public void setNbhCm(BigDecimal nbhCm) {
		this.nbhCm = nbhCm;
	}

	public BigDecimal getNbhTd() {
		return this.nbhTd;
	}

	public void setNbhTd(BigDecimal nbhTd) {
		this.nbhTd = nbhTd;
	}

	public BigDecimal getNbhTp() {
		return this.nbhTp;
	}

	public void setNbhTp(BigDecimal nbhTp) {
		this.nbhTp = nbhTp;
	}

	public Enseignant getEnseignant() {
		return this.enseignant;
	}

	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}

	public UniteEnseignement getUniteEnseignement() {
		return this.uniteEnseignement;
	}

	public void setUniteEnseignement(UniteEnseignement uniteEnseignement) {
		this.uniteEnseignement = uniteEnseignement;
	}

	public List<Evaluation> getEvaluations() {
		return this.evaluations;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}

	public Evaluation addEvaluation(Evaluation evaluation) {
		getEvaluations().add(evaluation);
		evaluation.setElementConstitutif(this);

		return evaluation;
	}

	public Evaluation removeEvaluation(Evaluation evaluation) {
		getEvaluations().remove(evaluation);
		evaluation.setElementConstitutif(null);

		return evaluation;
	}

}
