package com.eduardo.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardo.exceptions.ResourceNotFoundException;
import com.eduardo.model.Person;
import com.eduardo.repositories.PersonRepository;

@Service
public class PersonServices {
    
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;
    
    public List<Person> findAll() {
        
        logger.info("finding all people!");

        return repository.findAll();
    }
    
    public Person findById(Long id) {
        
        logger.info("finding one person!");

        return repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records for this id"));
    }

    public Person create(Person person)
    {
        logger.info("Creating one person");

        return repository.save(person);
    }
    
    public Person update(Person person)
    {
        logger.info("Updating one person");

        var entity = repository.findById(person.getId()).orElseThrow(()-> new ResourceNotFoundException("No records for this id"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(entity);
    }
    
    public void delete(Long id)
    {
        logger.info("Deleting one person " + id.toString());

        var entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records for this id"));

        repository.delete(entity);
    }
}
