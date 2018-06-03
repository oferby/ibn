package openstack;

import com.huawei.ibn.cloud.openstack.identity.OpenstackIdentityManager;
import com.huawei.ibn.cloud.openstack.network.OpenstackNetworkManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openstack4j.model.network.Network;
import org.openstack4j.model.network.Port;
import org.openstack4j.model.network.Router;
import org.openstack4j.model.network.Subnet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
//@TestPropertySource(locations = "classpath:application.properties")
public class TestOpenstackNetworking {

    @Autowired
    private OpenstackNetworkManager networkManager;

    @Autowired
    private OpenstackIdentityManager identityManager;

    private String getDemoProjectId() {
        return identityManager.getProjectIdByName("demo");
    }

    @Test
    public void testNetworkList() {

        String projectId = this.getDemoProjectId();

        List<? extends Network> networkList = networkManager.getNetworkList(projectId);

        Assert.assertTrue(networkList.size() > 2);
    }


    @Test
    public void testSubnets() {

        String projectId = this.getDemoProjectId();

        List<? extends Network> networkList = networkManager.getNetworkList(projectId);

        Optional<? extends Network> network = networkList.stream().filter(n -> n.getName().equals("private")).findFirst();

        assert network.isPresent();

        List<? extends Subnet> subnets = networkManager.getSubnets(projectId, network.get().getId());

        Optional<? extends Subnet> subnet = subnets.stream().filter(s -> s.getName().equals("private-subnet")).findFirst();

        assert subnet.isPresent();


    }


    @Test
    public void testRouterList() {

        String projectId = this.getDemoProjectId();

        List<? extends Router> routers = networkManager.getRouters(projectId);

        Optional<? extends Router> router = routers.stream().filter(r -> r.getName().equals("router1")).findFirst();

        assert router.isPresent();

    }


    @Test
    public void testCreateNetworking() {

        String projectId = this.getDemoProjectId();

        Network network = networkManager.createNetwork(projectId, "network-1");
        String networkId = network.getId();
        assert networkId != null;

        Subnet subnet = networkManager.createSubnet(projectId, networkId, "subnet-1", "192.168.111.0/24");
        String subnetId = subnet.getId();
        assert subnetId != null;

        Router router = networkManager.createRouter(projectId, "test-router1");
        String routerId = router.getId();

        assert router.getName().equals("test-router1");
        assert router.getId() != null;

        Port port = networkManager.createPort(projectId, networkId, routerId);
        String portId = port.getId();
        assert portId !=null;


        networkManager.deleteRouter(projectId, routerId);

        assert networkManager.getRouter(projectId, routerId) == null;

        assert networkManager.getPort(projectId, portId) == null;

        networkManager.deleteNetwork(projectId, networkId);

        assert networkManager.getNetwork(projectId, networkId) == null;

    }


}