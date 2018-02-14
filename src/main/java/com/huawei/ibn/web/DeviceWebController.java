package com.huawei.ibn.web;

import com.huawei.ibn.model.controller.DeviceController;
import com.huawei.ibn.model.controller.LineCardController;
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

    @RequestMapping(value = "/device/{name}", method = RequestMethod.POST)
    @ResponseBody
    void addDevice(@PathVariable  String name) {
        Device device = new Device();
        device.setName(name);
        deviceController.save(device);
    }

    @RequestMapping(value = "/device/{name}/linecard/{index}", method = RequestMethod.POST)
    @ResponseStatus
    ResponseEntity addLineCardToDevice(@PathVariable String name, @PathVariable String index) {

        Device device = deviceController.findByName(name);
        if (device==null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        LineCard lineCard = new LineCard();
        lineCard.setName(index);

        lineCardController.save(lineCard);

        device.setLineCard(lineCard);

        deviceController.save(device);

        return new ResponseEntity(HttpStatus.OK);

    }

    @RequestMapping(value = "/device", method = RequestMethod.POST)
    @ResponseStatus
    ResponseEntity<Device> createDevice(@RequestBody Device device){
        Device save = deviceController.save(device);
        return new ResponseEntity<Device>(save, HttpStatus.OK);
    }

}
