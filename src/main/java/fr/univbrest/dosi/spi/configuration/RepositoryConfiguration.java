package fr.univbrest.dosi.spi.configuration;

import fr.univbrest.dosi.spi.bean.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class RepositoryConfiguration extends RepositoryRestConfigurerAdapter {
    @Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Enseignant.class);
        config.exposeIdsFor(Formation.class);
        config.exposeIdsFor(Rubrique.class);
        config.exposeIdsFor(Question.class);
        config.exposeIdsFor(Qualificatif.class);
        config.exposeIdsFor(Evaluation.class);
    }
}
