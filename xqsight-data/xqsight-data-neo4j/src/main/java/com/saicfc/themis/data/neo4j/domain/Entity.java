package com.saicfc.themis.data.neo4j.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.GraphId;

/**
 * Created by GTaurus on 2016/4/7.
 */
public class Entity {

    @GraphId private Long graphId;

    public Long getGraphId() {
        return graphId;
    }

    public void setGraphId(Long graphId) {
        this.graphId = graphId;
    }
}
