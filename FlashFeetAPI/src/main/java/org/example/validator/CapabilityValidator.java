package org.example.validator;

import org.example.cli.Capability;

public class CapabilityValidator {

    public boolean isValidCapability(Capability capability){
        return capability.getCapability().length() <= 70;
        // more validation needed as fields are added further down the tickets
    }

}
