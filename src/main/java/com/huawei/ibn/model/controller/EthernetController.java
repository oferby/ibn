package com.huawei.ibn.model.controller;

import com.huawei.ibn.model.l1.EthernetInterface;
import com.huawei.ibn.model.physical.Device;
import com.huawei.ibn.model.query.DeviceEthernet;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

public interface EthernetController extends GraphRepository<EthernetInterface> {

    @Query("MATCH (d:Device)-[:HAS*]->(e:EthernetInterface) RETURN d as device,COLLECT(e) as EthernetInterfaces")
    List<DeviceEthernet> findDeviceEthernetInterfaces();

}
