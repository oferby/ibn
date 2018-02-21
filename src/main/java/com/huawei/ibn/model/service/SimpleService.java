package com.huawei.ibn.model.service;

import com.huawei.ibn.model.common.GraphNode;
import com.huawei.ibn.model.l4.Port;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

public class SimpleService extends GraphNode{

    @Relationship(type = "BOUND")
    private Port port;

    @Relationship(type = "TYPE_OF")
    private Set<ServiceType> serviceTypeSet;

    public Port getPort() {
        return port;
    }

    public void setPort(Port port) {
        this.port = port;
    }

    public Set<ServiceType> getServiceTypeSet() {
        return serviceTypeSet;
    }

    public void setServiceTypeSet(Set<ServiceType> serviceTypeSet) {
        this.serviceTypeSet = serviceTypeSet;
    }
}
