package com.huawei.ibn.model.l3;

import com.huawei.ibn.model.common.GraphNode;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

public class RoutingTable extends GraphNode {

    @Relationship(type = "ROUTE")
    private Set<AbstractRoute> routes;

    public Set<AbstractRoute> getRoutes() {
        return routes;
    }

    public void setRoutes(Set<AbstractRoute> routes) {
        this.routes = routes;
    }

    public void addRoute(AbstractRoute route) {
        if (routes == null) {
            routes = new HashSet<>();
        }

        routes.add(route);
    }

}
