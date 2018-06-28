package com.huawei.ibn.web;

import com.huawei.ibn.model.controller.DeviceController;
import com.huawei.ibn.model.controller.EthernetController;
import com.huawei.ibn.model.controller.GraphNodeController;
import com.huawei.ibn.model.l1.EthernetInterface;
import com.huawei.ibn.model.l1.Interface;
import com.huawei.ibn.model.l1.L1Controller;
import com.huawei.ibn.model.l2.*;
import com.huawei.ibn.model.l3.V4IpAddress;
import com.huawei.ibn.model.physical.Device;
import com.huawei.ibn.model.physical.LineCard;
import com.huawei.ibn.model.query.DeviceEthernet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.text.DecimalFormat;
import java.util.*;

//@Controller
class DemoConfig {

    @Autowired
    private DeviceController deviceController;

    @Autowired
    private EthernetController ethernetController;

    @Autowired
    private L1Controller l1Controller;

    @Autowired
    private L2Controller l2Controller;

    @Autowired
    private GraphNodeController graphNodeController;

    private short vlanId = 1000;
    private short devNum = 1;

    void config() {

        graphNodeController.deleteAll();

        this.doPhysical();
        this.doDeviceL2();
        this.doL1Connection();
//        l2Controller.setTrunkVlan("dev2", "eth3", (short) 10);
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

        deviceController.saveAll(deviceList);

    }

    private void doL1Connection() {

        l1Controller.connect("dev2", "eth0", "dev3", "eth0");
        l1Controller.connect("dev2", "eth1", "dev4", "eth0");
        l1Controller.connect("dev2", "eth2", "dev5", "eth0");

    }



    private void doDeviceL2() {

        List<DeviceEthernet> deviceEthernetList = ethernetController.findDeviceEthernetInterfaces();
        List<Device> deviceList = new ArrayList<>();

        for (DeviceEthernet dev : deviceEthernetList) {
            Device dev2Device = dev.getDevice();
            Switch aSwitch = dev2Device.addNewBridge((short) 0);

            for (EthernetInterface e : dev.getEthernetInterfaces()) {
                e.setPortVlan(new AccessVlan(aSwitch.getVlan()));
            }

            deviceList.add(dev2Device);

        }

        deviceController.saveAll(deviceList);

    }


    private Switch doBridge(short vlanId, Set<EthernetInterface> accessMembers, Set<EthernetInterface> trunkMembers) throws Exception {

        Switch aSwitch = new Switch();
        Vlan vlan = new Vlan(vlanId);
        aSwitch.setVlan(vlan);

        if (accessMembers != null) {

            for (EthernetInterface e : accessMembers) {
                AccessVlan accessVlan = new AccessVlan(vlan);
                e.setPortVlan(accessVlan);
            }
        }

        if (trunkMembers != null) {

            for (EthernetInterface e : trunkMembers) {
                PortVlan trunkVlan = e.getPortVlan();
                if (trunkVlan == null) {
                    trunkVlan = new TrunkVlan();
                    e.setPortVlan(trunkVlan);
                } else if (!(trunkVlan instanceof TrunkVlan)) {
                    throw new Exception("invalid configuration");
                }
                ((TrunkVlan) trunkVlan).addVlanMembership(vlan);
            }


        }

        return aSwitch;
    }


}
