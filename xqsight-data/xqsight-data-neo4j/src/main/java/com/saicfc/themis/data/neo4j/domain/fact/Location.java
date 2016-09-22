package com.saicfc.themis.data.neo4j.domain.fact;

import com.saicfc.themis.data.neo4j.domain.Entity;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by GTaurus on 2016/4/5.
 */
@NodeEntity(label = "Location")
public class Location extends Entity {

    private String address;

    private String locationType;

    public Location(String address, String locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }
}
