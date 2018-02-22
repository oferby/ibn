package com.huawei.ibn.grpc;

import com.test.tutorial.GreetingServiceGrpc;
import com.test.tutorial.HelloRequest;
import com.test.tutorial.HelloResponse;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase{
    @Override
    public void greeting(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {

        HelloResponse helloResponse = HelloResponse.newBuilder().setResponse("hello " + request.getRequest()).build();

        responseObserver.onNext(helloResponse);
        responseObserver.onCompleted();

    }
}
