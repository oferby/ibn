package com.huawei.ibn.nlu;

import com.huawei.ibn.nlu.intent.IntentMessage;
import com.huawei.ibn.nlu.model.UserInputEntity;
import com.huawei.ibn.nlu.model.UserInputRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class SimpleDialogEngine implements DialogEngine {

    private static final Logger logger = LoggerFactory.getLogger(SimpleDialogEngine.class);

    @Autowired
    private NLUEngine nluEngine;

    @Override
    public Dialog getDialogResponse(Dialog dialog) {
        logger.debug("got dialog message: " + dialog.getLastText());
        dialog.setLastText("ECHO: " + dialog.getLastText());
        return dialog;
    }

    @Override
    public IntentMessage getIntentRespose(IntentMessage intentMessage) {

        if (intentMessage.getSessionId() == null) {
            UUID sessionId = UUID.randomUUID();
            intentMessage.setSessionId(sessionId.toString());
        }

        IntentMessage response = nluEngine.getIntent(intentMessage);

        return response;
    }
}
