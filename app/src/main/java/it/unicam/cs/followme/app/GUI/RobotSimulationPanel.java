package it.unicam.cs.followme.app.GUI;

import it.unicam.cs.followme.app.Area.Area;
import it.unicam.cs.followme.app.Area.CircularArea;
import it.unicam.cs.followme.app.Area.RectangularArea;
import it.unicam.cs.followme.app.Robot.Robot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.List;

public class RobotSimulationPanel extends JPanel {

    private final List<Area> areas;
    private final List<Robot> robots;
    private RobotSimulationGUI gui;

    public RobotSimulationPanel(List<Area> areas, List<Robot> robots) {
        this.areas = areas;
        this.robots = robots;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Point2D point = e.getPoint();
                selectRobot(point);
            }
        });
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

    public void selectRobot(Point2D point) {
        for (Robot robot : robots) {
            if (robot.containsPoint(point)) {
                gui.selectRobot(robot); // Chiama il metodo selectRobot dell'istanza di RobotSimulationGUI
                System.out.println("Robot selezionato: " + robot.getLabel());
                return; // Termina il metodo dopo aver selezionato il robot
            }
        }
        System.out.println("Il robot non è stato selezionato correttamente.");
    }

    public void setGUI(RobotSimulationGUI gui) {
        this.gui = gui;
    }
}


