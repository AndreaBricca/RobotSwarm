package Parser;

import it.unicam.cs.followme.utilities.FollowMeParserHandler;
import it.unicam.cs.followme.app.Robot.RobotBase;


public class MyFollowMeParserHandler implements FollowMeParserHandler {
    @Override
    public void parsingStarted() {

    }

    @Override
    public void parsingDone() {

    }

    @Override
    public void moveCommand(double[] args) {
        // Chiamare il metodo moveTo della classe RobotBase (o del tuo robot specifico)
        // e passare i parametri appropriati per il movimento del robot.
        // Ad esempio:
        RobotBase.moveTo(args[0], args[1], args[2]);
    }

    @Override
    public void moveRandomCommand(double[] args) {

    }

    @Override
    public void signalCommand(String label) {

    }

    @Override
    public void unsignalCommand(String label) {

    }

    @Override
    public void followCommand(String label, double[] args) {

    }

    @Override
    public void stopCommand() {

    }

    @Override
    public void waitCommand(int s) {

    }

    @Override
    public void repeatCommandStart(int n) {

    }

    @Override
    public void untilCommandStart(String label) {

    }

    @Override
    public void doForeverStart() {

    }

    @Override
    public void doneCommand() {

    }
}
