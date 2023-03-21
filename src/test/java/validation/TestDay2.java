package validation;

import me.nicodax.day2.Day2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static me.nicodax.day2.RPSAction.PAPER;
import static me.nicodax.day2.RPSAction.ROCK;
import static me.nicodax.day2.RPSAction.SCISSORS;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDay2 {
    private final String PATH_SEPARATOR = File.separator;
    private final Path PATH_TO_TEST_INPUT = Paths.get(System.getProperty("user.dir")
            + PATH_SEPARATOR + "src"
            + PATH_SEPARATOR + "main"
            + PATH_SEPARATOR + "resources"
            + PATH_SEPARATOR + "test-input-day2.txt");
    private final Integer PART_1 = 1;
    private final Integer PART_2 = 2;

    @Test
    @DisplayName("It should return the the score of an RPS round - part 1 round 1.")
    public void getRoundScore_part1Round1() {
        Day2 day2 = new Day2(PART_1);
        day2.setMyAction(PAPER);
        day2.setOpponentAction(ROCK);

        assertEquals(8, day2.getRoundScore());
    }

    @Test
    @DisplayName("It should return the the score of an RPS round - part 1 round 2.")
    public void getRoundScore_part1Round2() {
        Day2 day2 = new Day2(PART_1);
        day2.setMyAction(ROCK);
        day2.setOpponentAction(PAPER);

        assertEquals(1, day2.getRoundScore());
    }

    @Test
    @DisplayName("It should return the the score of an RPS round - part 1 round 3.")
    public void getRoundScore_part1Round3() {
        Day2 day2 = new Day2(PART_1);
        day2.setMyAction(SCISSORS);
        day2.setOpponentAction(SCISSORS);

        assertEquals(6, day2.getRoundScore());
    }

    @Test
    @DisplayName("It should return the the total score of an RPS game - part 1")
    public void getTotalScore_part1() {
        Day2 day2 = new Day2(PART_1);

        day2.readAndParseFile(PATH_TO_TEST_INPUT);

        assertEquals(15, day2.getTotalScore());
    }

    @Test
    @DisplayName("It should return the the score of an RPS round - part 2 round 1.")
    public void getRoundScore_part2Round1() {
        Day2 day2 = new Day2(PART_2);
        day2.setMyAction(ROCK);
        day2.setOpponentAction(ROCK);

        assertEquals(4, day2.getRoundScore());
    }

    @Test
    @DisplayName("It should return the the score of an RPS round - part 2 round 2.")
    public void getRoundScore_part2Round2() {
        Day2 day2 = new Day2(PART_2);
        day2.setMyAction(ROCK);
        day2.setOpponentAction(PAPER);

        assertEquals(1, day2.getRoundScore());
    }

    @Test
    @DisplayName("It should return the the score of an RPS round - part 2 round 3.")
    public void getRoundScore_part2Round3() {
        Day2 day2 = new Day2(PART_2);
        day2.setMyAction(ROCK);
        day2.setOpponentAction(SCISSORS);

        assertEquals(7, day2.getRoundScore());
    }

    @Test
    @DisplayName("It should return the the total score of an RPS game - part 2")
    public void getTotalScore_part2() {
        Day2 day2 = new Day2(PART_2);

        day2.readAndParseFile(PATH_TO_TEST_INPUT);

        assertEquals(12, day2.getTotalScore());
    }
}
