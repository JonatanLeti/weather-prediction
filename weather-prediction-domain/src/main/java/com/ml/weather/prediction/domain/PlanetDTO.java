package com.ml.weather.prediction.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlanetDTO {

    private final double sunDistance;
    private final double angularVelocity;


}
