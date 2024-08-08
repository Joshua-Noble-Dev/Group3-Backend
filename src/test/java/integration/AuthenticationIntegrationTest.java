package integration;

import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.example.TestApplication;
import org.example.TestConfiguration;
import org.example.models.LoginRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ExtendWith(DropwizardExtensionsSupport.class)
public class AuthenticationIntegrationTest {
    private static final DropwizardAppExtension<TestConfiguration> APP =
            new DropwizardAppExtension<>(TestApplication.class);




    @Test
    void loginShouldReturnJWTToken() {
        Client client = APP.client();

        String adminUsername = System.getenv().get("ADMIN_USERNAME");
        String adminPassword = System.getenv().get("ADMIN_PASSWORD");

        LoginRequest loginRequest = new LoginRequest(adminUsername, adminPassword);

        Response response = client
                .target("http://localhost:8080/api/auth/login")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(loginRequest, MediaType.APPLICATION_JSON));

        Assertions.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        String token = response.readEntity(String.class);
        Assertions.assertNotNull(token);
    }
}
