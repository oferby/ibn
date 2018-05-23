package com.huawei.ibn.web;

import com.huawei.ibn.nlu.model.UserInputEntity;
import com.huawei.ibn.nlu.model.UserInputRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IntentWebController {

    @Autowired
    private UserInputRepository userInputRepository;

    @RequestMapping(value = "/intent")
    public ResponseEntity<Iterable<UserInputEntity>> getAllIntents() {

        Iterable<UserInputEntity> entities = userInputRepository.findAll();
        return new ResponseEntity<Iterable<UserInputEntity>>(entities, HttpStatus.OK);

    }

}
