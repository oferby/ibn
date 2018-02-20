package com.huawei.ibn.model.l2;

import com.huawei.ibn.model.common.GraphNode;
import com.huawei.ibn.model.l1.EthernetInterface;
import org.neo4j.ogm.annotation.Relationship;

public class SwitchTableEntry extends GraphNode {

    @Relationship(type = "DISCOVER")
    private MacAddress macAddress;

    @Relationship(type = "DISCOVER")
    private EthernetInterface outPort;

    public MacAddress getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(MacAddress macAddress) {
        this.macAddress = macAddress;
    }

    public EthernetInterface getOutPort() {
        return outPort;
    }

    public void setOutPort(EthernetInterface outPort) {
        this.outPort = outPort;
    }
}
