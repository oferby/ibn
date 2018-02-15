package com.huawei.ibn.model.common;

public enum Direction {
    INCOMING("INCOMING"), OUTGOING("OUTGOING"),UNDIRECTED("UNDIRECTED")  ;

    private final String direction;

    Direction(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

}
