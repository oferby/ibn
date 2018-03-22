package com.huawei.ibn.aws;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.model.*;
import com.amazonaws.services.ec2.model.Subnet;
import com.fasterxml.jackson.databind.node.IntNode;
import com.huawei.ibn.model.acl.AccessControl;
import com.huawei.ibn.model.acl.AccessControlRule;
import com.huawei.ibn.model.acl.AccessControlType;
import com.huawei.ibn.model.acl.SecurityGroupNode;
import com.huawei.ibn.model.controller.*;
import com.huawei.ibn.model.l1.EthernetInterface;
import com.huawei.ibn.model.l3.*;
import com.huawei.ibn.model.l3.CidrBlock;
import com.huawei.ibn.model.service.InternetGatwayNode;
import com.huawei.ibn.model.service.InternetNode;
import com.huawei.ibn.model.virtual.ComputeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AwsManagerImpl {

    private static final Logger logger = LoggerFactory.getLogger(AwsManagerImpl.class);

    @Autowired
    private Ec2ManagerImpl ec2Manager;

    @Autowired
    private VpcController vpcController;

    @Autowired
    private GraphNodeController graphNodeController;

    @Autowired
    private AccessControlController accessControlController;

    @Autowired
    private SubnetController subnetController;

    @Autowired
    private VirtualRouterController virtualRouterController;

    public List<String> syncWithAws() {

        graphNodeController.deleteAll();

        Regions region = Regions.US_EAST_1;

        this.addVpcs(region);

        this.addInternetGateways(region);

        this.addSecurityGroups(region);

        this.addSubnets(region);

        this.addNetworkAccessLists(region);

        this.addRoutingTables(region);

        this.addInstances(region);

        List<String> result = new ArrayList<>();
        result.add("Done.");
        return result;

    }

    private void addVpcs(Regions regions) {

        List<Vpc> vpcList = new ArrayList<>();

        vpcList.addAll(ec2Manager.getRegionVpcList(regions));

        List<VirtualPrivateNetwork> vpcNodeList = new ArrayList<>();
        VirtualPrivateNetwork vpcNode;
        for (Vpc vpc : vpcList) {
            vpcNode = new VirtualPrivateNetwork();
            vpcNode.setName(vpc.getVpcId());
            CidrBlock cidrBlock = new CidrBlock();
            cidrBlock.setCidr(vpc.getCidrBlock());
            vpcNode.addCidr(cidrBlock);

            vpcNodeList.add(vpcNode);
        }

        vpcController.save(vpcNodeList);

    }

    private void addInternetGateways(Regions region) {

        InternetNode internetNode = new InternetNode();


        List<InternetGateway> gateways = ec2Manager.getRegionInternetGateways(region);

        List<InternetGatwayNode> gatwayNodes = new ArrayList<>();
        for (InternetGateway gateway : gateways) {

            InternetGatwayNode node = new InternetGatwayNode();
            node.setName(gateway.getInternetGatewayId());
            node.setInternetNode(internetNode);

            for (InternetGatewayAttachment attachment : gateway.getAttachments()) {
                String vpcId = attachment.getVpcId();
                VirtualPrivateNetwork vpc = vpcController.findByName(vpcId);
                node.addVpv(vpc);

            }

            gatwayNodes.add(node);

        }
        graphNodeController.save(gatwayNodes);
    }


    private void addSubnets(Regions region) {

        List<com.amazonaws.services.ec2.model.Subnet> subnetList = ec2Manager.getRegionSubnetList(region);

        List<VirtualPrivateNetwork> vpcList = new ArrayList<>();
        List<com.huawei.ibn.model.l3.Subnet> subnets = new ArrayList<>();
        for (com.amazonaws.services.ec2.model.Subnet subnet : subnetList) {

            com.huawei.ibn.model.l3.Subnet subnetNode = new com.huawei.ibn.model.l3.Subnet();
            subnetNode.setName(subnet.getSubnetId());
            subnetNode.setNetworkAddress(new CidrBlock(subnet.getCidrBlock()));
            String vpcId = subnet.getVpcId();
            VirtualPrivateNetwork vpc = vpcController.findByName(vpcId);
            vpc.addSubnet(subnetNode);
            vpcList.add(vpc);

        }

        vpcController.save(vpcList);

    }

    private void addNetworkAccessLists(Regions region) {

        List<VirtualPrivateNetwork> vpcList = new ArrayList<>();

        List<NetworkAcl> regionAclList = ec2Manager.getRegionAcl(region);

        for (NetworkAcl networkAcl : regionAclList) {

            AccessControl accessControl = new AccessControl();
            accessControl.setName(networkAcl.getNetworkAclId());

            for (NetworkAclAssociation networkAclAssociation : networkAcl.getAssociations()) {
                String subnetId = networkAclAssociation.getSubnetId();
                com.huawei.ibn.model.l3.Subnet subnet = subnetController.findByName(subnetId);
                accessControl.addSubnet(subnet);
            }

            List<NetworkAclEntry> entries = networkAcl.getEntries();
            AccessControlRule rule;
            for (NetworkAclEntry entry : entries) {

                rule = new AccessControlRule();
                rule.setRuleNumber(entry.getRuleNumber());
                rule.setAllow(entry.getRuleAction().equals("allow"));

                if (entry.getEgress()) {

                    rule.setDestination(new CidrBlock(entry.getCidrBlock()));
                    rule.setAccessControlType(AccessControlType.OUTBOUND);


                } else {
                    rule.setSource(new CidrBlock(entry.getCidrBlock()));
                    rule.setAccessControlType(AccessControlType.INBOUND);
                }

                accessControl.addRule(rule);

                List<com.huawei.ibn.model.l3.Subnet> subnets = new ArrayList<>();
                for (NetworkAclAssociation networkAclAssociation : networkAcl.getAssociations()) {
                    String subnetId = networkAclAssociation.getSubnetId();
                    com.huawei.ibn.model.l3.Subnet subnet = subnetController.findByName(subnetId);
                    subnet.setAccessControlList(accessControl);

                }

                graphNodeController.save(subnets);

            }

            VirtualPrivateNetwork vpc = vpcController.findByName(networkAcl.getVpcId());
            vpc.addAccessControl(accessControl);
            vpcList.add(vpc);

        }

        vpcController.save(vpcList);

    }

    private void addRoutingTables(Regions region) {

        List<RouteTable> routeTables = ec2Manager.getRegionRouteTables(region);

        for (RouteTable routeTable : routeTables) {

            String vpcId = routeTable.getVpcId();
            VirtualRouter virtualRouter = virtualRouterController.findByName("rtr-" + vpcId);
            if (virtualRouter == null) {
                virtualRouter = new VirtualRouter();
                virtualRouter.setName("rtr-" + vpcId);
            }

            RoutingTable routingTable = new RoutingTable();
            routingTable.setName(routeTable.getRouteTableId());
            virtualRouter.addRoutingTable(routingTable);

            List<com.huawei.ibn.model.l3.Subnet> subnets = new ArrayList<>();
            for (RouteTableAssociation routeTableAssociation : routeTable.getAssociations()) {

                String subnetId = routeTableAssociation.getSubnetId();

                if (subnetId == null) {
                    continue;
                }

                com.huawei.ibn.model.l3.Subnet subnet = subnetController.findByName(subnetId);
                EthernetInterface eth = new EthernetInterface();
                V4IpAddress ipAddress = new V4IpAddress();
                eth.setIpAddress(ipAddress);
                virtualRouter.addEthernetInterface(eth);
                LocalRoute localRoute = new LocalRoute();
                localRoute.setIpAddress(ipAddress);
                routingTable.addRoute(localRoute);
                subnet.addMember(ipAddress);
                subnets.add(subnet);

            }

            graphNodeController.save(subnets);

            virtualRouterController.save(virtualRouter);

        }

    }


    private void addSecurityGroups(Regions region) {
        List<SecurityGroup> securityGroups = ec2Manager.getRegionSecurityGroup(region);

        List<SecurityGroupNode> groupNodes = new ArrayList<>();
        for (SecurityGroup securityGroup : securityGroups) {
            SecurityGroupNode node = new SecurityGroupNode();
            node.setName(securityGroup.getGroupId());

            VirtualPrivateNetwork vpc = vpcController.findByName(securityGroup.getVpcId());
            node.setVpc(vpc);

            for (IpPermission ipPermission : securityGroup.getIpPermissions()) {

            }

            for (IpPermission ipPermission : securityGroup.getIpPermissionsEgress()) {


            }

            groupNodes.add(node);

        }

        graphNodeController.save(groupNodes);

    }

    private void addInstances(Regions region) {

        List<Instance> instances = ec2Manager.getRegionInstances(region);

        List<com.huawei.ibn.model.l3.Subnet>subnets= new ArrayList<>();
        Set<ComputeNode> computeNodes = new HashSet<>();
        for (Instance instance : instances) {

            ComputeNode node = new ComputeNode();
            computeNodes.add(node);
            node.setName(instance.getInstanceId());

            for (InstanceNetworkInterface inf : instance.getNetworkInterfaces()) {
                EthernetInterface eth = new EthernetInterface();
                node.addInterface(eth);
                eth.setName(inf.getNetworkInterfaceId());
                V4IpAddress ipAddress = new V4IpAddress();
                ipAddress.setStringAddress(inf.getPrivateIpAddress());
                eth.setIpAddress(ipAddress);
                String subnetId = inf.getSubnetId();
                com.huawei.ibn.model.l3.Subnet subnet = subnetController.findByName(subnetId);
                subnet.addMember(ipAddress);
                subnets.add(subnet);

            }
        }

        graphNodeController.save(computeNodes);
        graphNodeController.save(subnets);

    }

}
