package fr.univbrest.dosi.spi.bean;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the DROIT database table.
 * 
 */
@Embeddable
public class DroitPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID_EVALUATION", insertable=false, updatable=false)
	private long idEvaluation;

	@Column(name="NO_ENSEIGNANT", insertable=false, updatable=false)
	private long noEnseignant;

	public DroitPK() {
	}
	public long getIdEvaluation() {
		return this.idEvaluation;
	}
	public void setIdEvaluation(long idEvaluation) {
		this.idEvaluation = idEvaluation;
	}
	public long getNoEnseignant() {
		return this.noEnseignant;
	}
	public void setNoEnseignant(long noEnseignant) {
		this.noEnseignant = noEnseignant;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DroitPK)) {
			return false;
		}
		DroitPK castOther = (DroitPK)other;
		return 
			(this.idEvaluation == castOther.idEvaluation)
			&& (this.noEnseignant == castOther.noEnseignant);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.idEvaluation ^ (this.idEvaluation >>> 32)));
		hash = hash * prime + ((int) (this.noEnseignant ^ (this.noEnseignant >>> 32)));
		
		return hash;
	}
}