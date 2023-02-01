package me.nicodax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day2 {
    private List<Integer> listOfRPSPointsByRound = new ArrayList<>();
    private final static Integer LOSS_POINTS = 0;
    private final static Integer DRAW_POINTS = 3;
    private final static Integer WIN_POINTS = 6;

    private final static Integer ROCK_POINTS = 1;
    private final static Integer PAPER_POINTS = 2;
    private final static Integer SCISSORS_POINTS = 3;

    public final static Character ROCK = 'X';
    public final static Character PAPER = 'Y';
    public final static Character SCISSORS = 'Z';

    public final static Character CONDITION_LOSE = 'X';
    public final static Character CONDITION_DRAW = 'Y';
    public final static Character CONDITION_WIN = 'Z';

    public final static Character OPPONENT_ROCK = 'A';
    public final static Character OPPONENT_PAPER = 'B';
    public final static Character OPPONENT_SCISSORS = 'C';

    private final static Integer MY_ACTION_INDEX = 2;
    private final static Integer ROUND_RESULT_INDEX = 2;
    private final static Integer OPPONENT_ACTION_INDEX = 0;

    public void readAndParseFile(Path path, Integer part) {
        listOfRPSPointsByRound = new ArrayList<>();
        try (Stream<String> lines = Files.lines(path)){
            for (String line : lines.toList()) {
                if (line.isBlank()) continue;
                Character opponentAction = line.charAt(OPPONENT_ACTION_INDEX);
                Character myAction = part == 1 ? line.charAt(MY_ACTION_INDEX) :
                                     getMyActionFromRoundResult(line.charAt(ROUND_RESULT_INDEX), opponentAction);
                listOfRPSPointsByRound.add(getRoundResult(myAction, opponentAction));
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public Integer getRoundResult(Character myAction, Character opponentAction) {
        if (myAction.equals(ROCK) && opponentAction.equals(OPPONENT_ROCK)) return ROCK_POINTS + DRAW_POINTS;
        else if (myAction.equals(ROCK) && opponentAction.equals(OPPONENT_PAPER)) return ROCK_POINTS + LOSS_POINTS;
        else if (myAction.equals(ROCK) && opponentAction.equals(OPPONENT_SCISSORS)) return ROCK_POINTS + WIN_POINTS;
        else if (myAction.equals(PAPER) && opponentAction.equals(OPPONENT_ROCK)) return PAPER_POINTS + WIN_POINTS;
        else if (myAction.equals(PAPER) && opponentAction.equals(OPPONENT_PAPER)) return PAPER_POINTS + DRAW_POINTS;
        else if (myAction.equals(PAPER) && opponentAction.equals(OPPONENT_SCISSORS)) return PAPER_POINTS + LOSS_POINTS;
        else if (myAction.equals(SCISSORS) && opponentAction.equals(OPPONENT_ROCK)) return SCISSORS_POINTS + LOSS_POINTS;
        else if (myAction.equals(SCISSORS) && opponentAction.equals(OPPONENT_PAPER)) return SCISSORS_POINTS + WIN_POINTS;
        else if (myAction.equals(SCISSORS) && opponentAction.equals(OPPONENT_SCISSORS)) return SCISSORS_POINTS + DRAW_POINTS;
        else return 0;
    }

    public Integer getMyTotalScore() {
        return listOfRPSPointsByRound.stream().mapToInt(v -> v).sum();
    }

    public List<Integer> getListOfRPSPointsByRound() {
        return listOfRPSPointsByRound;
    }

    public void setListOfRPSPointsByRound(List<Integer> listOfRPSPointsByRound) {
        this.listOfRPSPointsByRound = listOfRPSPointsByRound;
    }

    public Character getMyActionFromRoundResult(Character roundResult, Character opponentAction) {
        if (roundResult.equals(CONDITION_LOSE) && opponentAction.equals(OPPONENT_ROCK)) return SCISSORS;
        else if (roundResult.equals(CONDITION_LOSE) && opponentAction.equals(OPPONENT_PAPER)) return ROCK;
        else if (roundResult.equals(CONDITION_LOSE) && opponentAction.equals(OPPONENT_SCISSORS)) return PAPER;
        else if (roundResult.equals(CONDITION_DRAW) && opponentAction.equals(OPPONENT_ROCK)) return ROCK;
        else if (roundResult.equals(CONDITION_DRAW) && opponentAction.equals(OPPONENT_PAPER)) return PAPER;
        else if (roundResult.equals(CONDITION_DRAW) && opponentAction.equals(OPPONENT_SCISSORS)) return SCISSORS;
        else if (roundResult.equals(CONDITION_WIN) && opponentAction.equals(OPPONENT_ROCK)) return PAPER;
        else if (roundResult.equals(CONDITION_WIN) && opponentAction.equals(OPPONENT_PAPER)) return SCISSORS;
        else if (roundResult.equals(CONDITION_WIN) && opponentAction.equals(OPPONENT_SCISSORS)) return ROCK;
        return ROCK;
    }
}
