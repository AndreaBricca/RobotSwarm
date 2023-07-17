package it.unicam.cs.followme.app.GUI;

import it.unicam.cs.followme.app.Robot.Robot;

import javax.swing.*;
import java.awt.*;

public class RobotInstructionPanel extends JPanel {
    private final Robot robot;

    public RobotInstructionPanel(Robot robot) {
        this.robot = robot;
        JTextArea instructionTextArea = new JTextArea(5, 20);
        instructionTextArea.setEditable(true);

        setLayout(new BorderLayout());
        add(new JLabel(robot.getLabel()), BorderLayout.NORTH);
        add(new JScrollPane(instructionTextArea), BorderLayout.CENTER);
    }
    public Robot getRobot() {
        return robot;
    }
}
