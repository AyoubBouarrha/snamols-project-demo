package fr.univbrest.dosi.spi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author DOSI
 *
 */
@ComponentScan
@EnableAutoConfiguration
@Configuration
// @PropertySource("classpath:/data/jdbc-dev.properties")
@EnableJpaRepositories
@EnableTransactionManagement
@EnableSwagger2
@Import(RepositoryRestMvcConfiguration.class)
public class Application extends WebMvcConfigurerAdapter {
	/**
	 *
	 * @param args
	 *            lancemant
	 */
	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public final void addResourceHandlers(final ResourceHandlerRegistry registry) {		
		registry.addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");
		
		registry.addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
		
		registry.addResourceHandler("/**").addResourceLocations("classpath:/client/").addResourceLocations("classpath:/.tmp/");
	}
	
	 @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**").allowCredentials(false).allowedOrigins("*").allowedMethods("GET", "POST","PUT", "DELETE");
	    }
	 
	 

}
