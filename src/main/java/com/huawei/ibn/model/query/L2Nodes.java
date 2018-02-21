package com.huawei.ibn.model.query;

import com.huawei.ibn.model.l1.EthernetInterface;
import com.huawei.ibn.model.l2.PortVlan;
import com.huawei.ibn.model.l2.Vlan;
import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class L2Nodes {

    private EthernetInterface ethernetInterface;
    private PortVlan portVlan;
    private Vlan vlan;

    public EthernetInterface getEthernetInterface() {
        return ethernetInterface;
    }

    public void setEthernetInterface(EthernetInterface ethernetInterface) {
        this.ethernetInterface = ethernetInterface;
    }

    public PortVlan getPortVlan() {
        return portVlan;
    }

    public void setPortVlan(PortVlan portVlan) {
        this.portVlan = portVlan;
    }

    public Vlan getVlan() {
        return vlan;
    }

    public void setVlan(Vlan vlan) {
        this.vlan = vlan;
    }
}
