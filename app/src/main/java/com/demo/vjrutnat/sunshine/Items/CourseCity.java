package com.demo.vjrutnat.sunshine.Items;

/**
 * Created by VjrutNAT on 1/3/2017.
 */

public class CourseCity {
    private String nameCity;
    private String id;

    public CourseCity(String nameCity, String id) {
        this.nameCity = nameCity;
        this.id = id;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
