package com.hammertime.hammertime2.database;

public class TempReview{
    String description;
    Integer rating;

    TempReview(){}
    TempReview(String description, Integer rating){
        this.description = description;
        this.rating = rating;
    }
    public String getDescription() {
        return description;
    }
    public Integer getRating() {
        return rating;
    }
}