package com.huawei.ibn.model.l1;

import com.huawei.ibn.model.l2.DuplexMode;
import com.huawei.ibn.model.l2.MacAddress;
import com.huawei.ibn.model.l2.PortVlan;
import com.huawei.ibn.model.l3.IpAddress;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class EthernetInterface extends Interface {

    private boolean autoNegotiation;

    private DuplexMode duplexMode;

    @Relationship(type = "CONFIG")
    private MacAddress macAddress;

    @Relationship(type = "L2_CONFIG")
    private PortVlan portVlan;

    @Relationship(type = "CONFIG")
    private IpAddress ipAddress;

    public MacAddress getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(MacAddress macAddress) {
        this.macAddress = macAddress;
    }

    public PortVlan getPortVlan() {
        return portVlan;
    }

    public void setPortVlan(PortVlan portVlan) {
        this.portVlan = portVlan;
    }

    public IpAddress getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(IpAddress ipAddress) {
        this.ipAddress = ipAddress;
    }

    public boolean isAutoNegotiation() {
        return autoNegotiation;
    }

    public void setAutoNegotiation(boolean autoNegotiation) {
        this.autoNegotiation = autoNegotiation;
    }

    public DuplexMode getDuplexMode() {
        return duplexMode;
    }

    public void setDuplexMode(DuplexMode duplexMode) {
        this.duplexMode = duplexMode;
    }
}
