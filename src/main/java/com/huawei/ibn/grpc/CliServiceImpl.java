package com.huawei.ibn.grpc;

import com.huawei.grpc.CliServiceGrpc;
import com.huawei.grpc.NextWordReply;
import com.huawei.grpc.NextWordRequest;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@GRpcService
public class CliServiceImpl extends CliServiceGrpc.CliServiceImplBase{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void getNextWord(NextWordRequest request, StreamObserver<NextWordReply> responseObserver) {

        System.out.println(request.getWordList());

        responseObserver.onNext(NextWordReply.newBuilder().setWord(0,"start").build());
        responseObserver.onCompleted();
    }


}
