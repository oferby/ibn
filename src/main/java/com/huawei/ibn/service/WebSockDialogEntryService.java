package com.huawei.ibn.service;

import com.huawei.ibn.nlu.DialogEngine;
import com.huawei.ibn.nlu.intent.IntentMessage;
import com.huawei.ibn.nlu.intent.IntentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSockDialogEntryService implements DialogEntryService{

    @Autowired
    private DialogEngine dialogEngine;

    @Autowired
    private SimpMessagingTemplate template;

    public void sendIntent(String target, IntentMessage intentMessage) {

        template.convertAndSend(target, intentMessage);

    }

    public void sendIntent(IntentMessage intentMessage) {

        template.convertAndSend("/topic/intent", intentMessage);
    }

    public void sendClearLocalIntent() {
        IntentMessage intentMessage = new IntentMessage();
        intentMessage.setStatus(IntentStatus.LOCAL);
        intentMessage.setIntent("clear");
        template.convertAndSend("/topic/intent", intentMessage);
    }

    public void sendUnknownInput(){

        IntentMessage intentMessage = new IntentMessage();
        intentMessage.setStatus(IntentStatus.INFO);
        intentMessage.addParam("type", "unknownRequest");
        intentMessage.addParam("question", "Sorry, did not understand your request. Please rephrase.");
        template.convertAndSend("/topic/intent",intentMessage);
    }


    @Override
    public void gotIntentMessgae(IntentMessage intentMessage) {

        IntentMessage intentRespose = dialogEngine.getIntentRespose(intentMessage);
        this.sendIntent(intentRespose);

    }
}
