package com.huawei.ibn.model.l2;

import org.neo4j.ogm.annotation.Relationship;

public class AccessVlan extends PortVlan{

    @Relationship(type = "L2_CONFIG")
    private Vlan vlan;

    public AccessVlan() {
    }

    public AccessVlan(Vlan vlan) {
        this.vlan = vlan;
    }

    public Vlan getVlan() {
        return vlan;
    }

    public void setVlan(Vlan vlan) {
        this.vlan = vlan;
    }
}
