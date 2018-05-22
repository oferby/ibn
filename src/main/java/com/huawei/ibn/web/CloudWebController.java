package com.huawei.ibn.web;


import com.huawei.ibn.cloud.CloudController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class CloudWebController {

    @Autowired
    private CloudController cloudController;

    @RequestMapping(value = "/sync", method = RequestMethod.GET)
    @ResponseStatus
    public ResponseEntity<String> syncGraphWithAws() {

        cloudController.sync();

        return new ResponseEntity<>("Done.", HttpStatus.OK);

    }

}
