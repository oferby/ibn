package com.huawei.ibn.model.l3;

import com.huawei.ibn.model.common.GraphNode;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

public class RoutingTable extends GraphNode {

    private boolean isMain;

    @Relationship(type = "ATTACH_TO")
    private VirtualPrivateNetwork vpc;

    @Relationship(type = "ROUTE")
    private Set<AbstractRoute> routes;

    public boolean isMain() {
        return isMain;
    }

    public void setMain(boolean main) {
        isMain = main;
    }

    public VirtualPrivateNetwork getVpc() {
        return vpc;
    }

    public void setVpc(VirtualPrivateNetwork vpc) {
        this.vpc = vpc;
    }

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
