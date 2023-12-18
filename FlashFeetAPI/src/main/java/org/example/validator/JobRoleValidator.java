package org.example.validator;

import org.example.cli.JobRole;

public class JobRoleValidator {

    public boolean isValidJobRole(JobRole jRole){
        if(jRole.getJobRole().length() > 50){
            return false;
        }
        // more validation needed as fields are added further down the tickets
        return true;
    }

}
