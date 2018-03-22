package com.huawei.ibn.aws;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeVpcsResult;
import com.amazonaws.services.ec2.model.Vpc;
import com.huawei.ibn.model.controller.GraphNodeController;
import com.huawei.ibn.model.controller.VpcController;
import com.huawei.ibn.model.l3.CidrBlock;
import com.huawei.ibn.model.l3.VirtualPrivateNetwork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.EventRecodingLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AwsManagerImpl {

    private static final Logger logger = LoggerFactory.getLogger(AwsManagerImpl.class);

    @Autowired
    private Ec2ManagerImpl ec2Manager;

    @Autowired
    private VpcController vpcController;

    @Autowired
    private GraphNodeController graphNodeController;

    public List<String> syncWithAws() {

        graphNodeController.deleteAll();

        List<Vpc> vpcList = new ArrayList<>();

        for (Regions region : Regions.values()) {
            try {
                vpcList.addAll(ec2Manager.getVpcList(region));
            } catch (Exception ignored) {
            }

        }

        List<String> result = new ArrayList<>();
        List<VirtualPrivateNetwork>vpcNodeList = new ArrayList<>();
        VirtualPrivateNetwork vpcNode;
        for (Vpc vpc : vpcList) {
            result.add(vpc.toString());

            vpcNode = new VirtualPrivateNetwork();
            vpcNode.setName(vpc.getVpcId());
            CidrBlock cidrBlock = new CidrBlock();
            cidrBlock.setCidr(vpc.getCidrBlock());
            vpcNode.addCidr(cidrBlock);

            vpcNodeList.add(vpcNode);
        }

        vpcController.save(vpcNodeList);

        return result;

    }


}
