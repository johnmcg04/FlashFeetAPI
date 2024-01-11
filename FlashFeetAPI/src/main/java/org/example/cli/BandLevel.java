package org.example.cli;

public class BandLevel {
    private String bandLevel;

    public String getBandLevel() {
        return bandLevel;
    }

    public void setBandLevel(String bandLevel) {
        this.bandLevel = bandLevel;
    }

    public BandLevel(String bandLevel) {
        this.bandLevel = bandLevel;
    }

    @Override
    public String toString() {
        return "Band Level: " + this.bandLevel;
    }
}
