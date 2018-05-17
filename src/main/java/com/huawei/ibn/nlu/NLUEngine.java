package com.huawei.ibn.nlu;


import com.huawei.ibn.nlu.intent.IntentMessage;

public interface NLUEngine {

    IntentMessage getIntent(IntentMessage intent);

}
