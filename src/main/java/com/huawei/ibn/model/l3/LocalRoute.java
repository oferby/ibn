package com.huawei.ibn.model.l3;

import org.neo4j.ogm.annotation.Relationship;

public class LocalRoute extends AbstractRoute{

    @Relationship(type = "LOCAL")
    private IpAddress ipAddress;

    public IpAddress getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(IpAddress ipAddress) {
        this.ipAddress = ipAddress;
    }
}
