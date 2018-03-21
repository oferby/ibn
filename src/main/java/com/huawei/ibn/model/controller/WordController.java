package com.huawei.ibn.model.controller;

import com.huawei.ibn.model.cli.Word;
import org.springframework.data.neo4j.annotation.Depth;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WordController extends GraphRepository<Word> {

    @Query(value = "MATCH (w:Word)-->(ws) RETURN ws")
    List<Word> getNextWords(String word);

    @Depth(1)
    Word findByText(String text);

}
