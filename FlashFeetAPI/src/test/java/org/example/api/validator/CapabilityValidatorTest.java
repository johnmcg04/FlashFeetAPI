package org.example.api.validator;

import org.example.cli.Capability;
import org.example.cli.JobRole;
import org.example.validator.CapabilityValidator;
import org.example.validator.JobRoleValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CapabilityValidatorTest {

     CapabilityValidator capabilityValidator = new CapabilityValidator();
    @Test
    void isValidCapability_shouldReturnTrue_whenValidCapability() {
        Capability capability = new Capability(
                "Engineering"

        );


        assertTrue(capabilityValidator.isValidCapability(capability));
    }

}
