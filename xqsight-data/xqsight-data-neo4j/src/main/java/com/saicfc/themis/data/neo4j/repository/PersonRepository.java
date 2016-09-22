package com.saicfc.themis.data.neo4j.repository;

import com.saicfc.themis.data.neo4j.domain.fact.Person;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by GTaurus on 2016/4/5.
 */
@Repository
public interface PersonRepository extends GraphRepository<Person> {

    @Query("start person:node({0}) match person-[:OWN]->(phone:Phone) return person")
    List<Person> getRelatives(Person person);

    @Query("match (person:Person) where person.certNo = {0} and person.certType = {1} return person")
    List<Person> findAllByCert(String certNo, String certType);

    @Query()
    List<Person> merge(String idNo, String location);
}
