package it.unicam.cs.followme.app.Simulation;

import it.unicam.cs.followme.app.Instruction.Instruction;
import it.unicam.cs.followme.app.Robot.Robot;
import it.unicam.cs.followme.app.Area.Area;
import it.unicam.cs.followme.app.Robot.RobotBase;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.ArrayList;

public class RobotSimulation implements Simulation {

    private List<RobotBase> robots;
    private List<Area> areas;


    public RobotSimulation() {
        this.robots = new ArrayList<>();
        this.areas = new ArrayList<>();
    }


    @Override
    public void addRobot(Robot robot) {
        if (robot instanceof RobotBase) {
            robots.add((RobotBase) robot);
        }
    }

    @Override
    public void removeRobot(Robot robot) {
        if (robot instanceof RobotBase) {
            robots.remove((RobotBase) robot);
        }
    }

    @Override
    public void run(double simulationTime, double timeStep) {
        int numSteps = (int) (simulationTime / timeStep);
        for (int step = 0; step < numSteps; step++) {
            for (Robot robot : robots) { // Modifica il tipo di dato in RobotBase
                // Calcola la nuova posizione del robot
                Point2D.Double currentPosition = robot.getPosition();
                double velocityX = robot.getVelocity().getX();
                double velocityY = robot.getVelocity().getY();
                double newX = currentPosition.getX() + velocityX * timeStep;
                double newY = currentPosition.getY() + velocityY * timeStep;
                robot.moveTo(newX, newY, robot.getVelocity().getSpeed()); // Chiamata corretta al metodo moveTo()

                // Aggiorna lo stato del robot (se necessario)
                // ...

                // Esegui altre operazioni specifiche per la simulazione del robot
                // ...

                // Puoi anche controllare le collisioni tra i robot qui
                // ...

                // Puoi aggiungere altre logiche di simulazione qui
                // ...
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
        Environment environment = new Environment(perceptibleAreas, robots);

        return environment;
    }
    @Override
    public List<RobotBase> getRobots() {
        return robots;
    }
}
