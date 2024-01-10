package org.example.validator;

import org.example.cli.JobRole;

public class JobRoleValidator {

    public boolean isValidJobRole(JobRole jRole){
        if(jRole.getJobRole().length() > 50){
            return false;
        }
        return true;
    }

}
