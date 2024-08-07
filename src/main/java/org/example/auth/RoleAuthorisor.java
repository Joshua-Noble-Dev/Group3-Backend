package org.example.auth;

import io.dropwizard.auth.Authorizer;
import org.example.models.JwtToken;

import javax.annotation.Nullable;
import javax.ws.rs.container.ContainerRequestContext;

public class RoleAuthorisor implements Authorizer<JwtToken> {
    @Override
    public boolean authorize(JwtToken jwtToken, String s) {
        return jwtToken.getUserRole().getRoleName().equals(s);
    }

    @Override
    public boolean authorize(JwtToken principal, String role,
        @Nullable ContainerRequestContext requestContext) {
            return principal.getUserRole().getRoleName().equals(role);
    }
}
