package me.nicodax.day2;

public enum RPSResult {
    WIN, DRAW, LOSE;

    public static RPSResult fromAlias(String alias) {
        return switch (alias) {
            case "X" -> LOSE;
            case "Y" -> DRAW;
            default -> WIN;
        };
    }
}
