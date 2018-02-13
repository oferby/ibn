package com.huawei.ibn.model.l2;

import com.huawei.ibn.model.l1.Interface;

import java.security.InvalidParameterException;

public class VlanInterface extends Interface {

    private short vlanId;

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
