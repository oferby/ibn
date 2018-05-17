package com.huawei.ibn.model.controller;

import com.huawei.ibn.model.acl.AccessControl;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface AccessControlController extends Neo4jRepository<AccessControl, Long> {

    AccessControl findByName(String name);

}
