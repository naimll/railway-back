package com.hekurudhe.app.route.Requests;

import com.hekurudhe.app.route.Entities.Attraction;
import com.hekurudhe.app.route.Entities.Station;

import java.util.List;

public record RouteAdditionRequest(
        String routeName,
        Integer startPoint,
        Integer endPoint,
        List<Station> middlePoints,

        Double distance,
        List<Attraction> attractions) {
}
