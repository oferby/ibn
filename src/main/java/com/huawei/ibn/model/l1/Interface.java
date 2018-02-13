package com.huawei.ibn.model.l1;

import com.huawei.ibn.model.common.GraphNode;
import org.neo4j.ogm.annotation.Relationship;

public class Interface extends GraphNode {

    private InterfaceType interfaceType;
    private int bps;
    private boolean isUp;
    private boolean isEnabled;

    private int inBites;
    private int outBytes;

    @Relationship(type = "connect-to")
    private Interface connection;


    public InterfaceType getInterfaceType() {
        return interfaceType;
    }

    void setInterfaceType(InterfaceType interfaceType) {
        this.interfaceType = interfaceType;
    }

    public int getBps() {
        return bps;
    }

    public void setBps(int bps) {
        this.bps = bps;
    }

    public Interface getConnection() {
        return connection;
    }

    public void setConnection(Interface connection) {
        this.connection = connection;
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
}
