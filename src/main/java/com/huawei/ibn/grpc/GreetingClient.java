package com.huawei.ibn.grpc;

import com.test.tutorial.GreetingServiceGrpc;
import com.test.tutorial.HelloRequest;
import com.test.tutorial.HelloResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetingClient {

    public static void main(String[] args) {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext(true)
                .build();

        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);

        HelloResponse response = stub.greeting(HelloRequest.newBuilder().setRequest("Ofer").build());

        System.out.println(response);

    }
}
