package com.huawei.ibn.model.acl;

import com.huawei.ibn.model.common.GraphNode;
import com.huawei.ibn.model.l3.VirtualPrivateNetwork;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

public class SecurityGroupNode extends GraphNode{

    @Relationship(type = "ATTACH")
    private VirtualPrivateNetwork vpc;

    @Relationship(type = "RULE")
    private Set<AccessControlRule>accessControlRules;

    public VirtualPrivateNetwork getVpc() {
        return vpc;
    }

    public void setVpc(VirtualPrivateNetwork vpc) {
        this.vpc = vpc;
    }

    public Set<AccessControlRule> getAccessControlRules() {
        return accessControlRules;
    }

    public void setAccessControlRules(Set<AccessControlRule> accessControlRules) {
        this.accessControlRules = accessControlRules;
    }

    public void addRule(AccessControlRule rule){
        if (accessControlRules==null){
            accessControlRules = new HashSet<>();
        }

        accessControlRules.add(rule);
    }
}
