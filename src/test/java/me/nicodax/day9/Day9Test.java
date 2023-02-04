package me.nicodax.day9;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day9Test {
    private final Path PATH_TO_INPUT = Paths.get(System.getProperty("user.dir")
                                                         + "\\src\\main\\resources\\input-day9.txt");
    private final Path PATH_TO_TEST_INPUT_PART1 = Paths.get(System.getProperty("user.dir")
                                                              + "\\src\\main\\resources\\test-input-day9.txt");
    private final Path PATH_TO_TEST_INPUT_PART2 = Paths.get(System.getProperty("user.dir")
                                                              + "\\src\\main\\resources\\test-input-day9-part2.txt");
    private final Integer PART_1_ROPE_LENGTH = 2;
    private final Integer PART_2_ROPE_LENGTH = 10;
    @Test
    @DisplayName("It should read and parse the test-input for day9")
    public void readAndParseFile_PART1() {
        Day9 day9 = new Day9(PART_1_ROPE_LENGTH);
        day9.readAndParseFile(PATH_TO_TEST_INPUT_PART1);
        assertEquals(13, day9.getRope().getNumberOfPositionsTailVisitedAtLeastOnce());
    }

    @Disabled
    @Test
    public void day9_part1() {
        Day9 day9 = new Day9(PART_1_ROPE_LENGTH);
        day9.readAndParseFile(PATH_TO_INPUT);
        System.out.println("SOLUTION PART 1: " + day9.getRope().getNumberOfPositionsTailVisitedAtLeastOnce());
    }

    @Test
    @DisplayName("It should read and parse the test-input for day9 - part2")
    public void readAndParseFile_PART2() {
        Day9 day9 = new Day9(PART_2_ROPE_LENGTH);
        day9.readAndParseFile(PATH_TO_TEST_INPUT_PART2);
        assertEquals(36, day9.getRope().getNumberOfPositionsTailVisitedAtLeastOnce());
    }

    @Disabled
    @Test
    public void day9_part2() {
        Day9 day9 = new Day9(PART_2_ROPE_LENGTH);
        day9.readAndParseFile(PATH_TO_INPUT);
        System.out.println("SOLUTION PART 2: " + day9.getRope().getNumberOfPositionsTailVisitedAtLeastOnce());
    }
}
