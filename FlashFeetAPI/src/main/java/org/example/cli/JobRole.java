package org.example.cli;

public class JobRole {

    private String jobRole;

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
