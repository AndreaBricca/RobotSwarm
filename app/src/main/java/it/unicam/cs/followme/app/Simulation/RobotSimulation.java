package it.unicam.cs.followme.app.Simulation;

import it.unicam.cs.followme.app.Instruction.Instruction;
import it.unicam.cs.followme.app.Robot.Robot;
import it.unicam.cs.followme.app.Area.Area;
import it.unicam.cs.followme.app.Robot.RobotBase;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.ArrayList;

public class RobotSimulation implements Simulation {

    private List<Robot> robots;
    private List<Area> areas;


    public RobotSimulation() {
        this.robots = new ArrayList<>();
        this.areas = new ArrayList<>();
    }


    @Override
    public void addRobot(Robot robot) {
        robots.add(robot);
    }

    @Override
    public void removeRobot(Robot robot) {
        robots.remove(robot);
    }

    @Override
    public void run(double time, double dt) {
        int steps = (int) (time / dt);

        for (int i = 0; i < steps; i++) {
            for (Robot robot : robots) {
                // Percepisce l'ambiente
                Environment environment = perceiveEnvironment(robot);

                // Gestisce le istruzioni del robot
                Instruction currentInstruction = robot.getCurrentInstruction();
                if (currentInstruction != null) {
                    currentInstruction.execute(robot, environment);
                    robot.incrementInstructionIndex();

                    // Se tutte le istruzioni sono state eseguite, resetta l'indice di instruzioni
                    if (robot.getInstructionIndex() >= robot.getInstructions().size()) {
                        robot.resetInstructionIndex();
                    }
                }
            }
        }

    }


    @Override
    public Environment perceiveEnvironment(Robot robot) {

        // Ottenere la posizione corrente del robot
        Point2D.Double currentPosition = robot.getPosition();

        // Creare una lista per memorizzare le aree percepibili dal robot
        List<Area> perceptibleAreas = new ArrayList<>();

        for (Area area : areas) {
            // Verificare se la posizione corrente del robot è contenuta nell'area
            if (area.containsPosition(currentPosition.getX(), currentPosition.getY())) {
                // Aggiungere l'area alla lista delle aree percepibili
                perceptibleAreas.add(area);
            }
        }

        // Creare un oggetto Environment contenente le aree percepibili
        Environment environment = new Environment(perceptibleAreas);

        return environment;
    }

    public static List<RobotBase> generateRandomRobots(int count) {
        List<RobotBase> robots = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            // Generazione di un robot con posizione casuale
            double x = Math.random() * 100;  // Genera una coordinata x casuale tra 0 e 100
            double y = Math.random() * 100;  // Genera una coordinata y casuale tra 0 e 100
            double speed = Math.random() * 10;  // Genera una velocità casuale tra 0 e 10
            RobotBase robot = new RobotBase("Robot " + (i + 1), x, y, speed);
            robots.add(robot);
        }
        return robots;
    }

    @Override
    public List<Robot> getRobots() {
        return robots;
    }
}
