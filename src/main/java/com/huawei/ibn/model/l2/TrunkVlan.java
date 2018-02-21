package com.huawei.ibn.model.l2;

import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

public class TrunkVlan extends PortVlan {

    @Relationship(type = "L2_PATH")
    private Set<Vlan> vlanSet;

    @Relationship(type = "L2_PATH")
    private Vlan nativeVlan;

    public Vlan getNativeVlan() {
        return nativeVlan;
    }

    public void setNativeVlan(Vlan nativeVlan) {
        this.nativeVlan = nativeVlan;
    }

    public void addVlanMembership(Vlan vlan) {
        if (vlanSet == null) {
            vlanSet = new HashSet<>();
        }

    }

    public Set<Vlan> getVlanSet() {
        return vlanSet;
    }

    public void setVlanSet(Set<Vlan> vlanSet) {
        this.vlanSet = vlanSet;
    }
}
