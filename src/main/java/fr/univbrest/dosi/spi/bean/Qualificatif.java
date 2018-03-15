package fr.univbrest.dosi.spi.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the QUALIFICATIF database table.
 * 
 */
@Entity
@NamedQuery(name="Qualificatif.findAll", query="SELECT q FROM Qualificatif q")
public class Qualificatif implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_QUALIFICATIF")
	// --Une Sequence doit être obligatoirement crée pour génerer un noEnseignant
	// (script de la sequence : "create sequence cq_seq start with 100 increment by 1 nomaxvalue;")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen-cq")
	@SequenceGenerator(name = "gen-cq", sequenceName = "cq_seq")
	private long idQualificatif;

	private String maximal;

	private String minimal;

	public Qualificatif() {
	}

	public long getIdQualificatif() {
		return this.idQualificatif;
	}

	public void setIdQualificatif(long idQualificatif) {
		this.idQualificatif = idQualificatif;
	}

	public String getMaximal() {
		return this.maximal;
	}

	public void setMaximal(String maximal) {
		this.maximal = maximal;
	}

	public String getMinimal() {
		return this.minimal;
	}

	public void setMinimal(String minimal) {
		this.minimal = minimal;
	}

}