package com.huawei.ibn.model.physical;

import com.huawei.ibn.model.common.GraphNode;
import com.huawei.ibn.model.l1.Interface;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class LineCard  extends GraphNode{

    @GraphId
    private
    Long id;

    private String name;

    @Relationship(type = "HAS")
    private Set<Interface> interfaces;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Set<Interface> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(Set<Interface> interfaces) {
        this.interfaces = interfaces;
    }

    public void addInterface(Interface nic) {
        if (interfaces == null) {
            interfaces = new HashSet<>();
        }

        interfaces.add(nic);
    }
}
