package com.huawei.ibn.model.controller;

import com.huawei.ibn.model.l3.Subnet;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface SubnetController extends GraphRepository<Subnet> {

    Subnet findByName(String name);
}
