package com.huawei.ibn.nlu.rasa;

import com.huawei.ibn.nlu.NLUEngine;
import com.huawei.ibn.nlu.intent.IntentMessage;
import com.huawei.ibn.nlu.model.UserInputEntity;
import com.huawei.ibn.nlu.model.UserInputRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class RasaNLUEngine implements NLUEngine {

    @Autowired
    private UserInputRepository userInputRepository;

    private RestTemplate restTemplate = new RestTemplate();

    private String url;

    public RasaNLUEngine(@Value("${rasa.nlu.url}") String url) {
        this.url = url;
    }

    @Override
    public IntentMessage getIntent(IntentMessage intent) {
        String req = "{\"q\":\"" + intent.getHint() + "\"}";
        RasaNluResponse rasaNluResponse = restTemplate.postForObject(url, req, RasaNluResponse.class);

        this.saveIntent(intent, rasaNluResponse);

        if (rasaNluResponse != null && rasaNluResponse.getIntent() != null) {
            intent.setIntent(rasaNluResponse.getIntent().getName());
            if (rasaNluResponse.getIntent().getConfidence() > 0.5) {
                intent.setHint("got you. you meant: " + rasaNluResponse.getIntent().getName());
            } else {
                intent.setHint("I'm, not sure you meant: " + rasaNluResponse.getIntent().getName());
            }
        } else {
            intent.setHint("Sorry, I don't understand");
        }

        return intent;

    }

    private void saveIntent(IntentMessage intentMessage, RasaNluResponse nluResponse) {
        UserInputEntity inputEntity = new UserInputEntity();
        inputEntity.setText(intentMessage.getHint());

        if (nluResponse != null && nluResponse.getIntent() != null) {
            inputEntity.setIntent(nluResponse.getIntent().getName());
            inputEntity.setConfidence(nluResponse.getIntent().getConfidence());
        }

        userInputRepository.save(inputEntity);
    }

}
