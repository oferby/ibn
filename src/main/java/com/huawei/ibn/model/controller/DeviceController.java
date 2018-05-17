package com.huawei.ibn.model.controller;

import com.huawei.ibn.model.physical.Device;
import org.springframework.data.neo4j.annotation.Depth;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface DeviceController extends Neo4jRepository<Device, Long> {

    Device findByName(String name);

    Device findByName(String name, @Depth int depth);


}
