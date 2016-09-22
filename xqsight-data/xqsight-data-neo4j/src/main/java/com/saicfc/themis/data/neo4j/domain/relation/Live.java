package com.saicfc.themis.data.neo4j.domain.relation;

import com.saicfc.themis.data.neo4j.domain.fact.Location;
import com.saicfc.themis.data.neo4j.domain.fact.Person;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Created by GTaurus on 2016/4/5.
 */
@RelationshipEntity(type = "LIVE")
public class Live {

    @StartNode
    private Person person;
    @EndNode
    private Location location;

    private String type;
}
