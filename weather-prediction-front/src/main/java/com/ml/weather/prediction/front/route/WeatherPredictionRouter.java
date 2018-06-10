package com.ml.weather.prediction.front.route;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ml.weather.prediction.domain.PlanetDTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/services")
public class WeatherPredictionRouter {
    private final Gson GSON = new GsonBuilder().create();

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {
        PlanetDTO planetDTO = PlanetDTO.builder().sunDistance(500).angularVelocity(1).build();
        return GSON.toJson(planetDTO);
    }

}