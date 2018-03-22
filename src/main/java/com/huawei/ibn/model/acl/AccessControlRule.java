package com.huawei.ibn.model.acl;

import com.huawei.ibn.model.common.GraphNode;
import com.huawei.ibn.model.l3.CidrBlock;
import org.neo4j.ogm.annotation.Relationship;

public class AccessControlRule extends GraphNode{

    private int ruleNumber;

    private AccessControlType accessControlType;

    @Relationship(type = "CONFIG")
    private TrafficTypeNode trafficTypeNode;

    @Relationship(type = "CONFIG")
    private ProtocolTypeNode protocolTypeNode;

    private boolean allow;

    @Relationship(type = "CONFIG")
    private CidrBlock source;

    @Relationship(type = "CONFIG")
    private CidrBlock destination;


    private Integer port;

    public int getRuleNumber() {
        return ruleNumber;
    }

    public void setRuleNumber(int ruleNumber) {
        this.ruleNumber = ruleNumber;
    }

    public AccessControlType getAccessControlType() {
        return accessControlType;
    }

    public void setAccessControlType(AccessControlType accessControlType) {
        this.accessControlType = accessControlType;
    }

    public TrafficTypeNode getTrafficTypeNode() {
        return trafficTypeNode;
    }

    public void setTrafficTypeNode(TrafficTypeNode trafficTypeNode) {
        this.trafficTypeNode = trafficTypeNode;
    }

    public ProtocolTypeNode getProtocolTypeNode() {
        return protocolTypeNode;
    }

    public void setProtocolTypeNode(ProtocolTypeNode protocolTypeNode) {
        this.protocolTypeNode = protocolTypeNode;
    }

    public boolean isAllow() {
        return allow;
    }

    public void setAllow(boolean allow) {
        this.allow = allow;
    }

    public CidrBlock getSource() {
        return source;
    }

    public void setSource(CidrBlock source) {
        this.source = source;
    }

    public CidrBlock getDestination() {
        return destination;
    }

    public void setDestination(CidrBlock destination) {
        this.destination = destination;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
