package it.unicam.cs.followme.app.Instruction;

import it.unicam.cs.followme.app.Robot.Robot;
import it.unicam.cs.followme.app.Simulation.Environment;

public class SignalInstruction implements Instruction {
    private final String label;

    public SignalInstruction(String label) {
        this.label = label;
    }


    @Override
    public void execute(Robot robot, Environment environment) {
        robot.signal(label);
    }

    public String getLabel() {
        return label;
    }
}
