package it.unicam.cs.followme.app;


import it.unicam.cs.followme.app.Instruction.FollowInstruction;
import it.unicam.cs.followme.app.Instruction.Instruction;
import it.unicam.cs.followme.app.Parser.RobotProgramParser;
import it.unicam.cs.followme.app.Robot.Robot;
import it.unicam.cs.followme.app.Robot.RobotBase;
import it.unicam.cs.followme.app.Robot.RobotProgram;
import it.unicam.cs.followme.app.Simulation.Environment;
import it.unicam.cs.followme.app.Simulation.EnvironmentLoader;
import it.unicam.cs.followme.app.Instruction.MoveInstruction;

import java.awt.geom.Point2D;
import java.io.IOException;

public class App {
    public static void main(String[] args) {

        String filePath = "C:\\Users\\Bricca\\Desktop\\followme-main\\app\\src\\main\\java\\it\\unicam\\cs\\followme\\app\\input.txt";

        // Carica l'ambiente dal file di input
        Environment environment = EnvironmentLoader.loadEnvironment(filePath);

        // Crea un nuovo robot
        Robot robot = new RobotBase("Robot1", 100, 100, 0);

        // Esegui il programma del robot sull'ambiente
        RobotProgram program = new RobotProgram();
        program.addInstruction(new MoveInstruction(500, 500, 10));
        program.addInstruction(new FollowInstruction("Area1", 50, 5));

        for (Instruction instruction : program.getInstructions()) {
            robot.executeInstruction(instruction, environment);
        }
        // controllo se program contiene istruzioni
        if (!program.getInstructions().isEmpty()) {
            for (Instruction instruction : program.getInstructions()) {
                robot.executeInstruction(instruction, environment);
            }
        } else {
            System.out.println("Il programma non contiene istruzioni.");
        }

        // Osserva il risultato o il comportamento del robot
        System.out.println("Posizione finale del robot: " + robot.getPosition());

    }
}

