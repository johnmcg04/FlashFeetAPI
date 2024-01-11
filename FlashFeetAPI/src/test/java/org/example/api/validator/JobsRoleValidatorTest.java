package org.example.api.validator;

import org.example.cli.JobEntry;
import org.example.validator.JobRoleValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JobsRoleValidatorTest {
    JobRoleValidator jobRoleValidator = new JobRoleValidator();
    @Test
    void isValidJobRole_shouldReturnTrue_whenValidJobRole() {
        JobEntry jRole = new JobEntry(
                "Business Development Manager",
                "Business Development"
        );
        assertTrue(jobRoleValidator.isValidJobRole(jRole));
    }

    @Test
    void isValidJobRole_shouldReturnFalse_whenInvalidJobRole() {
        JobEntry jRole = new JobEntry(
                "12345678901234567890123456789012345678901234567890123456789012345678901",
                "Business Development"
        );
        assertFalse(jobRoleValidator.isValidJobRole(jRole));
    }
}