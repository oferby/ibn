package com.huawei.ibn.model.query;

import com.huawei.ibn.model.l1.EthernetInterface;
import com.huawei.ibn.model.physical.Device;
import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.List;

@QueryResult
public class DeviceEthernet {
    Device device;
    List<EthernetInterface> ethernetInterfaces;

    public Device getDevice() {
        return device;
    }

    public List<EthernetInterface> getEthernetInterfaces() {
        return ethernetInterfaces;
    }
}
