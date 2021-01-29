package com.monportailrh.utilities.models;

import com.monportailrh.utilities.GeneralPropertyManger;
import lombok.Data;

@Data
public class Credentials {
    private String username;
    private String password;

    public static final Credentials SUPERADMIN = Credentials.newBuilder()
            .setUsername(GeneralPropertyManger.SUPERADMIN_USERNAME)
            .setPassword(GeneralPropertyManger.SUPERADMIN_PASSWORD)
            .build();

    public static final Credentials ANGELINA_JOLIE = Credentials.newBuilder()
            .setUsername(GeneralPropertyManger.ANGELINA_JOLIE_USERNAME)
            .setPassword(GeneralPropertyManger.ANGELINA_JOLIE_PASSWORD)
            .build();

    public static final Credentials BRAD_PITT = Credentials.newBuilder()
            .setUsername(GeneralPropertyManger.BRAD_PITT_USERNAME)
            .setPassword(GeneralPropertyManger.BRAD_PITT_PASSWORD)
            .build();

    public static final Credentials DOLPH_LUNDGREN = Credentials.newBuilder()
            .setUsername(GeneralPropertyManger.DOLPH_LUNDGREN_USERNAME)
            .setPassword(GeneralPropertyManger.DOLPH_LUNDGREN_PASSWORD)
            .build();

    public static final Credentials JESSICA_ALBA = Credentials.newBuilder()
            .setUsername(GeneralPropertyManger.JESSICA_ALBA_USERNAME)
            .setPassword(GeneralPropertyManger.JESSICA_ALBA_PASSWORD)
            .build();


    private Credentials() {
    }

    public static Credentials.Builder newBuilder() {
        return new Credentials().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Credentials.Builder setUsername(String username) {
            Credentials.this.username = username;
            return this;
        }

        public Credentials.Builder setPassword(String password) {
            Credentials.this.password = password;
            return this;
        }

        public Credentials build() {
            return Credentials.this;
        }
    }
}

