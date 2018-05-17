package com.huawei.ibn.nlu.rasa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RasaNluResponse {

    private RasaIntentRanking[] intent_rankings;
    private RasaIntent intent;
    private String text;

    public RasaIntentRanking[] getIntent_rankings() {
        return intent_rankings;
    }

    public void setIntent_rankings(RasaIntentRanking[] intent_rankings) {
        this.intent_rankings = intent_rankings;
    }

    public RasaIntent getIntent() {
        return intent;
    }

    public void setIntent(RasaIntent intent) {
        this.intent = intent;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
