package it.unicam.cs.followme.app.Instruction;

import it.unicam.cs.followme.app.Robot.Robot;
import it.unicam.cs.followme.app.Simulation.Environment;

public class DoForeverInstruction implements Instruction{
    @Override
    public void execute(Robot robot, Environment environment) {
        while (true) {
            System.out.println("Executing forever loop...");
            //condizione di uscita
            int maxIterations = 10;
            int iteration = 0;
            while (iteration < maxIterations) {
                System.out.println("Executing iteration: " + iteration);
                iteration++;
            }
            // Esci dal loop dopo il numero massimo di iterazioni
            break;
        }
    }
}
