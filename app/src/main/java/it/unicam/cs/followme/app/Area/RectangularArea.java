package it.unicam.cs.followme.app.Area;

import java.awt.geom.Point2D;

public class RectangularArea implements Area{
    private final double x;
    private final double y;
    private final double width;
    private final double height;
    private final String label;

    public RectangularArea(double x, double y, double width, double height, String label) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.label = label;
    }
    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public Point2D.Double getCoordinates() {
        return new Point2D.Double(x,y);
    }


    public int getWidth() {
        return (int) width;
    }


    public int getHeight() {
        return (int) height;
    }

    public Point2D.Double getTopLeftCoordinates() {
        return new Point2D.Double(x, y);
    }
}
