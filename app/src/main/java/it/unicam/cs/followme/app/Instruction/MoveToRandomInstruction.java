package it.unicam.cs.followme.app.Instruction;

import it.unicam.cs.followme.app.Robot.Robot;
import it.unicam.cs.followme.app.Simulation.Environment;

public class MoveToRandomInstruction implements Instruction {
    private final double x1;
    private final double x2;
    private final double y1;
    private final double y2;
    private final double speed;

    public MoveToRandomInstruction(double x1, double x2, double y1, double y2, double speed) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.speed = speed;
    }

    @Override
    public void execute(Robot robot, Environment environment) {
        robot.moveToRandom(x1, x2, y1, y2, speed);
    }
    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }

    public double getY1() {
        return y1;
    }

    public double getY2() {
        return y2;
    }

    public double getSpeed() {
        return speed;
    }
}
