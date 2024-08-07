package auth;

import org.example.auth.RoleAuthorisor;
import org.example.models.JwtToken;
import org.example.models.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.container.ContainerRequestContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RoleAuthorisorTest {
    private RoleAuthorisor roleAuthorisor;
    private JwtToken jwtToken;
    private UserRole userRole;

    @BeforeEach
    public void setUp() {
        roleAuthorisor = new RoleAuthorisor();
        jwtToken = mock(JwtToken.class);
        userRole = mock(UserRole.class);
        when(jwtToken.getUserRole()).thenReturn(userRole);
    }

    @Test
    public void testAuthorizeWithRoleString_Success() {
        when(userRole.getRoleName()).thenReturn("admin");

        boolean result = roleAuthorisor.authorize(jwtToken, "admin");

        assertTrue(result);
    }

    @Test
    public void testAuthorizeWithRoleString_Failure() {
        when(userRole.getRoleName()).thenReturn("admin");

        boolean result = roleAuthorisor.authorize(jwtToken, "user");

        assertFalse(result);
    }

    @Test
    public void testAuthorizeWithContext_Success() {
        when(userRole.getRoleName()).thenReturn("admin");
        ContainerRequestContext requestContext = mock(ContainerRequestContext.class);

        boolean result = roleAuthorisor.authorize(jwtToken, "admin", requestContext);

        assertTrue(result);
    }

    @Test
    public void testAuthorizeWithContext_Failure() {
        when(userRole.getRoleName()).thenReturn("admin");
        ContainerRequestContext requestContext = mock(ContainerRequestContext.class);

        boolean result = roleAuthorisor.authorize(jwtToken, "user", requestContext);

        assertFalse(result);
    }
}
