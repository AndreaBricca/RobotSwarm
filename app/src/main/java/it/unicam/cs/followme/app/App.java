package it.unicam.cs.followme.app;

import it.unicam.cs.followme.app.Area.Area;
import it.unicam.cs.followme.app.GUI.RobotSimulationGUI;
import it.unicam.cs.followme.app.Robot.RobotBase;
import it.unicam.cs.followme.app.Simulation.Environment;
import it.unicam.cs.followme.app.Simulation.EnvironmentLoader;
import it.unicam.cs.followme.app.Simulation.RobotSimulation;
import java.util.List;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {

        String filePath = "input.txt";

        // Carica le aree dal file di input utilizzando EnvironmentLoader

        List<Area> areas = EnvironmentLoader.loadAreas(filePath);


        // Carica l'ambiente dal file di input
        Environment environment = EnvironmentLoader.loadEnvironment(filePath);

        // Creazione dell'interfaccia grafica
        RobotSimulationGUI gui = new RobotSimulationGUI(areas);
        gui.pack();
        gui.setVisible(true);

        // Crea una simulazione dei robot
        RobotSimulation simulation = new RobotSimulation();

        // Genera un numero specificato di robot casuali
        int numRobots = 20;
        generateRandomRobots(simulation, numRobots, gui.getPanelWidth(), gui.getPanelHeight(), gui, environment);

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



    public static void generateRandomRobots(RobotSimulation simulation, int count, int panelWidth, int panelHeight, RobotSimulationGUI gui,Environment environment) {
        List<RobotBase> robots = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            // Generazione di un robot con posizione casuale
            double x = Math.random() * panelWidth;
            double y = Math.random() * panelHeight;
            RobotBase robot = new RobotBase("Robot " + (i + 1), x, y, 8.0, environment);

            robots.add(robot);
            simulation.addRobot(robot);

            // Aggiungi il robot anche all'interfaccia grafica
            gui.addRobot(robot);
        }
    }
}

