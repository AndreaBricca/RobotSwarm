package it.unicam.cs.followme.app.Area;

import java.awt.geom.Point2D;

public class CircularArea implements Area {

    private double centerX;
    private double centerY;
    private double radius;
    private String label;

    public CircularArea(double centerX, double centerY, double radius, String label) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.label = label;
    }


    @Override
    public boolean containsPosition(double x, double y) {
        double distance = Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2));
        return distance <= radius;
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
