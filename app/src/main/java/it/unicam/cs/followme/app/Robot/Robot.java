package it.unicam.cs.followme.app.Robot;


import it.unicam.cs.followme.app.Instruction.Instruction;
import it.unicam.cs.followme.app.Simulation.Environment;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

public interface Robot {
    void moveTo(double x, double y, double speed);
    void moveToRandom(double x1, double x2, double y1, double y2, double speed);
    void signal(String label);
    void unsignal(String label);
    void follow(String label, double distance, double speed);
    void continueMoving(double duration);
    void stopMoving();
    void setContinueDuration(double duration);
    String getLabel();
    Point2D.Double getPosition();
    Instruction getCurrentInstruction();
    void incrementInstructionIndex();
    int getInstructionIndex();
    List<Instruction> getInstructions();
    void resetInstructionIndex();
    boolean isSignaled(String label);
    double getRadius();
    void executeInstruction(Instruction instruction, Environment environment);
    Component getRobotComponent();
    Velocity getVelocity();
}
