package com.huawei.ibn.nlu;

import com.huawei.ibn.nlu.intent.IntentMessage;

public interface DialogEngine {

    Dialog getDialogResponse(Dialog dialog);

    IntentMessage getIntentRespose(IntentMessage intentMessage);


}
