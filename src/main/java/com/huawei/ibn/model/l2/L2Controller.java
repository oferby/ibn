package com.huawei.ibn.model.l2;

import com.huawei.ibn.model.controller.DeviceController;
import com.huawei.ibn.model.controller.EthernetController;
import com.huawei.ibn.model.controller.GraphNodeController;
import com.huawei.ibn.model.l1.EthernetInterface;
import com.huawei.ibn.model.physical.Device;
import com.huawei.ibn.model.query.DeviceEthernet;
import com.huawei.ibn.model.query.L2Nodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class L2Controller {

    @Autowired
    private DeviceController deviceController;

    @Autowired
    private EthernetController ethernetController;

    @Autowired
    private GraphNodeController graphNodeController;

    public void setTrunkVlan(String deviceName, String portName, short vlanId) {

        L2Nodes nodes = ethernetController.findL2Connection(deviceName, portName);

        EthernetInterface ethernetInterface = nodes.getEthernetInterface();
        ethernetInterface.setName("eth3");
        graphNodeController.save(ethernetInterface);



//        if (!(nodes.getPortVlan() instanceof TrunkVlan)) {
//            TrunkVlan trunkVlan = new TrunkVlan();
//            nodes.getEthernetInterface().setPortVlan(trunkVlan);
//        }

    }

}
