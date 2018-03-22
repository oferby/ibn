package com.huawei.ibn.model.l3;

import com.huawei.ibn.model.common.GraphNode;
import com.huawei.ibn.model.l1.EthernetInterface;
import com.huawei.ibn.model.l1.Interface;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

public class VirtualRouter extends GraphNode {

    @Relationship(type = "CONFIG")
    private Set<RoutingTable> routingTableSet;

    @Relationship(type = "HAS")
    private Set<EthernetInterface> interfaceSet;

    public Set<RoutingTable> getRoutingTableSet() {
        return routingTableSet;
    }

    public void setRoutingTableSet(Set<RoutingTable> routingTableSet) {
        this.routingTableSet = routingTableSet;
    }

    public void addRoutingTable(RoutingTable routingTable){
        if (routingTableSet == null){
            routingTableSet = new HashSet<>();
        }

        routingTableSet.add(routingTable);

    }

    public Set<EthernetInterface> getInterfaceSet() {
        return interfaceSet;
    }

    public void setInterfaceSet(Set<EthernetInterface> interfaceSet) {
        this.interfaceSet = interfaceSet;
    }

    public void addEthernetInterface(EthernetInterface ethernetInterface){
        if (interfaceSet == null) {
            interfaceSet = new HashSet<>();
        }

        interfaceSet.add(ethernetInterface);

    }
}
