package com.huawei.ibn.cloud.hec;

import org.openstack4j.api.OSClient;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.openstack.OSFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Controller
public class HecIdentityManager {

    @Value("${hec.user}")
    private String user;
    @Value("${hec.password}")
    private String password;
    @Value("${hec.account}")
    private String account;

    private OSClient.OSClientV3 os;

    public OSClient.OSClientV3 getClient() {

        if (os != null) {
            return os;
        }

        connect();
        return os;
    }

    private void connect() {

        os = OSFactory.builderV3()
                .endpoint("https://iam.cn-north-1.myhuaweicloud.com/v3")
                .credentials(user, password, Identifier.byName(account))
                .authenticate();

    }


}
