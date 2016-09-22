package com.saicfc.themis.data.neo4j.domain.relation;

import com.saicfc.themis.data.neo4j.domain.fact.Person;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Created by GTaurus on 2016/4/5.
 */
@RelationshipEntity(type = "RELATIVE")
public class Relative {
    @StartNode
    private Person person;
    @EndNode
    private Person anotherPerson;

    private String type;
}
