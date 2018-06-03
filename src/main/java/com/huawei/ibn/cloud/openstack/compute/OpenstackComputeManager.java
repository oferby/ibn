package com.huawei.ibn.cloud.openstack.compute;

import com.huawei.ibn.cloud.openstack.identity.OpenstackIdentityManager;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.compute.*;
import org.openstack4j.model.compute.builder.ServerCreateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class OpenstackComputeManager {

    @Autowired
    private OpenstackIdentityManager identityManager;

    public Server createServer(String projectId, Map<String,String> params) {

        OSClient.OSClientV3 client = identityManager.getClientForProjectName(projectId);

        ServerCreateBuilder serverBuilder = client.compute().servers().serverBuilder();

        if (params.containsKey("network-port"))
            serverBuilder.addNetworkPort(params.get("network-port"));
        if (params.containsKey("availability-zone"))
            serverBuilder.availabilityZone(params.get("availability-zone"));
        if (params.containsKey("flavor"))
            serverBuilder.flavor(params.get("flavor"));
        if (params.containsKey("name"))
            serverBuilder.name(params.get("name"));
        if (params.containsKey("network"))
            serverBuilder.networks(Collections.singletonList(params.get("network")));
        if (params.containsKey("image"))
            serverBuilder.image(params.get("image"));

        ServerCreate serverCreate = serverBuilder.build();

        return client.compute().servers().boot(serverCreate);

    }

    public String getServerIdByName(String projectId, String serverName) {

        OSClient.OSClientV3 client = identityManager.getClientForProject(projectId);

        List<? extends Server> servers = client.compute().servers().list();

        Optional<? extends Server> optionalServer = servers.stream().filter(s -> s.getName().equals(serverName)).findFirst();

        return optionalServer.map(Server::getId).orElse(null);

    }


    public boolean deleteServer(String projectId, String serverId) {

        OSClient.OSClientV3 client = identityManager.getClientForProject(projectId);

        ActionResponse response = client.compute().servers().delete(serverId);

        return response.isSuccess();

    }

    public Addresses getServerAddresses(String projectId, String serverId) {

        OSClient.OSClientV3 client = identityManager.getClientForProject(projectId);

        return client.compute().servers().get(serverId).getAddresses();

    }

    private String getFreePublicIp(String projectId){

        OSClient.OSClientV3 client = identityManager.getClientForProject(projectId);

        List<? extends FloatingIP> floatingIPS = client.compute().floatingIps().list();

        Optional<? extends FloatingIP> optionalIp = floatingIPS.stream().filter(fip -> fip.getInstanceId() == null).findFirst();

        if (optionalIp.isPresent())
            return optionalIp.get().getFloatingIpAddress();


        FloatingIP floatingIP = client.compute().floatingIps().allocateIP("public");

        return floatingIP.getFloatingIpAddress();

    }


    public boolean setPublicIp(String projectId, String serverId) {

        OSClient.OSClientV3 client = identityManager.getClientForProject(projectId);

        Server server = client.compute().servers().get(serverId);

        String publicIp = this.getFreePublicIp(projectId);

        ActionResponse actionResponse = client.compute().floatingIps().addFloatingIP(server, publicIp);

        return actionResponse.isSuccess();

    }


    public String getImageIdByName(String projectId, String imageName) {

        OSClient.OSClientV3 client = identityManager.getClientForProject(projectId);

        List<? extends Image> images = client.compute().images().list();

        Optional<? extends Image> optionalImage = images.stream().filter(i -> i.getName().equals(imageName)).findFirst();

        return optionalImage.map(Image::getId).orElse(null);

    }

    public String getFlavorIdByName(String projectId, String flavorName) {

        OSClient.OSClientV3 client = identityManager.getClientForProject(projectId);

        List<? extends Flavor> flavors = client.compute().flavors().list();

        Optional<? extends Flavor> optionalFlavor = flavors.stream().filter(f -> f.getName().equals(flavorName)).findFirst();

        return optionalFlavor.map(Flavor::getId).orElse(null);

    }



}
