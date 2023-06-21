package Parser;

import it.unicam.cs.followme.app.Robot.RobotBase;
import it.unicam.cs.followme.app.Simulation.RobotSimulation;
import it.unicam.cs.followme.utilities.FollowMeParserHandler;
import it.unicam.cs.followme.app.Robot.Robot;
import it.unicam.cs.followme.utilities.FollowMeShapeChecker;

import java.awt.geom.Point2D;


public class MyFollowMeParserHandler implements FollowMeParserHandler {
    private Robot robot;
    private FollowMeShapeChecker shapeChecker;
    private RobotSimulation simulation;

    public MyFollowMeParserHandler(RobotSimulation simulation) {
        robot = new RobotBase("", 0, 0, 0);
        shapeChecker = FollowMeShapeChecker.DEFAULT_CHECKER;
        this.simulation = simulation;
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

            //sposto il Robot nella simulazione
            Robot robot = simulation.getRobots().get(0);
            this.robot.moveTo(x, y, speed);
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
            double randomX = getRandomValue(x1, x2);
            double randomY = getRandomValue(y1, y2);
            Point2D.Double position = new Point2D.Double(randomX, randomY);
            //sposto il robot nella simulazione
            Robot robot = simulation.getRobots().get(0);
            this.robot.moveToRandom(x1, x2, y1, y2, speed);
        } else {
            System.out.println("Invalid move random command parameters");
        }
    }

    @Override
    public void signalCommand(String label) {
        String[] stringArgs = new String[]{label};

        if (shapeChecker.checkParameters(stringArgs)) {
            Point2D.Double position = new Point2D.Double(0, 0);
            //segnala il primo robot nella simulazione
            Robot robot = simulation.getRobots().get(0);
            this.robot.signal(label);
        } else {
            System.out.println("Invalid signal command parameter");
        }
    }

    @Override
    public void unsignalCommand(String label) {
        String[] stringArgs = new String[]{label};

        if (shapeChecker.checkParameters(stringArgs)) {
            Point2D.Double position = new Point2D.Double(0, 0);
            //rimuovi la segnalazione del robot nella simulazione
            Robot robot = simulation.getRobots().get(0);
            this.robot.unsignal(label);
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
            Point2D.Double position = new Point2D.Double(0, 0);
            // Esegui il comando di "follow" del robot nella simulazione
            Robot robot = simulation.getRobots().get(0);
            this.robot.follow(label, distance, speed);
        } else {
            System.out.println("Invalid follow command parameters");
        }
    }

    @Override
    public void stopCommand() {
        Point2D.Double position = new Point2D.Double(0, 0);
        // Ferma il robot nella simulazione
        Robot robot = simulation.getRobots().get(0);
        this.robot.stopMoving();
    }

    @Override
    public void waitCommand(int s) {
        //mette in pausa l'esecuzione per tot tempo
        try {
            Thread.sleep(s * 1000); // Attendi per s secondi
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void repeatCommandStart(int n) {
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
}
