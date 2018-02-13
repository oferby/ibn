package com.huawei.ibn.model.l2;

import com.huawei.ibn.model.common.GraphNode;

import java.security.InvalidParameterException;

public class MacAddress extends GraphNode {

    private byte[] address;
    private String stringAddress;

    public byte[] getAddress() {
        return address;
    }

    public void setAddress(byte[] address) {
        if (address.length != 6) {
            throw new InvalidParameterException();
        }
        this.address = address;
    }

    public String getStringAddress() {
        return stringAddress;
    }

    public void setStringAddress(String stringAddress) {
        this.stringAddress = stringAddress;
    }
}
