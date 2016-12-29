package com.demo.vjrutnat.sunshine.Items;

/**
 * Created by VjrutNAT on 12/28/2016.
 */

public class Weather {
    private String day;
    private String status;
    private String temperutare_from;
    private String temperutare_to;
    private int id;

    public Weather(String day, String status, String temperutare_from, String temperutare_to, int id) {
        this.day = day;
        this.status = status;
        this.temperutare_from = temperutare_from;
        this.temperutare_to = temperutare_to;
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTemperutare_from() {
        return temperutare_from;
    }

    public void setTemperutare_from(String temperutare_from) {
        this.temperutare_from = temperutare_from;
    }

    public String getTemperutare_to() {
        return temperutare_to;
    }

    public void setTemperutare_to(String temperutare_to) {
        this.temperutare_to = temperutare_to;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
