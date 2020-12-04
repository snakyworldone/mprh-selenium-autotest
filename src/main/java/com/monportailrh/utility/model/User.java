package com.monportailrh.utility.model;

import com.monportailrh.utility.RestAssuredUtil.Authorization;
import lombok.Data;

import java.util.List;

@Data
public class User {
    private Authorization authorization;
    private String name;
    private String surname;
    private List<String> listOfRoles;
    private List<Module> listOfModules;

    public User(Credential userCredentials) {
        authorization = new Authorization(userCredentials);
        this.listOfModules = authorization.getListOfModules();
    }
}