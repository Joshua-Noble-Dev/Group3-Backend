package auth;

import io.dropwizard.auth.AuthenticationException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.example.auth.JwtAuthenticator;
import org.example.models.JwtToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class JwtAuthenticatorTest {

    private static final String SECRET_KEY_STRING = "your-256-bit-secret-your-256-bit-secret";
    private Key key;
    private JwtAuthenticator jwtAuthenticator;

    @BeforeEach
    void setUp() {
        key = Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());
        jwtAuthenticator = new JwtAuthenticator(key);
    }

    @Test
    void authenticate_ValidToken_ShouldReturnJwtToken() throws AuthenticationException {
        Integer roleId = 1;
        String token = Jwts.builder()
                .setSubject("user")
                .claim("Role", roleId)
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour from now
                .signWith(key)
                .compact();

        Optional<JwtToken> result = jwtAuthenticator.authenticate(token);

        assertThat(result).isPresent();
        assertThat(result.get().getUserRole().getRoleId()).isEqualTo(roleId);
    }

    @Test
    void authenticate_ExpiredToken_ShouldReturnEmpty() throws AuthenticationException {
        Integer roleId = 1;
        String token = Jwts.builder()
                .setSubject("user")
                .claim("Role", roleId)
                .setExpiration(new Date(System.currentTimeMillis() - 3600000)) // Token expired 1 hour ago
                .signWith(key)
                .compact();

        Optional<JwtToken> result = jwtAuthenticator.authenticate(token);

        assertThat(result).isEmpty();
    }

    @Test
    void authenticate_InvalidToken_ShouldReturnEmpty() throws AuthenticationException {
        String invalidToken = "invalidToken";

        Optional<JwtToken> result = jwtAuthenticator.authenticate(invalidToken);

        assertThat(result).isEmpty();
    }

    @Test
    void authenticate_TokenWithoutRole_ShouldReturnEmpty() throws AuthenticationException {
        String token = Jwts.builder()
                .setSubject("user")
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour from now
                .signWith(key)
                .compact();

        Optional<JwtToken> result = jwtAuthenticator.authenticate(token);

        assertThat(result).isEmpty();
    }
}