package com.huawei.ibn.model.physical;

import com.huawei.ibn.model.common.GraphNode;
import com.huawei.ibn.model.l1.Interface;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

public class LineCard extends GraphNode{

    @Relationship(type = "in")
    private Device device;

    @Relationship(type = "has")
    private Set<Interface> interfaces;

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Set<Interface> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(Set<Interface> interfaces) {
        this.interfaces = interfaces;
    }
}
