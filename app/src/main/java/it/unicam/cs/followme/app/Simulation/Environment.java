package it.unicam.cs.followme.app.Simulation;


import it.unicam.cs.followme.app.Area.Area;
import it.unicam.cs.followme.app.Robot.Robot;
import it.unicam.cs.followme.app.Robot.RobotBase;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Environment {

    private final List<Area> areas;
    private final List<Robot> robots;

    public Environment() {
        this.areas = new ArrayList<>();
        this.robots = new ArrayList<>();
    }

    public Environment(List<Area> areas, List<RobotBase> robots) {
        this.areas = new ArrayList<>(areas);
        this.robots = new ArrayList<>();
    }
    public List<Area> getAreas() {
        return areas;
    }

    public void addRobot(Robot robot) {
        robots.add(robot);
    }

    public Point2D.Double getRobotPosition(String label) {
        for (Robot robot : robots) {
            if (robot.getLabel().equals(label)) {
                return robot.getPosition();
            }
        }
        return null;
    }
}



