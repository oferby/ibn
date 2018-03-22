package com.huawei.ibn.model.l3;

import com.huawei.ibn.model.common.GraphNode;
import com.huawei.ibn.model.l1.Interface;
import org.neo4j.ogm.annotation.Relationship;

public class Route extends AbstractRoute{

    @Relationship(type = "DESTINATION")
    private CidrBlock destination;

    @Relationship(type = "NEXT_HOP")
    private IpAddress nextHop;

    @Relationship(type = "OUT_INF")
    private Interface outInterface;

    public CidrBlock getDestination() {
        return destination;
    }

    public void setDestination(CidrBlock destination) {
        this.destination = destination;
    }

    public IpAddress getNextHop() {
        return nextHop;
    }

    public void setNextHop(IpAddress nextHop) {
        this.nextHop = nextHop;
    }

    public Interface getOutInterface() {
        return outInterface;
    }

    public void setOutInterface(Interface outInterface) {
        this.outInterface = outInterface;
    }
}
