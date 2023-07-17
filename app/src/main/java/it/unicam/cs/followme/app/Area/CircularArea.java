package it.unicam.cs.followme.app.Area;

import java.awt.geom.Point2D;

public class CircularArea implements Area {

    private final double centerX;
    private final double centerY;
    private final double radius;
    private final String label;

    public CircularArea(double centerX, double centerY, double radius, String label) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.label = label;
    }
    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public Point2D.Double getCoordinates() {
        return new Point2D.Double(centerX,centerY);
    }

    public double getRadius() {
        return radius;
    }

    public Point2D.Double getCenterCoordinates() {
        return new Point2D.Double(centerX, centerY);
    }

}
