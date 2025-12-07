package com.example.countryroutefinder.model;

import java.util.List;

public class Country {
    private String cca3;
    private List<String> borders;

    // Getter for cca3
    public String getCca3() {
        return cca3;
    }

    // Setter for cca3
    public void setCca3(String cca3) {
        this.cca3 = cca3;
    }

    // Getter for borders
    public List<String> getBorders() {
        return borders;
    }

    // Setter for borders
    public void setBorders(List<String> borders) {
        this.borders = borders;
    }
}