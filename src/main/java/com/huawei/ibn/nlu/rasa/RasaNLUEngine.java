package com.huawei.ibn.nlu.rasa;

import com.huawei.ibn.nlu.NLUEngine;
import com.huawei.ibn.nlu.intent.IntentMessage;
import com.huawei.ibn.nlu.model.UserInputEntity;
import com.huawei.ibn.nlu.model.UserInputRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RasaNLUEngine implements NLUEngine {

    @Autowired
    private UserInputRepository userInputRepository;

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${rasa.nlu.url}")
    private String url;

    private int beta = 3;

    @Override
    public IntentMessage getIntent(String inputText) {
        String req = "{\"q\":\"" + inputText + "\"}";
        RasaNluResponse rasaNluResponse = restTemplate.postForObject(url, req, RasaNluResponse.class);

        this.saveIntent(inputText, rasaNluResponse);

        if (rasaNluResponse != null && rasaNluResponse.getIntent() != null) {

            IntentMessage intent = new IntentMessage();
            intent.setIntent(rasaNluResponse.getIntent().getName());
            intent.setConfidence(this.calcEntropy(rasaNluResponse));

            for (RasaEntity rasaEntity : rasaNluResponse.getEntities()) {
                intent.addSlot(rasaEntity.getEntity(), rasaEntity.getValue());
            }

            return intent;

        }

        return null;

    }


    private double calcEntropy(RasaNluResponse response) {

        ArrayList<Double> allButMax = new ArrayList<>();
        double sum = 0;
        double max = 0;
        double origMax = response.getIntent().getConfidence();
        for (RasaIntentRanking rasaIntentRanking : response.getIntent_ranking()) {
            if (rasaIntentRanking.getName().equals(response.getIntent().getName()))
                continue;
            double confidence = rasaIntentRanking.getConfidence();
            if (confidence > max)
                max = confidence;
            allButMax.add(confidence);
            sum += confidence;
        }

        for (int i = 0; i < allButMax.size(); i++) {
            allButMax.set(i, allButMax.get(i) / sum);
        }

//        calculate entropy
        double h = -allButMax.stream().map(v -> (v * Math.log(v))).reduce((s, v) -> s + v).get();
        double score = h / Math.log(allButMax.size());
        double info = Math.pow(score, beta);
        double runnerUpEffect = 1 - Math.pow((max / origMax),beta);
        return info * runnerUpEffect;

    }


    private void saveIntent(String inputText, RasaNluResponse nluResponse) {
        UserInputEntity inputEntity = new UserInputEntity();
        inputEntity.setText(inputText);

        if (nluResponse != null && nluResponse.getIntent() != null) {
            inputEntity.setIntent(nluResponse.getIntent().getName());
            inputEntity.setConfidence(nluResponse.getIntent().getConfidence());
        }

        userInputRepository.save(inputEntity);
    }

}
