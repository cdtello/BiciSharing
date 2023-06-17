package com.example.bicisharing.Entities;

public class Event {
    private int id;
    private String name;
    private String description;
    private String image;
    private String time;

    public Event(int id, String name, String description, String image, String time) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.time = time;
    }

    public Event(String name, String description, String image, String time) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
