package com.huawei.ibn.web;

import com.huawei.ibn.nlu.intent.Intent;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/getIntent")
    public void getIntentRequest(Intent intent) {

    }

}
