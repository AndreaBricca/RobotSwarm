package it.unicam.cs.followme.app.Robot;

import it.unicam.cs.followme.app.Instruction.ContinueInstruction;
import it.unicam.cs.followme.app.Instruction.Instruction;
import it.unicam.cs.followme.app.Simulation.Environment;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import java.util.List;


public class RobotBase implements Robot {
    private  Point2D.Double position;
    private  Velocity velocity;
    private List<String> conditions;
    private double continueDuration; // Durata del comando continue in secondi
    private double continueTimer; // Timer per tenere traccia della durata rimanente
    private Environment environment;
    private String label;
    private List<Instruction> instructions;
    private int currentInstructionIndex;


    public RobotBase() {
        this.position = new Point2D.Double(0, 0);
        this.velocity = new Velocity(0,0);
        this.conditions = new ArrayList<>();
        this.environment = environment;
        this.label = label;
        this.instructions = new ArrayList<>();
        this.currentInstructionIndex = -1;
    }

    public void executeInstruction(Instruction instruction, Environment environment) {
        if (instruction instanceof ContinueInstruction) {
            ContinueInstruction continueInstruction = (ContinueInstruction) instruction;
            continueDuration = continueInstruction.getDuration();
            continueTimer = continueDuration;
        } else {
            //TODO
        }
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
    public Instruction getCurrentInstruction() {
        if (currentInstructionIndex >= 0 && currentInstructionIndex < instructions.size()) {
            return instructions.get(currentInstructionIndex);
        }
        return null;
    }

    @Override
    public void incrementInstructionIndex() {
        currentInstructionIndex++;
    }

    @Override
    public int getInstructionIndex() {
        return currentInstructionIndex;
    }

    @Override
    public List<Instruction> getInstructions() {
        return instructions;
    }

    @Override
    public void resetInstructionIndex() {
        currentInstructionIndex = -1;
    }

    public void update ( double deltaTime){
            if (continueTimer > 0) {
                // Il robot è in esecuzione del comando continue
                continueTimer -= deltaTime;
                if (continueTimer <= 0) {
                    //TODO
                    // Il comando continue è terminato, esegui altre azioni
                }
            } else {
                //TODO
            }
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
                position.setLocation(targetPosition);
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




}


