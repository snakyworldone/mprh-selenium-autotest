package com.monportailrh.utilities.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Module {
    private String alias;
    private String icon;
    private int id;
    private String name;
    @JsonProperty("web_url")
    private String webUrl;
    @JsonProperty("permissions")
    private ModulePermissions modulePermissions;
}
