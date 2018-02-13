package com.huawei.ibn.model.location;

import org.neo4j.ogm.annotation.Relationship;

public class Room extends AbstractLocation{

    @Relationship(type = "in")
    private Floor floor;

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }
}
