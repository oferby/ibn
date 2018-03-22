package com.huawei.ibn.model.acl;

public enum TrafficType {

    All("all"),
    TCP("tcp"),
    UDP("udp"),
    SSH("22"),
    HTTP("80"),
    HTTPS("443"),
    CUSTOM("custom");

    private final String type;

    private TrafficType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
