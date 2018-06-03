package openstack;

import com.huawei.ibn.cloud.openstack.compute.OpenstackComputeManager;
import com.huawei.ibn.cloud.openstack.identity.OpenstackIdentityManager;
import com.huawei.ibn.cloud.openstack.network.OpenstackNetworkManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openstack4j.model.compute.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
public class TestOpenstackCompute {

    @Autowired
    private OpenstackComputeManager computeManager;

    @Autowired
    private OpenstackNetworkManager networkManager;

    @Autowired
    private OpenstackIdentityManager identityManager;

    private String getDemoProjectId() {
        return identityManager.getProjectIdByName("demo");
    }

    @Test
    public void createServer() {

        String projectId = this.getDemoProjectId();

        String networkId = networkManager.getNetworkIdByName(projectId, "private");

        assert networkId != null;

        Map<String, String> params = new HashMap<>();

        params.put("network", networkId);
        params.put("flavor", computeManager.getFlavorIdByName(projectId, "m1.nano"));
        params.put("name", "server1");
        params.put("image", computeManager.getImageIdByName(projectId, "cirros-0.3.5-x86_64-disk"));

        Server server = computeManager.createServer("demo", params);

        assert server != null && server.getId() != null;

    }


    @Test
    public void addServerPublicIp() {

        String projectId = getDemoProjectId();

        String serverId = computeManager.getServerIdByName(projectId, "server1");

        boolean result = computeManager.setPublicIp(projectId, serverId);

        assert result;

    }


    @Test
    public void testDeleteServer() {

        String projectId = this.getDemoProjectId();

        String serverId = computeManager.getServerIdByName(projectId, "server1");

        assert computeManager.deleteServer(projectId, serverId);

    }

    @Test
    public void testGetImage() {

        String projectId = this.getDemoProjectId();
        String imageId = computeManager.getImageIdByName(projectId, "cirros-0.3.5-x86_64-disk");

        assert imageId != null;

    }

    @Test
    public void testGetFlavor() {

        String projectId = this.getDemoProjectId();

        String flavorId = computeManager.getFlavorIdByName(projectId, "m1.nano");

        assert flavorId != null;

    }

}
