package com.trofimenko.service;

import com.trofimenko.entites.Person;
import com.trofimenko.entites.Product;
import com.trofimenko.repositories.PersonRepository;
import com.trofimenko.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class PersonService {
    private PersonRepository personRepository;


    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void addPerson(Person p){
        personRepository.save(p);
    }

    public void removeById(Integer id){
        personRepository.deleteById(id);
    }

    @Transactional
    public List<Product> getProductsByPersonId(int id){
        return personRepository.getOneById(id).getProducts();
    }

    public Person getPersonByName(String name){
        return personRepository.getPersonByName(name);
    }









}
