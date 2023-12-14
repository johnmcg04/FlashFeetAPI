package org.example.cli;

public class JobEntry {

    public JobEntry(){}

    private String jobRole;
    private String jobSpecification;
    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public String getJobSpecification() {
        return jobSpecification;
    }

    public void setJobSpecification(String jobSpecification) {
        this.jobSpecification = jobSpecification;
    }


    public JobEntry(String jobRole, String jobSpecification) {
        this.jobRole = jobRole;
        this.jobSpecification = jobSpecification;
    }

    @Override
    public String toString() {
        return "Job Role: " + this.jobRole + ", Job Specification: " + this.jobSpecification;
    }
}
