package it.unicam.cs.followme.app.Instruction;

import it.unicam.cs.followme.app.Robot.Robot;
import it.unicam.cs.followme.app.Simulation.Environment;

public class UntilInstruction implements Instruction{

    private String label;

    public UntilInstruction(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
    @Override
    public void execute(Robot robot, Environment environment) {
        while (true) {
            System.out.println("Executing until loop...");
            boolean isLabelSignaled = robot.isSignaled(label);
            if (isLabelSignaled) {
                break;
            }
            System.out.println("Executing desired action...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
