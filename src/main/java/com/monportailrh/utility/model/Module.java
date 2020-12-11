package com.monportailrh.utility.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Module {
    private String alias;
    private String icon;
    private int id;
    private String name;
    private String webUrl;
}
