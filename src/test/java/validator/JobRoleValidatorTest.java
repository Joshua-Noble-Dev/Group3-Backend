package validator;

import org.example.exceptions.InvalidException;
import org.example.models.JobRoleRequest;
import org.example.validators.JobRoleValidator;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JobRoleValidatorTest {

    JobRoleValidator jobRoleValidator = new JobRoleValidator();

    @Test
    public void validateJobRole_shouldReturnTrue_whenValidJobRole()
            throws InvalidException {

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

        assertTrue(jobRoleValidator.validateJobRole(jobRoleRequest));
    }

    @Test
    public void validateJobRole_shouldReturnInvalidException_whenRoleNameTooLong()
            throws InvalidException {

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

        assertThrows(InvalidException.class,
                () -> jobRoleValidator.validateJobRole(jobRoleRequest));
    }

    @Test
    public void validateJobRole_shouldReturnInvalidException_whenPositionsTooSmall()
            throws InvalidException {

        JobRoleRequest jobRoleRequest = new JobRoleRequest.Builder()
                .roleName("Engineering")
                .location("Belfast")
                .capabilityID(1)
                .bandID(1)
                .closingDate(new Date(System.currentTimeMillis()))
                .description("description")
                .responsibilities( "responsibilities")
                .jobSpec("jobSpec")
                .positions(-1)
                .build();

        assertThrows(InvalidException.class,
                () -> jobRoleValidator.validateJobRole(jobRoleRequest));
    }

    @Test
    public void validateJobRole_shouldReturnInvalidException_whenDescriptionTooLong()
            throws InvalidException {

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
                .positions(2)
                .build();

        assertThrows(InvalidException.class,
                () -> jobRoleValidator.validateJobRole(jobRoleRequest));
    }

    @Test
    public void validateJobRole_shouldReturnInvalidException_whenResponsibilitiesTooLong()
            throws InvalidException {

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

        assertThrows(InvalidException.class,
                () -> jobRoleValidator.validateJobRole(jobRoleRequest));
    }

    @Test
    public void validateJobRole_shouldReturnInvalidException_whenJobSpecTooLong()
            throws InvalidException {

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

        assertThrows(InvalidException.class,
                () -> jobRoleValidator.validateJobRole(jobRoleRequest));
    }

    @Test
    public void validateJobRole_shouldReturnInvalidException_whenLocationTooLong()
            throws InvalidException {

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

        assertThrows(InvalidException.class,
                () -> jobRoleValidator.validateJobRole(jobRoleRequest));
    }



}
