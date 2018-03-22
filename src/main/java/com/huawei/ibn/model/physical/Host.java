package com.huawei.ibn.model.physical;

import com.huawei.ibn.model.service.PortBasedService;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

public class Host extends Device {

    @Relationship(type = "RUNS")
    private Set<PortBasedService> serviceSet;

    public void addService(PortBasedService service) {
        if (serviceSet == null) {
            serviceSet = new HashSet<>();
        }

        serviceSet.add(service);
    }

    public Set<PortBasedService> getServiceSet() {
        return serviceSet;
    }

    public void setServiceSet(Set<PortBasedService> serviceSet) {
        this.serviceSet = serviceSet;
    }
}
