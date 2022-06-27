package com.example.ridalooka.models.data;

public class Category {

    private String name;
    private String description;
    private int goal;

    public Category() {
    }

    public Category(String name, String description, int goal) {
        this.name = name;
        this.description = description;
        this.goal = goal;
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

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }
}
