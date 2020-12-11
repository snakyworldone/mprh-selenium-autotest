package com.monportailrh.utility.model;

import com.monportailrh.utility.RestAssuredUtilityManager;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    private RestAssuredUtilityManager restAssuredUtilityManager;
    private String name;
    private String surname;
    private String username;
    private String password;
    private List<String> listOfRoles;
    private List<Module> listOfModules;

    public User(Credential userCredentials) {
        restAssuredUtilityManager = new RestAssuredUtilityManager(userCredentials);
        this.username = userCredentials.getUsername();
        this.password = userCredentials.getPassword();
        this.listOfModules = restAssuredUtilityManager.getListOfModules();
    }

    public List<String> listAllModuleNames() {
        List<String> listOfModuleNames = new ArrayList<>();
        for (Module module : listOfModules) {
            listOfModuleNames.add(module.getName());
        }
        return listOfModuleNames;
    }
}