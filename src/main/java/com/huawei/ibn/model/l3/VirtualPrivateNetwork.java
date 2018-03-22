package com.huawei.ibn.model.l3;

import com.huawei.ibn.model.acl.AccessControl;
import com.huawei.ibn.model.common.GraphNode;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

public class VirtualPrivateNetwork extends GraphNode {

    @Relationship(type = "CONFIG")
    private Set<CidrBlock> cidrBlockSet;

    @Relationship(type = "CONFIG")
    private Set<Subnet> subnets;

    @Relationship(type = "CONFIG")
    private Set<AccessControl>accessControls;

    @Relationship(type = "CONFIG")
    private VirtualRouter virtualRouter;

    public Set<CidrBlock> getCidrBlockSet() {
        return cidrBlockSet;
    }

    public void setCidrBlockSet(Set<CidrBlock> cidrBlockSet) {
        this.cidrBlockSet = cidrBlockSet;
    }

    public void addCidr(CidrBlock cidr) {
        if (cidrBlockSet == null) {
            cidrBlockSet = new HashSet<>();
        }

        cidrBlockSet.add(cidr);
    }

    public Set<Subnet> getSubnets() {
        return subnets;
    }

    public void setSubnets(Set<Subnet> subnets) {
        this.subnets = subnets;
    }

    public void addSubnet(Subnet subnet) {
        if (subnets == null) {
            subnets = new HashSet<>();
        }

        subnets.add(subnet);
    }

    public Set<AccessControl> getAccessControls() {
        return accessControls;
    }

    public void setAccessControls(Set<AccessControl> accessControls) {
        this.accessControls = accessControls;
    }

    public void addAccessControl(AccessControl accessControl){
        if (accessControls == null) {
            accessControls = new HashSet<>();
        }

        accessControls.add(accessControl);
    }

    public VirtualRouter getVirtualRouter() {
        return virtualRouter;
    }

    public void setVirtualRouter(VirtualRouter virtualRouter) {
        this.virtualRouter = virtualRouter;
    }
}
