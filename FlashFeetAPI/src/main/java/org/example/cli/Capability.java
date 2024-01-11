package org.example.cli;

public class Capability {
   private String capability;

    public String getCapability() {
        return capability;
    }

    public void setCapability(String capability) {
        this.capability = capability;
    }

    public Capability(String capability) {
        this.capability = capability;
    }

    @Override
    public String toString() {
        return "Capability: " + this.capability;
    }
}
