package com.ml.weather.prediction.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.awt.geom.Point2D;

import java.lang.Math;

public class GalaxyWeatherHelper {

    public final Gson GSON = new GsonBuilder().create();

    public double calculateDistance(Point2D a, Point2D b) {
        double dx = a.getX() - b.getX();
        double dy = a.getY() - b.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * @link https://stackoverflow.com/questions/2145571/java-calculating-area-of-a-triangle/2145633#2145633
     * @link https://en.wikipedia.org/wiki/Triangle#Using_Heron.27s_formula
     */
    public double calculateTriangleArea(Point2D a, Point2D b, Point2D c) {
        double ab = this.calculateDistance(a, b);
        double bc = this.calculateDistance(c, b);
        double ca = this.calculateDistance(a, c);

        double shape = (ab + bc + ca) / 2;
        return Math.sqrt(shape * (shape - ab) * (shape - bc) * (shape - ca));
    }

    public boolean pointsAreAlineated(Point2D a, Point2D b, Point2D c) {
        return calculateTriangleArea(a, b, c) == 0;
    }

    public double calculateSimpleTriangleArea(Point2D a, Point2D b, Point2D c) {
        return Math.abs(
                    (
                        a.getX() * (b.getY() - c.getY()) +
                        b.getX() * (c.getY() - a.getY()) +
                        c.getX() * (a.getY() - b.getY())
                    ) / 2.0
        );
    }

    /**
     * @link http://www.crazyforcode.com/check-point-lies-triangle/
     */
    public boolean pointIsInsideTriangle(Point2D a, Point2D b, Point2D c, Point2D p) {

        double ABC = calculateSimpleTriangleArea(a, b, c);
        double PBC = calculateSimpleTriangleArea(p, b, c);
        double APC = calculateSimpleTriangleArea(a, p, c);
        double ABP = calculateSimpleTriangleArea(a, b, p);

        return (ABC == PBC + APC + ABP);
    }
}
