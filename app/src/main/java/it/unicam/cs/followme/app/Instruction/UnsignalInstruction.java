package it.unicam.cs.followme.app.Instruction;

import it.unicam.cs.followme.app.Robot.Robot;
import it.unicam.cs.followme.app.Simulation.Environment;

public class UnsignalInstruction implements Instruction {
    private String label;


    public UnsignalInstruction(String label) {
        this.label = label;
    }

    @Override
    public void execute(Robot robot, Environment environment) {
        robot.unsignal(label);
    }
    public String getLabel() {
        return label;
    }
}
