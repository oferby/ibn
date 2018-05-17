package com.huawei.ibn.model.controller;

import com.huawei.ibn.model.common.GraphNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface GraphNodeController extends Neo4jRepository<GraphNode, Long> {


}
