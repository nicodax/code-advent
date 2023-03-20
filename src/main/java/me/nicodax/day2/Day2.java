package me.nicodax.day2;

import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static me.nicodax.day2.RPSAction.PAPER;
import static me.nicodax.day2.RPSAction.ROCK;
import static me.nicodax.day2.RPSAction.SCISSORS;
import static me.nicodax.day2.RPSAction.fromAlias;
import static me.nicodax.day2.RPSAction.fromResultAndOpponentAction;

public class Day2 {
    private final Integer PART_NUMBER;
    @Getter
    private Integer totalScore;

    public Day2(Integer partNumber) {
        this.PART_NUMBER = partNumber;
        this.totalScore = 0;
    }

    public void readAndParseFile(Path path) {
        try (Stream<String> lines = Files.lines(path)) {
            for (String line : lines.toList()) {
                if (line.isBlank()) continue;
                List<String> args = stream(line.trim().split(" ")).toList();
                RPSAction myAction;
                RPSAction opponentAction = fromAlias(args.get(0));
                if (PART_NUMBER.equals(1)) {
                    // if it is parsing for part 1, my action is the second character from the current line
                    myAction = fromAlias(args.get(1));
                } else {
                    // if it is parsing for part 2, the round result is the second character from the current line
                    opponentAction = fromAlias(args.get(0));
                    myAction = fromResultAndOpponentAction(RPSResult.fromAlias(args.get(1)), opponentAction);
                }
                totalScore += getRoundScore(myAction, opponentAction);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public Integer getRoundScore(RPSAction myAction, RPSAction opponentAction) {
        // get round score from both players' action
        if (myAction.equals(ROCK) && opponentAction.equals(PAPER)) return 1;
        else if (myAction.equals(PAPER) && opponentAction.equals(SCISSORS)) return 2;
        else if (myAction.equals(SCISSORS) && opponentAction.equals(ROCK)) return 3;
        else if (myAction.equals(ROCK) && opponentAction.equals(ROCK)) return 4;
        else if (myAction.equals(PAPER) && opponentAction.equals(PAPER)) return 5;
        else if (myAction.equals(SCISSORS) && opponentAction.equals(SCISSORS)) return 6;
        else if (myAction.equals(ROCK) && opponentAction.equals(SCISSORS)) return 7;
        else if (myAction.equals(PAPER) && opponentAction.equals(ROCK)) return 8;
        else return 9;
    }

    public Integer getSolution() {
        return totalScore;
    }
}
