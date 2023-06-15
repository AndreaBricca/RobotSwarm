package it.unicam.cs.followme.app.Instruction;

import it.unicam.cs.followme.app.Instruction.Instruction;
import it.unicam.cs.followme.app.Robot.Robot;
import it.unicam.cs.followme.app.Simulation.Environment;

public class MoveInstruction implements Instruction {
    private double x;
    private double y;
    private double speed;

    public MoveInstruction(double x, double y, double speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    @Override
    public void execute(Robot robot, Environment environment) {
        robot.moveTo(x, y, speed);
    }
}
