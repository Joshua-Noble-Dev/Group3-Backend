package integration;

import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.example.TestApplication;
import org.example.TestConfiguration;
import org.example.models.JobRole;
import org.example.models.LoginRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobRoleIntegrationTest {
    private static final DropwizardAppExtension<TestConfiguration> APP =
            new DropwizardAppExtension<>(TestApplication.class);
    private static String token;

    @BeforeAll
    public static void setUp() {
        Client client = APP.client();

        String adminUsername = System.getenv().get("ADMIN_USERNAME");
        String adminPassword = System.getenv().get("ADMIN_PASSWORD");

        LoginRequest loginRequest = new LoginRequest(adminUsername, adminPassword);

        Response response = client
                .target("http://localhost:8080/api/auth/login")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(loginRequest, MediaType.APPLICATION_JSON));

        token = response.readEntity(String.class);
    }

    @Test
    void getJobRoles_ShouldReturnListOfJobRoles() {
        Client client = APP.client();

        List<JobRole> response = client
                .target("http://localhost:8080/api/job-roles")
                .request()
                .header("Authorization", "Bearer " + token)
                .get(List.class);

        Assertions.assertFalse(response.isEmpty());
    }

    @Test
    void getJobRoleById_shouldReturnJobRole() {
        Client client = APP.client();

        Response response = client
                .target("http://localhost:8080/api/job-roles/1")
                .request()
                .header("Authorization", "Bearer " + token)
                .get();

        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals(1, response.readEntity(JobRole.class).getId());
    }

    @Test
    void getJobRoleById_shouldReturnIDErrorCode404() {
        Client client = APP.client();

        int response = client
                .target("http://localhost:8080/hr/employee/123456")
                .request()
                .header("Authorization", "Bearer " + token)
                .get()
                .getStatus();

        Assertions.assertEquals(404, response);
    }
}
