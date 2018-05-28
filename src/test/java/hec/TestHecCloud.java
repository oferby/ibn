package hec;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openstack4j.api.OSClient;
import org.openstack4j.api.identity.v3.ProjectService;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.compute.Flavor;
import org.openstack4j.model.identity.v3.Domain;
import org.openstack4j.model.identity.v3.Project;
import org.openstack4j.model.identity.v3.Region;
import org.openstack4j.openstack.OSFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application.properties")
public class TestHecCloud {

    @Value("${hec.user}")
    private String user;
    @Value("${hec.password}")
    private String password;
    @Value("${hec.account}")
    private String account;

    @Test
    public void testConnection() {

        OSClient.OSClientV3 os = OSFactory.builderV3()
                .endpoint("https://iam.cn-north-1.myhuaweicloud.com/v3")
                .credentials(user, password, Identifier.byName(account))
                .authenticate();

        assert os != null;

        List<? extends Region> regions = os.identity().regions().list();

        System.out.println("Regions:");
        for (Region region : regions) {
            System.out.println(region.getId());
        }

        List<? extends Domain> domains = os.identity().domains().list();
        System.out.println("Domains:");
        for (Domain domain : domains) {
            System.out.println(domain.getName());
        }

        ProjectService projects = os.identity().projects();
        List<? extends Project> projectList = projects.list();
        System.out.println("Projects:");
        for (Project project : projectList) {
            System.out.println(project.getName());
        }

        os = OSFactory.builderV3()
                .endpoint("https://iam.cn-north-1.myhuaweicloud.com/v3")
                .token(os.getToken().getId())
                .scopeToProject(Identifier.byName("cn-north-1"))
                .authenticate();


        // Find all Compute Flavors
        List<? extends Flavor> flavors = os.compute().flavors().list();

        assert flavors != null;


    }

    @Test
    public void testCompute() {

        String region = "cn-north-1";
        String endpoint = "ecs";

//        OSClient.OSClientV3 os = OSFactory.builderV3()
//                .endpoint("https://iam.cn-north-1.myhuaweicloud.com/v3")
//                .credentials(user, password, Identifier.byName(account))
//                .authenticate();

//        assert os != null;

        OSClient.OSClientV3 os = OSFactory.builderV3()
//                .endpoint("https://" + endpoint + "." + region + ".myhuaweicloud.com/v3")
                .endpoint("https://ecs.cn-north-1.myhuaweicloud.com")
//                .token(os.getToken().getId())
                .credentials(user, password, Identifier.byName(account))
                .scopeToProject(Identifier.byName("cn-north-1"))
                .authenticate();

        assert os != null;

        System.out.println(os.compute().servers().list());

    }

}
