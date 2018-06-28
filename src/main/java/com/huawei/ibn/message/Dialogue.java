package com.huawei.ibn.message;

import com.huawei.ibn.nlu.intent.IntentMessage;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Dialogue {

    private UUID sessionId;
    private DialogueState dialogueState = DialogueState.INIT;
    private String text;
    private List<String > history = new LinkedList<>();
    private IntentMessage intent;
    private int negativeCount;

    public UUID getSessionId() {
        return sessionId;
    }

    public void setSessionId(UUID sessionId) {
        this.sessionId = sessionId;
    }

    public void setDialogueState(DialogueState dialogueState) {
        this.dialogueState = dialogueState;
    }

    public DialogueState getDialogueState() {
        return dialogueState;
    }

    public String getText() {
        return text;
    }

    public void seText(String text) {
        if (this.text != null)
            history.add(this.text);
        this.text = text;
    }

    public List<String> getHistory() {
        return history;
    }

    public IntentMessage getIntent() {
        return intent;
    }

    public void setIntent(IntentMessage intent) {
        this.intent = intent;
    }

    public int getNegativeCount() {
        return negativeCount;
    }

    public void increaseNegativeCount() {
        this.negativeCount++;
    }

    public void resetNegativeCount() {
        this.negativeCount = 0;
    }
}
