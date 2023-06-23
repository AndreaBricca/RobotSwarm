package it.unicam.cs.followme.app.Instruction;

import it.unicam.cs.followme.app.Robot.Robot;
import it.unicam.cs.followme.app.Simulation.Environment;

public class DoneInstruction implements Instruction{
    @Override
    public void execute(Robot robot, Environment environment) {
        System.out.println("Done command executed");
    }
}
