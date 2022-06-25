package com.example.ridalooka.models.data;

public class Car {
    private String category;
    private String description;
    private long date;

    public Car() {}

    public Car(String category, String description, long date) {
        this.category = category;
        this.description = description;
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
