package com.huawei.ibn.model.virtual;

import com.huawei.ibn.model.common.GraphNode;
import com.huawei.ibn.model.l1.EthernetInterface;
import com.huawei.ibn.model.l3.Subnet;
import com.huawei.ibn.model.provider.Cloud;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

public class ComputeNode extends GraphNode {

    private Integer numOfCpu;

    private Integer memorySize;

    private String imageType;

    private String state;

    @Relationship(type = "HAS")
    private Set<EthernetInterface> ethernetInterfaces;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(Integer memorySize) {
        this.memorySize = memorySize;
    }

    public Integer getNumOfCpu() {
        return numOfCpu;
    }

    public void setNumOfCpu(Integer numOfCpu) {
        this.numOfCpu = numOfCpu;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

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
