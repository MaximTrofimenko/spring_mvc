package com.trofimenko.repositories;

import com.trofimenko.entites.Person;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PersonRepository extends PagingAndSortingRepository<Person, Integer> {

    Person getPersonByName(String name);


    Person getOneById(Integer id);
}
