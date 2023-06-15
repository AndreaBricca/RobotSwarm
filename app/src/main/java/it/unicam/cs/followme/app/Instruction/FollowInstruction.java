package it.unicam.cs.followme.app.Instruction;

import it.unicam.cs.followme.app.Robot.Robot;
import it.unicam.cs.followme.app.Simulation.Environment;

public class FollowInstruction implements Instruction {
    private String label;
    private double distance;
    private double speed;

    public FollowInstruction(String label, double distance, double speed) {
        this.label = label;
        this.distance = distance;
        this.speed = speed;
    }
    @Override
    public void execute(Robot robot, Environment environment) {
        robot.follow(label, distance, speed);
    }
}
