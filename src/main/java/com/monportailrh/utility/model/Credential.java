package com.monportailrh.utility.model;

import com.monportailrh.utility.GeneralPropertyManger;
import lombok.Data;

@Data
public class Credential {
    private String username;
    private String password;

    public static final Credential SUPERADMIN = Credential.newBuilder()
            .setUsername(GeneralPropertyManger.SUPERADMIN_USERNAME)
            .setPassword(GeneralPropertyManger.SUPERADMIN_PASSWORD)
            .build();

    public static final Credential ANGELINA_JOLIE = Credential.newBuilder()
            .setUsername(GeneralPropertyManger.ANGELINA_JOLIE_USERNAME)
            .setPassword(GeneralPropertyManger.ANGELINA_JOLIE_PASSWORD)
            .build();

    public static final Credential BRAD_PITT = Credential.newBuilder()
            .setUsername(GeneralPropertyManger.BRAD_PITT_USERNAME)
            .setPassword(GeneralPropertyManger.BRAD_PITT_PASSWORD)
            .build();

    public static final Credential DOLPH_LUNDGREN = Credential.newBuilder()
            .setUsername(GeneralPropertyManger.DOLPH_LUNDGREN_USERNAME)
            .setPassword(GeneralPropertyManger.DOLPH_LUNDGREN_PASSWORD)
            .build();

    public static final Credential JESSICA_ALBA = Credential.newBuilder()
            .setUsername(GeneralPropertyManger.JESSICA_ALBA_USERNAME)
            .setPassword(GeneralPropertyManger.JESSICA_ALBA_PASSWORD)
            .build();


    private Credential() {
    }

    public static Credential.Builder newBuilder() {
        return new Credential().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Credential.Builder setUsername(String username) {
            Credential.this.username = username;
            return this;
        }

        public Credential.Builder setPassword(String password) {
            Credential.this.password = password;
            return this;
        }

        public Credential build() {
            return Credential.this;
        }
    }
}

