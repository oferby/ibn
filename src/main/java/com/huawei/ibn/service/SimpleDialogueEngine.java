package com.huawei.ibn.service;

import com.huawei.ibn.knowledgebase.KnowledgebaseController;
import com.huawei.ibn.message.Dialogue;
import com.huawei.ibn.message.DialogueSentiment;
import com.huawei.ibn.nlu.NLUEngine;
import com.huawei.ibn.nlu.intent.IntentMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class SimpleDialogueEngine implements DialogueEngine {

    private static final Logger logger = LoggerFactory.getLogger(SimpleDialogueEngine.class);

    @Autowired
    private NLUEngine nluEngine;

    @Autowired
    private SentimentAnalysisController sentimentAnalysisController;

    @Autowired
    private KnowledgebaseController knowledgebaseController;

    @Override
    public Dialogue getDialogueResponse(Dialogue dialogue) {

        if (dialogue.getSessionId() == null) {
            dialogue.setSessionId(UUID.randomUUID());
        } else {
//            we check for negative sentiment only if it is not the first message
            DialogueSentiment dialogueSentiment = sentimentAnalysisController.getDialogueSentiment(dialogue);
            if (dialogueSentiment == DialogueSentiment.NEGATIVE) {
                return this.handleRouteToHuman(dialogue);
            }

        }

        IntentMessage intent = nluEngine.getIntent(dialogue.getText());

        if (intent == null || intent.getConfidence() < 0.3) {
            return handleUnknown(dialogue);
        }



        return null;

    }

    private Dialogue handleUnknown(Dialogue dialogue) {

        if (dialogue.getNegativeCount() > 2)
            return this.handleRouteToHuman(dialogue);

        dialogue.increaseNegativeCount();
        dialogue.seText("I'm sorry but I don't exactly understand... can you rephrase?");
        return dialogue;
    }

    private Dialogue handleRouteToHuman(Dialogue dialogue) {
        dialogue.seText("Sorry for not be able to help you. I will transfer you to my manager.");
        return dialogue;
    }


}
