package com.huawei.ibn.model.physical;

import com.huawei.ibn.model.common.GraphNode;
import com.huawei.ibn.model.location.AbstractLocation;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Device extends GraphNode {

    private String vendor;
    private Date uptime;

    @Relationship(type = "in")
    private AbstractLocation location;

    @Relationship(type = "has")
    private Set<LineCard> lineCards;

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

    public void addLineCard(LineCard lineCard){
        if (lineCards == null){
            lineCards = new HashSet<>();
        }

        lineCards.add(lineCard);
    }
}
