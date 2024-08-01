package controller;

import org.example.controllers.AuthController;
import org.example.exceptions.InvalidException;
import org.example.models.LoginRequest;
import org.example.services.AuthService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class AuthControllerTest {
    AuthService authService = Mockito.mock(AuthService.class);
    AuthController authController = new AuthController(authService);

    @Test
    void loginShouldReturnJWTTokenOnValidLoginRequest() throws SQLException, InvalidException {
        LoginRequest loginRequest = new LoginRequest("username", "password");
        String expectedToken = "jwtToken";

        Mockito.when(authService.login(loginRequest)).thenReturn(expectedToken);
        Response response = authController.login(loginRequest);
        
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(expectedToken, response.getEntity());
    }

    @Test
    void loginShouldThrowInvalidExceptionWhenAuthServiceThrowsInvalidException()
            throws SQLException, InvalidException {
        LoginRequest loginRequest = new LoginRequest("username", "password");

        when(authService.login(loginRequest)).thenThrow(InvalidException.class);
        Response response = authController.login(loginRequest);

        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    void loginShouldThrowSQLExcpetionWhenAuthServiceThrowsSQLException()
        throws SQLException, InvalidException {
        LoginRequest loginRequest = new LoginRequest("username", "password");

        when(authService.login(loginRequest)).thenThrow(SQLException.class);
        Response response = authController.login(loginRequest);

        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
    }
}
