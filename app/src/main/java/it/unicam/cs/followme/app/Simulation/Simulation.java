package it.unicam.cs.followme.app.Simulation;

import it.unicam.cs.followme.app.Robot.Robot;

import java.util.List;


public interface Simulation {
    void addRobot(Robot robot);
    void removeRobot(Robot robot);
    void run(double time, double dt);
    List<Robot> getRobots();

}
