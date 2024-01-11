package org.example.api.validator;

import org.example.cli.JobRole;
import org.example.validator.JobRoleValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class JobsRoleValidatorTest {
    JobRoleValidator jobRoleValidator = new JobRoleValidator();
    @Test
    void isValidJobRole_shouldReturnTrue_whenValidJobRole() {
        JobRole jRole = new JobRole(
                "software engineer"

        );


        assertTrue(jobRoleValidator.isValidJobRole(jRole));
    }
}