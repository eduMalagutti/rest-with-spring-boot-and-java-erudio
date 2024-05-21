package com.eduardo.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardo.exceptions.ResourceNotFoundException;
import com.eduardo.mapper.DozerMapper;
import com.eduardo.mapper.custom.PersonMapper;
import com.eduardo.model.Person;
import com.eduardo.repositories.PersonRepository;
import com.eduardo.data.vo.v1.PersonVO;
import com.eduardo.data.vo.v2.PersonVOV2;

@Service
public class PersonServices {
    
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;
    
    @Autowired
    PersonMapper personMapper;
    
    public List<PersonVO> findAll() {
        
        logger.info("finding all people!");

        return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
    }
    
    public PersonVO findById(Long id) {
        
        logger.info("finding one person!");

        var entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records for this id"));

        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public PersonVO create(PersonVO person)
    {
        logger.info("Creating one person");

        var entity = DozerMapper.parseObject(person, Person.class);
        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        return vo;
    }
    
    public PersonVOV2 createV2(PersonVOV2 person)
    {
        logger.info("Creating one person");

        var entity = personMapper.convertVotoEntity(person);
        var vo = personMapper.convertEntityToVo(repository.save(entity));
        return vo;
    }
    
    public PersonVO update(PersonVO person)
    {
        logger.info("Updating one person");

        var entity = repository.findById(person.getId()).orElseThrow(()-> new ResourceNotFoundException("No records for this id"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        return vo;
    }
    
    public void delete(Long id)
    {
        logger.info("Deleting one person " + id.toString());

        var entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records for this id"));

        repository.delete(entity);
    }
}
