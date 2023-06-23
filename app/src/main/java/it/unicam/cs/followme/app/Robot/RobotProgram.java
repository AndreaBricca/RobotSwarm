package it.unicam.cs.followme.app.Robot;

import it.unicam.cs.followme.app.Instruction.Instruction;
import it.unicam.cs.followme.app.Simulation.Environment;

import java.util.ArrayList;
import java.util.List;

public class RobotProgram {

    private List<Instruction> instructions;

    public RobotProgram() {
        this.instructions = new ArrayList<>();
    }

    public void addInstruction(Instruction instruction) {
        instructions.add(instruction);
    }

    public void execute(Robot robot, Environment environment) {
        for (Instruction instruction : instructions) {
            instruction.execute(robot , environment);
        }
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }
}