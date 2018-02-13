package com.huawei.ibn.model.location;

import org.neo4j.ogm.annotation.Relationship;

public class Floor extends AbstractLocation {

    private short number;

    @Relationship(type = "in")
    private Building building;

    public short getNumber() {
        return number;
    }

    public void setNumber(short number) {
        this.number = number;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
