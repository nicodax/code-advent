package me.nicodax.day2;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.util.Arrays.asList;
import static me.nicodax.day2.Day2.CONDITION_DRAW;
import static me.nicodax.day2.Day2.CONDITION_LOSE;
import static me.nicodax.day2.Day2.CONDITION_WIN;
import static me.nicodax.day2.Day2.OPPONENT_PAPER;
import static me.nicodax.day2.Day2.OPPONENT_ROCK;
import static me.nicodax.day2.Day2.OPPONENT_SCISSORS;
import static me.nicodax.day2.Day2.PAPER;
import static me.nicodax.day2.Day2.ROCK;
import static me.nicodax.day2.Day2.SCISSORS;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day2Test {
    private final Path PATH_TO_INPUT = Paths.get(System.getProperty("user.dir")
                                                         + "\\src\\main\\resources\\input-day2.txt");
    private final Path PATH_TO_TEST_INPUT = Paths.get(System.getProperty("user.dir")
                                                              + "\\src\\main\\resources\\test-input-day2.txt");

    @Test
    @DisplayName("Should return the score result of a given round")
    public void getRoundResult() {
        Day2 day2 = new Day2();
        Integer expectedResult_LOSS_ROCK = 1;
        Integer expectedResult_LOSS_PAPER = 2;
        Integer expectedResult_LOSS_SCISSORS = 3;
        Integer expectedResult_DRAW_ROCK = 4;
        Integer expectedResult_DRAW_PAPER = 5;
        Integer expectedResult_DRAW_SCISSORS = 6;
        Integer expectedResult_WIN_ROCK = 7;
        Integer expectedResult_WIN_PAPER = 8;
        Integer expectedResult_WIN_SCISSORS = 9;

        assertEquals(expectedResult_LOSS_ROCK, day2.getRoundResult(ROCK, OPPONENT_PAPER));
        assertEquals(expectedResult_LOSS_PAPER, day2.getRoundResult(PAPER, OPPONENT_SCISSORS));
        assertEquals(expectedResult_LOSS_SCISSORS, day2.getRoundResult(SCISSORS, OPPONENT_ROCK));
        assertEquals(expectedResult_DRAW_ROCK, day2.getRoundResult(ROCK, OPPONENT_ROCK));
        assertEquals(expectedResult_DRAW_PAPER, day2.getRoundResult(PAPER, OPPONENT_PAPER));
        assertEquals(expectedResult_DRAW_SCISSORS, day2.getRoundResult(SCISSORS, OPPONENT_SCISSORS));
        assertEquals(expectedResult_WIN_ROCK, day2.getRoundResult(ROCK, OPPONENT_SCISSORS));
        assertEquals(expectedResult_WIN_PAPER, day2.getRoundResult(PAPER, OPPONENT_ROCK));
        assertEquals(expectedResult_WIN_SCISSORS, day2.getRoundResult(SCISSORS, OPPONENT_PAPER));
    }

    @Test
    @DisplayName("Should parse the file and save my round score for each round in a list - PART 1")
    public void parseFile_PART_1() {
        Day2 day2 = new Day2();
        List<Integer> expectedListOfRPSPointsByRound = asList(9, 1, 2, 5, 5, 4, 4, 9, 3, 9);

        day2.readAndParseFile(PATH_TO_TEST_INPUT, 1);
        assertEquals(expectedListOfRPSPointsByRound, day2.getListOfRPSPointsByRound());
    }

    @Test
    @DisplayName("Should parse the file and save my round score for each round in a list - PART 2")
    public void parseFile_PART_2() {
        Day2 day2 = new Day2();
        List<Integer> expectedListOfRPSPointsByRound = asList(9, 1, 6, 5, 5, 3, 3, 9, 8, 9);

        day2.readAndParseFile(PATH_TO_TEST_INPUT, 2);
        assertEquals(expectedListOfRPSPointsByRound, day2.getListOfRPSPointsByRound());
    }

    @Test
    @DisplayName("Should return the total score of the RPS game")
    public void getTotalScore() {
        Day2 day2 = new Day2();
        List<Integer> listOfRPSPointsByRound = asList(9, 1, 2, 5, 5, 4, 4, 9, 3, 9);
        Integer expectedTotalScore = 51;
        day2.setListOfRPSPointsByRound(listOfRPSPointsByRound);

        assertEquals(expectedTotalScore, day2.getSolution());
    }

    @Disabled
    @Test
    public void day2_part1() {
        Day2 day2 = new Day2();
        day2.readAndParseFile(PATH_TO_INPUT, 1);
        System.out.println("PART 1 SOLUTION: " + day2.getSolution());
    }
    
    @Test
    @DisplayName("Should return my action, given a round result and my opponent's action")
    public void getMyActionFromRoundResult() {
        Day2 day2 = new Day2();
        Character expectedAction_ROCK = 'X';
        Character expectedAction_PAPER = 'Y';
        Character expectedAction_SCISSORS = 'Z';

        assertEquals(expectedAction_ROCK, day2.getMyActionFromRoundResult(CONDITION_LOSE, OPPONENT_PAPER));
        assertEquals(expectedAction_ROCK, day2.getMyActionFromRoundResult(CONDITION_DRAW, OPPONENT_ROCK));
        assertEquals(expectedAction_ROCK, day2.getMyActionFromRoundResult(CONDITION_WIN, OPPONENT_SCISSORS));
        assertEquals(expectedAction_PAPER, day2.getMyActionFromRoundResult(CONDITION_LOSE, OPPONENT_SCISSORS));
        assertEquals(expectedAction_PAPER, day2.getMyActionFromRoundResult(CONDITION_DRAW, OPPONENT_PAPER));
        assertEquals(expectedAction_PAPER, day2.getMyActionFromRoundResult(CONDITION_WIN, OPPONENT_ROCK));
        assertEquals(expectedAction_SCISSORS, day2.getMyActionFromRoundResult(CONDITION_LOSE, OPPONENT_ROCK));
        assertEquals(expectedAction_SCISSORS, day2.getMyActionFromRoundResult(CONDITION_DRAW, OPPONENT_SCISSORS));
        assertEquals(expectedAction_SCISSORS, day2.getMyActionFromRoundResult(CONDITION_WIN, OPPONENT_PAPER));
    }

    @Disabled
    @Test
    public void day2_part2() {
        Day2 day2 = new Day2();
        day2.readAndParseFile(PATH_TO_INPUT, 2);
        System.out.println("PART 2 SOLUTION: " + day2.getSolution());
    }
}
