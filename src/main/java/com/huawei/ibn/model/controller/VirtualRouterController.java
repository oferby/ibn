package com.huawei.ibn.model.controller;

import com.huawei.ibn.model.l3.VirtualRouter;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface VirtualRouterController extends Neo4jRepository<VirtualRouter, Long> {

    VirtualRouter findByName(String name);

}
