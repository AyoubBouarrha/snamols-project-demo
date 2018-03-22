package fr.univbrest.dosi.spi.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.univbrest.dosi.spi.bean.Promotion;
import fr.univbrest.dosi.spi.bean.PromotionPK;

/**
 * @author DOSI
 *
 */
//@RepositoryRestResource(collectionResourceRel = "promotion", path = "promotion")
public interface PromotionRepository extends CrudRepository<Promotion, PromotionPK> {


}
