package com.huawei.ibn.model.l1;

import com.huawei.ibn.model.l2.MacAddress;
import com.huawei.ibn.model.l3.IpAddress;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class EthernetInterface extends Interface {

    @GraphId
    private
    Long id;

    private String name;

    private InterfaceType interfaceType = InterfaceType.ETHERNET;
    private int bps;
    private boolean isUp;
    private boolean isEnabled;

    private int inBites;
    private int outBytes;

    @Relationship(type = "CONFIG", direction = "INCOMING")
    private MacAddress macAddress;

    @Relationship(type = "CONFIG", direction = "INCOMING")
    private IpAddress ipAddress;

    @Relationship(type = "CONNECT_TO", direction = "UNDIRECTED")
    private EthernetInterface connected;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setId(Long id) {
        this.id = id;
    }

    public InterfaceType getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(InterfaceType interfaceType) {
        this.interfaceType = interfaceType;
    }

    public int getBps() {
        return bps;
    }

    public void setBps(int bps) {
        this.bps = bps;
    }

    public boolean isUp() {
        return isUp;
    }

    public void setUp(boolean up) {
        isUp = up;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public int getInBites() {
        return inBites;
    }

    public void setInBites(int inBites) {
        this.inBites = inBites;
    }

    public int getOutBytes() {
        return outBytes;
    }

    public void setOutBytes(int outBytes) {
        this.outBytes = outBytes;
    }

    public EthernetInterface getConnected() {
        return connected;
    }

    public void setConnected(EthernetInterface connected) {
        this.connected = connected;
    }
}
