package com.huawei.ibn.model.controller;

import com.huawei.ibn.model.physical.Device;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface DeviceController extends GraphRepository<Device> {

    Device findByName(String name);


}
