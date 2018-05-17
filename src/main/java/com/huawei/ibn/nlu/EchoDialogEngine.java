package com.huawei.ibn.nlu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class EchoDialogEngine implements DialogEngine {

    private static final Logger logger = LoggerFactory.getLogger(EchoDialogEngine.class);

    @Override
    public Dialog getDialogResponse(Dialog dialog) {
        logger.debug("got dialog message: " + dialog.getLastText());
        dialog.setLastText("ECHO: " + dialog.getLastText());
        return dialog;
    }
}
