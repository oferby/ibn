package com.huawei.ibn.cloud.openstack.identity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
