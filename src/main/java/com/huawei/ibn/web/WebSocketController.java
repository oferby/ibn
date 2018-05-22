package com.huawei.ibn.web;

import com.huawei.ibn.nlu.intent.IntentMessage;
import com.huawei.ibn.service.DialogEntryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class WebSocketController {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    @Autowired
    private DialogEntryService dialogEntryService;

    @MessageMapping("/getIntent")
    public void getIntentRequest(IntentMessage intent) {
        logger.debug("got new intent: " + intent);


        dialogEntryService.gotIntentMessgae(intent);

    }

}
