package com.jobportal.job;

import java.sql.Timestamp;

public class Job {
    private int id;
    private String title;
    private String description;
    private String location;
    private String category;
    private String status;
    private Timestamp postTime;

    // Constructors
    public Job(int id, String title, String description, String location, String category, String status, Timestamp postTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.category = category;
        this.status = status;
        this.postTime = postTime;
    }

    public Job() {

    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getCategory() {
        return category;
    }

    public String getStatus() {
        return status;
    }

    public Timestamp getPostTime() {
        return postTime;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPostTime(Timestamp postTime) {
        this.postTime = postTime;
    }


}
