package com.huawei.ibn.service;

import com.huawei.ibn.message.Dialogue;
import com.huawei.ibn.nlu.NLUEngine;
import com.huawei.ibn.nlu.intent.IntentMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

//@Controller
public class SimpleEchoDialogueEngine implements DialogueEngine {

    private static final Logger logger = LoggerFactory.getLogger(SimpleEchoDialogueEngine.class);

    @Autowired
    private NLUEngine nluEngine;

    @Override
    public Dialogue getDialogueResponse(Dialogue dialogue) {
        logger.debug("got dialogue message: " + dialogue.getText());
        dialogue.seText("ECHO: " + dialogue.getText());
        return dialogue;
    }

}
