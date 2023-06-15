package it.unicam.cs.followme.app.Instruction;

import it.unicam.cs.followme.app.Instruction.Instruction;
import it.unicam.cs.followme.app.Robot.Robot;
import it.unicam.cs.followme.app.Simulation.Environment;

public class SignalInstruction implements Instruction {
    private String label;

    public SignalInstruction(String label) {
        this.label = label;
    }


    @Override
    public void execute(Robot robot, Environment environment) {
        robot.signal(label);
    }
}
