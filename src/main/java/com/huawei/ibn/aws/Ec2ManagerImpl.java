package com.huawei.ibn.aws;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeVpcsResult;
import com.amazonaws.services.ec2.model.Vpc;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class Ec2ManagerImpl {

    private AWSCredentialsProvider awsCredentialsProvider;

    private Map<Regions,AmazonEC2> amazonEC2Map = new HashMap<>();

    public Ec2ManagerImpl(@Value("${aws.access_key_id}") String accessKey, @Value("${aws.secret_key_id}") String secretKey) {

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        awsCredentialsProvider = new AWSStaticCredentialsProvider(awsCreds);

    }


    List<Vpc> getVpcList(Regions region) {

        AmazonEC2 amazonEC2 = amazonEC2Map.get(region);

        if (amazonEC2 == null){
            amazonEC2 = AmazonEC2ClientBuilder.standard()
                    .withCredentials(awsCredentialsProvider)
                    .withRegion(region)
                    .build();

            amazonEC2Map.put(region,amazonEC2);
        }

        DescribeVpcsResult vpcsResult = amazonEC2.describeVpcs();

        return vpcsResult.getVpcs();

    }

}
