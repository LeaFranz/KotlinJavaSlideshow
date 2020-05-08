package com.mobappdev.javaslideshow.model;

import java.time.LocalDate;
import java.util.Comparator;

public class Slide{

    private int id = 0;
    private String title = "";
    private String imageName = "";
    private String gps = "";
    private int timestamp = 0;
    private String description="";

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageName() {
        return imageName;
    }
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getGps() {
        return gps;
    }
    public void setGps(String gps) {
        this.gps = gps;
    }

    public int getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString(){
        return "Slide number " + this.id + " " + this.title;
    }
}
