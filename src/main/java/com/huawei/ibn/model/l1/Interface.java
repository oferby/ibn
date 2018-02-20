package com.huawei.ibn.model.l1;

import com.huawei.ibn.model.common.GraphNode;
import org.neo4j.ogm.annotation.Relationship;

import java.security.InvalidParameterException;

public class Interface extends GraphNode {

    private InterfaceType interfaceType;
    private int portSpeed;
    private boolean isUp;
    private boolean isEnabled;

    private int inBites;
    private int outBytes;

    @Relationship(type = "CONNECT_TO", direction = "UNDIRECTED")
    private Interface connected;

    public InterfaceType getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(InterfaceType interfaceType) {
        this.interfaceType = interfaceType;
    }

    public int getPortSpeed() {
        return portSpeed;
    }

    public void setPortSpeed(int portSpeed) {
        this.portSpeed = portSpeed;
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

    public Interface getConnected() {
        return connected;
    }

    public void setConnected(Interface connected) {

        if (connected.getInterfaceType() != this.getInterfaceType()) {
            throw new InvalidParameterException("the interface is not with the same type");
        }

        this.connected = connected;
    }
}
