package com.huawei.ibn.model.l1;

import com.huawei.ibn.model.controller.EthernetController;
import com.huawei.ibn.model.query.DeviceEthernet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.HashSet;

@Controller
public class L1Controller {

    @Autowired
    private EthernetController ethernetController;

    public void connect(String deviceName1, String interfaceName1, String deviceName2, String interfaceName2) {

        DeviceEthernet sideA = ethernetController.findDeviceEthernetInterfaceByNames(deviceName1, interfaceName1);
        DeviceEthernet sideB = ethernetController.findDeviceEthernetInterfaceByNames(deviceName2, interfaceName2);

        EthernetInterface nic1 = sideA.getEthernetInterfaces().get(0);
        EthernetInterface nic2 = sideB.getEthernetInterfaces().get(0);

        nic1.setConnected(nic2);
        nic2.setConnected(nic1);

        ethernetController.save(new HashSet<EthernetInterface>(Arrays.asList(nic1, nic2)));

    }


}
