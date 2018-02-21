package com.huawei.ibn.model.controller;

import com.huawei.ibn.model.physical.Device;
import org.springframework.data.neo4j.annotation.Depth;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;
import java.util.ListIterator;
import java.util.Set;

public interface DeviceController extends GraphRepository<Device> {

    Device findByName(String name);

    Device findByName(String name, @Depth int depth);


}
