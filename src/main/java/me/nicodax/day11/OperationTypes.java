package me.nicodax.day11;

public enum OperationTypes {
    MULTIPLY, DIVIDE, ADD, SUBTRACT;

    public static OperationTypes of(String operation) {
        return switch (operation) {
            case "*" -> MULTIPLY;
            case "/" -> DIVIDE;
            case "+" -> ADD;
            default -> SUBTRACT;
        };
    }
}
