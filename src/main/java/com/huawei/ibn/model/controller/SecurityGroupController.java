package com.huawei.ibn.model.controller;

import com.huawei.ibn.model.acl.SecurityGroupNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface SecurityGroupController extends Neo4jRepository<SecurityGroupNode, Long> {

    SecurityGroupNode findByName(String name);


}
