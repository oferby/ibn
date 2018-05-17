package com.huawei.ibn.model.controller;

import com.huawei.ibn.model.physical.LineCard;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface LineCardController extends Neo4jRepository<LineCard, Long> {


}
