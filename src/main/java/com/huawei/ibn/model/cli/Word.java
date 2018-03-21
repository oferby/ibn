package com.huawei.ibn.model.cli;

import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
public class Word {

    @Index(unique=true, primary=true)
    private String text;

    @Relationship(type = "NEXT")
    private List<Word> nextWords;

    public Word() {
    }

    public Word(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public List<Word> getNextWords() {
        return nextWords;
    }

    public void addWord(Word word){
        if (nextWords==null){
            nextWords = new ArrayList<>();
        }
        nextWords.add(word);
    }
    public void setNextWords(List<Word> nextWords) {
        this.nextWords = nextWords;
    }
}