package org.example.validator;

import org.example.cli.JobEntry;

public class JobRoleValidator {

    public boolean isValidJobRole(JobEntry jobRole){
        if(jobRole.getJobRole().length() > 70){
            return false;
        }
        return true;
    }

}
