package com.example.blaze.repository;

import com.example.blaze.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("""
            select p from Person p
            join fetch p.addresses a
            join fetch p.phones
            where p.id = :id
            """)
    List<Person> findPersons(@Param("id") Long id);
}
