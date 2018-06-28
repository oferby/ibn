package com.huawei.ibn.nlu.intent;

import java.util.HashMap;
import java.util.Map;
/*
* Class to use to handle conversation between the dialogue server and the user
*
* */
public class IntentMessage {

    private String sessionId;
    private String hint;
    private IntentStatus status;
    private String intent;
    private double confidence;
    private Map<String,String> slots;

    public IntentMessage() {
    }

    public IntentMessage(String hint, IntentStatus status, String intent) {
        this.hint = hint;
        this.status = status;
        this.intent = intent;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public IntentStatus getStatus() {
        return status;
    }

    public void setStatus(IntentStatus status) {
        this.status = status;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public Map<String, String> getSlots() {
        return slots;
    }

    public String getSlotValue(String key){
        if (slots ==null){
            return null;
        }

        return slots.get(key);
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public void setSlots(Map<String, String> slots) {
        this.slots = slots;
    }

    public void addSlot(String key, String value){
        if (slots ==null){
            slots = new HashMap<>();
        }
        slots.put(key, value);
    }
}
