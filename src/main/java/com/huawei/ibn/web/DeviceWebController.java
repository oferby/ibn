package com.huawei.ibn.web;

import com.huawei.ibn.model.controller.DeviceController;
import com.huawei.ibn.model.physical.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeviceWebController {

    @Autowired
    private DeviceController deviceController;

    @RequestMapping(value = "/device", method = RequestMethod.GET)
    @ResponseBody
    List<Device> getAllDevices(){
        return (List<Device>) deviceController.findAll();
    }

    @RequestMapping(value = "/device/{name}", method = RequestMethod.POST)
    @ResponseBody
    String addDevice(@PathVariable  String name) {
        Device device = new Device();
        device.setName(name);
        deviceController.save(device);

        return "done";
    }


}
