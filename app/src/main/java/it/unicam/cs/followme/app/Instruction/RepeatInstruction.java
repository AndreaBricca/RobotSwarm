package it.unicam.cs.followme.app.Instruction;

import it.unicam.cs.followme.app.Robot.Robot;
import it.unicam.cs.followme.app.Simulation.Environment;

public class RepeatInstruction implements Instruction{

    private int repetitions;

    public RepeatInstruction(int repetitions) {
        this.repetitions = repetitions;
    }
    public int getRepetitions() {
        return repetitions;
    }
    @Override
    public void execute(Robot robot, Environment environment) {
        int counter = 0;

        // Esegui un'azione specifica per n volte
        for (int i = 0; i < repetitions; i++) {
            System.out.println("Executing action: " + (i + 1) + " out of " + repetitions);

            // Esegui l'azione desiderata nel loop
            counter++;
        }
        System.out.println("Final counter value: " + counter);
    }
}
