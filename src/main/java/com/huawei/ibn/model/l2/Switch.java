package com.huawei.ibn.model.l2;

import com.huawei.ibn.model.common.GraphNode;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Switch extends GraphNode {

    @Relationship(type = "CONFIG")
    private Vlan vlan;

    @Relationship(type = "CONFIG")
    private Set<SwitchTableEntry> switchTableEntrySet;

    public Switch() {
    }

    public Switch(Vlan vlan) {
        this.vlan = vlan;
    }

    public void addSwithTableEntry(SwitchTableEntry switchTableEntry){
        if (switchTableEntry==null){
            switchTableEntrySet  = new HashSet<>();
        }

        switchTableEntrySet.add(switchTableEntry);
    }

    public Set<SwitchTableEntry> getSwitchTableEntrySet() {
        return switchTableEntrySet;
    }

    public void setSwitchTableEntrySet(Set<SwitchTableEntry> switchTableEntrySet) {
        this.switchTableEntrySet = switchTableEntrySet;
    }

    public Vlan getVlan() {
        return vlan;
    }

    public void setVlan(Vlan vlan) {
        this.vlan = vlan;
    }

}
