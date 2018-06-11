package com.ml.weather.prediction.main.job;

import com.ml.weather.prediction.domain.PlanetDTO;
import com.ml.weather.prediction.services.GalaxyWeatherHelper;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.awt.geom.Point2D;

@Singleton
public class GalaxyWeatherPredictionJob {

    private final GalaxyWeatherHelper galaxyWeatherHelper;
    private final int yearsToPredict;
    private final int daysPerYear;
    private final int precisionInPositionScale;

    @Inject
    public GalaxyWeatherPredictionJob(GalaxyWeatherHelper galaxyWeatherHelper,
                                      @Named("job.galaxy.weather.prediction.in.years") int yearsToPredict,
                                      @Named("job.galaxy.weather.prediction.days.per.year") int daysPerYear,
                                      @Named("precision.in.position.scale") int precisionInPositionScale) {

        this.galaxyWeatherHelper = galaxyWeatherHelper;
        this.yearsToPredict = yearsToPredict;
        this.daysPerYear = daysPerYear;
        this.precisionInPositionScale = precisionInPositionScale;
    }

    public void run() {
        int totalDaysToCalculate = yearsToPredict * daysPerYear;

        final PlanetDTO FERENGI = new PlanetDTO(500, -1);
        final PlanetDTO VULCANO = new PlanetDTO(1000, 5);
        final PlanetDTO BETASOIDE = new PlanetDTO(2000, -3);
        final Point2D sunPosition = new Point2D.Double(0, 0);

        for (int day = 0; day <= totalDaysToCalculate; day++) {
            Point2D ferengiPosition = FERENGI.getPositionInDayAprox(day);
            Point2D vulcanoPosition = VULCANO.getPositionInDayAprox(day);
            Point2D betasoide = BETASOIDE.getPositionInDayAprox(day);

            boolean planetsAreAligned = galaxyWeatherHelper.pointsAreAlineated(ferengiPosition, vulcanoPosition, betasoide);
            boolean twoPlanetsAreAlignedWithSun = galaxyWeatherHelper.pointsAreAlineated(sunPosition, ferengiPosition, vulcanoPosition);
            boolean sunIsInsidePlanetsArea = galaxyWeatherHelper.pointIsInsideTriangle(ferengiPosition, vulcanoPosition, betasoide, sunPosition);


        }
    }


}
