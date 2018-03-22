package com.huawei.ibn.web;


import com.huawei.ibn.aws.AwsManagerImpl;
import com.huawei.ibn.model.controller.GraphNodeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
public class AwsWebController {

    @Autowired
    private AwsManagerImpl awsManager;

    @Autowired
    private GraphNodeController graphNodeController;

    @RequestMapping(value = "/sync", method = RequestMethod.GET)
    @ResponseStatus
    public ResponseEntity<List<String>> syncGraphWithAws() {



        List<String> vpcs = awsManager.syncWithAws();

        return new ResponseEntity<>(vpcs, HttpStatus.OK);

    }

}
