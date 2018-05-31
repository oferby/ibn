package com.huawei.ibn.cloud.openstack.identity;

import org.openstack4j.api.OSClient;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.openstack.OSFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Controller
public class OpenstackIdentityManager {

    @Value("${openstack.user}")
    private String user;

    @Value("${openstack.password}")
    private String password;

    @Value("${openstack.domain}")
    private String domain;

    @Value("${openstack.url}")
    private String identityUrl;

    private String unscopedToken;

    private void getClient() {

        OSClient.OSClientV3 osClient = OSFactory.builderV3()
                .endpoint(identityUrl)
                .credentials(user, password, Identifier.byName(domain))
                .authenticate();

        this.unscopedToken = osClient.getToken().getId();

    }

    public OSClient.OSClientV3 getClientForProject(String projectId) {

        if (unscopedToken == null)
            this.getClient();

        return this.getClientForProject(projectId, unscopedToken);
    }

    private OSClient.OSClientV3 getClientForProject(String projectId, String token) {

        return OSFactory.builderV3()
                .endpoint(identityUrl)
                .token(token)
                .scopeToProject(Identifier.byId(projectId))
                .authenticate();

    }

    public OSClient.OSClientV3 getClientForProjectName(String projectName) {

        if (unscopedToken == null)
            this.getClient();

        return this.getClientForProjectName(projectName, unscopedToken);

    }

    private OSClient.OSClientV3 getClientForProjectName(String projectName, String token) {

        String projectId = this.getProjectIdByName(projectName);

        return this.getClientForProject(projectId, token);


    }

    public List<Project> getProjectList() {

        ProjectListRequest projectListRequest = this.sendProjectListRequest();

        if (projectListRequest == null)
            return new ArrayList<>();

        return Arrays.asList(Objects.requireNonNull(projectListRequest.getProjects()));

    }

    public String getProjectIdByName(String name) {

        return this.sendProjectListRequest().getProjectIdByName(name);

    }

    private ProjectListRequest sendProjectListRequest() {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        if (unscopedToken == null)
            this.getClient();

        headers.set("x-auth-token", unscopedToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(identityUrl + "/auth/projects", HttpMethod.GET, entity, ProjectListRequest.class).getBody();

    }


}
