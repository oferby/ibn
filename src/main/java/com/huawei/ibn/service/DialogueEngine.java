package com.huawei.ibn.service;

import com.huawei.ibn.message.Dialogue;
import com.huawei.ibn.nlu.intent.IntentMessage;

public interface DialogueEngine {

    Dialogue getDialogueResponse(Dialogue dialogue);

}
