package com.huawei.ibn.model.physical;

import com.huawei.ibn.model.common.GraphNode;
import com.huawei.ibn.model.l1.Interface;
import com.huawei.ibn.model.l2.Switch;
import com.huawei.ibn.model.l2.Vlan;
import com.huawei.ibn.model.location.AbstractLocation;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Device extends GraphNode {

    private String vendor;
    private Date uptime;

    @Relationship(type = "IN")
    private AbstractLocation location;

    @Relationship(type = "HAS")
    private Set<LineCard> lineCards;

    @Relationship(type = "HAS")
    private Set<Interface> interfaceSet;

    @Relationship(type = "CONFIG")
    private Set<Switch> switchSet;

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public Date getUptime() {
        return uptime;
    }

    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }

    public AbstractLocation getLocation() {
        return location;
    }

    public void setLocation(AbstractLocation location) {
        this.location = location;
    }

    public Set<LineCard> getLineCards() {
        return lineCards;
    }

    public void setLineCards(Set<LineCard> lineCards) {
        this.lineCards = lineCards;
    }

    public void addLineCard(LineCard lineCard) {
        if (lineCards == null) {
            lineCards = new HashSet<>();
        }

        lineCards.add(lineCard);
    }

    public void addInterface(Interface anInterface) {
        if (interfaceSet == null) {
            interfaceSet = new HashSet<>();
        }
        interfaceSet.add(anInterface);
    }

    public Set<Interface> getInterfaceSet() {
        return interfaceSet;
    }

    public void setInterfaceSet(Set<Interface> interfaceSet) {
        this.interfaceSet = interfaceSet;
    }

    public Switch addNewBridge(short vlanId) {
        if (switchSet == null) {
            switchSet = new HashSet<>();
        }
        Switch aSwitch = new Switch(new Vlan(vlanId));
        switchSet.add(aSwitch);

        return aSwitch;
    }

    public Set<Switch> getSwitchSet() {
        return switchSet;
    }

    public void setSwitchSet(Set<Switch> switchSet) {
        this.switchSet = switchSet;
    }
}
