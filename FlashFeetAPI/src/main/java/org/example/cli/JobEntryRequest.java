package org.example.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class JobEntryRequest {

    private String jobRole;
    private String jobSpecification;
    private String capability;
    public String getJobRole() {
        return jobRole;
    }
    private String bandLevel;


    private String responsibilities;

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



    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    @JsonCreator
    public JobEntryRequest(
            @JsonProperty("jobRole") String jobRole,
            @JsonProperty("jobSpecification") String jobSpecification,
            @JsonProperty("capability") String capability,
            @JsonProperty("bandLevel") String bandLevel,
            @JsonProperty("responsibilities") String responsibilities
            ){
        this.jobRole= jobRole;
        this.jobSpecification = jobSpecification;
        this.capability = capability;
        this.bandLevel = bandLevel;
        this.responsibilities = responsibilities;
    }
}
