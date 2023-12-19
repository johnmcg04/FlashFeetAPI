package org.example.api.validator;

import org.example.cli.JobEntry;
import org.example.cli.JobEntryRequest;
import org.example.validator.JobEntryValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JobsRoleValidatorTest {
    JobEntryValidator jobRoleValidator = new JobEntryValidator();
    @Test
    void isValidJobRole_shouldReturnTrue_whenValidJobRole() {
        JobEntryRequest jRole = new JobEntryRequest(
                "Business Development Manager",
                "Business Development",
                "Business Development and Market",
                "Manager",
                "Develop and implement business development strategies"

        );


        assertNull(jobRoleValidator.isValidJobRole(jRole));
    }
}