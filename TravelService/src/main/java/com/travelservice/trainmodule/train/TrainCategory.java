package com.travelservice.trainmodule.train;

public enum TrainCategory {


    FAST ("Fast"),
    REGULAR("Regular");

    private final String category;

    TrainCategory(String category) {
        this.category=category;
    }

}
