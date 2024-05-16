package com.eduardo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduardo.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {}
