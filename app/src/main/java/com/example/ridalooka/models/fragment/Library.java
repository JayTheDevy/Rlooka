package com.example.ridalooka.models.fragment;

import com.example.ridalooka.models.data.Category;

public class Library {
    private String category;
    private int currentNumberOfGoal = 0;
    private int overallNumberOfGoal;
    private Category categoryObj;

    public Library() {
    }

    public Library(String category, int currentNumberOfGoal, int overallNumberOfGoal) {
        this.category = category;
        this.currentNumberOfGoal = currentNumberOfGoal;
        this.overallNumberOfGoal = overallNumberOfGoal;
    }

    public Library(Category categoryObj) {
        category = categoryObj.getName();
        this.overallNumberOfGoal = categoryObj.getGoal();
        this.currentNumberOfGoal = 0;
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
