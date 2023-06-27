package it.unicam.cs.followme.app.Parser;

import it.unicam.cs.followme.app.Instruction.*;
import it.unicam.cs.followme.app.Simulation.RobotSimulation;
import it.unicam.cs.followme.app.utilities.FollowMeParserHandler;
import it.unicam.cs.followme.app.Robot.Robot;
import it.unicam.cs.followme.app.utilities.FollowMeShapeChecker;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;


public class MyFollowMeParserHandler implements FollowMeParserHandler {
    private FollowMeShapeChecker shapeChecker;
    private RobotSimulation simulation;
    private List<Instruction> instructions;

    public MyFollowMeParserHandler(RobotSimulation simulation) {
        shapeChecker = FollowMeShapeChecker.DEFAULT_CHECKER;
        this.simulation = simulation;
        this.instructions = new ArrayList<>();
    }

    @Override
    public void parsingStarted() {
        // TODO implementazione
        System.out.println("Parsing started");
    }

    @Override
    public void parsingDone() {
        // TODO implementazione
        System.out.println("Parsing done");
    }

    @Override
    public void moveCommand(double[] args) {
        String[] stringArgs = convertToStringArray(args);

        if (shapeChecker.checkParameters(stringArgs)) {
            double x = args[0];
            double y = args[1];
            double speed = args[2];
            Point2D.Double position = new Point2D.Double(x, y);

            Instruction instruction = new MoveInstruction(x, y, speed);
            instructions.add(instruction);
            // Sposta il Robot nella simulazione
            Robot robot = simulation.getRobots().get(0);
            robot.moveTo(position.x, position.y, speed);
        } else {
            System.out.println("Invalid move command parameters");
        }
    }

    @Override
    public void moveRandomCommand(double[] args) {

        String[] stringArgs = convertToStringArray(args);

        if (shapeChecker.checkParameters(stringArgs)) {
            double x1 = args[0];
            double x2 = args[1];
            double y1 = args[2];
            double y2 = args[3];
            double speed = args[4];

            Instruction instruction = new MoveToRandomInstruction(x1, x2, y1, y2, speed);
            instructions.add(instruction);
            double randomX = getRandomValue(x1, x2);
            double randomY = getRandomValue(y1, y2);
            Point2D.Double position = new Point2D.Double(randomX, randomY);
            //sposto il robot nella simulazione
            Robot robot = simulation.getRobots().get(0);
            robot.moveToRandom(x1, x2, y1, y2, speed);
        } else {
            System.out.println("Invalid move random command parameters");
        }
    }

    @Override
    public void signalCommand(String label) {
        String[] stringArgs = new String[]{label};

        if (shapeChecker.checkParameters(stringArgs)) {

            Instruction instruction = new SignalInstruction(label);
            instructions.add(instruction);
            Point2D.Double position = new Point2D.Double(0, 0);
            //segnala il primo robot nella simulazione
            Robot robot = simulation.getRobots().get(0);
            robot.signal(label);

        } else {
            System.out.println("Invalid signal command parameter");
        }
    }

    @Override
    public void unsignalCommand(String label) {
        String[] stringArgs = new String[]{label};

        if (shapeChecker.checkParameters(stringArgs)) {
            Instruction instruction = new UnsignalInstruction(label);
            instructions.add(instruction);
            Point2D.Double position = new Point2D.Double(0, 0);
            //rimuovi la segnalazione del robot nella simulazione
            Robot robot = simulation.getRobots().get(0);
            robot.unsignal(label);
        } else {
            System.out.println("Invalid unsignal command parameter");
        }
    }

    @Override
    public void followCommand(String label, double[] args) {
        String[] stringArgs = convertToStringArray(args);

        if (shapeChecker.checkParameters(stringArgs)) {
            double distance = args[0];
            double speed = args[1];

            Instruction instruction = new FollowInstruction(label, distance, speed);
            instructions.add(instruction);
            Point2D.Double position = new Point2D.Double(0, 0);
            // Esegui il comando di "follow" del robot nella simulazione
            Robot robot = simulation.getRobots().get(0);
            robot.follow(label, distance, speed);
        } else {
            System.out.println("Invalid follow command parameters");
        }
    }

    @Override
    public void stopCommand() {

        Instruction instruction = new StopMovingInstruction();
        instructions.add(instruction);
        Point2D.Double position = new Point2D.Double(0, 0);
        // Ferma il robot nella simulazione
        Robot robot = simulation.getRobots().get(0);
        robot.stopMoving();
    }

    @Override
    public void waitCommand(int s) {
        Instruction instruction = new WaitInstruction(s);
        instructions.add(instruction);

        try {
            Thread.sleep(s * 1000); // Attendi per s secondi
        } catch (InterruptedException e) {
            System.out.println("Il thread di attesa è stato interrotto.");
            Thread.currentThread().interrupt(); // reimposta lo stato di interruzione del thread
        }
    }

    @Override
    public void repeatCommandStart(int n) {
        Instruction instruction = new RepeatInstruction(n);
        instructions.add(instruction);
        int counter = 0;

        // Esegui un'azione specifica per un numero specificato di volte
        for (int i = 0; i < n; i++) {
            System.out.println("Executing action: " + (i + 1) + " out of " + n);

            // Esegui l'azione desiderata nel loop
            counter++;
        }
        System.out.println("Final counter value: " + counter);
    }

    @Override
    public void untilCommandStart(String label) {

        Instruction instruction = new UntilInstruction(label);
        instructions.add(instruction);

        Robot robot = simulation.getRobots().get(0);
        while (true) {
            System.out.println("Executing until loop...");
            boolean isLabelSignaled = robot.isSignaled(label);
            if (isLabelSignaled) {
                break;
            }
            System.out.println("Executing desired action...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void doForeverStart() {
        Instruction instruction = new DoForeverInstruction();
        instructions.add(instruction);

        // Esegui un'azione in loop infinito
        while (true) {
            System.out.println("Executing forever loop...");
            //condizione di uscita
            int maxIterations = 10;
            int iteration = 0;
            while (iteration < maxIterations) {
                System.out.println("Executing iteration: " + iteration);
                iteration++;
            }
            // Esci dal loop dopo il numero massimo di iterazioni
            break;
        }
    }

    @Override
    public void doneCommand() {
        Instruction instruction = new DoneInstruction();
        instructions.add(instruction);

        // da fare l'instruction
        System.out.println("Done command executed");
    }

    // Metodo per generare un valore casuale compreso tra min e max
    private double getRandomValue(double min, double max) {
        return min + Math.random() * (max - min);
    }

    private String[] convertToStringArray(double[] args) {
        String[] stringArgs = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            stringArgs[i] = String.valueOf(args[i]);
        }
        return stringArgs;
    }

    public List<Instruction> getInstructions() {
        // Restituisci l'elenco delle istruzioni raccolte durante il parsing
        return instructions;
    }
}
