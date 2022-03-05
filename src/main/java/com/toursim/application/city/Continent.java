package com.toursim.application.city;

import java.util.NoSuchElementException;

public enum Continent {
    Europe("Europe"),
    Asia("Asia"),
    America("America"),
    Africa("Africa"),
    Australia("Australia");

    private String continent;
    private Continent(String continent){
        this.continent = continent;
    }

    public String getContinent() {
        return continent;
    }

    public Continent fromString(String continent){
        for(Continent c : Continent.values()){
            if(c.getContinent().equalsIgnoreCase(continent)){
                return c;
            }
        }
        throw new NoSuchElementException("Enum type is not allowed");
    }
}
