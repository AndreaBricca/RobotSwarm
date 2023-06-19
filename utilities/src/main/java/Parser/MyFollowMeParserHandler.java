package Parser;

import it.unicam.cs.followme.app.Robot.RobotBase;
import it.unicam.cs.followme.app.Robot.Velocity;
import it.unicam.cs.followme.utilities.FollowMeParserHandler;
import it.unicam.cs.followme.app.Robot.Robot;

import java.awt.geom.Point2D;


public class MyFollowMeParserHandler implements FollowMeParserHandler {
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
        double x = args[0];
        double y = args[1];
        double speed = args[2];
        Point2D.Double position = new Point2D.Double(x, y);
        Robot robot = new RobotBase("", position.getX(), position.getY(), 0);
        robot.moveTo(x, y, speed);
    }

    @Override
    public void moveRandomCommand(double[] args) {

        double x1 = args[0];
        double x2 = args[1];
        double y1 = args[2];
        double y2 = args[3];
        double speed = args[4];
        double randomX = getRandomValue(x1, x2);
        double randomY = getRandomValue(y1, y2);
        Point2D.Double position = new Point2D.Double(randomX, randomY);
        Robot robot = new RobotBase("", position.getX(), position.getY(), 0);
        robot.moveToRandom(x1, x2, y1, y2, speed);
    }

    @Override
    public void signalCommand(String label) {
        Point2D.Double position = new Point2D.Double(0, 0);
        Robot robot = new RobotBase(label, position.getX(), position.getY(), 0);
        robot.signal(label);
    }

    @Override
    public void unsignalCommand(String label) {
        Point2D.Double position = new Point2D.Double(0, 0);
        Robot robot = new RobotBase(label, position.getX(), position.getY(), 0);
        robot.unsignal(label);
    }

    @Override
    public void followCommand(String label, double[] args) {
        double distance = args[0];
        double speed = args[1];
        Point2D.Double position = new Point2D.Double(0, 0);
        Robot robot = new RobotBase(label, position.getX(), position.getY(), 0);
        robot.follow(label, distance, speed);
    }

    @Override
    public void stopCommand() {
        Point2D.Double position = new Point2D.Double(0, 0);
        Robot robot = new RobotBase("", position.getX(), position.getY(), 0);
        robot.stopMoving();
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
        // Esegui un'azione fino a quando l'etichetta specificata viene segnalata
        RobotBase robot = new RobotBase("", 0, 0, 0);
        while (true) {
            System.out.println("Executing until loop...");

            // Verifica se l'etichetta è stata segnalata
            boolean isLabelSignaled = robot.isSignaled(label);

            // Se l'etichetta è stata segnalata esci dal loop
            if (isLabelSignaled) {
                break;
            }
            System.out.println("Executing desired action...");
            // Metti in pausa per un breve periodo prima di ripetere il ciclo
            try {
                Thread.sleep(1000); // Pausa di 1 secondo
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

}
