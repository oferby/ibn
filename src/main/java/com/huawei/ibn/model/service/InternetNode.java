package com.huawei.ibn.model.service;

import com.huawei.ibn.model.common.GraphNode;
import com.huawei.ibn.model.l3.Subnet;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

public class InternetNode extends GraphNode {

    @Relationship(type = "L3_PATH")
    private Set<Subnet>subnets;

    public Set<Subnet> getSubnets() {
        return subnets;
    }

    public void setSubnets(Set<Subnet> subnets) {
        this.subnets = subnets;
    }

    public void addSubnet(Subnet subnet){
        if (subnets==null){
            subnets = new HashSet<>();
        }
        subnets.add(subnet);
    }
}
