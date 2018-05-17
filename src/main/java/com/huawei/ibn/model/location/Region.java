package com.huawei.ibn.model.location;

import com.huawei.ibn.model.common.GraphNode;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Region extends AbstractLocation {

    @Relationship(type = "HAS")
    private Set<AvailabilityZone>availabilityZones;

    @Relationship(type = "HOLDS")
    private Set<GraphNode> nodeSet;

    public Set<GraphNode> getNodeSet() {
        return nodeSet;
    }

    public void setNodeSet(Set<GraphNode> nodeSet) {
        this.nodeSet = nodeSet;
    }

    public void addAll(Collection collection) {

        if (nodeSet == null) {
            nodeSet = new HashSet<>();
        }

        nodeSet.addAll(collection);

    }

    public void addHoldedNode(GraphNode node) {
        if (nodeSet == null) {
            nodeSet = new HashSet<>();
        }
        nodeSet.add(node);
    }

    public void setAvailabilityZones(Set<AvailabilityZone> availabilityZones) {
        this.availabilityZones = availabilityZones;
    }

    public void addAvailabilityZone(AvailabilityZone availabilityZone){
        if (availabilityZones == null) {
            availabilityZones = new HashSet<>();
        }
        availabilityZones.add(availabilityZone);
    }

    public void addAllZones(Collection collection) {

        if (availabilityZones == null) {
            availabilityZones = new HashSet<>();
        }

        availabilityZones.addAll(collection);

    }

    public Set<AvailabilityZone> getAvailabilityZones() {
        return availabilityZones;
    }
}
