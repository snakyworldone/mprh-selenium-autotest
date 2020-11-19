package com.monportailrh.utility;

import lombok.Data;

@Data
public class User {
    private String username;
    private String password;

    private User() {

    }

    public static Builder newBuilder() {
        return new User().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setUsername(String username) {
            User.this.username = username;

            return this;
        }

        public Builder setPassword(String password) {
            User.this.password = password;

            return this;
        }

        public User build() {
            return User.this;
        }
    }
}


