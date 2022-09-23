package com.travelservice.trainmodule.seat;



public enum SeatCategory {

    FIRSTCLASS ("First Class"),
    REGULAR("Regular");

    private final String category;

    SeatCategory(String category) {
        this.category=category;
    }

}
