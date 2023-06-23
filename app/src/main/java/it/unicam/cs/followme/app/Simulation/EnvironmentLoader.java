package it.unicam.cs.followme.app.Simulation;

import it.unicam.cs.followme.app.Area.Area;
import it.unicam.cs.followme.app.Area.CircularArea;
import it.unicam.cs.followme.app.Area.RectangularArea;
import it.unicam.cs.followme.app.Robot.Robot;
import it.unicam.cs.followme.app.Robot.RobotBase;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EnvironmentLoader {
    public static Environment loadEnvironment(String filePath) {
        // Carica le informazioni dell'ambiente da una sorgente esterna
        List<Area> areas = loadAreas(filePath);
        List<Robot> robots = createRobots();

        // Crea un'istanza di Environment con le aree e i robot caricate
        Environment environment = new Environment(areas);
        for (Robot robot : robots) {
            environment.addRobot(robot);
        }

        return environment;
    }

    private static List<Area> loadAreas(String filePath) {
        List<Area> areas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(" ");
                String areaType = tokens[0];

                if (areaType.equals("CIRCULAR")) {
                    double x = Double.parseDouble(tokens[1]);
                    double y = Double.parseDouble(tokens[2]);
                    double radius = Double.parseDouble(tokens[3]);
                    String label = tokens[4];
                    Area circularArea = new CircularArea(x, y, radius, label);
                    areas.add(circularArea);
                } else if (areaType.equals("RECTANGULAR")) {
                    double x1 = Double.parseDouble(tokens[1]);
                    double y1 = Double.parseDouble(tokens[2]);
                    double x2 = Double.parseDouble(tokens[3]);
                    double y2 = Double.parseDouble(tokens[4]);
                    String label = tokens[5];
                    Area rectangularArea = new RectangularArea(x1, y1, x2, y2, label);
                    areas.add(rectangularArea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return areas;
    }

    private static List<Robot> createRobots() {
        List<Robot> robots = new ArrayList<>();

        // Creazione manuale delle istanze dei robot
        RobotBase robot1 = new RobotBase("Robot1", 0, 0, 0);
        RobotBase robot2 = new RobotBase("Robot2", 1, 1, 90);
        // Aggiungi i robot alla lista
        robots.add(robot1);
        robots.add(robot2);

        return robots;
    }

}


