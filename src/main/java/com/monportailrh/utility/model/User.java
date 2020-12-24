package com.monportailrh.utility.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.monportailrh.utility.RestAssuredUtilityManager;
import com.monportailrh.utility.Utility;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private RestAssuredUtilityManager restAssuredUtilityManager;
    @JsonProperty("first_name")
    private String name;
    @JsonProperty("last_name")
    private String surname;
    private String username;
    private String password;
    private List<String> listOfRoles;
    @JsonProperty("modules")
    private List<Module> listOfModules;

    private Map<String, Module> mapOfModules;

    public User() {
    }

    public User(Credential userCredentials) {
        restAssuredUtilityManager = new RestAssuredUtilityManager(userCredentials);
        this.username = userCredentials.getUsername();
        this.password = userCredentials.getPassword();
        this.name = restAssuredUtilityManager.getUser().getName();
        this.surname = restAssuredUtilityManager.getUser().getSurname();
        this.listOfModules = restAssuredUtilityManager.getUser().getListOfModules();
    }

    public Map<String, Module> getMapOfModules() {
        Map<String, Module> mapOfModules = new HashMap<>();
        for (Module module : getListOfModules()) {
            mapOfModules.put(module.getName(), module);
        }
        return mapOfModules;
    }

    public List<String> getListWithAllModuleNames() {
        List<String> listOfModuleNames = new ArrayList<>();
        getListOfModules().forEach((module
                -> {
            if (module.getModulePermissions().isAccess()) {
                listOfModuleNames.add(module.getName());
            }
        }));
        return listOfModuleNames;
    }

    @Override
    public String toString() {
        Utility utility = new Utility();
        return "User {" + "\n" +
                "\tname ='" + getName() + '\'' + "\n" +
                ", \tsurname = '" + getSurname() + '\'' + "\n" +
                ", \tusername = '" + getUsername() + '\'' + "\n" +
                ", \tpassword = '" + getPassword() + '\'' + "\n" +
                ", \tlistOfRoles = " + listOfRoles + "\n" +
                ", \tlistOfModules = " + utility.listAllElements(getListWithAllModuleNames()) + "\n" +
                '}';
    }
}