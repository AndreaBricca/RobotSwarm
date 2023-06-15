package Parser;


public class RobotProgramParser {
    private String filePath;

    /*public RobotProgramParser(String filePath) {
        this.filePath = filePath;
    }

    public List<RobotCommand> parseCommands() throws IOException {
        List<RobotCommand> commands = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;
        while ((line = reader.readLine()) != null) {
            RobotCommand command = parseCommand(line);
            if (command != null) {
                commands.add(command);
            }
        }

        reader.close();
        return commands;
    }

    private RobotCommand parseCommand(String line) {
        String[] parts = line.split(" ");
        if (parts.length == 0) {
            return null; // Ignoring empty lines
        }

        String commandType = parts[0];
        switch (commandType) {
            case "MOVE":
                // Parse MOVE command arguments
                // Example: MOVE x y s
                if (parts.length != 4) {
                    return null; // Invalid command format
                }
                double x = Double.parseDouble(parts[1]);
                double y = Double.parseDouble(parts[2]);
                double speed = Double.parseDouble(parts[3]);
                return new MoveCommand(x, y, speed);

            case "MOVE_RANDOM":
                // Parse MOVE_RANDOM command arguments
                // Example: MOVE_RANDOM x1 x2 y1 y2 s
                if (parts.length != 6) {
                    return null; // Invalid command format
                }
                double x1 = Double.parseDouble(parts[1]);
                double x2 = Double.parseDouble(parts[2]);
                double y1 = Double.parseDouble(parts[3]);
                double y2 = Double.parseDouble(parts[4]);
                double speedRandom = Double.parseDouble(parts[5]);
                return new MoveRandomCommand(x1, x2, y1, y2, speedRandom);

            case "SIGNAL":
                // Parse SIGNAL command arguments
                // Example: SIGNAL label
                if (parts.length != 2) {
                    return null; // Invalid command format
                }
                String signalLabel = parts[1];
                return new SignalCommand(signalLabel);

            case "UNSIGNAL":
                // Parse UNSIGNAL command arguments
                // Example: UNSIGNAL label
                if (parts.length != 2) {
                    return null; // Invalid command format
                }
                String unsignalLabel = parts[1];
                return new UnsignalCommand(unsignalLabel);

            // Add cases for other command types as needed

            default:
                return null; // Invalid command type
        }
    }*/
}
