package it.unicam.cs.followme.app.Instruction;

import it.unicam.cs.followme.app.Robot.Robot;
import it.unicam.cs.followme.app.Simulation.Environment;

public class WaitInstruction implements Instruction {
    private int seconds;
    public WaitInstruction(int seconds) {
        this.seconds = seconds;
    }
    public int getSeconds() {
        return seconds;
    }

    @Override
    public void execute(Robot robot, Environment environment) {
        try {
        Thread.sleep(seconds * 1000); // Metti in pausa l'esecuzione per il numero di secondi specificato
    } catch (InterruptedException e) {
        e.printStackTrace();
         }
    }
}