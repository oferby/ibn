package com.huawei.ibn.model.l2;

import com.huawei.ibn.model.common.GraphNode;
import com.huawei.ibn.model.l1.Interface;
import com.huawei.ibn.model.physical.LineCard;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Bridge extends GraphNode {

    @Relationship(type = "ASSIGN")
    private Set<Interface> interfaces;

    public void addInterface(Interface nic) {
        if (interfaces == null) {
            interfaces = new HashSet<>();
        }

        interfaces.add(nic);
    }

    public Set<Interface> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(Set<Interface> interfaces) {
        this.interfaces = interfaces;
    }
}
