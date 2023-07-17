package it.unicam.cs.followme.app.Parser;

import it.unicam.cs.followme.app.Instruction.*;
import it.unicam.cs.followme.app.Robot.RobotBase;
import it.unicam.cs.followme.app.Simulation.Environment;
import it.unicam.cs.followme.app.Simulation.RobotSimulation;
import it.unicam.cs.followme.utilities.utilities.FollowMeParserHandler;
import it.unicam.cs.followme.app.Robot.Robot;
import it.unicam.cs.followme.utilities.utilities.FollowMeShapeChecker;
import java.util.ArrayList;
import java.util.List;


public class MyFollowMeParserHandler implements FollowMeParserHandler {
    private final FollowMeShapeChecker shapeChecker;
    private final RobotSimulation simulation;
    private final List<Instruction> instructions;
    private final Environment environment;

    public MyFollowMeParserHandler(RobotSimulation simulation, Environment environment) {
        shapeChecker = FollowMeShapeChecker.DEFAULT_CHECKER;
        this.simulation = simulation;
        this.instructions = new ArrayList<>();
        this.environment = environment;

    }

    @Override
    public void parsingStarted() {
        System.out.println("Parsing started");
    }

    @Override
    public void parsingDone() {
        System.out.println("Parsing done");
    }

    @Override
    public void moveCommand(double[] args) {
        String[] stringArgs = convertToStringArray(args);

        if (shapeChecker.checkParameters(stringArgs)) {
            double x = args[0];
            double y = args[1];
            double speed = args[2];


            Instruction instruction = new MoveInstruction(x, y, speed);
            instructions.add(instruction);
            // Sposta il Robot nella simulazione
            Robot robot = simulation.getRobots().get(0);
            robot.executeInstruction(instruction,environment);
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

            // Sposta il robot nella simulazione
            Robot robot = simulation.getRobots().get(0);
            robot.executeInstruction(instruction,environment);
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

            // Segnala il primo robot nella simulazione
            Robot robot = simulation.getRobots().get(0);
            robot.executeInstruction(instruction,environment);
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

            // Rimuovi la segnalazione del robot nella simulazione
            Robot robot = simulation.getRobots().get(0);
            robot.executeInstruction(instruction,environment);
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

            // Esegui il comando di "follow" del robot nella simulazione
            Robot robot = simulation.getRobots().get(0);
            robot.executeInstruction(instruction,environment);
        } else {
            System.out.println("Invalid follow command parameters");
        }
    }

    @Override
    public void stopCommand() {
        Instruction instruction = new StopMovingInstruction();
        instructions.add(instruction);

        // Ferma il robot nella simulazione se è presente almeno un robot
        List<RobotBase> robots = simulation.getRobots();
        if (!robots.isEmpty()) {
            RobotBase robot = robots.get(0);
            robot.executeInstruction(instruction, environment);
        } else {
            System.out.println("Non ci sono robot nella simulazione.");
        }
    }


    @Override
    public void waitCommand(int s) {
        Instruction instruction = new WaitInstruction(s);
        instructions.add(instruction);

        try {
            Thread.sleep(s * 1000L); // Attendi per s secondi
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
            // Condizione di uscita
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

        System.out.println("Done command executed");
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
