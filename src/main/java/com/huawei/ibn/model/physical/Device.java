package com.huawei.ibn.model.physical;

import com.huawei.ibn.model.common.GraphNode;
import com.huawei.ibn.model.location.AbstractLocation;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Date;

public class Device extends GraphNode {

    private String vendor;
    private Date uptime;

    @Relationship(type = "in")
    private AbstractLocation location;

    @Relationship(type = "has")
    private LineCard lineCard;

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

    public LineCard getLineCard() {
        return lineCard;
    }

    public void setLineCard(LineCard lineCard) {
        this.lineCard = lineCard;
    }

}
