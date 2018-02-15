package com.huawei.ibn.model.l1;

import com.huawei.ibn.model.l2.MacAddress;
import com.huawei.ibn.model.l3.IpAddress;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class EthernetInterface extends Interface{

    @Relationship(type = "has")
    private MacAddress macAddress;

    private IpAddress ipAddress;

    public EthernetInterface() {
        this.setInterfaceType(InterfaceType.ETHERNET);
    }

    public MacAddress getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(MacAddress macAddress) {
        this.macAddress = macAddress;
    }

    public IpAddress getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(IpAddress ipAddress) {
        this.ipAddress = ipAddress;
    }
}
