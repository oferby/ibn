package com.huawei.ibn.web;

import com.huawei.ibn.model.controller.DeviceController;
import com.huawei.ibn.model.controller.LineCardController;
import com.huawei.ibn.model.l1.EthernetInterface;
import com.huawei.ibn.model.l2.MacAddress;
import com.huawei.ibn.model.l3.IpAddress;
import com.huawei.ibn.model.l3.V4IpAddress;
import com.huawei.ibn.model.physical.Device;
import com.huawei.ibn.model.physical.LineCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeviceWebController {

    @Autowired
    private DeviceController deviceController;

    @Autowired
    private LineCardController lineCardController;

    @RequestMapping(value = "/device", method = RequestMethod.GET)
    @ResponseBody
    List<Device> getAllDevices(){
        return (List<Device>) deviceController.findAll();
    }

    @RequestMapping(value = "/device/{name}", method = RequestMethod.GET)
    @ResponseBody
    Device addDevice(@PathVariable  String name) {
        return deviceController.findByName(name);
    }

    @RequestMapping(value = "/device", method = RequestMethod.POST)
    @ResponseStatus
    ResponseEntity<Device> createDevice(@RequestBody Device device){
        Device save = deviceController.save(device);
        return new ResponseEntity<Device>(save, HttpStatus.OK);
    }


    @RequestMapping(value = "/do", method = RequestMethod.GET)
    @ResponseStatus
    ResponseEntity<Device> doInternaly() {

        Device d = new Device();
        d.setName("dev1");

        EthernetInterface ethernetInterface = new EthernetInterface();
        ethernetInterface.setName("eth0");

        MacAddress macAddress = new MacAddress();
        macAddress.setStringAddress("00:11:22:33:44:55:66");
        ethernetInterface.setMacAddress(macAddress);

        V4IpAddress ipAddress = new V4IpAddress();
        ipAddress.setStringAddress("10.10.10.1");

        ethernetInterface.setIpAddress(ipAddress);

        LineCard lineCard = new LineCard();
        lineCard.setName("lc-0");

        lineCard.addInterface(ethernetInterface);
        d.addLineCard(lineCard);

        Device save = deviceController.save(d);

        return new ResponseEntity<Device>(save, HttpStatus.OK);
    }
}
