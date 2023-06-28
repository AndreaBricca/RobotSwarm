package it.unicam.cs.followme.app;

import it.unicam.cs.followme.app.Area.Area;
import it.unicam.cs.followme.app.Instruction.FollowInstruction;
import it.unicam.cs.followme.app.Instruction.Instruction;
import it.unicam.cs.followme.app.Instruction.MoveInstruction;
import it.unicam.cs.followme.app.Instruction.SignalInstruction;
import it.unicam.cs.followme.app.Parser.RobotProgramParser;
import it.unicam.cs.followme.app.Robot.RobotBase;
import it.unicam.cs.followme.app.Robot.RobotProgram;
import it.unicam.cs.followme.app.Simulation.Environment;
import it.unicam.cs.followme.app.Simulation.EnvironmentLoader;
import it.unicam.cs.followme.app.Simulation.RobotSimulation;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Bricca\\Desktop\\followme-main\\app\\src\\main\\java\\it\\unicam\\cs\\followme\\app\\input.txt";

        // Carica l'ambiente dal file di input
        Environment environment = EnvironmentLoader.loadEnvironment(filePath);

        // Crea una simulazione dei robot
        RobotSimulation simulation = new RobotSimulation();

        // Genera un numero specificato di robot casuali
        int numRobots = 5;
        List<RobotBase> randomRobots = RobotSimulation.generateRandomRobots(numRobots);
        for (RobotBase robot : randomRobots) {
            simulation.addRobot(robot);
        }

        // Esegui la simulazione dei robot
        double simulationTime = 10.0;  // Tempo totale di simulazione (in secondi)
        double timeStep = 0.1;  // Intervallo di tempo per ogni passo di simulazione (in secondi)
        simulation.run(simulationTime, timeStep);

        // Stampa le informazioni sulle aree create
        System.out.println("Aree create nel file di input:");
        for (Area area : environment.getAreas()) {
            System.out.println("Label dell'area: " + area.getLabel());
            System.out.println("Coordinate dell'area: " + area.getCoordinates());
        }
    }
}
