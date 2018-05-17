package com.huawei.ibn.nlu;

import com.huawei.ibn.nlu.intent.Intent;

public interface NLUEngine {

    Intent getIntent(Intent intent);

}
