package com.huawei.ibn.model.controller;

import com.huawei.ibn.model.l3.Subnet;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface SubnetController extends Neo4jRepository<Subnet, Long> {

    Subnet findByName(String name);
}
