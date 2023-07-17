package it.unicam.cs.followme.app.GUI;

import it.unicam.cs.followme.app.Area.Area;
import it.unicam.cs.followme.app.Robot.Robot;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;


public class RobotSimulationGUI extends JFrame {

    private final List<Robot> robots;
    private final RobotSimulationPanel panel;
    private final JPanel robotInstructionsPanel;
    private Robot selectedRobot; // variabile per tenere traccia del robot selezionato


    public RobotSimulationGUI(List<Area> areas) {
        this.robots = new ArrayList<>();

        setTitle("Robot Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 700));

        panel = new RobotSimulationPanel(areas, robots);
        panel.setGUI(this);
        getContentPane().add(panel);

        pack();
        setVisible(true);

        robotInstructionsPanel = new JPanel();
        robotInstructionsPanel.setLayout(new BoxLayout(robotInstructionsPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(robotInstructionsPanel);
        scrollPane.setPreferredSize(new Dimension(200, 600));

        getContentPane().add(scrollPane, BorderLayout.EAST);

        JButton addInstructionButton = new JButton("Aggiungi istruzioni");

        addInstructionButton.addActionListener(e -> {

            // Verifica se è stato selezionato un robot
            if (selectedRobot != null) {
                System.out.println("Pulsante 'Aggiungi istruzioni' premuto");

                // Creazione di un InputDialog personalizzato per l'inserimento delle istruzioni
                String instructions = JOptionPane.showInputDialog(this, "Inserisci le istruzioni per il robot:", "Aggiungi istruzioni", JOptionPane.PLAIN_MESSAGE);

                // Verifica se l'utente ha inserito delle istruzioni
                if (instructions != null && !instructions.isEmpty()) {
                    System.out.println("Istruzioni inserite: " + instructions);

                    // Assegna le istruzioni al robot
                    selectedRobot.setInstructions(instructions);

                    // Aggiunta delle istruzioni al RobotInstructionPanel
                    RobotInstructionPanel instructionPanel = new RobotInstructionPanel(selectedRobot);
                    addRobotInstructionPanel(instructionPanel);
                }
            } else {
                System.out.println("Nessun robot selezionato");
            }
        });

        getContentPane().add(addInstructionButton, BorderLayout.SOUTH);
    }

    // Metodo per aggiungere un robot
    public void addRobot(Robot robot) {
        robots.add(robot);
        panel.revalidate(); // Aggiorna il pannello per visualizzare il nuovo robot
    }

    // Metodo per rimuovere un robot (non utilizzato ma implementabile)
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

    public void addRobotInstructionPanel(RobotInstructionPanel instructionPanel) {
        robotInstructionsPanel.add(instructionPanel);
        revalidate();
        repaint();
    }

    public void selectRobot (Robot robot) {
        selectedRobot = robot;
    }



}


