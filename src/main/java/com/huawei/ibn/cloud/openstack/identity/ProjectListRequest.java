package com.huawei.ibn.cloud.openstack.identity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectListRequest {

    @JsonProperty
    private Project[] projects;

    public String getProjectIdByName(String name) {
        Optional<Project> project = Arrays.stream(projects).filter(p -> p.getName().equals(name)).findFirst();
        return project.map(Project::getId).orElse(null);
    }

    public Project[] getProjects() {
        return projects;
    }
}

