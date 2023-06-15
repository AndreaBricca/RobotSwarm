package it.unicam.cs.followme.app.Area;

public class RectangularArea implements Area{
    private double x;
    private double y;
    private double width;
    private double height;
    private String label;

    public RectangularArea(double x, double y, double width, double height, String label) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.label = label;
    }


    @Override
    public boolean containsPosition(double x, double y) {
        return x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
