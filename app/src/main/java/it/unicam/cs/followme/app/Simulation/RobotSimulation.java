package it.unicam.cs.followme.app.Simulation;


import it.unicam.cs.followme.app.Robot.Robot;
import it.unicam.cs.followme.app.Robot.RobotBase;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.ArrayList;

public class RobotSimulation implements Simulation {

    private final List<RobotBase> robots;


    public RobotSimulation() {
        this.robots = new ArrayList<>();
    }


    @Override
    public void addRobot(Robot robot) {
        if (robot instanceof RobotBase) {
            robots.add((RobotBase) robot);
        }
    }
    @Override
    public void run(double simulationTime, double timeStep) {

        int numSteps = (int) (simulationTime / timeStep);
        for (int step = 0; step < numSteps; step++) {
            for (Robot robot : robots) {
                // Calcola la nuova posizione del robot
                Point2D.Double currentPosition = robot.getPosition();
                double velocityX = robot.getVelocity().getX();
                double velocityY = robot.getVelocity().getY();
                double newX = currentPosition.getX() + velocityX * timeStep;
                double newY = currentPosition.getY() + velocityY * timeStep;
                robot.moveTo(newX, newY, robot.getVelocity().getSpeed()); // Chiamata corretta al metodo moveTo()
            }
        }
    }
    @Override
    public List<RobotBase> getRobots() {
        return robots;
    }
}
