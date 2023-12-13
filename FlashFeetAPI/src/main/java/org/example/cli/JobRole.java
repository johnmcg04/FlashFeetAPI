package org.example.cli;

public class JobRole {

    public JobRole(){

    }

    private String jobRole;

    private String capability;

    public JobRole(String jobRole, String capability) {
        this.jobRole = jobRole;
        this.capability = capability;
    }

    public String getCapability() {
        return capability;
    }

    public void setCapability(String capability) {
        this.capability = capability;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public JobRole(String jobRole) {
        this.jobRole = jobRole;
    }


    @Override
    public String toString() {
        return "Job Role: " + this.jobRole;
    }
}
