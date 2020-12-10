package com.example.springsocial.model;

public class SimpleLocation {
    
    private String place;
    private String state;
    private String stateAbbr;
    
    public SimpleLocation(String place, String state, String stateAbbr ) {
        this.place = place;
        this.state = state;
        this.stateAbbr = stateAbbr;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateAbbr() {
        return stateAbbr;
    }

    public void setStateAbbr(String stateAbbr) {
        this.stateAbbr = stateAbbr;
    }
    
}
