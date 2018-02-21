package com.huawei.ibn.model.physical;

import com.huawei.ibn.model.service.SimpleService;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

public class Host extends Device {

    @Relationship(type = "RUNS")
    private Set<SimpleService> serviceSet;

    public void addService(SimpleService service) {
        if (serviceSet == null) {
            serviceSet = new HashSet<>();
        }

        serviceSet.add(service);
    }

    public Set<SimpleService> getServiceSet() {
        return serviceSet;
    }

    public void setServiceSet(Set<SimpleService> serviceSet) {
        this.serviceSet = serviceSet;
    }
}
