package com.saicfc.themis.data.neo4j;

import com.saicfc.themis.data.neo4j.domain.fact.Person;
import com.saicfc.themis.data.neo4j.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.data.neo4j.template.Neo4jTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * Created by GTaurus on 2016/4/5.
 */
@Service
@Transactional
public class PersonRelationShipService {

    @Autowired
    private Neo4jOperations neo4jTemplate;

    @Autowired
    private PersonRepository personRepository;

    public List<Person> getPersons() {
        Person p = new Person();
        p.setName("GTaurus");
//        List<Person> persons = personRepository.getRelatives(p);
        return Collections.emptyList();
    }



}
