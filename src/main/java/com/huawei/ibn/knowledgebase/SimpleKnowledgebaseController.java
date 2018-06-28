package com.huawei.ibn.knowledgebase;

import com.huawei.ibn.message.KnowledgebaseRequest;
import com.huawei.ibn.message.KnowledgebaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class SimpleKnowledgebaseController implements KnowledgebaseController{

    private static final Logger logger = LoggerFactory.getLogger(SimpleKnowledgebaseController.class);

    @Override
    public KnowledgebaseResponse getKnowledgebaseResponse(KnowledgebaseRequest request) {


        return null;


    }



}
