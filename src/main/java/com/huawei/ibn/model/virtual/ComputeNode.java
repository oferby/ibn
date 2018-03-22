package com.huawei.ibn.model.virtual;

import com.huawei.ibn.model.common.GraphNode;
import com.huawei.ibn.model.l1.EthernetInterface;
import com.huawei.ibn.model.l3.Subnet;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

public class ComputeNode extends GraphNode {

    @Relationship(type = "HAS")
    private Set<EthernetInterface> ethernetInterfaces;

    public Set<EthernetInterface> getEthernetInterfaces() {
        return ethernetInterfaces;
    }

    public void setEthernetInterfaces(Set<EthernetInterface> ethernetInterfaces) {
        this.ethernetInterfaces = ethernetInterfaces;
    }

    public void addInterface(EthernetInterface eth) {
        if (ethernetInterfaces == null) {
            ethernetInterfaces = new HashSet<>();
        }

        ethernetInterfaces.add(eth);
    }
}
