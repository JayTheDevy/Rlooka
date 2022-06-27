package com.example.ridalooka.models.data;

public class Car {
    private String category;
    private String description;
    private String name;
    private String imgUrl;

    public Car() {}

    public Car(String category, String description, String name, String imgUrl) {
        this.category = category;
        this.description = description;
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
