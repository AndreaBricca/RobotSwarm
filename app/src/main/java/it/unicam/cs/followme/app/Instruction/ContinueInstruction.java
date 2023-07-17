package it.unicam.cs.followme.app.Instruction;

import it.unicam.cs.followme.app.Robot.Robot;
import it.unicam.cs.followme.app.Simulation.Environment;

public class ContinueInstruction implements Instruction {

    private final double duration;

    public ContinueInstruction(double duration) {
        this.duration = duration;
    }

    public double getDuration (){
        return duration;
    }

    @Override
    public void execute(Robot robot, Environment environment) {
        robot.setContinueDuration(duration);
    }
}
