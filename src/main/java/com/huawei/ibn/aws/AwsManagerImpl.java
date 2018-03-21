package com.huawei.ibn.aws;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeVpcsResult;
import com.amazonaws.services.ec2.model.Vpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.EventRecodingLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AwsManagerImpl {

    private AWSCredentialsProvider awsCredentialsProvider;

    private static final Logger logger = LoggerFactory.getLogger(AwsManagerImpl.class);

    public AwsManagerImpl(@Value("${aws.access_key_id}") String accessKey, @Value("${aws.secret_key_id}") String secretKey) {

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        awsCredentialsProvider = new AWSStaticCredentialsProvider(awsCreds);
    }

    public List<String> syncWithAws() {

        AmazonEC2 amazonEC2 = AmazonEC2ClientBuilder.standard()
                .withCredentials(awsCredentialsProvider)
                .withRegion(Regions.SA_EAST_1)
                .build();

        DescribeVpcsResult vpcsResult = amazonEC2.describeVpcs();

        List<Vpc> vpcs = vpcsResult.getVpcs();

        assert vpcs != null;

        logger.debug(vpcs.toString());

        List<String> result = new ArrayList<>();
        for (Vpc vpc : vpcs) {
            result.add(vpc.toString());
        }

        return result;

    }


}
