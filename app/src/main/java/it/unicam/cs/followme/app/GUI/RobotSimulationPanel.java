package it.unicam.cs.followme.app.GUI;

import it.unicam.cs.followme.app.Area.Area;
import it.unicam.cs.followme.app.Area.CircularArea;
import it.unicam.cs.followme.app.Area.RectangularArea;
import it.unicam.cs.followme.app.Robot.Robot;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

public class RobotSimulationPanel extends JPanel {

    private List<Area> areas;
    private List<Robot> robots;

    public RobotSimulationPanel(List<Area> areas, List<Robot> robots) {
        this.areas = areas;
        this.robots = robots;
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //disegna le aree
        if (areas != null) {
            for (int i = areas.size() - 1; i >= 0; i--) {
                Area area = areas.get(i);
                if (area instanceof RectangularArea) {
                    drawRectangularArea((RectangularArea) area, g);
                } else if (area instanceof CircularArea) {
                    drawCircularArea((CircularArea) area, g);
                }
            }
        }

        //disegna i robot
        if (robots != null) {
            for (Robot robot : robots) {
                drawRobot(robot, g);
            }
        }
    }

    private void drawRectangularArea(RectangularArea area, Graphics g) {
        Point2D.Double topLeft = area.getTopLeftCoordinates();
        int x = (int) topLeft.getX();
        int y = (int) topLeft.getY();
        int width = area.getWidth();
        int height = area.getHeight();
        g.drawRect(x, y, width, height);
    }

    private void drawCircularArea(CircularArea area, Graphics g) {
        Point2D.Double center = area.getCenterCoordinates();
        int x = (int) (center.getX() - area.getRadius());
        int y = (int) (center.getY() - area.getRadius());
        int diameter = (int) (area.getRadius() * 2);
        g.drawOval(x, y, diameter, diameter);
    }

    private void drawRobot(Robot robot, Graphics g) {

        Point2D.Double position = robot.getPosition();
        double radius = robot.getRadius();

        int x = (int) (position.getX() - radius);
        int y = (int) (position.getY() - radius);
        int diameter = (int) (radius * 2);

        g.setColor(Color.RED);
        g.fillOval(x, y, diameter, diameter);

        g.setColor(Color.BLACK);
        g.drawString(robot.getLabel(), x + diameter / 2, y + diameter / 2);
    }
}
