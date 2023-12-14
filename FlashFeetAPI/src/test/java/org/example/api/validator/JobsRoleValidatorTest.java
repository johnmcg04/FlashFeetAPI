package org.example.api.validator;

import org.example.cli.JobEntry;
import org.example.validator.JobRoleValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class JobsRoleValidatorTest {
    JobRoleValidator jobRoleValidator = new JobRoleValidator();
    @Test
    void isValidJobRole_shouldReturnTrue_whenValidJobRole() {
        JobEntry jRole = new JobEntry(
                "Business Development Manager",
                "Business Development",
                "Business Development and Market"
        );


        assertTrue(jobRoleValidator.isValidJobRole(jRole));
    }
}