package com.huawei.ibn.model.controller;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface PathController extends Neo4jRepository {

    @Query("match (e1) where id(e1)={id1} match (e2) where id(e2)={id2} match (e1)-[:L2_PATH|:CONNECT_TO*]-(e2) return e1 is not null")
    boolean l2PathExists(@Param("id1") int id1, @Param("id2") int id2);

}
