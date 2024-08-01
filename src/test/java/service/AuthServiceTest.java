package service;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.example.daos.AuthDao;
import org.example.daos.DatabaseConnector;
import org.example.exceptions.InvalidException;
import org.example.models.LoginRequest;
import org.example.models.User;
import org.example.services.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.crypto.SecretKey;
import java.security.Key;
import java.sql.Connection;
import java.sql.SQLException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    AuthDao authDao = Mockito.mock(AuthDao.class);
    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    AuthService authService = new AuthService(authDao, key);


    @Test
    void loginShouldReturnTokenWithValidCredentials() throws SQLException, InvalidException {
        LoginRequest loginRequest = new LoginRequest("username", "password");
        User user = new User("username", "hashedPassword", 1);

        Mockito.when(authDao.getUser(loginRequest)).thenReturn(user);
        String token = authService.generateJwtToken(user);

        assertNotNull(token);
    }

    @Test
    void loginShouldThrowInvalidExceptionOnInvalidCredentials() throws SQLException, InvalidException {
        LoginRequest loginRequest = new LoginRequest("username", "password");

        Mockito.when(authDao.getUser(loginRequest)).thenReturn(null);

        assertThrows(InvalidException.class,
                () -> authService.login(loginRequest));
    }

    @Test
    void loginShouldReturnSQLExceptionOnDatabaseError() throws SQLException, InvalidException {
        LoginRequest loginRequest = new LoginRequest("username", "password");

        Mockito.when(authDao.getUser(loginRequest)).thenThrow(new SQLException());

        assertThrows(SQLException.class,
                () -> authService.login(loginRequest));
    }
}
