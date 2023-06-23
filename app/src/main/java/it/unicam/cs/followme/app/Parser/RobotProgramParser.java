package it.unicam.cs.followme.app.Parser;

import it.unicam.cs.followme.app.Instruction.Instruction;
import it.unicam.cs.followme.app.Robot.RobotProgram;
import it.unicam.cs.followme.app.Simulation.RobotSimulation;
import it.unicam.cs.followme.utilities.FollowMeParser;
import it.unicam.cs.followme.utilities.FollowMeParserException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class RobotProgramParser {

    public RobotProgram parseRobotProgram(String filePath) throws IOException {
        RobotProgram robotProgram = new RobotProgram();
        RobotSimulation simulation = new RobotSimulation();
        MyFollowMeParserHandler parserHandler = new MyFollowMeParserHandler(simulation);

        FollowMeParser parser = new FollowMeParser(parserHandler);

        File sourceFile = new File(filePath);

        try {
            parser.parseRobotProgram(sourceFile);
            // Aggiungi le istruzioni al robotProgram durante il parsing
            List<Instruction> instructions = parserHandler.getInstructions();
            for (Instruction instruction : instructions) {
                robotProgram.addInstruction(instruction);
            }
        } catch (IOException e) {
            // Gestisci l'errore di input
            e.printStackTrace();
        } catch (FollowMeParserException e) {
            // Gestisci l'errore di parsing
            e.printStackTrace();
        }

        return robotProgram;
    }
}
