package com.huawei.ibn.service;

import com.huawei.ibn.message.Dialogue;
import com.huawei.ibn.message.DialogueSentiment;

public interface SentimentAnalysisController {

    DialogueSentiment getDialogueSentiment(Dialogue dialogue);

}
