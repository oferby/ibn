package com.huawei.ibn.grpc;

import com.huawei.ibn.model.controller.DeviceController;
import com.huawei.ibn.model.physical.Device;
import com.test.tutorial.GreetingServiceGrpc;
import com.test.tutorial.HelloRequest;
import com.test.tutorial.HelloResponse;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {


    @Autowired
    private DeviceController deviceController;


    @Override
    public void greeting(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {

        Device device = deviceController.findAll().iterator().next();
        HelloResponse helloResponse = HelloResponse.newBuilder()
                .setResponse("found device: " + device.getName())
                .build();

        responseObserver.onNext(helloResponse);
        responseObserver.onCompleted();

    }
}
