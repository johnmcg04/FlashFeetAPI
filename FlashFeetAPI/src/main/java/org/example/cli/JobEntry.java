package org.example.cli;

public class JobEntry {

    public JobEntry(){}

    private String jobRole;
    private String jobSpecification;
    private String capability;
    public String getJobRole() {
        return jobRole;
    }
    private String bandLevel;

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

    public String getBandLevel() {
        return bandLevel;
    }

    public void setBandLevel(String bandLevel) {
        this.bandLevel = bandLevel;
    }

    public JobEntry(String jobRole, String jobSpecification, String capability, String bandLevel ) {
        this.jobRole = jobRole;
        this.jobSpecification = jobSpecification;
        this.capability = capability;
        this.bandLevel = bandLevel;
    }

    @Override
    public String toString() {
        return "Job Role: " + this.jobRole +
                ", Job Specification: " + this.jobSpecification +
                ", Capability: " + this.capability +
                ", Band Level: " + this.bandLevel;
    }
}
