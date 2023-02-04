package me.nicodax.day2;

import static me.nicodax.day2.RPSResult.DRAW;
import static me.nicodax.day2.RPSResult.LOSE;
import static me.nicodax.day2.RPSResult.WIN;

public enum RPSAction {
    ROCK, PAPER, SCISSORS;

    public static RPSAction fromResultAndOpponentAction(RPSResult result, RPSAction opponentAction) {
        // get my action from round result and opponent action
        if (opponentAction.equals(ROCK) && result.equals(WIN)) return PAPER;
        else if (opponentAction.equals(ROCK) && result.equals(DRAW)) return ROCK;
        else if (opponentAction.equals(ROCK) && result.equals(LOSE)) return SCISSORS;
        else if (opponentAction.equals(PAPER) && result.equals(WIN)) return SCISSORS;
        else if (opponentAction.equals(PAPER) && result.equals(DRAW)) return PAPER;
        else if (opponentAction.equals(PAPER) && result.equals(LOSE)) return ROCK;
        else if (opponentAction.equals(SCISSORS) && result.equals(WIN)) return ROCK;
        else if (opponentAction.equals(SCISSORS) && result.equals(DRAW)) return SCISSORS;
        else return PAPER;
    }

    public static RPSAction fromAlias(String alias) {
        return switch (alias) {
            case "A", "X" -> ROCK;
            case "B", "Y" -> PAPER;
            default -> SCISSORS;
        };
    }
}
