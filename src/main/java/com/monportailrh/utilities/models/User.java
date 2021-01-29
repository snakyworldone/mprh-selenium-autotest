package com.monportailrh.utilities.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.monportailrh.utilities.RestAssuredUtilityManager;
import com.monportailrh.utilities.Utilities;
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

    public User(Credentials userCredentials) {
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
        Utilities utilities = new Utilities();
        return "User {" + "\n" +
                "\tname ='" + getName() + '\'' + "\n" +
                ", \tsurname = '" + getSurname() + '\'' + "\n" +
                ", \tusername = '" + getUsername() + '\'' + "\n" +
                ", \tpassword = '" + getPassword() + '\'' + "\n" +
                ", \tlistOfRoles = " + listOfRoles + "\n" +
                ", \tlistOfModules = " + utilities.listAllElements(getListWithAllModuleNames()) + "\n" +
                '}';
    }
}