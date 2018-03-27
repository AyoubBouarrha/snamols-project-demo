package fr.univbrest.dosi.spi.service;

import java.util.List;

import fr.univbrest.dosi.spi.bean.Enseignant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univbrest.dosi.spi.bean.Evaluation;
import fr.univbrest.dosi.spi.dao.EvaluationRepository;

@Service
public class EvaluationService {
	@Autowired
	private EvaluationRepository evaluationRepository;
	public final Evaluation addEvaluation(final Evaluation evaluation) throws Exception {
		if (evaluationRepository.exists(evaluation.getIdEvaluation())) {
			throw new Exception("l'evaluation que vous souhaitez ajouter exsite déja ");
		}
		return evaluationRepository.save(evaluation);
	}




	public final void deleteEvaluation(final long idEvaluation) throws Exception {
		if (evaluationRepository.exists(idEvaluation)) {
			try {
				evaluationRepository.delete(idEvaluation);
			} catch(Exception c) {
				throw new Exception("Cant delete Evaluation");
			}
		} else {
			throw new Exception("Evaluation not found");
		}

	}


	public final Boolean existEvaluation(final long idEvaluation) throws Exception {
		final Boolean exist = evaluationRepository.exists(idEvaluation);
		if (exist) {
			return exist;
		} else {
			throw new Exception("Il y a aucune evaluation avec ce numero");
		}
	}



	public final Evaluation getEvaluation(final long idEvaluation) {
		return evaluationRepository.findOne(idEvaluation);
	}


	public final List<Evaluation> getEvaluationByDesination(final String designation) {
		return evaluationRepository.findByDesignation(designation);
	}



	/**
	 *
	 * @return getter
	 */
	public final EvaluationRepository getEvaluationRepository() {
		return evaluationRepository;
	}

	/**
	 *
	 * @return liste des evaluations
	 */
	public final Iterable<Evaluation> listEvaluations() {
		final Iterable<Evaluation> evaluations = evaluationRepository.findAll();
		return evaluations;
	}

	/**
	 *
	 * @param evaluationRepository
	 *            setter
	 */
	public final void setEvaluationRepository(final EvaluationRepository evaluationRepository) {
		this.evaluationRepository = evaluationRepository;
	}

	public final Evaluation updateEvaluation(final Evaluation evaluation) throws Exception {
		if (evaluationRepository.exists( evaluation.getIdEvaluation())) {
			return evaluationRepository.save(evaluation);
		} else {
			throw new Exception("l'evaluation que vous souhaitez modifier n'exsite pas ");
		}
	}

    public List<Evaluation> findByEnseignant (Enseignant enseignant) {
	    return evaluationRepository.findByEnseignant(enseignant);

    }

}
