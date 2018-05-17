package com.huawei.ibn.model.acl;

import com.huawei.ibn.model.common.GraphNode;
import com.huawei.ibn.model.l3.Subnet;
import com.huawei.ibn.model.l3.VirtualPrivateNetwork;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

public class AccessControl extends GraphNode{

    @Relationship(type = "ATTACH_TO")
    private VirtualPrivateNetwork vpc;

    @Relationship(type = "CONFIG")
    private Set<AccessControlRule> controlRules;

    @Relationship(type = "APPLY_TO")
    private Set<Subnet>subnets;

    public VirtualPrivateNetwork getVpc() {
        return vpc;
    }

    public void setVpc(VirtualPrivateNetwork vpc) {
        this.vpc = vpc;
    }

    public Set<AccessControlRule> getControlRules() {
        return controlRules;
    }

    public void setControlRules(Set<AccessControlRule> controlRules) {
        this.controlRules = controlRules;
    }

    public void addRule(AccessControlRule rule){
        if (controlRules ==null){
            controlRules = new HashSet<>();
        }

        controlRules.add(rule);
    }

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
