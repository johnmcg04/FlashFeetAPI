package org.example.cli;

public class JobEntry {

    public JobEntry(){}

    private String jobRole;
    private String jobSpecification;
    private String capability;
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

    public String getCapability() {
        return capability;
    }

    public void setCapability(String capability) {
        this.capability = capability;
    }

    public JobEntry(String jobRole, String jobSpecification, String capability ) {
        this.jobRole = jobRole;
        this.jobSpecification = jobSpecification;
        this.capability = capability;
    }

    @Override
    public String toString() {
        return "Job Role: " + this.jobRole + ", Job Specification: " + this.jobSpecification + ", Capability: " + this.capability;
    }
}
