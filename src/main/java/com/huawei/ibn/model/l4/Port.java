package com.huawei.ibn.model.l4;

import com.huawei.ibn.model.common.GraphNode;
import com.huawei.ibn.model.l3.IpAddress;
import org.neo4j.ogm.annotation.Relationship;

public class Port extends GraphNode{

    private int portNumber;

    @Relationship(type = "BOUND")
    private IpAddress ipAddress;

    public int getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

    public IpAddress getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(IpAddress ipAddress) {
        this.ipAddress = ipAddress;
    }
}
