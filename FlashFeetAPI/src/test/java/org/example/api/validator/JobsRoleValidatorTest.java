package org.example.api.validator;

import org.example.cli.JobRole;
import org.example.validator.JobRoleValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class JobsRoleValidatorTest {
    JobRoleValidator jobRoleValidator = new JobRoleValidator();
    @Test
    void isValidJobRole_shouldReturnTrue_whenValidJobRole() {
        JobRole jRole = new JobRole(
                "software engineer"

        );


        assertTrue(jobRoleValidator.isValidJobRole(jRole));
    }

    @Test
    void isValidJobRole_shouldReturnFalse_whenInvalidJobRole() {
        JobRole jRole = new JobRole(
                "12345678901234567890123456789012345678901234567890123456789012345678901"
        );

        assertFalse(jobRoleValidator.isValidJobRole(jRole));
    }
}