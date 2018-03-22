package com.huawei.ibn.model.acl;

public enum ProtocolType {
    All(0),
    UDP(17),
    TCP(6);

    private final int protocolNumber;

    ProtocolType(int protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public int getProtocolNumber() {
        return protocolNumber;
    }
}
