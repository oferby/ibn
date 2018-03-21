package com.huawei.ibn.web;

import com.huawei.ibn.aws.AwsManagerImpl;
import com.huawei.ibn.model.cli.Word;
import com.huawei.ibn.model.controller.DeviceController;
import com.huawei.ibn.model.controller.LineCardController;
import com.huawei.ibn.model.controller.PathController;
import com.huawei.ibn.model.controller.WordController;
import com.huawei.ibn.model.physical.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
public class DemoWebController {

    @Autowired
    private DeviceController deviceController;

    @Autowired
    private LineCardController lineCardController;

    @Autowired
    private DemoConfig demoConfig;

    @Autowired
    private PathController pathController;

    @Autowired
    private WordController wordController;

    @Autowired
    private AwsManagerImpl awsManager;


    @RequestMapping(value = "/do", method = RequestMethod.GET)
    @ResponseStatus
    ResponseEntity<Device> doInternaly() {

        demoConfig.config();

        return new ResponseEntity<Device>(HttpStatus.OK);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseStatus
    ResponseEntity<String> test() {

        boolean path = pathController.l2PathExists(350, 318);

        return new ResponseEntity<>(path == true ? "Yes" : "No", HttpStatus.OK);

    }

    @RequestMapping(value = "/word", method = RequestMethod.POST)
    @ResponseStatus
    ResponseEntity<List<String>> getNextWords(@RequestBody List<String> words) {

        if (words == null || words.size() == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (Objects.equals(words.get(0), "learn")) {

            List<Word> wordList = new ArrayList<>();

            Word currentWord = wordController.findByText("AStart");
            if (currentWord == null) {
                currentWord = new Word("AStart");
            }
            wordList.add(currentWord);

            for (String w : words) {
                Word word = new Word(w);
                wordList.add(word);
                currentWord.addWord(word);
                currentWord = word;
            }

            wordController.save(wordList);

            return new ResponseEntity<>(new ArrayList<>(Collections.singletonList("learned")), HttpStatus.OK);

        }

        String lastWord = words.get(words.size() - 1);

        if (lastWord.equals("\n")) {
            return new ResponseEntity<>(new ArrayList<>(Collections.singletonList("done")), HttpStatus.OK);
        }

        Word word = wordController.findByText(words.get(words.size() - 1));

        List<String> nextWords = new ArrayList<>();

        word.getNextWords().forEach(w -> nextWords.add(w.getText()));

        return new ResponseEntity<>(new ArrayList<>(nextWords), HttpStatus.OK);

    }

    @RequestMapping(value = "/sync", method = RequestMethod.GET)
    @ResponseStatus
    public ResponseEntity<List<String>> syncGraphWithAws() {

        List<String> vpcs = awsManager.syncWithAws();

        return new ResponseEntity<>(vpcs, HttpStatus.OK);

    }

}
