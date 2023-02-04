package me.nicodax.day6;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day6Test {
    private final Path PATH_TO_INPUT = Paths.get(System.getProperty("user.dir")
                                                         + "\\src\\main\\resources\\input-day6.txt");
    private final Path PATH_TO_TEST_INPUT = Paths.get(System.getProperty("user.dir")
                                                              + "\\src\\main\\resources\\test-input-day6.txt");
    private final Integer PART_1_MARKER_SIZE = 4;

    @Test
    @DisplayName("Should find the first marker index within given line.")
    public void findFirstMarkerIndex() {
        Day6 day6 = new Day6(PART_1_MARKER_SIZE);
        String line = "nfddjzjjjmrjjfttzctzqhqzqbbvhhcfcpcqpcqpccsmsvsswbwzw";
        Integer expectedIndex = 21;
        assertEquals(expectedIndex, day6.findFirstMarkerIndex(line));
    }

    @Test
    @DisplayName("Should read and parse input file.")
    public void readAndParseFile() {
        Day6 day6 = new Day6(PART_1_MARKER_SIZE);
        day6.readAndParseFile(PATH_TO_TEST_INPUT);
        Integer expectedFirstMarkerIndex = 21;
        assertEquals(expectedFirstMarkerIndex, day6.getSolution());
    }

    @Disabled
    @Test
    public void day6_part1() {
        Day6 day6 = new Day6(PART_1_MARKER_SIZE);
        day6.readAndParseFile(PATH_TO_INPUT);
        System.out.println("PART 1 SOLUTION: " + day6.getSolution());
    }

    @Disabled
    @Test
    public void day6_part2() {
        Integer PART_2_MARKER_SIZE = 14;
        Day6 day6 = new Day6(PART_2_MARKER_SIZE);
        day6.readAndParseFile(PATH_TO_INPUT);
        System.out.println("PART 2 SOLUTION: " + day6.getSolution());
    }
}
