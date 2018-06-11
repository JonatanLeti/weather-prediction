package com.ml.weather.prediction.services;

import com.ml.weather.prediction.domain.PlanetDTO;
import lombok.AllArgsConstructor;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@AllArgsConstructor(onConstructor = @__(@Inject))
public class GalaxyWeatherService {

    private final GalaxyWeatherHelper galaxyWeatherHelper;


    public String getWeatherInDay(Integer day) {
        if (day == null || day < 0) {
            return "ERROR MESSAGE";
        }

        PlanetDTO planetDTO = PlanetDTO.builder().sunDistance(500).angularVelocity(day).build();
        return galaxyWeatherHelper.GSON.toJson(planetDTO);
    }

}
