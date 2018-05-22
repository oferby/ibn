package com.huawei.ibn.cloud.hec;

import com.huawei.ibn.cloud.CloudController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
@Qualifier("hecCloudController")
public class HECloudController implements CloudController {

    @Override
    public void sync() {

    }
}
