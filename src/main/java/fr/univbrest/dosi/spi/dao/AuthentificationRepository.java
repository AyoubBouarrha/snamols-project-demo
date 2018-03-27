package fr.univbrest.dosi.spi.dao;

import fr.univbrest.dosi.spi.bean.Authentification;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthentificationRepository extends CrudRepository<Authentification, Long> {

    List<Authentification> findByLoginConnectionAndMotPasse (String login , String mdp);
}
