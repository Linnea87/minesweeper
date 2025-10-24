package core;

import model.Board;

public class Command {
    private CommandType type;
    private Coordinate coordinate;


    public Command(CommandType type, Coordinate coordinate) {
        this.type = type;
        this.coordinate = coordinate;
    }

    public CommandType getType() {
        return type;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
