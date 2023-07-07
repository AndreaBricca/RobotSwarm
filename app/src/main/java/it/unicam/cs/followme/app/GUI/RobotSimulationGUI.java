package it.unicam.cs.followme.app.GUI;

import it.unicam.cs.followme.app.Area.Area;
import it.unicam.cs.followme.app.Area.CircularArea;
import it.unicam.cs.followme.app.Area.RectangularArea;
import it.unicam.cs.followme.app.Robot.Robot;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class RobotSimulationGUI extends JFrame {

    private List<Area> areas;
    private List<Robot> robots;
    private RobotSimulationPanel panel;

    public RobotSimulationGUI(List<Area> areas) {
        this.areas = areas;
        this.robots = new ArrayList<>();

        setTitle("Robot Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 700));

        panel = new RobotSimulationPanel(areas, robots);
        getContentPane().add(panel);

        pack();
        setVisible(true);
    }

    // Metodo per aggiungere un robot
    public void addRobot(Robot robot) {
        robots.add(robot);
        panel.revalidate(); // Aggiorna il pannello per visualizzare il nuovo robot
    }

    // Metodo per rimuovere un robot
    public void removeRobot(Robot robot) {
        robots.remove(robot);
        panel.repaint();
    }

    public int getPanelWidth() {
        return panel.getWidth();
    }

    public int getPanelHeight() {
        return panel.getHeight();
    }
}