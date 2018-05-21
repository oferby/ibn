package com.huawei.ibn.nlu.rasa;

import com.huawei.ibn.nlu.NLUEngine;
import com.huawei.ibn.nlu.intent.IntentMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class RasaNLUEngine implements NLUEngine {

    private RestTemplate restTemplate = new RestTemplate();

    private String url;

    public RasaNLUEngine(@Value("${rasa.nlu.url}") String url) {
        this.url = url;
    }

    @Override
    public IntentMessage getIntent(IntentMessage intent) {
        String req = "{\"q\":\"" + intent.getHint() + "\"}";
        RasaNluResponse rasaNluResponse = restTemplate.postForObject(url, req, RasaNluResponse.class);

        if (rasaNluResponse != null && rasaNluResponse.getIntent() != null) {
            intent.setIntent(rasaNluResponse.getIntent().getName());
            if (rasaNluResponse.getIntent().getConfidence() > 0.5) {
                intent.setHint("got you. you ment: " + rasaNluResponse.getIntent().getName());
            } else {
                intent.setHint("I'm, not sure you ment: " + rasaNluResponse.getIntent().getName());
            }
        } else {
            intent.setHint("Sorry, I don't understand");
        }

        return intent;

    }
}
