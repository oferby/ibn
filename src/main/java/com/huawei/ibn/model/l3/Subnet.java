package com.huawei.ibn.model.l3;

import com.huawei.ibn.model.acl.AccessControl;
import com.huawei.ibn.model.common.GraphNode;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

public class Subnet extends GraphNode{

    @Relationship(type = "CONFIG")
    private CidrBlock networkAddress;

    @Relationship(type = "MEMBERS")
    private Set<IpAddress> ipAddressSet;

    @Relationship(type = "CONFIG")
    private AccessControl accessControl;

    public CidrBlock getNetworkAddress() {
        return networkAddress;
    }

    public void setNetworkAddress(CidrBlock networkAddress) {
        this.networkAddress = networkAddress;
    }

    public Set<IpAddress> getIpAddressSet() {
        return ipAddressSet;
    }

    public void setIpAddressSet(Set<IpAddress> ipAddressSet) {
        this.ipAddressSet = ipAddressSet;
    }

    public void addMember(IpAddress ipAddress){
        if (ipAddressSet ==null){
            ipAddressSet = new HashSet<>();
        }

        ipAddressSet.add(ipAddress);
    }

    public AccessControl getAccessControlList() {
        return accessControl;
    }

    public void setAccessControlList(AccessControl accessControlList) {
        this.accessControl = accessControl;
    }
}
