package com.eduardo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardo.model.Person;

//@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {}
