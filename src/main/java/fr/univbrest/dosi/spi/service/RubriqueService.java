package fr.univbrest.dosi.spi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univbrest.dosi.spi.bean.Rubrique;
import fr.univbrest.dosi.spi.dao.RubriqueRepository;
@Service
public class RubriqueService {
	@Autowired
	private RubriqueRepository rubriqueRepository;
	
	public final Rubrique addRubrique(final Rubrique rubrique) throws Exception {
		if (rubriqueRepository.exists(rubrique.getIdRubrique())) {
			throw new Exception("la rubrique que vous souhaitez ajouter exsite déja ");
		}
		return rubriqueRepository.save(rubrique);
	}


	public final void deleteRubrique(final long idRubrique) throws Exception {
		if (rubriqueRepository.exists(idRubrique)) {
			try {
				rubriqueRepository.delete(idRubrique);
			} catch(Exception c) {
				throw new Exception("Cant delete Rubrique");
			}
		} else {
			throw new Exception("Rubrique not found");
		}
		
	}

	
	public final Boolean existRubrique(final long idRubrique) throws Exception {
		final Boolean exist = rubriqueRepository.exists(idRubrique);
		if (exist) {
			return exist;
		} else {
			throw new Exception("Il y a aucun rubrique avec ce numero");
		}
	}

	

	public final Rubrique getRubrique(final long idRubrique) {
		return rubriqueRepository.findOne(idRubrique);
	}

	
	public final List<Rubrique> getRubriqueByDesination(final String designation) {
		return rubriqueRepository.findByDesignation(designation);
	}
	
	

	/**
	 *
	 * @return getter
	 */
	public final RubriqueRepository getRubriqueRepository() {
		return rubriqueRepository;
	}

	/**
	 *
	 * @return liste des rubriques
	 */
	public final Iterable<Rubrique> listens() {
		final Iterable<Rubrique> rubriques = rubriqueRepository.findAll();
		return rubriques;
	}

	/**
	 *
	 * @param rubriqueRepository
	 *            setter
	 */
	public final void setRubriqueRepository(final RubriqueRepository rubriqueRepository) {
		this.rubriqueRepository = rubriqueRepository;
	}

	public final Rubrique updateRubrique(final Rubrique rubrique) throws Exception {
		if (rubriqueRepository.exists( rubrique.getIdRubrique())) {
			return rubriqueRepository.save(rubrique);
		} else {
			throw new Exception("la rubrique que vous souhaitez modifier n'exsite pas ");
		}
	}
}
