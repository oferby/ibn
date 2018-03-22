package com.huawei.ibn.aws;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class Ec2ManagerImpl {

    private AWSCredentialsProvider awsCredentialsProvider;

    private Map<Regions, AmazonEC2> amazonEC2Map = new HashMap<>();

    public Ec2ManagerImpl(@Value("${aws.access_key_id}") String accessKey, @Value("${aws.secret_key_id}") String secretKey) {

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        awsCredentialsProvider = new AWSStaticCredentialsProvider(awsCreds);

    }

    private AmazonEC2 getClient(Regions region) {

        AmazonEC2 amazonEC2 = amazonEC2Map.get(region);

        if (amazonEC2 == null) {
            amazonEC2 = AmazonEC2ClientBuilder.standard()
                    .withCredentials(awsCredentialsProvider)
                    .withRegion(region)
                    .build();

            amazonEC2Map.put(region, amazonEC2);
        }

        return amazonEC2;

    }


    List<Vpc> getRegionVpcList(Regions region) {

        AmazonEC2 amazonEC2 = getClient(region);

        DescribeVpcsResult vpcsResult = amazonEC2.describeVpcs();

        return vpcsResult.getVpcs();

    }

    List<Subnet> getRegionSubnetList(Regions region) {

        AmazonEC2 amazonEC2 = getClient(region);

        DescribeSubnetsResult result = amazonEC2.describeSubnets();

        return result.getSubnets();

    }

    List<NetworkAcl> getRegionAcl(Regions region) {

        AmazonEC2 amazonEC2 = getClient(region);

        DescribeNetworkAclsResult result = amazonEC2.describeNetworkAcls();

        return result.getNetworkAcls();

    }

    List<RouteTable> getRegionRouteTables(Regions region) {

        AmazonEC2 amazonEC2 = getClient(region);

        DescribeRouteTablesResult result = amazonEC2.describeRouteTables();

        return result.getRouteTables();

    }


    List<InternetGateway> getRegionInternetGateways(Regions region) {

        AmazonEC2 amazonEC2 = getClient(region);

        DescribeInternetGatewaysResult result = amazonEC2.describeInternetGateways();

        return result.getInternetGateways();

    }

    List<SecurityGroup> getRegionSecurityGroup(Regions region) {

        AmazonEC2 amazonEC2 = getClient(region);

        DescribeSecurityGroupsResult result = amazonEC2.describeSecurityGroups();

        return result.getSecurityGroups();

    }

    List<Instance> getRegionInstances(Regions region) {

        AmazonEC2 client = getClient(region);

        List<Instance> instanceList = new ArrayList<>();
        List<Reservation> reservations = client.describeInstances().getReservations();
        for (Reservation reservation : reservations) {
            instanceList.addAll(reservation.getInstances());
        }

        return instanceList;

    }

}
