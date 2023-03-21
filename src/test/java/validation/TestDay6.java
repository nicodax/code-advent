package validation;

import me.nicodax.day6.Day6;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDay6 {
    private final String PATH_SEPARATOR = File.separator;
    private final Path PATH_TO_TEST_INPUT_1 = getPathToTestInput(1);
    private final Path PATH_TO_TEST_INPUT_2 = getPathToTestInput(2);
    private final Path PATH_TO_TEST_INPUT_3 = getPathToTestInput(3);
    private final Path PATH_TO_TEST_INPUT_4 = getPathToTestInput(4);
    private final Path PATH_TO_TEST_INPUT_5 = getPathToTestInput(5);
    private final Integer MARKER_SIZE_PART_1 = 4;
    private final Integer MARKER_SIZE_PART_2 = 14;

    private Path getPathToTestInput(Integer inputNumber) {
        return Paths.get(System.getProperty("user.dir")
                + PATH_SEPARATOR + "src"
                + PATH_SEPARATOR + "main"
                + PATH_SEPARATOR + "resources"
                + PATH_SEPARATOR + "test-input" + inputNumber + "-day6.txt");
    }

    @Test
    @DisplayName("It should return the first character index after the first start-of-packet marker - input 1 part 1")
    public void findFirstMarkerIndex_input1_part1() {
        Day6 day6 = new Day6(MARKER_SIZE_PART_1);
        day6.readAndParseFile(PATH_TO_TEST_INPUT_1);
        assertEquals(7, day6.getSolution());
    }

    @Test
    @DisplayName("It should return the first character index after the first start-of-packet marker - input 2 part 1")
    public void findFirstMarkerIndex_input2_part1() {
        Day6 day6 = new Day6(MARKER_SIZE_PART_1);
        day6.readAndParseFile(PATH_TO_TEST_INPUT_2);
        assertEquals(5, day6.getSolution());
    }

    @Test
    @DisplayName("It should return the first character index after the first start-of-packet marker - input 3 part 1")
    public void findFirstMarkerIndex_input3_part1() {
        Day6 day6 = new Day6(MARKER_SIZE_PART_1);
        day6.readAndParseFile(PATH_TO_TEST_INPUT_3);
        assertEquals(6, day6.getSolution());
    }

    @Test
    @DisplayName("It should return the first character index after the first start-of-packet marker - input 4 part 1")
    public void findFirstMarkerIndex_input4_part1() {
        Day6 day6 = new Day6(MARKER_SIZE_PART_1);
        day6.readAndParseFile(PATH_TO_TEST_INPUT_4);
        assertEquals(10, day6.getSolution());
    }

    @Test
    @DisplayName("It should return the first character index after the first start-of-packet marker - input 5 part 1")
    public void findFirstMarkerIndex_input5_part1() {
        Day6 day6 = new Day6(MARKER_SIZE_PART_1);
        day6.readAndParseFile(PATH_TO_TEST_INPUT_5);
        assertEquals(11, day6.getSolution());
    }

    @Test
    @DisplayName("It should return the first character index after the first start-of-packet marker - input 1 part 2")
    public void findFirstMarkerIndex_input1_part2() {
        Day6 day6 = new Day6(MARKER_SIZE_PART_2);
        day6.readAndParseFile(PATH_TO_TEST_INPUT_1);
        assertEquals(19, day6.getSolution());
    }

    @Test
    @DisplayName("It should return the first character index after the first start-of-packet marker - input 2 part 2")
    public void findFirstMarkerIndex_input2_part2() {
        Day6 day6 = new Day6(MARKER_SIZE_PART_2);
        day6.readAndParseFile(PATH_TO_TEST_INPUT_2);
        assertEquals(23, day6.getSolution());
    }

    @Test
    @DisplayName("It should return the first character index after the first start-of-packet marker - input 3 part 2")
    public void findFirstMarkerIndex_input3_part2() {
        Day6 day6 = new Day6(MARKER_SIZE_PART_2);
        day6.readAndParseFile(PATH_TO_TEST_INPUT_3);
        assertEquals(23, day6.getSolution());
    }

    @Test
    @DisplayName("It should return the first character index after the first start-of-packet marker - input 4 part 2")
    public void findFirstMarkerIndex_input4_part2() {
        Day6 day6 = new Day6(MARKER_SIZE_PART_2);
        day6.readAndParseFile(PATH_TO_TEST_INPUT_4);
        assertEquals(29, day6.getSolution());
    }

    @Test
    @DisplayName("It should return the first character index after the first start-of-packet marker - input 5 part 2")
    public void findFirstMarkerIndex_input5_part2() {
        Day6 day6 = new Day6(MARKER_SIZE_PART_2);
        day6.readAndParseFile(PATH_TO_TEST_INPUT_5);
        assertEquals(26, day6.getSolution());
    }
}
