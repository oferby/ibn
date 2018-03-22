package com.huawei.ibn.model.controller;

import com.huawei.ibn.model.acl.AccessControl;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface AccessControlController extends GraphRepository<AccessControl>{

    AccessControl findByName(String name);

}
