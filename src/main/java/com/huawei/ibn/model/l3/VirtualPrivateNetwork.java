package com.huawei.ibn.model.l3;

import com.huawei.ibn.model.common.GraphNode;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

public class VirtualPrivateNetwork extends GraphNode {

    @Relationship(type = "CONFIG")
    private Set<CidrBlock> cidrBlockSet;

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
}
