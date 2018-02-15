package com.huawei.ibn.model.query;

import com.huawei.ibn.model.controller.EthernetController;
import com.huawei.ibn.model.l1.EthernetInterface;
import com.huawei.ibn.model.physical.Device;
import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.List;
import java.util.Set;

@QueryResult
public class DeviceEthernet {
    Device device;
    List<EthernetInterface> EthernetInterfaces;

    public Device getDevice() {
        return device;
    }

    public List<EthernetInterface> getEthernetInterfaces() {
        return EthernetInterfaces;
    }
}
