package fr.univbrest.dosi.spi.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univbrest.dosi.spi.bean.Qualificatif;
import fr.univbrest.dosi.spi.dao.QualificatifRepository;
import fr.univbrest.dosi.spi.exception.SPIException;

/**
 * @author DOSI
 *
 */
@Service
public class QualificatifService {
	/**
	 *
	 */
	@Autowired
	private QualificatifRepository qualificatifRepository;

	/**
	 *
	 * @param qualificatif
	 *            l'entité
	 * @return qualificatif ajouter
	 */
	public final Qualificatif addQualificatif(final Qualificatif qualificatif) {
		if (qualificatifRepository.exists((long) qualificatif.getIdQualificatif())) {
			throw new SPIException("le qualificatif que vous souhaitez ajouter exsite déja ");
		}
		return qualificatifRepository.save(qualificatif);
	}

	/**
	 *
	 * @param idQualificatif
	 *            l'id de qualificatif
	 */
	
		
		public final void deleteQualificatif(final long IdQualificatif) throws Exception {
			if (qualificatifRepository.exists(IdQualificatif)) {
				try {
					qualificatifRepository.delete(IdQualificatif);
				} catch(Exception c) {
					throw new Exception("Cant delete qualificatif");
				}
			} else {
				throw new Exception("qualificatif not found");
			}
			
		}
		
	

	/**
	 *
	 * @param noEnseignant
	 *            l'id de l'enseignant
	 * @return test de existe
	 */
	public final Boolean existQualificatif(final long IdQualificatif)throws Exception {
		final Boolean exist = qualificatifRepository.exists(IdQualificatif);
		if (exist) {
			return exist;
		} else {
			throw new Exception("Il y a aucun qualificatif avec ce numero");
		}
	}

	
	
	/**
	 *
	 * @param id
	 *            l'id de l'enseignant
	 * @return l'enseignant
	 */
	public final Qualificatif getQualificatif(final long idQualificatif) {
		return qualificatifRepository.findOne(idQualificatif);
	}

	/**
	 *
	 * @param nom
	 *            de qualificatif
	 * @return la liste des qualificatifs
	 */
	

	/**
	 *
	 * @return getter
	 */
	public final QualificatifRepository getQualificatifRepository() {
		return qualificatifRepository;
	}

	/**
	 *
	 * @return liste des qualificatifs
	 */
	public final Iterable<Qualificatif> listens() {
		final Iterable<Qualificatif> qualificatifs = qualificatifRepository.findAll();
		return qualificatifs;
	}

	/**
	 *
	 * @param enseignantRepository
	 *            setter
	 */
	public final void setEnseignantRepository(final QualificatifRepository qualificatifRepository) {
		this.qualificatifRepository = qualificatifRepository;
	}

	/**
	 *
	 * @param qualificatif
	 *            l'entité qualificatif
	 * @return qualificatif modifier
	 */
	public final Qualificatif updateQualificatif(final Qualificatif qualificatif)throws Exception  {
		if (qualificatifRepository.exists((long) qualificatif.getIdQualificatif())) {
			return qualificatifRepository.save(qualificatif);
		} else {
			throw new Exception("le qualificatif que vous souhaitez modifier n'exsite pas ");
		}
	}

}

