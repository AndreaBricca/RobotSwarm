package it.unicam.cs.followme.app.Area;


import java.awt.geom.Point2D;

public interface Area {
    boolean containsPosition(double x, double y);
    String getLabel();
    Point2D.Double getCoordinates();
}
