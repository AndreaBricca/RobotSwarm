package it.unicam.cs.followme.app.Instruction;

import it.unicam.cs.followme.app.Robot.Robot;
import it.unicam.cs.followme.app.Simulation.Environment;

public class ContinueMovingInstruction implements  Instruction {

    private double duration;

    public ContinueMovingInstruction(double duration) {
        this.duration = duration;
    }

    @Override
    public void execute(Robot robot, Environment environment) {
        robot.continueMoving(duration);
    }
}
