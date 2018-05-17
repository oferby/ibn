package com.huawei.ibn.nlu;

import java.util.LinkedList;
import java.util.List;

public class Dialog {

    private String text;
    private List<String > dialogText = new LinkedList<>();

    public String getLastText() {
        return text;
    }

    public void setLastText(String lastText) {
        if (this.text != null)
            dialogText.add(this.text);
        this.text = lastText;
    }

    public List<String> getDialogText() {
        return dialogText;
    }
}
