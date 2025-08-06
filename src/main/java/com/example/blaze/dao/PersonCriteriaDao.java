package com.example.blaze.dao;

import com.example.blaze.model.Address;
import com.example.blaze.model.Person;
import com.example.blaze.model.Person_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class PersonCriteriaDao {

    private final EntityManager entityManager;

    public List<Person> findByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
        Root<Person> person = criteriaQuery.from(Person.class);
        criteriaQuery.select(person);
        criteriaQuery.where(criteriaBuilder.equal(person.get("lastName"), name));
        Query query = entityManager.createQuery(criteriaQuery);
        var result = query.getResultList();
        log.info("result: {}", result);
        return result;
    }

}
