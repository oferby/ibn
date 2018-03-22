package com.huawei.ibn.model.l3;

import com.huawei.ibn.model.common.GraphNode;
import org.apache.commons.net.util.SubnetUtils;

public class CidrBlock extends GraphNode{

    private String cidr;

    public CidrBlock() {
    }

    public CidrBlock(String cidr) {
        this.cidr = cidr;
    }

    public String getCidr() {
        return cidr;
    }

    public void setCidr(String cidr) {
        this.cidr = cidr;
    }

    public boolean isInRange(String ipAddress) {

        SubnetUtils utils = new SubnetUtils(cidr);
        return utils.getInfo().isInRange(ipAddress);

    }

}
