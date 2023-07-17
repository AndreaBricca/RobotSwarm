package it.unicam.cs.followme.app.Instruction;

import it.unicam.cs.followme.app.Robot.Robot;
import it.unicam.cs.followme.app.Simulation.Environment;

public class FollowInstruction implements Instruction {
    private final String label;
    private final double distance;
    private final double speed;

    public FollowInstruction(String label, double distance, double speed) {
        this.label = label;
        this.distance = distance;
        this.speed = speed;
    }
    @Override
    public void execute(Robot robot, Environment environment) {
        robot.follow(label, distance, speed);
    }

    public double getDistance(){
        return distance;
    }

    public String getLabel(){
        return label;
    }

    public double getSpeed() {
        return speed;
    }
}
