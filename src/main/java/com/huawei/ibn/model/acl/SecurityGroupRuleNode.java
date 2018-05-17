package com.huawei.ibn.model.acl;

import com.huawei.ibn.model.common.GraphNode;
import com.huawei.ibn.model.l3.CidrBlock;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

public class SecurityGroupRuleNode extends GraphNode{

    private String ipProtocol;
    private Integer fromPort;
    private Integer toPort;
    private AccessControlType type;

    @Relationship(type = "APPLY_TO")
    private Set<CidrBlock> cidrBlocks;

    @Relationship(type = "APPLY_TO")
    private Set<SecurityGroupNode>securityGroupNodes;


    public String getIpProtocol() {
        return ipProtocol;
    }

    public void setIpProtocol(String ipProtocol) {
        this.ipProtocol = ipProtocol;
    }

    public Integer getFromPort() {
        return fromPort;
    }

    public void setFromPort(Integer fromPort) {
        this.fromPort = fromPort;
    }

    public Integer getToPort() {
        return toPort;
    }

    public void setToPort(Integer toPort) {
        this.toPort = toPort;
    }

    public Set<CidrBlock> getCidrBlocks() {
        return cidrBlocks;
    }

    public void addCidr(CidrBlock cidrBlock){
        if (cidrBlocks==null){
            cidrBlocks = new HashSet<>();
        }
        cidrBlocks.add(cidrBlock);
    }

    public AccessControlType getType() {
        return type;
    }

    public void setType(AccessControlType type) {
        this.type = type;
    }

    public Set<SecurityGroupNode> getSecurityGroupNodes() {
        return securityGroupNodes;
    }

    public void setSecurityGroupNodes(Set<SecurityGroupNode> securityGroupNodes) {
        this.securityGroupNodes = securityGroupNodes;
    }

    public void addSecurityGroupNode(SecurityGroupNode node){
        if (securityGroupNodes==null){
            securityGroupNodes = new HashSet<>();
        }

        securityGroupNodes.add(node);
    }
}
