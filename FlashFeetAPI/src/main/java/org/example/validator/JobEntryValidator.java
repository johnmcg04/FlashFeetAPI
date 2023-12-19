package org.example.validator;

import org.example.cli.JobEntry;
import org.example.cli.JobEntryRequest;

public class JobEntryValidator {

    public String isValidJobRole(JobEntryRequest jobRole){
        if(jobRole.getJobRole().length() > 50){
            return "The job role is too long, must be below 50 characters";
        }
        if(jobRole.getJobSpecification().length() > 500){
            return "The job specification is too long, must be below 500 characters";
        }
        if(jobRole.getCapability().length() > 50){
            return "The capability is too long, must be below 50 characters";
        }
        if(jobRole.getBandLevel().length() > 50){
            return "The band level is too long, must be below 50 characters";
        }
        if(jobRole.getResponsibilities().length() > 100){
            return "The responsibilities are too long, must be below 100 characters";
        }

        // more validation needed as fields are added further down the tickets
        return null;
    }

}
