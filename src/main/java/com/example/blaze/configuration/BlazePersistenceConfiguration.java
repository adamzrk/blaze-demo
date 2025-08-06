package com.example.blaze.configuration;

import com.blazebit.persistence.Criteria;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.spi.CriteriaBuilderConfiguration;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViews;
import com.blazebit.persistence.view.spi.EntityViewConfiguration;
import com.example.blaze.view.AddressView;
import com.example.blaze.view.CountryView;
import com.example.blaze.view.PersonView;
import com.example.blaze.view.TelephoneView;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.reflections.Reflections;

import java.util.Set;

@Configuration
public class BlazePersistenceConfiguration {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public CriteriaBuilderFactory createCriteriaBuilderFactory() {
        CriteriaBuilderConfiguration config = Criteria.getDefault();
        return config.createCriteriaBuilderFactory(entityManagerFactory);
    }

    @Bean
    public EntityViewManager createEntityViewManager(CriteriaBuilderFactory cbf, EntityViewConfiguration entityViewConfiguration) {
        return entityViewConfiguration.createEntityViewManager(cbf);
    }

    @Bean
    public EntityViewConfiguration entityViewSettings() throws ClassNotFoundException {
        EntityViewConfiguration config = EntityViews.createDefaultConfiguration();
        Reflections reflections = new Reflections(CountryView.class.getPackage().getName());
        Set<Class<?>> entityViews = reflections.getTypesAnnotatedWith(EntityView.class);
        entityViews.forEach(entityView -> config.addEntityView(entityView));
        return config;
    }
}
