package com.huawei.ibn.model.service;

import com.huawei.ibn.model.common.GraphNode;
import com.huawei.ibn.model.l3.VirtualPrivateNetwork;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

public class InternetGatwayNode extends GraphNode{

    @Relationship(type = "PROVIDE")
    private InternetNode internetNode;

    @Relationship(type = "ATTACH")
    private Set<VirtualPrivateNetwork> vpcs;

    public Set<VirtualPrivateNetwork> getVpcs() {
        return vpcs;
    }

    public void setVpcs(Set<VirtualPrivateNetwork> vpcs) {
        this.vpcs = vpcs;
    }

    public void addVpv(VirtualPrivateNetwork vpc){
        if (vpcs==null){
            vpcs = new HashSet<>();
        }

        vpcs.add(vpc);
    }

    public InternetNode getInternetNode() {
        return internetNode;
    }

    public void setInternetNode(InternetNode internetNode) {
        this.internetNode = internetNode;
    }
}
