package org.example.validator;

import org.example.cli.JobEntry;

public class JobRoleValidator {

    public boolean isValidJobRole(JobEntry jobRole){
        if(jobRole.getJobRole().length() > 50){
            return false;
        }
        // more validation needed as fields are added further down the tickets
        return true;
    }

}
