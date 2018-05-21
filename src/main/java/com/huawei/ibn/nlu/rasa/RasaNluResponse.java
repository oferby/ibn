package com.huawei.ibn.nlu.rasa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RasaNluResponse {

    private RasaIntentRanking[] intent_ranking;
    private RasaIntent intent;
    private RasaEntity[] entities;
    private String text;

    public RasaIntentRanking[] getIntent_ranking() {
        return intent_ranking;
    }

    public void setIntent_ranking(RasaIntentRanking[] intent_ranking) {
        this.intent_ranking = intent_ranking;
    }

    public RasaIntent getIntent() {
        return intent;
    }

    public void setIntent(RasaIntent intent) {
        this.intent = intent;
    }

    public RasaEntity[] getEntities() {
        return entities;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
