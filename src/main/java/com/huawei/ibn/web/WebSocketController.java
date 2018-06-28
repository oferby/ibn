package com.huawei.ibn.web;

import com.huawei.ibn.message.Dialogue;
import com.huawei.ibn.service.DialogueEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/*
* This class is a starting point for user input that comes from web socket.
* Different channels can exist to send user inputs to the dialogue
* engine.
*
* */
@Controller
public class WebSocketController {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    @Autowired
    private DialogueEngine dialogueEngine;

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/parseDialogue")
    public void getIntentRequest(Dialogue dialogue) {

        logger.debug("got new user input: " + dialogue.getText());

        Dialogue dialogueResponse = dialogueEngine.getDialogueResponse(dialogue);
        this.sendResponse(dialogueResponse);

    }

    private void sendResponse(Dialogue dialogueResponse) {
        template.convertAndSend("/topic/dialogue", dialogueResponse);
    }

}
