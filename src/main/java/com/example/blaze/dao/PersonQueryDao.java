package com.example.blaze.dao;

import com.example.blaze.projection.IntermediatePersonDTO;
import com.example.blaze.projection.PersonDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonQueryDao {

    @PersistenceContext
    private final EntityManager entityManager;

    public List<IntermediatePersonDTO> findByName(@NonNull  String name) {
        return entityManager.unwrap(Session.class).createQuery("""
                select new com.example.blaze.projection.IntermediatePersonDTO(p.id, 
                                                                              p.firstName, 
                                                                              p.lastName, 
                                                                              a.city, 
                                                                              a.country.code,
                                                                              a.country.name,
                                                                              ph.model,
                                                                              ph.number)
               from Person p
                join p.addresses a
                join p.phones ph
                where p.lastName = :name
                """, IntermediatePersonDTO.class)
        .setParameter("name", name)
                //.setResultListTransformer()
        .getResultList();
    }
}
