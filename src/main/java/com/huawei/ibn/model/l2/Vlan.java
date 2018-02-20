package com.huawei.ibn.model.l2;

import com.huawei.ibn.model.common.GraphNode;

import java.security.InvalidParameterException;

public class Vlan extends GraphNode{

    private short vlanId;

    public Vlan() {
    }

    public Vlan(short vlanId) {
        this.vlanId = vlanId;
    }

    public short getVlanId() {
        return vlanId;
    }

    public void setVlanId(short vlanId) {
        if (vlanId > 4096 || vlanId < 0) {
            throw new InvalidParameterException();
        }
        this.vlanId = vlanId;
    }
}
