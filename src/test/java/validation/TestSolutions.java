package validation;

import me.nicodax.day1.Day1;
import me.nicodax.day2.Day2;
import me.nicodax.day3.Day3;
import me.nicodax.day4.Day4;
import me.nicodax.day4.Day4Bak;
import me.nicodax.day5.Day5;
import me.nicodax.day6.Day6;
import me.nicodax.day7.Day7;
import me.nicodax.day8.Day8;
import me.nicodax.day9.Day9;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSolutions {
    private final Path PATH_TO_DAY_1_INPUT = Paths.get(System.getProperty("user.dir")
                                                               + "\\src\\main\\resources\\input-day1.txt");
    private final Path PATH_TO_DAY_2_INPUT = Paths.get(System.getProperty("user.dir")
                                                               + "\\src\\main\\resources\\input-day2.txt");
    private final Path PATH_TO_DAY_3_INPUT = Paths.get(System.getProperty("user.dir")
                                                               + "\\src\\main\\resources\\input-day3.txt");
    private final Path PATH_TO_DAY_4_INPUT = Paths.get(System.getProperty("user.dir")
                                                               + "\\src\\main\\resources\\input-day4.txt");
    private final Path PATH_TO_DAY_5_INPUT = Paths.get(System.getProperty("user.dir")
                                                               + "\\src\\main\\resources\\input-day5.txt");
    private final Path PATH_TO_DAY_6_INPUT = Paths.get(System.getProperty("user.dir")
                                                               + "\\src\\main\\resources\\input-day6.txt");
    private final Path PATH_TO_DAY_7_INPUT = Paths.get(System.getProperty("user.dir")
                                                               + "\\src\\main\\resources\\input-day7.txt");
    private final Path PATH_TO_DAY_8_INPUT = Paths.get(System.getProperty("user.dir")
                                                               + "\\src\\main\\resources\\input-day8.txt");
    private final Path PATH_TO_DAY_9_INPUT = Paths.get(System.getProperty("user.dir")
                                                               + "\\src\\main\\resources\\input-day9.txt");

    @Test
    public void day1Part1Solution() {
        Day1 day1 = new Day1();
        day1.readAndParseFile(PATH_TO_DAY_1_INPUT);
        assertEquals(72602, day1.getPart1Solution());
    }

    @Test
    public void day1Part2Solution() {
        Day1 day1 = new Day1();
        day1.readAndParseFile(PATH_TO_DAY_1_INPUT);
        assertEquals(207410, day1.getPart2Solution());
    }

    @Test
    public void day2Part1Solution() {
        Day2 day2 = new Day2(1);
        day2.readAndParseFile(PATH_TO_DAY_2_INPUT);
        assertEquals(11666, day2.getSolution());
    }

    @Test
    public void day2Part2Solution() {
        Day2 day2 = new Day2(2);
        day2.readAndParseFile(PATH_TO_DAY_2_INPUT);
        assertEquals(12767, day2.getSolution());
    }

    @Test
    public void day3Part1Solution() {
        Day3 day3 = new Day3();
        day3.readAndParseFile(PATH_TO_DAY_3_INPUT);
        assertEquals(8233, day3.getPart1Solution());
    }

    @Test
    public void day3Part2Solution() {
        Day3 day3 = new Day3();
        day3.readAndParseFile(PATH_TO_DAY_3_INPUT);
        assertEquals(2821, day3.getPart2Solution());
    }

    @Test
    public void day4Part1Solution() {
        Day4 day4 = new Day4();
        day4.readAndParseFile(PATH_TO_DAY_4_INPUT);
        assertEquals(602, day4.getPart1Solution());
    }

    @Test
    public void day4Part2Solution() {
        Day4 day4 = new Day4();
        day4.readAndParseFile(PATH_TO_DAY_4_INPUT);
        assertEquals(891, day4.getPart2Solution());
    }

    @Test
    public void day5Part1Solution() {
        Day5 day5 = new Day5();
        day5.readAndParseFile(PATH_TO_DAY_5_INPUT, 1);
        assertEquals("CFFHVVHNC", day5.getSolution());
    }

    @Test
    public void day5Part2Solution() {
        Day5 day5 = new Day5();
        day5.readAndParseFile(PATH_TO_DAY_5_INPUT, 2);
        assertEquals("FSZWBPTBG", day5.getSolution());
    }

    @Test
    public void day6_part1() {
        Day6 day6 = new Day6(4);
        day6.readAndParseFile(PATH_TO_DAY_6_INPUT);
        assertEquals(1210, day6.getSolution());
    }

    @Test
    public void day6_part2() {
        Day6 day6 = new Day6(14);
        day6.readAndParseFile(PATH_TO_DAY_6_INPUT);
        assertEquals(3476, day6.getSolution());
    }

    @Test
    public void day7Part1Solution() {
        Day7 day7 = new Day7();
        day7.readAndParseFile(PATH_TO_DAY_7_INPUT);
        assertEquals(1477771, day7.getPart1Solution());
    }

    @Test
    public void day7_part2() {
        Day7 day7 = new Day7();
        day7.readAndParseFile(PATH_TO_DAY_7_INPUT);
        assertEquals(3579501, day7.getPart2Solution());
    }

    @Test
    public void day8Part1Solution() {
        Day8 day8 = new Day8();
        day8.readAndParseFile(PATH_TO_DAY_8_INPUT);
        assertEquals(1679, day8.getPart1Solution());
    }

    @Test
    public void day8Part2Solution() {
        Day8 day8 = new Day8();
        day8.readAndParseFile(PATH_TO_DAY_8_INPUT);
        assertEquals(536625, day8.getPart2Solution());
    }

    @Test
    public void day9Part1Solution() {
        Day9 day9 = new Day9(2);
        day9.readAndParseFile(PATH_TO_DAY_9_INPUT);
        assertEquals(6494, day9.getSolution());
    }

    @Test
    public void day9_part2() {
        Day9 day9 = new Day9(10);
        day9.readAndParseFile(PATH_TO_DAY_9_INPUT);
        assertEquals(2691, day9.getSolution());
    }
}
