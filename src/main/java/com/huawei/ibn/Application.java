package com.huawei.ibn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import java.io.IOException;

@SpringBootApplication
@EnableNeo4jRepositories
public class Application {

    public static void main(String[] args) throws IOException {

        SpringApplication.run(Application.class, args);

    }

}
