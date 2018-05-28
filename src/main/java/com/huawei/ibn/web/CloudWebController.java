package com.huawei.ibn.web;


import com.huawei.ibn.cloud.CloudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class CloudWebController {

    @Autowired
    @Qualifier("awsCloudController")
    private CloudController awsCloudController;

    @Autowired
    @Qualifier("hecCloudController")
    private CloudController hecCloudController;

    @RequestMapping(value = "/sync/aws", method = RequestMethod.GET)
    @ResponseStatus
    public ResponseEntity<String> syncGraphWithAws() {

        awsCloudController.sync();

        return new ResponseEntity<>("Done.", HttpStatus.OK);

    }

    @RequestMapping(value = "/sync/hec", method = RequestMethod.GET)
    @ResponseStatus
    public ResponseEntity<String> syncGraphWithHec() {

        hecCloudController.sync();

        return new ResponseEntity<>("Done.", HttpStatus.OK);

    }

}
