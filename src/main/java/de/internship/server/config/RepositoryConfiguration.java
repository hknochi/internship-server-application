package de.internship.server.config;


import de.internship.server.model.Book;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

@Configuration
public class RepositoryConfiguration extends RepositoryRestConfigurerAdapter
{

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration restConfig)
    {
        //restConfig.setDefaultMediaType(MediaType.APPLICATION_JSON);
        //restConfig.useHalAsDefaultJsonMediaType(false);
        restConfig.setBasePath("/api/v1");
        ExposureConfiguration config = restConfig.getExposureConfiguration();
        config.forDomainType(Book.class).withItemExposure((metadata, httpMethods) ->
            httpMethods.disable(HttpMethod.PATCH));
    }
}
