package openstack;

import com.huawei.ibn.cloud.openstack.compute.OpenstackComputeManager;
import com.huawei.ibn.cloud.openstack.identity.OpenstackIdentityManager;
import com.huawei.ibn.cloud.openstack.network.OpenstackNetworkManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public OpenstackNetworkManager getNetworkManager() {
        return new OpenstackNetworkManager();
    }

    @Bean
    public OpenstackIdentityManager getIdentityManager() {
        return new OpenstackIdentityManager();
    }

    @Bean
    public OpenstackComputeManager getComputeManager() {
        return new OpenstackComputeManager();
    }

}
