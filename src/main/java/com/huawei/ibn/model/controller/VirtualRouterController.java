package com.huawei.ibn.model.controller;

import com.huawei.ibn.model.l3.VirtualRouter;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface VirtualRouterController extends GraphRepository<VirtualRouter>{

    VirtualRouter findByName(String name);

}
