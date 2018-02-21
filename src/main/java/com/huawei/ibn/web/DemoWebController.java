package com.huawei.ibn.web;

import com.huawei.ibn.model.controller.DeviceController;
import com.huawei.ibn.model.controller.LineCardController;
import com.huawei.ibn.model.controller.PathController;
import com.huawei.ibn.model.physical.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoWebController {


    @Autowired
    private DeviceController deviceController;

    @Autowired
    private LineCardController lineCardController;

    @Autowired
    private DemoConfig demoConfig;

    @Autowired
    private PathController pathController;

    @RequestMapping(value = "/do", method = RequestMethod.GET)
    @ResponseStatus
    ResponseEntity<Device> doInternaly() {

        demoConfig.config();

        return new ResponseEntity<Device>(HttpStatus.OK);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseStatus
    ResponseEntity<String> test(){

        boolean path = pathController.l2PathExists(350, 318);

        return new ResponseEntity(path == true ? "Yes" : "No", HttpStatus.OK);

    }


}
