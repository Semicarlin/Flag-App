package com.example.flags.models;

import android.content.Context;

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

    public int getResourceID(Context context, String packageName) {
        return context.getResources().getIdentifier(
                "flag_" + code.toLowerCase(),
                "drawable",
                packageName
        );
    }
}
