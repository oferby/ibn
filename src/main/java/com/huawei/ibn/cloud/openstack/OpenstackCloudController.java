package com.huawei.ibn.cloud.openstack;

import com.huawei.ibn.cloud.CloudController;
import com.huawei.ibn.cloud.openstack.identity.OpenstackIdentityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
@Qualifier("openstackCloudController")
public class OpenstackCloudController implements CloudController {

    @Autowired
    private OpenstackIdentityManager identityManager;

    @Override
    public void sync() {

    }



}
