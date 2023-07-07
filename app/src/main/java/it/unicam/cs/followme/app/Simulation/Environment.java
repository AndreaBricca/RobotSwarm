package it.unicam.cs.followme.app.Simulation;


import it.unicam.cs.followme.app.Area.Area;
import it.unicam.cs.followme.app.Robot.Robot;
import it.unicam.cs.followme.app.Robot.RobotBase;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Environment {

    private List<Area> areas;
    private List<Robot> robots;

    public Environment() {
        this.areas = new ArrayList<>();
        this.robots = new ArrayList<>();
    }

    public Environment(List<Area> areas, List<RobotBase> robots) {
        this.areas = new ArrayList<>(areas);
        this.robots = new ArrayList<>();
    }

    public void addArea(Area area) {
        areas.add(area);
    }

    public void removeArea(Area area) {
        areas.remove(area);
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

    public List<Area> getPerceptibleAreas(Point2D.Double position) {
        List<Area> perceptibleAreas = new ArrayList<>();
        for (Area area : areas) {
            if (area.containsPosition(position.getX(), position.getY())) {
                perceptibleAreas.add(area);
            }
        }
        return perceptibleAreas;
    }

    public boolean isPositionInArea(Point2D.Double position) {
        for (Area area : areas) {
            if (area.containsPosition(position.getX(), position.getY())) {
                return true;
            }
        }
        return false;
    }

    public List<Robot> getRobots() {
        return robots;
    }
}



