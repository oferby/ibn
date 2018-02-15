package com.huawei.ibn.model.l2;

import com.huawei.ibn.model.common.GraphNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import java.security.InvalidParameterException;

@NodeEntity
public class MacAddress {

    @GraphId
    private
    Long id;

    private String name;

    private byte[] address;
    private String stringAddress;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
