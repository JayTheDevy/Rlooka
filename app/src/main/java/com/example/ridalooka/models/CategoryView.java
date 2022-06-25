package com.example.ridalooka.models;

public class CategoryView {
    private String imageLink;
    private int resLink;

    public CategoryView(String imageLink) {
        this.imageLink = imageLink;
    }

    public CategoryView(int resLink) {
        this.resLink = resLink;
    }

    public CategoryView() {
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public int getResLink() {
        return resLink;
    }

    public void setResLink(int resLink) {
        this.resLink = resLink;
    }
}
