package com.example.demo.config;

import com.nimbusds.jose.shaded.gson.internal.LinkedTreeMap;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomJwtGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private static final String DESIRED_CLAIM_NAME = "matinreza";

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Collection<GrantedAuthority> roles = new ArrayList<>();
        if (jwt.getClaims().get(DESIRED_CLAIM_NAME) != null)
            for (String role : ((List<String>) ((LinkedTreeMap) jwt.getClaims().get(DESIRED_CLAIM_NAME)).get("roles"))) {
                roles.add(new MyCustomGrantedAuthority("ROLE_" + role));
            }
        return roles;
    }

    // Define your custom GrantedAuthority implementation
    // This is just an example; you can customize it based on your requirements
    private static class MyCustomGrantedAuthority implements GrantedAuthority {
        private final String authority;

        public MyCustomGrantedAuthority(String authority) {
            this.authority = authority;
        }

        @Override
        public String getAuthority() {
            return authority;
        }

        @Override
        public String toString() {
            return authority; // Return the role name as the string representation
        }
    }
}
