package com.huawei.ibn.web;

import com.huawei.ibn.model.controller.DeviceController;
import com.huawei.ibn.model.controller.EthernetController;
import com.huawei.ibn.model.l1.EthernetInterface;
import com.huawei.ibn.model.l1.Interface;
import com.huawei.ibn.model.l2.MacAddress;
import com.huawei.ibn.model.l3.V4IpAddress;
import com.huawei.ibn.model.physical.Device;
import com.huawei.ibn.model.physical.LineCard;
import com.huawei.ibn.model.query.DeviceEthernet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.text.DecimalFormat;
import java.util.*;

@Controller
class DemoConfig {

    @Autowired
    private DeviceController deviceController;

    @Autowired
    private EthernetController ethernetController;

    void config() {
        this.doPhysical();
        this.doL1Connection();
    }

    private void doPhysical() {

        List<Device> deviceList = new ArrayList<>();
        List<EthernetInterface> interfaces = new ArrayList<>();

        DecimalFormat formatter = new DecimalFormat("00");

        Set<Interface> ethernetInterfaceSet;

        for (int i = 1; i < 11; i++) {

            Device d = new Device();
            d.setName("dev" + i);

            ethernetInterfaceSet = new HashSet<>();
            for (int j = 0; j < 4; j++) {

                EthernetInterface ethernetInterface = new EthernetInterface();
                ethernetInterface.setName("eth" + j);

                MacAddress macAddress = new MacAddress();
                macAddress.setStringAddress("00:11:22:33:44:55:" + formatter.format(i * 10 + j));
                ethernetInterface.setMacAddress(macAddress);

                V4IpAddress ipAddress = new V4IpAddress();
                ipAddress.setStringAddress("10.10.10." + (i * 10 + j));

                ethernetInterface.setIpAddress(ipAddress);

                ethernetInterfaceSet.add(ethernetInterface);

            }

            LineCard lineCard = new LineCard();
            lineCard.setName("lc-0");

            lineCard.setInterfaces(ethernetInterfaceSet);
            d.addLineCard(lineCard);

            deviceList.add(d);

        }

        deviceController.save(deviceList);

    }

    private void doL1Connection() {

        List<EthernetInterface> connected = new ArrayList<>();
        EthernetInterface e1;
        EthernetInterface e2;

        List<DeviceEthernet> deviceEthernetList = ethernetController.findDeviceEthernetInterfaces();

        DeviceEthernet root = deviceEthernetList.get(0);
        List<EthernetInterface> rootInterfaces = root.getEthernetInterfaces();

        DeviceEthernet dev;

        for (int i = 0; i < 4; i++) {

            dev = deviceEthernetList.get(i+1);
            e1 = rootInterfaces.get(i);
            e2 = dev.getEthernetInterfaces().get(0);
            e1.setConnected(e2);
            e2.setConnected(e1);
            connected.add(e1);
            connected.add(e2);

        }

        Iterable<EthernetInterface> save = ethernetController.save(connected);

        assert save!=null;

    }


}
