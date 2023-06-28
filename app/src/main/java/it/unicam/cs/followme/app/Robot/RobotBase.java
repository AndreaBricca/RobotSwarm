package it.unicam.cs.followme.app.Robot;

import it.unicam.cs.followme.app.Instruction.*;
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
    private double orientation;
    private List<Instruction> executedInstructions;



    public RobotBase(String label, double x, double y, double orientation) {
        this.position = new Point2D.Double(0, 0);
        this.velocity = new Velocity(0,0);
        this.conditions = new ArrayList<>();
        this.continueDuration = 0;
        this.continueTimer = 0;
        this.environment = null;
        this.label = label;
        this.instructions = new ArrayList<>();
        this.currentInstructionIndex = -1;
        this.orientation = orientation;
        executedInstructions = new ArrayList<>();

    }
    @Override
    public void executeInstruction(Instruction instruction, Environment environment) {
        if (instruction instanceof MoveInstruction) {
            MoveInstruction moveInstruction = (MoveInstruction) instruction;
            Point2D targetPosition = moveInstruction.getTargetPosition();
            double x = moveInstruction.getX();
            double y = moveInstruction.getY();
            double speed = moveInstruction.getSpeed();
            moveTo(x, y, speed);
        } else if (instruction instanceof MoveToRandomInstruction) {
            MoveToRandomInstruction moveRandomInstruction = (MoveToRandomInstruction) instruction;
            double x1 = moveRandomInstruction.getX1();
            double x2 = moveRandomInstruction.getX2();
            double y1 = moveRandomInstruction.getY1();
            double y2 = moveRandomInstruction.getY2();
            double speed = moveRandomInstruction.getSpeed();
            moveToRandom(x1, x2, y1, y2, speed);
        } else if (instruction instanceof SignalInstruction) {
            SignalInstruction signalInstruction = (SignalInstruction) instruction;
            String label = signalInstruction.getLabel();
            signal(label);
        } else if (instruction instanceof UnsignalInstruction) {
            UnsignalInstruction unsignalInstruction = (UnsignalInstruction) instruction;
            String label = unsignalInstruction.getLabel();
            unsignal(label);
        } else if (instruction instanceof FollowInstruction) {
            FollowInstruction followInstruction = (FollowInstruction) instruction;
            String label = followInstruction.getLabel();
            double distance = followInstruction.getDistance();
            double speed = followInstruction.getSpeed();
            follow(label, distance, speed);
        } else if (instruction instanceof ContinueInstruction) {
            ContinueInstruction continueInstruction = (ContinueInstruction) instruction;
            double duration = continueInstruction.getDuration();
            continueMoving(duration);
        } else if (instruction instanceof StopMovingInstruction) {
            stopMoving();
        }
        System.out.println("Esecuzione istruzione: " + instruction.toString());
    }

    public List<Instruction> getExecutedInstructions() {
        return executedInstructions;
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

    @Override
    public boolean isSignaled(String label) {
        return conditions.contains(label);
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


