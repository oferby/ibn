package com.huawei.ibn.model.l2;

import com.huawei.ibn.model.common.GraphNode;
import com.huawei.ibn.model.l1.Interface;
import com.huawei.ibn.model.physical.LineCard;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

@NodeEntity
public class Bridge extends GraphNode{

    @Relationship(type = "in")
    private LineCard lineCard;

    @Relationship(type = "has")
    private Set<Interface> interfaces;

}
