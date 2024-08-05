package integration;

import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.example.TestApplication;
import org.example.TestConfiguration;
import org.example.models.JobRole;
import org.example.models.JobRoleRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.StringUtils;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class JobRoleIntegrationTest {
    private static final DropwizardAppExtension<TestConfiguration> APP =
            new DropwizardAppExtension<>(TestApplication.class);

    @Test
    void getJobRoles_ShouldReturnListOfJobRoles() {
        Client client = APP.client();

        List<JobRole> response = client
                .target("http://localhost:8080/api/job-roles")
                .request()
                .get(List.class);

        Assertions.assertFalse(response.isEmpty());
    }

    @Test
    void postJobRoles_ShouldReturnIdOfJobRole() {

        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Software Engineer",
                "Belfast",
                1,
                1,
                new Date(System.currentTimeMillis()),

                "description",
                "responsibilities",
                "jobSpec",
                2

        );

        Client client = APP.client();

        int id = client
                .target("http://localhost:8080/api/job-roles")
                .request()
                .post(Entity.json(jobRoleRequest))
                .readEntity((Integer.class));

        Assertions.assertTrue(id > 0);
    }

    @Test
    void postJobRoles_RoleNameTooLong_ShouldReturn400() {

        String roleName = "Engineer";

        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                roleName.repeat(20),
                "Belfast",
                1,
                1,
                new Date(System.currentTimeMillis()),

                "description",
                "responsibilities",
                "jobSpec",
                2

        );

        Client client = APP.client();

        Response response = client
                .target("http://localhost:8080/api/job-roles")
                .request()
                .post(Entity.json(jobRoleRequest));

        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void postJobRoles_PositionsTooSmall_ShouldReturn400() {

        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Engineer",
                "Belfast",
                1,
                1,
                new Date(System.currentTimeMillis()),

                "description",
                "responsibilities",
                "jobSpec",
                -1

        );

        Client client = APP.client();

        Response response = client
                .target("http://localhost:8080/api/job-roles")
                .request()
                .post(Entity.json(jobRoleRequest));

        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void postJobRoles_DescriptionTooLong_ShouldReturn400() {

        String description = "description";

        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Engineer",
                "Belfast",
                1,
                1,
                new Date(System.currentTimeMillis()),

                description.repeat(20),
                "responsibilities",
                "jobSpec",
                2

        );

        Client client = APP.client();

        Response response = client
                .target("http://localhost:8080/api/job-roles")
                .request()
                .post(Entity.json(jobRoleRequest));

        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void postJobRoles_ResponsibilitiesTooLong_ShouldReturn400() {

        String responsibilities = "responsibilities";

        JobRoleRequest jobRoleRequest = new JobRoleRequest(
                "Engineer",
                "Belfast",
                1,
                1,
                new Date(System.currentTimeMillis()),

                "description",
                responsibilities.repeat(20),
                "jobSpec",
                2

        );

        Client client = APP.client();

        Response response = client
                .target("http://localhost:8080/api/job-roles")
                .request()
                .post(Entity.json(jobRoleRequest));

        Assertions.assertEquals(400, response.getStatus());
    }




    @Test
    void getJobRoleById_shouldReturnJobRole() {
        Client client = APP.client();

        Response response = client
                .target("http://localhost:8080/api/job-roles/1")
                .request()
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
                .get()
                .getStatus();

        Assertions.assertEquals(404, response);
    }
}
