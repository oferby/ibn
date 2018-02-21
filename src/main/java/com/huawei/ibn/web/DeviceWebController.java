package com.huawei.ibn.web;

import com.huawei.ibn.model.controller.DeviceController;
import com.huawei.ibn.model.controller.LineCardController;
import com.huawei.ibn.model.physical.Device;
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
    Iterable<Device> getAllDevices() {
        Iterable<Device> devices = deviceController.findAll(2);
        return devices;
    }

    @RequestMapping(value = "/device/{name}", method = RequestMethod.GET)
    @ResponseBody
    Device addDevice(@PathVariable String name) {
        return deviceController.findByName(name);
    }

    @RequestMapping(value = "/device", method = RequestMethod.POST)
    @ResponseStatus
    ResponseEntity<Device> createDevice(@RequestBody Device device) {
        Device save = deviceController.save(device);
        return new ResponseEntity<Device>(save, HttpStatus.OK);
    }

}
