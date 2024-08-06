package org.example.validators;

import org.example.exceptions.Entity;
import org.example.exceptions.InvalidException;
import org.example.models.JobRoleRequest;

public class JobRoleValidator {

    public boolean validateJobRole(final JobRoleRequest jobRoleRequest)
            throws InvalidException {

        if (jobRoleRequest.getRoleName().length() > 100) {
            throw new InvalidException(Entity.ROLE,
                    "Role Name greater than 100 characters");
        }

        if (jobRoleRequest.getLocation().length() > 200) {
            throw new InvalidException(Entity.ROLE,
                    "Location greater than 200 characters");
        }

        if (jobRoleRequest.getJobSpec().length() > 100) {
            throw new InvalidException(Entity.ROLE,
                    "Role Name greater than 100 characters");
        }

        if (jobRoleRequest.getDescription().length() > 500) {
            throw new InvalidException(Entity.ROLE,
                    "Description greater than 500 characters");
        }

        if (jobRoleRequest.getResponsibilities().length() > 200) {
            throw new InvalidException(Entity.ROLE,
                    "Responsibilities greater than 200 characters");
        }

        if (jobRoleRequest.getPositions() < 1) {
            throw new InvalidException(Entity.ROLE,
                    "Positions must be 1 or greater");
        }
        return true;

    }

}
