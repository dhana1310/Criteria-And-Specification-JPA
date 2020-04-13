package com.asyouwish.CriteriaAndSpecification.configuration;

import com.asyouwish.CriteriaAndSpecification.entity.Brand;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class RepositoryConfiguration extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Brand.class);
        super.configureRepositoryRestConfiguration(config);
    }

}
