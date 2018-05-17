package com.huawei.ibn.model.acl;

import com.huawei.ibn.model.common.GraphNode;
import com.huawei.ibn.model.l3.VirtualPrivateNetwork;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

public class SecurityGroupNode extends GraphNode {

    @Relationship(type = "ATTACH_TO")
    private VirtualPrivateNetwork vpc;

    @Relationship(type = "RULE")
    private Set<SecurityGroupRuleNode> securityGroupRuleNodes;

    public VirtualPrivateNetwork getVpc() {
        return vpc;
    }

    public void setVpc(VirtualPrivateNetwork vpc) {
        this.vpc = vpc;
    }

    public Set<SecurityGroupRuleNode> getSecurityGroupRuleNodes() {
        return securityGroupRuleNodes;
    }

    public void setSecurityGroupRuleNodes(Set<SecurityGroupRuleNode> securityGroupRuleNodes) {
        this.securityGroupRuleNodes = securityGroupRuleNodes;
    }

    public void addRule(SecurityGroupRuleNode rule) {
        if (securityGroupRuleNodes == null) {
            securityGroupRuleNodes = new HashSet<>();
        }

        securityGroupRuleNodes.add(rule);
    }

}
