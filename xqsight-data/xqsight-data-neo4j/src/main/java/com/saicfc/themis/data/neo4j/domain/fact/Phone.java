package com.saicfc.themis.data.neo4j.domain.fact;

import com.saicfc.themis.data.neo4j.domain.Entity;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by GTaurus on 2016/4/5.
 */
@NodeEntity(label="Phone")
public class Phone extends Entity {

    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
