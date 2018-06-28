package com.huawei.ibn.service;

import com.huawei.ibn.message.Dialogue;
import com.huawei.ibn.message.DialogueSentiment;
import org.springframework.stereotype.Controller;

@Controller
public class SimpleSentimentAnalysisController implements SentimentAnalysisController{

    @Override
    public DialogueSentiment getDialogueSentiment(Dialogue dialogue) {
        return DialogueSentiment.NEUTRAL;
    }

}
