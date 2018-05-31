package com.huawei.ibn.cloud.openstack.network;

import com.huawei.ibn.cloud.openstack.identity.OpenstackIdentityManager;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.common.IdEntity;
import org.openstack4j.model.network.*;
import org.openstack4j.model.network.builder.NetSecurityGroupRuleBuilder;
import org.openstack4j.model.network.options.PortListOptions;
import org.openstack4j.openstack.networking.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class OpenstackNetworkManager {

    @Autowired
    private OpenstackIdentityManager identityManager;

    public List<? extends Network> getNetworkList(String projectId) {

        OSClient.OSClientV3 client = identityManager.getClientForProject(projectId);

        return client.networking().network().list();

    }

    public String getNetworkIdByName(String projectId, String networkName) {

        List<? extends Network> networkList = this.getNetworkList(projectId);

        Optional<? extends Network> optionalNetwork = networkList.stream().filter(n -> n.getName().equals(networkName)).findFirst();

        return optionalNetwork.map(Network::getId).orElse(null);

    }

    public Network getNetwork(String projectId, String networkId) {

        OSClient.OSClientV3 client = identityManager.getClientForProject(projectId);

        return client.networking().network().get(networkId);

    }


    public List<? extends Subnet> getSubnets(String projectId, String networkId) {

        OSClient.OSClientV3 client = identityManager.getClientForProject(projectId);

        return client.networking().subnet().list().stream().filter(subnet -> subnet.getNetworkId().equals(networkId)).collect(Collectors.toList());

    }



    public Network createNetwork(String projectId, String name) {

        OSClient.OSClientV3 client = identityManager.getClientForProject(projectId);

        Network network = NeutronNetwork.builder().name(name).adminStateUp(true).build();

        return client.networking().network().create(network);

    }

    public void deleteNetwork(String projectId, String networkId) {

        OSClient.OSClientV3 client = identityManager.getClientForProject(projectId);

        client.networking().network().delete(networkId);

    }

    public Subnet createSubnet(String projectId, String networkId, String name, String cidr) {

        OSClient.OSClientV3 client = identityManager.getClientForProject(projectId);

        Subnet subnet = NeutronSubnet.builder().cidr(cidr).name(name).networkId(networkId).ipVersion(IPVersionType.V4).build();

        return client.networking().subnet().create(subnet);

    }


    public List<? extends Router> getRouters(String projectId) {

        OSClient.OSClientV3 client = identityManager.getClientForProject(projectId);

        return client.networking().router().list();

    }

    public Router getRouter(String projectId, String routerId){

        OSClient.OSClientV3 client = identityManager.getClientForProject(projectId);

        return client.networking().router().get(routerId);

    }

    public Router createRouter(String projectId, String name) {

        OSClient.OSClientV3 client = identityManager.getClientForProject(projectId);

        Router router = NeutronRouter.builder().name(name).build();

        return client.networking().router().create(router);

    }

    public void deleteRouter(String projectId, String routerId) {

        OSClient.OSClientV3 client = identityManager.getClientForProject(projectId);

        PortListOptions portListOptions = PortListOptions.create().deviceId(routerId);
        List<? extends Port> ports = client.networking().port().list(portListOptions);

        for (Port p: ports) {
            client.networking().port().delete(p.getId());
        }

        client.networking().router().delete(routerId);

    }

    public Port createPort(String projectId, String networkId, String deviceId) {

        OSClient.OSClientV3 client = identityManager.getClientForProject(projectId);

        Port port = NeutronPort.builder().name("port222").deviceId(deviceId).adminState(true).networkId(networkId).build();

        return client.networking().port().create(port);

    }

    public Port getPort(String projectId, String portId) {

        OSClient.OSClientV3 client = identityManager.getClientForProject(projectId);

        return client.networking().port().get(portId);

    }

    public List<? extends SecurityGroup> getSecurityGroup(String projectId) {

        OSClient.OSClientV3 client = identityManager.getClientForProject(projectId);

        return client.networking().securitygroup().list();

    }

    public SecurityGroup getSecurityGroup(String projectId, String securityGroupId) {

        OSClient.OSClientV3 client = identityManager.getClientForProject(projectId);

        return client.networking().securitygroup().get(securityGroupId);

    }

    public SecurityGroup createSecurityGroup(String projectId, String name) {

        OSClient.OSClientV3 client = identityManager.getClientForProject(projectId);

        SecurityGroup securityGroup = NeutronSecurityGroup.builder().name(name).build();

        return client.networking().securitygroup().create(securityGroup);

    }

    public List<? extends SecurityGroupRule> getSecurityGroupRules(String projectId, String securityGroupId) {

        OSClient.OSClientV3 client = identityManager.getClientForProject(projectId);

        return client.networking().securitygroup().get(securityGroupId).getRules();

    }

    public SecurityGroupRule createSecurityGroupRule(String projectId, String securityGroupId, Map<String,String> params) {

        NetSecurityGroupRuleBuilder builder = NeutronSecurityGroupRule.builder();

        builder.direction(params.getOrDefault("direction", "Ingress"));
        builder.ethertype(params.getOrDefault("ethertype", "IPv4"));
        builder.portRangeMin(Integer.parseInt(params.getOrDefault("portRangeMin", "0")));
        builder.portRangeMax(Integer.parseInt(params.getOrDefault("portRangeMax", "0")));
        builder.protocol(params.getOrDefault("protocol","Any"));
        builder.remoteGroupId(params.getOrDefault("remoteGroupId",null));
        builder.remoteIpPrefix(params.getOrDefault("remoteIpPrefix",null));
        builder.securityGroupId(securityGroupId);

        SecurityGroupRule securityGroupRule = builder.build();

        OSClient.OSClientV3 client = identityManager.getClientForProject(projectId);

        return client.networking().securityrule().create(securityGroupRule);

    }


}
