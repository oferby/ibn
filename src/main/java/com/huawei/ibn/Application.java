package com.huawei.ibn;

import com.huawei.ibn.grpc.GreetingServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import java.io.IOException;

@SpringBootApplication
@EnableNeo4jRepositories
public class Application {

    public static void main(String[] args) throws IOException {

//        Server server = ServerBuilder.forPort(8081)
//                .addService(new GreetingServiceImpl())
//                .build();
//        server.start();

        SpringApplication.run(Application.class, args);

    }

}
