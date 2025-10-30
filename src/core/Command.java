package core;

public class Command {

    // === Fields ==============================================================

    private CommandType type;
    private Coordinate coordinate;

    // === Constructor =========================================================

    public Command(CommandType type, Coordinate coordinate) {
        this.type = type;
        this.coordinate = coordinate;
    }

    // === Getters =============================================================

    public CommandType getType() {
        return type;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
