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

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Entity;
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

        JobRoleRequest jobRoleRequest = new JobRoleRequest.Builder()
                .roleName("Software Engineer")
                .location("Belfast")
                .capabilityID(1)
                .bandID(1)
                .closingDate(new Date(System.currentTimeMillis()))
                .description("description")
                .responsibilities( "responsibilities")
                .jobSpec("jobSpec")
                .positions(2)
                .build();

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

        JobRoleRequest jobRoleRequest = new JobRoleRequest.Builder()
                .roleName(roleName.repeat(20))
                .location("Belfast")
                .capabilityID(1)
                .bandID(1)
                .closingDate(new Date(System.currentTimeMillis()))
                .description("description")
                .responsibilities( "responsibilities")
                .jobSpec("jobSpec")
                .positions(2)
                .build();

        Client client = APP.client();

        Response response = client
                .target("http://localhost:8080/api/job-roles")
                .request()
                .post(Entity.json(jobRoleRequest));

        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void postJobRoles_PositionsTooSmall_ShouldReturn400() {

        JobRoleRequest jobRoleRequest = new JobRoleRequest.Builder()
                .roleName("Software Engineer")
                .location("Belfast")
                .capabilityID(1)
                .bandID(1)
                .closingDate(new Date(System.currentTimeMillis()))
                .description("description")
                .responsibilities( "responsibilities")
                .jobSpec("jobSpec")
                .positions(-1)
                .build();

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

       JobRoleRequest jobRoleRequest = new JobRoleRequest.Builder()
               .roleName("Software Engineer")
               .location("Belfast")
               .capabilityID(1)
               .bandID(1)
               .closingDate(new Date(System.currentTimeMillis()))
               .description(description.repeat(50))
               .responsibilities( "responsibilities")
               .jobSpec("jobSpec")
               .positions(-1)
               .build();


        Client client = APP.client();

        Response response = client
                .target("http://localhost:8080/api/job-roles")
                .request()
                .post(Entity.json(jobRoleRequest));

        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void postJobRoles_ResponsibitiesTooLong_ShouldReturn400() {

        String responsibility = "responsibilities";

        JobRoleRequest jobRoleRequest = new JobRoleRequest.Builder()
                .roleName("Software Engineer")
                .location("Belfast")
                .capabilityID(1)
                .bandID(1)
                .closingDate(new Date(System.currentTimeMillis()))
                .description("description")
                .responsibilities( responsibility.repeat(20))
                .jobSpec("jobSpec")
                .positions(2)
                .build();


        Client client = APP.client();

        Response response = client
                .target("http://localhost:8080/api/job-roles")
                .request()
                .post(Entity.json(jobRoleRequest));

    }

    @Test
    void postJobRoles_LocationTooLong_ShouldReturn400() {

        String location = "location";

        JobRoleRequest jobRoleRequest = new JobRoleRequest.Builder()
                .roleName("Software Engineer")
                .location(location.repeat(30))
                .capabilityID(1)
                .bandID(1)
                .closingDate(new Date(System.currentTimeMillis()))
                .description("description")
                .responsibilities( "responsibilities")
                .jobSpec("jobSpec")
                .positions(2)
                .build();


        Client client = APP.client();

        Response response = client
                .target("http://localhost:8080/api/job-roles")
                .request()
                .post(Entity.json(jobRoleRequest));

        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void postJobRoles_JobSpecTooLong_ShouldReturn400() {

        String jobSpec = "jobSpec";

        JobRoleRequest jobRoleRequest = new JobRoleRequest.Builder()
                .roleName("Software Engineer")
                .location("Belfast")
                .capabilityID(1)
                .bandID(1)
                .closingDate(new Date(System.currentTimeMillis()))
                .description("description")
                .responsibilities( "responsibilities")
                .jobSpec(jobSpec.repeat(20))
                .positions(2)
                .build();


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
