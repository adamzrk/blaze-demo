package com.example.blaze.dao;

import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViewSetting;
import com.example.blaze.model.Person;
import com.example.blaze.view.PersonView;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonBlazeDao {

    private final CriteriaBuilderFactory criteriaBuilderFactory;
    private final EntityManager entityManager;
    private final EntityViewManager entityViewManager;

    public List<PersonView> findByName(String name) {
        var criteriaBuilder = criteriaBuilderFactory.create(entityManager, Person.class)
                .where("lastName").eq(name);

        return entityViewManager.applySetting(EntityViewSetting.create(PersonView.class), criteriaBuilder)
                .getResultList();
    }


}
