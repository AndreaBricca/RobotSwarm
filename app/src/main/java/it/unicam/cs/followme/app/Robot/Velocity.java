package it.unicam.cs.followme.app.Robot;



// classe per gestire le componenti x e y come fossero double

public class Velocity {
    private double x;
    private double y;
    private double speed;

    public Velocity(double x, double y) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getSpeed(){
        return speed;
    }
}