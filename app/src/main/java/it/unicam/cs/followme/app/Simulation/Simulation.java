package it.unicam.cs.followme.app.Simulation;

import it.unicam.cs.followme.app.Robot.Robot;
import it.unicam.cs.followme.app.Robot.RobotBase;

import java.util.List;


public interface Simulation {
    void addRobot(Robot robot);
    void run(double time, double dt);
    List<RobotBase> getRobots();

}

