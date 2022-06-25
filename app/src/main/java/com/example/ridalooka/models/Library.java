package com.example.ridalooka.models;

public class Library {
    private String name;
    private int currentNumberOfGoal;
    private int overallNumberOfGoal;

    public Library() {
    }

    public Library(String name, int currentNumberOfGoal, int overallNumberOfGoal) {
        this.name = name;
        this.currentNumberOfGoal = currentNumberOfGoal;
        this.overallNumberOfGoal = overallNumberOfGoal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
