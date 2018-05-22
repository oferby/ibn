package com.huawei.ibn.cloud.hec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Controller
public class HecIdentityManager {

    @Value("${hec.user}") String user;
    @Value("${hec.password}") String password;
    @Value("${hec.account}") String account;



}
