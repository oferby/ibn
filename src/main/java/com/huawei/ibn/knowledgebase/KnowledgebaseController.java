package com.huawei.ibn.knowledgebase;

import com.huawei.ibn.message.KnowledgebaseRequest;
import com.huawei.ibn.message.KnowledgebaseResponse;

public interface KnowledgebaseController {

    KnowledgebaseResponse getKnowledgebaseResponse(KnowledgebaseRequest request);

}
