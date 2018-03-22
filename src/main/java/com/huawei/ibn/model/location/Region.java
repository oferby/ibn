package com.huawei.ibn.model.location;

import com.huawei.ibn.model.common.GraphNode;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

public class Region extends AbstractLocation {

    @Relationship(type = "HOLDS")
    private Set<GraphNode> nodeSet;

    public Set<GraphNode> getNodeSet() {
        return nodeSet;
    }

    public void setNodeSet(Set<GraphNode> nodeSet) {
        this.nodeSet = nodeSet;
    }

    public void addHoldedNode(GraphNode node){
        if (nodeSet==null) {
            nodeSet = new HashSet<>();
        }
        nodeSet.add(node);
    }
}
