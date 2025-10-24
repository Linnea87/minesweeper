package core;

public class Command {
    private CommandType type;
    private Coordinate coordinate;

    public Command(CommandType type, Coordinate coordinate) {
        this.type = type;
        this.coordinate = coordinate;
    }

    enum CommandType {
         REVEAL,
         FLAG,
         QUIT
    }

    public CommandType getType() {
        return type;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
