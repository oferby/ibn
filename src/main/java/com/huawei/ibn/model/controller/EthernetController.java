package com.huawei.ibn.model.controller;

import com.huawei.ibn.model.l1.EthernetInterface;
import com.huawei.ibn.model.l2.PortVlan;
import com.huawei.ibn.model.physical.Device;
import com.huawei.ibn.model.query.DeviceEthernet;
import com.huawei.ibn.model.query.L2Nodes;
import org.springframework.data.neo4j.annotation.Depth;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EthernetController extends Neo4jRepository<EthernetInterface, Long> {

    @Query("MATCH (d:Device)-[:HAS*]->(e:EthernetInterface) " +
            "RETURN d as device,COLLECT(e) as ethernetInterfaces")
    List<DeviceEthernet> findDeviceEthernetInterfaces();

    @Query("MATCH (d:Device { name : {name} })-[:HAS*]->(e:EthernetInterface) " +
            "RETURN d as device, COLLECT(e) as ethernetInterfaces")
    DeviceEthernet findDeviceEthernetInterfacesByName(@Param("name") String name);

    @Query("MATCH (d:Device { name : {devName} } )-[:HAS*]->(e:EthernetInterface { name : {nicName} } ) " +
            "RETURN d as device, COLLECT(e) as ethernetInterfaces")
    DeviceEthernet findDeviceEthernetInterfaceByNames(@Param("devName") String deviceName, @Param("nicName") String interfaceName);

    @Query("MATCH (d:Device {name:{devName}})-[:HAS*]->(e:EthernetInterface {name:{nicName}}) " +
            "MATCH (e)-[:L2_PATH]->(p:PortVlan) " +
            "MATCH (p)-[:L2_PATH]->(v:Vlan) " +
            "RETURN e as ethernetInterface, p as portVlan, v as Vlan")
//    @Depth(3)
    L2Nodes findL2Connection(@Param("devName") String devName, @Param("nicName") String nicName);

}
