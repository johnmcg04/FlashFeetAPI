package org.example.cli;

public class JobEntry {

    public JobEntry(){}
    private String jobRole;
    private String jobSpecification;
    private String capability;
    private String bandLevel;
    private String jobFamily;
    private String responsibilities;
    private String jobSpecSummary;

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

    public String getBandLevel() {
        return bandLevel;
    }

    public void setBandLevel(String bandLevel) {
        this.bandLevel = bandLevel;
    }
    public String getJobFamily() { return jobFamily; }

    public void setJobFamily(String jobFamily) {
        this.jobFamily = jobFamily;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }
    public String getJobSpecSummary() { return jobSpecSummary; }
    public void setJobSpecSummary(String jobSpecSummary) { this.jobSpecSummary = jobSpecSummary; }

    public JobEntry(String jobRole, String jobSpecification, String capability, String bandLevel, String jobFamily, String responsibilities, String jobSpecSummary ) {
        this.jobRole = jobRole;
        this.jobSpecification = jobSpecification;
        this.capability = capability;
        this.bandLevel = bandLevel;
        this.jobFamily = jobFamily;
        this.responsibilities = responsibilities;
        this.jobSpecSummary = jobSpecSummary;
    }

    @Override
    public String toString() {
        return "Job Role: " + this.jobRole +
                ", Job Specification: " + this.jobSpecification +
                ", Capability: " + this.capability +
                ", Band Level: " + this.bandLevel +
                ", Job Family" + this.jobFamily +
                ", Responsibilities: " + this.responsibilities +
                ", Job Specificaton Summary" + this.jobSpecSummary;
    }
}
