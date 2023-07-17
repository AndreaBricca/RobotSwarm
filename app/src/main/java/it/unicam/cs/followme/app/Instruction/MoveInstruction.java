package it.unicam.cs.followme.app.Instruction;

import it.unicam.cs.followme.app.Robot.Robot;
import it.unicam.cs.followme.app.Simulation.Environment;


public class MoveInstruction implements Instruction {
    private final double x;
    private final double y;
    private final double speed;

    public MoveInstruction(double x, double y, double speed) {
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

    public double getSpeed() {
        return speed;
    }

    @Override
    public void execute(Robot robot, Environment environment) {
        robot.moveTo(x, y, speed);
    }
}
