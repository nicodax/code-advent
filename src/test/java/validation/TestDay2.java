package validation;

import me.nicodax.day2.Day2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static me.nicodax.day2.RPSAction.PAPER;
import static me.nicodax.day2.RPSAction.ROCK;
import static me.nicodax.day2.RPSAction.SCISSORS;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDay2 {
    private final Path PATH_TO_TEST_INPUT = Paths.get(System.getProperty("user.dir")
                                                              + "\\src\\main\\resources\\test-input-day2.txt");

    @Test
    @DisplayName("It should return the the score of an RPS round - part 1 round 1.")
    public void getRoundScore_part1Round1() {
        Day2 day2 = new Day2(1);
        Integer expectedScore = 8;

        assertEquals(expectedScore, day2.getRoundScore(PAPER, ROCK));
    }

    @Test
    @DisplayName("It should return the the score of an RPS round - part 1 round 2.")
    public void getRoundScore_part1Round2() {
        Day2 day2 = new Day2(1);
        Integer expectedScore = 1;

        assertEquals(expectedScore, day2.getRoundScore(ROCK, PAPER));
    }

    @Test
    @DisplayName("It should return the the score of an RPS round - part 1 round 3.")
    public void getRoundScore_part1Round3() {
        Day2 day2 = new Day2(1);
        Integer expectedScore = 6;

        assertEquals(expectedScore, day2.getRoundScore(SCISSORS, SCISSORS));
    }

    @Test
    @DisplayName("It should return the the total score of an RPS game - part 1")
    public void getTotalScore_part1() {
        Day2 day2 = new Day2(1);
        Integer expectedScore = 15;

        day2.readAndParseFile(PATH_TO_TEST_INPUT);

        assertEquals(expectedScore, day2.getTotalScore());
    }

    @Test
    @DisplayName("It should return the the score of an RPS round - part 2 round 1.")
    public void getRoundScore_part2Round1() {
        Day2 day2 = new Day2(2);
        Integer expectedScore = 4;

        assertEquals(expectedScore, day2.getRoundScore(ROCK, ROCK));
    }

    @Test
    @DisplayName("It should return the the score of an RPS round - part 2 round 2.")
    public void getRoundScore_part2Round2() {
        Day2 day2 = new Day2(2);
        Integer expectedScore = 1;

        assertEquals(expectedScore, day2.getRoundScore(ROCK, PAPER));
    }

    @Test
    @DisplayName("It should return the the score of an RPS round - part 2 round 3.")
    public void getRoundScore_part2Round3() {
        Day2 day2 = new Day2(2);
        Integer expectedScore = 7;

        assertEquals(expectedScore, day2.getRoundScore(ROCK, SCISSORS));
    }

    @Test
    @DisplayName("It should return the the total score of an RPS game - part 2")
    public void getTotalScore_part2() {
        Day2 day2 = new Day2(2);
        Integer expectedScore = 12;

        day2.readAndParseFile(PATH_TO_TEST_INPUT);

        assertEquals(expectedScore, day2.getTotalScore());
    }
}
