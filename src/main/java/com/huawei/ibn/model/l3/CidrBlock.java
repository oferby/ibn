package com.huawei.ibn.model.l3;

import com.huawei.ibn.model.common.GraphNode;
import org.apache.commons.net.util.SubnetUtils;

public class CidrBlock extends GraphNode{

    private SubnetUtils utils;

    public CidrBlock() {
    }

    public CidrBlock(String cidr) {
        this.utils = new SubnetUtils(cidr);
    }

    public String getCidr() {
        return utils.getInfo().getCidrSignature();
    }

    public void setCidr(String cidr) {
        this.utils = new SubnetUtils(cidr);
    }

    public boolean isInRange(String ipAddress) {
        return utils.getInfo().isInRange(ipAddress);
    }

}
