package com.huawei.ibn.cloud.aws;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.model.*;
import com.huawei.ibn.cloud.CloudController;
import com.huawei.ibn.model.acl.*;
import com.huawei.ibn.model.controller.*;
import com.huawei.ibn.model.l1.EthernetInterface;
import com.huawei.ibn.model.l3.*;
import com.huawei.ibn.model.l3.CidrBlock;
import com.huawei.ibn.model.location.Region;
import com.huawei.ibn.model.service.InternetGatwayNode;
import com.huawei.ibn.model.service.InternetNode;
import com.huawei.ibn.model.virtual.ComputeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@Qualifier("awsCloudController")
public class AwsManagerImpl implements CloudController {

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

    @Autowired
    private SecurityGroupController securityGroupController;


    @Override
    public void sync() {

        graphNodeController.deleteAll();

        for (Regions region : Regions.values()) {

            try {

                doAll(region);

            } catch (Exception ignored) {
            }
        }

//        doAll(Regions.US_WEST_1);

    }


    private void doAll(Regions region) {

        Region awsRegion = new Region();
        awsRegion.setName(region.getName());

        this.addAviaAvailabilityZones(region, awsRegion);

        this.addVpcs(region, awsRegion);

        this.addInternetGateways(region);

        this.addSecurityGroups(region);

        this.addSecurityGroupRules(region);

        this.addSubnets(region);

        this.addNetworkAccessLists(region);

        this.addRoutingTables(region);

        this.addInstances(region);

        graphNodeController.save(awsRegion);
    }

    private void addAviaAvailabilityZones(Regions region, Region awsRegion) {

        List<AvailabilityZone> availabilityZones = ec2Manager.getAvailabilityZones(region);

        Set<com.huawei.ibn.model.location.AvailabilityZone> zones = new HashSet<>();

        for (AvailabilityZone availabilityZone : availabilityZones) {

            com.huawei.ibn.model.location.AvailabilityZone zone = new com.huawei.ibn.model.location.AvailabilityZone();
            zone.setName(availabilityZone.getZoneName());
            zones.add(zone);
        }

        awsRegion.setAvailabilityZones(zones);

    }

    private void addVpcs(Regions region, Region awsRegion) {

        List<Vpc> vpcList = new ArrayList<>();

        vpcList.addAll(ec2Manager.getRegionVpcList(region));

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

        vpcController.saveAll(vpcNodeList);

        awsRegion.addAll(vpcNodeList);

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
        graphNodeController.saveAll(gatwayNodes);
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
            subnetNode.setVpc(vpc);
            vpcList.add(vpc);
            subnets.add(subnetNode);

        }

        graphNodeController.saveAll(subnets);

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

                graphNodeController.saveAll(subnets);

            }

            VirtualPrivateNetwork vpc = vpcController.findByName(networkAcl.getVpcId());
            accessControl.setVpc(vpc);
            vpcList.add(vpc);

        }

        vpcController.saveAll(vpcList);

    }

    private void addRoutingTables(Regions region) {

        List<RouteTable> routeTables = ec2Manager.getRegionRouteTables(region);

        List<RoutingTable> routingTables = new ArrayList<>();
        for (RouteTable routeTable : routeTables) {

            RoutingTable routingTable = new RoutingTable();
            routingTables.add(routingTable);
            routingTable.setName(routeTable.getRouteTableId());

            String vpcId = routeTable.getVpcId();
            VirtualPrivateNetwork vpc = vpcController.findByName(vpcId);
            routingTable.setVpc(vpc);

            List<com.huawei.ibn.model.l3.Subnet> subnets = new ArrayList<>();
            for (RouteTableAssociation routeTableAssociation : routeTable.getAssociations()) {

                routingTable.setMain(routeTableAssociation.getMain());

                String subnetId = routeTableAssociation.getSubnetId();
                if (subnetId == null) {
                    continue;
                }
                com.huawei.ibn.model.l3.Subnet subnet = subnetController.findByName(subnetId);
                subnet.setRoutingTable(routingTable);
                EthernetInterface eth = new EthernetInterface();
                V4IpAddress ipAddress = new V4IpAddress();
                eth.setIpAddress(ipAddress);
                LocalRoute localRoute = new LocalRoute();
                localRoute.setIpAddress(ipAddress);
                routingTable.addRoute(localRoute);
                subnet.addMember(ipAddress);
                subnets.add(subnet);

            }

            graphNodeController.saveAll(subnets);

        }

        graphNodeController.saveAll(routingTables);

    }

    private void addOrphanSubnets(Regions region) {


    }

    private void addSecurityGroups(Regions region) {
        List<SecurityGroup> securityGroups = ec2Manager.getRegionSecurityGroup(region);

        List<SecurityGroupNode> groupNodes = new ArrayList<>();
        for (SecurityGroup securityGroup : securityGroups) {
            SecurityGroupNode node = new SecurityGroupNode();
            node.setName(securityGroup.getGroupId());

            VirtualPrivateNetwork vpc = vpcController.findByName(securityGroup.getVpcId());
            node.setVpc(vpc);

            groupNodes.add(node);

        }

        graphNodeController.saveAll(groupNodes);

    }

    private void addSecurityGroupRules(Regions region) {

        List<SecurityGroup> securityGroups = ec2Manager.getRegionSecurityGroup(region);
        List<SecurityGroupNode> groupNodes = new ArrayList<>();
        for (SecurityGroup securityGroup : securityGroups) {

            SecurityGroupNode node = securityGroupController.findByName(securityGroup.getGroupId());

            for (IpPermission ipPermission : securityGroup.getIpPermissions()) {

                SecurityGroupRuleNode rule = new SecurityGroupRuleNode();
                rule.setType(AccessControlType.INBOUND);
                rule.setIpProtocol(ipPermission.getIpProtocol());
                rule.setFromPort(ipPermission.getFromPort());
                rule.setToPort(ipPermission.getToPort());

                for (IpRange ipRange : ipPermission.getIpv4Ranges()) {
                    CidrBlock cidrBlock = new CidrBlock(ipRange.getCidrIp());
                    rule.addCidr(cidrBlock);
                }

                for (UserIdGroupPair userIdGroupPair : ipPermission.getUserIdGroupPairs()) {
                    SecurityGroupNode securityGroupNode = securityGroupController.findByName(userIdGroupPair.getGroupId());
                    rule.addSecurityGroupNode(securityGroupNode);
                }

                node.addRule(rule);
            }

            for (IpPermission ipPermission : securityGroup.getIpPermissionsEgress()) {

                SecurityGroupRuleNode rule = new SecurityGroupRuleNode();
                rule.setType(AccessControlType.OUTBOUND);
                rule.setIpProtocol(ipPermission.getIpProtocol());
                rule.setFromPort(ipPermission.getFromPort());
                rule.setToPort(ipPermission.getToPort());

                for (IpRange ipRange : ipPermission.getIpv4Ranges()) {
                    CidrBlock cidrBlock = new CidrBlock(ipRange.getCidrIp());
                    rule.addCidr(cidrBlock);
                }

                for (UserIdGroupPair userIdGroupPair : ipPermission.getUserIdGroupPairs()) {
                    SecurityGroupNode securityGroupNode = securityGroupController.findByName(userIdGroupPair.getGroupId());
                    rule.addSecurityGroupNode(securityGroupNode);
                }

                node.addRule(rule);

            }

            groupNodes.add(node);

        }


        graphNodeController.saveAll(groupNodes);

    }


    private void addInstances(Regions region) {

        List<Instance> instances = ec2Manager.getRegionInstances(region);

        List<com.huawei.ibn.model.l3.Subnet> subnets = new ArrayList<>();
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

        graphNodeController.saveAll(computeNodes);
        graphNodeController.saveAll(subnets);

    }

}
