package com.example.flags;

public class Country {
    private String name;
    private String code;
    private String continent;

    public Country(String name, String code, String continent) {
        this.name = name;
        this.code = code;
        this.continent = continent;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getContinent() {
        return continent;
    }
}
