package com.monportailrh.utility.model;

import com.monportailrh.utility.RestAssuredUtilityManager;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class User {
    private RestAssuredUtilityManager restAssuredUtilityManager;
    private String name;
    private String surname;
    private String username;
    private String password;
    private List<String> listOfRoles;
    private List<Module> listOfModules;

    private Map<String, Module> mapOfModules;

    public User(Credential userCredentials) {
        restAssuredUtilityManager = new RestAssuredUtilityManager(userCredentials);
        this.username = userCredentials.getUsername();
        this.password = userCredentials.getPassword();
        this.listOfModules = restAssuredUtilityManager.getListOfModules();
    }

    public Map<String, Module> getMapOfModules() {
        Map<String, Module> mapOfModules = new HashMap<>();
        for (Module module : getListOfModules()) {
            mapOfModules.put(module.getName(), module);
        }
        return mapOfModules;
    }

    public List<String> listAllModuleNames() {
        List<String> listOfModuleNames = new ArrayList<>();
        getListOfModules().forEach((module
                -> {
            if (module.getModulePermissions().isAccess()) {
                listOfModuleNames.add(module.getName());
            }
        }));
        return listOfModuleNames;
    }
}