package com.monportailrh.model;

import com.monportailrh.utility.GeneralPropertyManger;
import com.monportailrh.utility.User;

public class Users {
    public static final User SUPERADMIN = User.newBuilder()
            .setUsername(GeneralPropertyManger.SUPERADMIN_USERNAME)
            .setPassword(GeneralPropertyManger.SUPERADMIN_PASSWORD)
            .build();
    public static final User ANGELINA_JOLIE = User.newBuilder()
            .setUsername(GeneralPropertyManger.ANGELINA_JOLIE_USERNAME)
            .setPassword(GeneralPropertyManger.ANGELINA_JOLIE_PASSWORD)
            .build();
}
