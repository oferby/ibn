package com.huawei.ibn.model.l3;

import com.huawei.ibn.model.acl.AccessControl;
import com.huawei.ibn.model.common.GraphNode;
import com.huawei.ibn.model.provider.Cloud;
import com.huawei.ibn.model.provider.CloudServiceProvider;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

public class VirtualPrivateNetwork extends Network {

    @Relationship(type = "IN")
    private Cloud cloud;

    public Cloud getCloud() {
        return cloud;
    }

    public void setCloud(Cloud cloud) {
        this.cloud = cloud;
    }
}
