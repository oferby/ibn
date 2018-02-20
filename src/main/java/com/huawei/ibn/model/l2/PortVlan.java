package com.huawei.ibn.model.l2;

import com.huawei.ibn.model.common.GraphNode;
import org.neo4j.ogm.annotation.Relationship;

public class PortVlan extends GraphNode{

    @Relationship(type = "CONFIG")
    private Vlan vlan;

    public Vlan getVlan() {
        return vlan;
    }

    public void setVlan(Vlan vlan) {
        this.vlan = vlan;
    }
}
