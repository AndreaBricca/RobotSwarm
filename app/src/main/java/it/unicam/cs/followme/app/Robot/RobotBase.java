package it.unicam.cs.followme.app.Robot;

import it.unicam.cs.followme.app.Instruction.*;
import it.unicam.cs.followme.app.Parser.MyFollowMeParserHandler;
import it.unicam.cs.followme.app.Simulation.Environment;
import it.unicam.cs.followme.app.Simulation.RobotSimulation;
import it.unicam.cs.followme.utilities.utilities.FollowMeParser;
import it.unicam.cs.followme.utilities.utilities.FollowMeParserException;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;


public class RobotBase implements Robot {

    private  Point2D.Double position;
    private  final Velocity velocity;
    private final List<String> conditions;
    private double continueDuration; // Durata del comando continue in secondi
    private final Environment environment;
    private final String label;
    private List<Instruction> instructions;
    private final double radius;


    public RobotBase(String label, double x, double y, double radius, Environment environment) {
        this.position = new Point2D.Double(x,y);
        this.velocity = new Velocity(0,0);
        this.conditions = new ArrayList<>();
        this.continueDuration = 0;
        this.environment = environment;
        this.label = label;
        this.instructions = new ArrayList<>();
        this.radius = radius;
    }
    @Override
    public void executeInstruction(Instruction instruction, Environment environment) {
        if (instruction instanceof MoveInstruction moveInstruction) {
            double x = moveInstruction.getX();
            double y = moveInstruction.getY();
            double speed = moveInstruction.getSpeed();
            moveTo(x, y, speed);
        } else if (instruction instanceof MoveToRandomInstruction moveRandomInstruction) {
            double x1 = moveRandomInstruction.getX1();
            double x2 = moveRandomInstruction.getX2();
            double y1 = moveRandomInstruction.getY1();
            double y2 = moveRandomInstruction.getY2();
            double speed = moveRandomInstruction.getSpeed();
            moveToRandom(x1, x2, y1, y2, speed);
        } else if (instruction instanceof SignalInstruction signalInstruction) {
            String label = signalInstruction.getLabel();
            signal(label);
        } else if (instruction instanceof UnsignalInstruction unsignalInstruction) {
            String label = unsignalInstruction.getLabel();
            unsignal(label);
        } else if (instruction instanceof FollowInstruction followInstruction) {
            String label = followInstruction.getLabel();
            double distance = followInstruction.getDistance();
            double speed = followInstruction.getSpeed();
            follow(label, distance, speed);
        } else if (instruction instanceof ContinueInstruction continueInstruction) {
            double duration = continueInstruction.getDuration();
            continueMoving(duration);
        } else if (instruction instanceof StopMovingInstruction) {
            stopMoving();
        }
        System.out.println("Esecuzione istruzione: " + instruction.toString());
    }

    public void setContinueDuration(double duration) {
        continueDuration = duration;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public Point2D.Double getPosition() {
        return position;
    }

    @Override
    public boolean isSignaled(String label) {
        return conditions.contains(label);
    }

    @Override
    public double getRadius() {
        return radius;
    }


    @Override
    public void moveTo ( double x, double y, double speed){
            position.setLocation(x, y);
        }

        @Override
        public void moveToRandom ( double x1, double x2, double y1, double y2, double speed){
            // Genera una posizione casuale all'interno dell'intervallo [x1, x2] x [y1, y2]
            double randomX = Math.random() * (x2 - x1) + x1;
            double randomY = Math.random() * (y2 - y1) + y1;
            position = new Point2D.Double(randomX, randomY);

            // Calcola la direzione verso la posizione casuale
            double dx = randomX - position.getX();
            double dy = randomY - position.getY();
            double angle = Math.atan2(dy, dx);

            // Calcola la velocità del movimento in base alla velocità massima consentita
            double velocityX = Math.cos(angle) * speed;
            double velocityY = Math.sin(angle) * speed;

            // Aggiorna la posizione e la velocità del robot
            position.setLocation(randomX, randomY);
            velocity.setX(velocityX);
            velocity.setY(velocityY);
        }

        @Override
        public void signal (String label){ conditions.add(label);}

        @Override
        public void unsignal (String label){ conditions.remove(label);}

        @Override
        public void follow (String label,double distance, double speed){

        if (conditions.contains(label)) {
                // Calcola la posizione del robot da seguire
                Point2D.Double targetPosition = calculateTargetPosition(label, distance);

                // Calcola la direzione verso la posizione del robot da seguire
                double dx = targetPosition.getX() - position.getX();
                double dy = targetPosition.getY() - position.getY();
                double angle = Math.atan2(dy, dx);

                // Calcola la velocità del movimento in base alla velocità massima consentita
                double velocityX = Math.cos(angle) * speed;
                double velocityY = Math.sin(angle) * speed;

                // Aggiorna la posizione e la velocità del robot
                position = targetPosition;
                velocity.setX(velocityX);
                velocity.setY(velocityY);
        }
        }

    private Point2D.Double calculateTargetPosition(String label, double distance) {

        // Recupera la posizione del robot con l'etichetta specificata
        Point2D.Double targetPosition = environment.getRobotPosition(label);

        // Calcola la direzione e la distanza dal robot corrente al robot target
        double dx = targetPosition.getX() - position.getX();
        double dy = targetPosition.getY() - position.getY();
        double currentDistance = Math.sqrt(dx * dx + dy * dy);

        if (currentDistance > 0) {
            dx /= currentDistance;
            dy /= currentDistance;
        }

        // Calcola la nuova posizione in base alla direzione e alla distanza
        double targetX = position.getX() + dx * distance;
        double targetY = position.getY() + dy * distance;

        return new Point2D.Double(targetX, targetY);
    }

        @Override
        public void continueMoving ( double duration){
            setContinueDuration(duration);
        }

        @Override
        public void stopMoving () {
            setContinueDuration(0);
        }


    @Override
    public Velocity getVelocity() {
        return velocity;
    }

    @Override
    public void setInstructions(String instructions) {
        try {

            RobotSimulation simulation = new RobotSimulation();
            this.instructions = convertInstructions(instructions, simulation);
        } catch (FollowMeParserException e) {
            // Gestione dell'eccezione specifica del parser
            System.out.println("Errore durante il parsing delle istruzioni: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public boolean containsPoint(Point2D point) {
        double distance = getPosition().distance(point);
        return distance <= getRadius();
    }

    private List<Instruction> convertInstructions(String instructions, RobotSimulation simulation) throws FollowMeParserException {

        // Parser personalizzato per le istruzioni
        MyFollowMeParserHandler parserHandler = new MyFollowMeParserHandler(simulation, environment);
        // Configurazione del parser con il parser handler
        FollowMeParser parser = new FollowMeParser(parserHandler);
        // Parsing delle istruzioni
        parser.parseRobotProgram(instructions);

        // Recupera le istruzioni convertite dal parser handler
        return new ArrayList<>(parserHandler.getInstructions());
    }

}




