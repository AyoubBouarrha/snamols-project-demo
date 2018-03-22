package fr.univbrest.dosi.spi.bean;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PROMOTION database table.
 */
@Entity
@NamedQuery(name = "Promotion.findAll", query = "SELECT p FROM Promotion p")
public class Promotion implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PromotionPK id;

    private String commentaire;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_RENTREE")
    private Date dateRentree;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_REPONSE_LALP")
    private Date dateReponseLalp;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_REPONSE_LP")
    private Date dateReponseLp;

    @Column(name = "LIEU_RENTREE")
    private String lieuRentree;

    @Column(name = "NB_MAX_ETUDIANT")
    private BigDecimal nbMaxEtudiant;

    @Column(name = "PROCESSUS_STAGE")
    private String processusStage;

    @Column(name = "SIGLE_PROMOTION")
    private String siglePromotion;

    //bi-directional many-to-one association to Candidat
    @OneToMany(mappedBy = "promotion")
    @JsonIgnore
    private List<Candidat> candidats;

    //bi-directional many-to-one association to Etudiant
    @OneToMany(mappedBy = "promotion")
    @JsonIgnore
    private List<Etudiant> etudiants;

    //bi-directional many-to-one association to Evaluation
    @OneToMany(mappedBy = "promotion")
    @JoinColumnsOrFormulas({
        @JoinColumnOrFormula(column = @JoinColumn(name = "CODE_FORMATION", referencedColumnName = "CODE_FORMATION")),
        @JoinColumnOrFormula(column = @JoinColumn(name = "ANNEE_UNIVERSITAIRE", referencedColumnName = "ANNEE_UNIVERSITAIRE"))
    })
    @JsonIgnore
    private List<Evaluation> evaluations;

    //bi-directional many-to-one association to Enseignant
    @ManyToOne
    @JoinColumn(name = "NO_ENSEIGNANT", insertable = false, updatable = false)
    @JsonIgnore
    private Enseignant enseignant;

    //bi-directional many-to-one association to Formation
    @ManyToOne
    @JoinColumn(name = "CODE_FORMATION", insertable = false, updatable = false)
    @JsonIgnore
    private Formation formation;

    public Promotion() {
    }

    public PromotionPK getId() {
        return this.id;
    }

    public void setId(PromotionPK id) {
        this.id = id;
    }

    public String getCommentaire() {
        return this.commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Date getDateRentree() {
        return this.dateRentree;
    }

    public void setDateRentree(Date dateRentree) {
        this.dateRentree = dateRentree;
    }

    public Date getDateReponseLalp() {
        return this.dateReponseLalp;
    }

    public void setDateReponseLalp(Date dateReponseLalp) {
        this.dateReponseLalp = dateReponseLalp;
    }

    public Date getDateReponseLp() {
        return this.dateReponseLp;
    }

    public void setDateReponseLp(Date dateReponseLp) {
        this.dateReponseLp = dateReponseLp;
    }

    public String getLieuRentree() {
        return this.lieuRentree;
    }

    public void setLieuRentree(String lieuRentree) {
        this.lieuRentree = lieuRentree;
    }

    public BigDecimal getNbMaxEtudiant() {
        return this.nbMaxEtudiant;
    }

    public void setNbMaxEtudiant(BigDecimal nbMaxEtudiant) {
        this.nbMaxEtudiant = nbMaxEtudiant;
    }

    public String getProcessusStage() {
        return this.processusStage;
    }

    public void setProcessusStage(String processusStage) {
        this.processusStage = processusStage;
    }

    public String getSiglePromotion() {
        return this.siglePromotion;
    }

    public void setSiglePromotion(String siglePromotion) {
        this.siglePromotion = siglePromotion;
    }

    public List<Candidat> getCandidats() {
        return this.candidats;
    }

    public void setCandidats(List<Candidat> candidats) {
        this.candidats = candidats;
    }

    public Candidat addCandidat(Candidat candidat) {
        getCandidats().add(candidat);
        candidat.setPromotion(this);

        return candidat;
    }

    public Candidat removeCandidat(Candidat candidat) {
        getCandidats().remove(candidat);
        candidat.setPromotion(null);

        return candidat;
    }

    public List<Etudiant> getEtudiants() {
        return this.etudiants;
    }

    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    public Etudiant addEtudiant(Etudiant etudiant) {
        getEtudiants().add(etudiant);
        etudiant.setPromotion(this);

        return etudiant;
    }

    public Etudiant removeEtudiant(Etudiant etudiant) {
        getEtudiants().remove(etudiant);
        etudiant.setPromotion(null);

        return etudiant;
    }

    public List<Evaluation> getEvaluations() {
        return this.evaluations;
    }

    public void setEvaluations(List<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

    public Evaluation addEvaluation(Evaluation evaluation) {
        getEvaluations().add(evaluation);
        evaluation.setPromotion(this);

        return evaluation;
    }

    public Evaluation removeEvaluation(Evaluation evaluation) {
        getEvaluations().remove(evaluation);
        evaluation.setPromotion(null);

        return evaluation;
    }

    public Enseignant getEnseignant() {
        return this.enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public Formation getFormation() {
        return this.formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

}
