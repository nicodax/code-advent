package me.nicodax.day9;

public enum MovementType {
    UP, UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT, UP_LEFT, NONE;

    public static MovementType of(String alias) {
        return switch (alias) {
            case "U" -> UP;
            case "R" -> RIGHT;
            case "D" -> DOWN;
            default -> LEFT;
        };
    }

    public static MovementType fromRelativePosition(Position relativePosition) {
        if (relativePosition.getX().equals(-2) && relativePosition.getY() >= 1 ||
                relativePosition.getX().equals(-1) && relativePosition.getY().equals(2)) {
            return DOWN_RIGHT;
        } else if (relativePosition.getX().equals(2) && relativePosition.getY() >= 1 ||
                relativePosition.getX().equals(1) && relativePosition.getY().equals(2)) {
            return DOWN_LEFT;
        } else if (relativePosition.getX().equals(2) && relativePosition.getY() <= -1 ||
                relativePosition.getX().equals(1) && relativePosition.getY().equals(-2)) {
            return UP_LEFT;
        } else if (relativePosition.getX().equals(-2) && relativePosition.getY() <= -1 ||
                relativePosition.getX().equals(-1) && relativePosition.getY().equals(-2)) {
            return UP_RIGHT;
        } else if (relativePosition.getX().equals(0) && relativePosition.getY().equals(2)) {
            return DOWN;
        } else if (relativePosition.getX().equals(2) && relativePosition.getY().equals(0)) {
            return LEFT;
        } else if (relativePosition.getX().equals(0) && relativePosition.getY().equals(-2)) {
            return UP;
        } else if (relativePosition.getX().equals(-2) && relativePosition.getY().equals(0)) {
            return RIGHT;
        } else return NONE;
    }
}
