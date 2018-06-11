package com.ml.weather.prediction.front.route;

import com.google.inject.Inject;
import com.ml.weather.prediction.services.GalaxyWeatherService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/services")
public class WeatherPredictionRouter {

    private final GalaxyWeatherService galaxyWeatherService;

    @Inject
    public WeatherPredictionRouter(GalaxyWeatherService galaxyWeatherService) {
        this.galaxyWeatherService = galaxyWeatherService;
    }

    /**
     * curl example:
     *              http://localhost:9290/services/weather?day=256
     */

    @GET
    @Path("/weather")
    @Produces(MediaType.APPLICATION_JSON)
    public String getWeatherInDay(@PathParam("day") Integer day){
        return galaxyWeatherService.getWeatherInDay(day);
    }

}