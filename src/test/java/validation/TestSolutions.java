package validation;

import me.nicodax.day1.Day1;
import me.nicodax.day10.Day10;
import me.nicodax.day2.Day2;
import me.nicodax.day3.Day3;
import me.nicodax.day4.Day4;
import me.nicodax.day5.Day5;
import me.nicodax.day6.Day6;
import me.nicodax.day7.Day7;
import me.nicodax.day8.Day8;
import me.nicodax.day9.Day9;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSolutions {
    private final String PATH_SEPARATOR = File.separator;
    private final Path PATH_TO_DAY_1_INPUT = getPathToInput(1);
    private final Path PATH_TO_DAY_2_INPUT = getPathToInput(2);
    private final Path PATH_TO_DAY_3_INPUT = getPathToInput(3);
    private final Path PATH_TO_DAY_4_INPUT = getPathToInput(4);
    private final Path PATH_TO_DAY_5_INPUT = getPathToInput(5);
    private final Path PATH_TO_DAY_6_INPUT = getPathToInput(6);
    private final Path PATH_TO_DAY_7_INPUT = getPathToInput(7);
    private final Path PATH_TO_DAY_8_INPUT = getPathToInput(8);
    private final Path PATH_TO_DAY_9_INPUT = getPathToInput(9);
    private final Path PATH_TO_DAY_10_INPUT = getPathToInput(10);

    private Path getPathToInput(Integer dayNumber) {
        return Paths.get(System.getProperty("user.dir")
                + PATH_SEPARATOR + "src"
                + PATH_SEPARATOR + "main"
                + PATH_SEPARATOR + "resources"
                + PATH_SEPARATOR + "input-day" + dayNumber + ".txt");
    }

    @Test
    @DisplayName("How many total Calories is that Elf carrying?")
    public void day1Part1Solution() {
        Day1 day1 = new Day1();
        day1.readAndParseFile(PATH_TO_DAY_1_INPUT);
        assertEquals(72602, day1.getPart1Solution());
    }

    @Test
    @DisplayName("How many Calories are those Elves carrying in total?")
    public void day1Part2Solution() {
        Day1 day1 = new Day1();
        day1.readAndParseFile(PATH_TO_DAY_1_INPUT);
        assertEquals(207410, day1.getPart2Solution());
    }

    @Test
    @DisplayName("What would your total score be if everything goes exactly according to your strategy guide?")
    public void day2Part1Solution() {
        Day2 day2 = new Day2(1);
        day2.readAndParseFile(PATH_TO_DAY_2_INPUT);
        assertEquals(11666, day2.getSolution());
    }

    @Test
    @DisplayName("What would your total score be if everything goes exactly according to your strategy guide?")
    public void day2Part2Solution() {
        Day2 day2 = new Day2(2);
        day2.readAndParseFile(PATH_TO_DAY_2_INPUT);
        assertEquals(12767, day2.getSolution());
    }

    @Test
    @DisplayName("What is the sum of the priorities of those item types?")
    public void day3Part1Solution() {
        Day3 day3 = new Day3();
        day3.readAndParseFile(PATH_TO_DAY_3_INPUT);
        assertEquals(8233, day3.getPart1Solution());
    }

    @Test
    @DisplayName("What is the sum of the priorities of those item types?")
    public void day3Part2Solution() {
        Day3 day3 = new Day3();
        day3.readAndParseFile(PATH_TO_DAY_3_INPUT);
        assertEquals(2821, day3.getPart2Solution());
    }

    @Test
    @DisplayName("In how many assignment pairs does one range fully contain the other?")
    public void day4Part1Solution() {
        Day4 day4 = new Day4();
        day4.readAndParseFile(PATH_TO_DAY_4_INPUT);
        assertEquals(602, day4.getPart1Solution());
    }

    @Test
    @DisplayName("In how many assignment pairs do the ranges overlap?")
    public void day4Part2Solution() {
        Day4 day4 = new Day4();
        day4.readAndParseFile(PATH_TO_DAY_4_INPUT);
        assertEquals(891, day4.getPart2Solution());
    }

    @Test
    @DisplayName("After the rearrangement procedure completes, what crate ends up on top of each stack?")
    public void day5Part1Solution() {
        Day5 day5 = new Day5(1);
        day5.readAndParseFile(PATH_TO_DAY_5_INPUT);
        assertEquals("CFFHVVHNC", day5.getSolution());
    }

    @Test
    @DisplayName("After the rearrangement procedure completes, what crate ends up on top of each stack?")
    public void day5Part2Solution() {
        Day5 day5 = new Day5(2);
        day5.readAndParseFile(PATH_TO_DAY_5_INPUT);
        assertEquals("FSZWBPTBG", day5.getSolution());
    }

    @Test
    @DisplayName("How many characters need to be processed before the first start-of-packet marker is detected?")
    public void day6Part1Solution() {
        Day6 day6 = new Day6(4);
        day6.readAndParseFile(PATH_TO_DAY_6_INPUT);
        assertEquals(1210, day6.getSolution());
    }

    @Test
    @DisplayName("How many characters need to be processed before the first start-of-message marker is detected?")
    public void day6Part2Solution() {
        Day6 day6 = new Day6(14);
        day6.readAndParseFile(PATH_TO_DAY_6_INPUT);
        assertEquals(3476, day6.getSolution());
    }

    @Test
    @DisplayName("What is the sum of the total sizes of those directories?")
    public void day7Part1Solution() {
        Day7 day7 = new Day7();
        day7.readAndParseFile(PATH_TO_DAY_7_INPUT);
        assertEquals(1477771, day7.getPart1Solution());
    }

    @Test
    @DisplayName("What is the total size of that directory?")
    public void day7Part2Solution() {
        Day7 day7 = new Day7();
        day7.readAndParseFile(PATH_TO_DAY_7_INPUT);
        assertEquals(3579501, day7.getPart2Solution());
    }

    @Test
    @DisplayName("How many trees are visible from outside the grid?")
    public void day8Part1Solution() {
        Day8 day8 = new Day8();
        day8.readAndParseFile(PATH_TO_DAY_8_INPUT);
        assertEquals(1679, day8.getPart1Solution());
    }

    @Test
    @DisplayName("What is the highest scenic score possible for any tree?")
    public void day8Part2Solution() {
        Day8 day8 = new Day8();
        day8.readAndParseFile(PATH_TO_DAY_8_INPUT);
        assertEquals(536625, day8.getPart2Solution());
    }

    @Test
    @DisplayName("How many positions does the tail of the rope visit at least once? (PART 1)")
    public void day9Part1Solution() {
        Day9 day9 = new Day9(2);
        day9.readAndParseFile(PATH_TO_DAY_9_INPUT);
        assertEquals(6494, day9.getSolution());
    }

    @Test
    @DisplayName("How many positions does the tail of the rope visit at least once? (PART 2)")
    public void day9Part2Solution() {
        Day9 day9 = new Day9(10);
        day9.readAndParseFile(PATH_TO_DAY_9_INPUT);
        assertEquals(2691, day9.getSolution());
    }

    @Test
    @DisplayName("What is the sum of these six signal strengths?")
    public void day10Part1Solution() {
        Day10 day10 = new Day10();
        day10.readAndParseFile(PATH_TO_DAY_10_INPUT);
        assertEquals(14780, day10.getPart1Solution());
    }
}
