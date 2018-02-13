package com.huawei.ibn.model.l1;

import com.huawei.ibn.model.l2.MacAddress;
import org.neo4j.ogm.annotation.Relationship;

public class EthernetInterface extends Interface{

    @Relationship(type = "has")
    private MacAddress macAddress;

    private EthernetInterface() {
        this.setInterfaceType(InterfaceType.ETHERNET);
    }

}
