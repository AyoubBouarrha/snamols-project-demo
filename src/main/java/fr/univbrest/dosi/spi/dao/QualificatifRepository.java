package fr.univbrest.dosi.spi.dao;





import org.springframework.data.repository.CrudRepository;


import fr.univbrest.dosi.spi.bean.Qualificatif;



/**
 * @author DOSI
 *
 */
//@RepositoryRestResource(collectionResourceRel = "qualificatif", path = "qualificatif")
public interface QualificatifRepository extends CrudRepository<Qualificatif, Long> {
	/**
	 * 
	 * @param nom
	 *            de qualificatif
	 * @return liste des qualificatifs
	 */


	// Qualificatif findOne(Integer idQualificatif);
	//@Override
	//Qualificatif save(@RequestBody Qualificatif ens);

	 Iterable<Qualificatif> findAll();

	 long count();

	// void delete(Qualificatif qualificatif);

	
	boolean exists(long idQualificatif);

}
