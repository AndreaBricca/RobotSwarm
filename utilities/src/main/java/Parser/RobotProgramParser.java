package Parser;


import it.unicam.cs.followme.app.Robot.Robot;
import it.unicam.cs.followme.app.Simulation.RobotSimulation;
import it.unicam.cs.followme.utilities.FollowMeParser;
import it.unicam.cs.followme.utilities.FollowMeParserException;
import it.unicam.cs.followme.utilities.FollowMeParserHandler;
import java.io.File;
import java.io.IOException;

public class RobotProgramParser {
    public void parseRobotProgram(String filePath) {
        RobotSimulation simulation = new RobotSimulation();
        FollowMeParserHandler parserHandler = new MyFollowMeParserHandler(simulation);

        FollowMeParser parser = new FollowMeParser(parserHandler);

        File sourceFile = new File(filePath);

        try {
            parser.parseRobotProgram(sourceFile);
        } catch (IOException e) {
            // gestisci errore di input
            e.printStackTrace();
        } catch (FollowMeParserException e) {
            // Gestisci errore di parsing del programma
            e.printStackTrace();
        }
    }

}
