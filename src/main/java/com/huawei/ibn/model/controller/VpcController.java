package com.huawei.ibn.model.controller;

import com.huawei.ibn.model.l3.VirtualPrivateNetwork;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface VpcController extends Neo4jRepository<VirtualPrivateNetwork, Long> {

    VirtualPrivateNetwork findByName(String name);

}
