package com.huawei.ibn.model.controller;

import com.huawei.ibn.model.l3.VirtualPrivateNetwork;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface VpcController extends GraphRepository<VirtualPrivateNetwork>{


}
