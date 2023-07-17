package it.unicam.cs.followme.app.Instruction;

import it.unicam.cs.followme.app.Robot.Robot;
import it.unicam.cs.followme.app.Simulation.Environment;

public class WaitInstruction implements Instruction {
    private final int seconds;
    public WaitInstruction(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public void execute(Robot robot, Environment environment) throws InterruptedException {
        Thread.sleep(seconds * 1000); // Metti in pausa l'esecuzione per il numero di secondi specificato
    }
}