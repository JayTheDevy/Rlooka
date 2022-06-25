package com.example.ridalooka.models.fragment;

public class Library {
    private String category;
    private int currentNumberOfGoal;
    private int overallNumberOfGoal;

    public Library() {
    }

    public Library(String category, int currentNumberOfGoal, int overallNumberOfGoal) {
        this.category = category;
        this.currentNumberOfGoal = currentNumberOfGoal;
        this.overallNumberOfGoal = overallNumberOfGoal;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCurrentNumberOfGoal() {
        return currentNumberOfGoal;
    }

    public void setCurrentNumberOfGoal(int currentNumberOfGoal) {
        this.currentNumberOfGoal = currentNumberOfGoal;
    }

    public int getOverallNumberOfGoal() {
        return overallNumberOfGoal;
    }

    public void setOverallNumberOfGoal(int overallNumberOfGoal) {
        this.overallNumberOfGoal = overallNumberOfGoal;
    }
}
