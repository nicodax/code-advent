package validation;

import me.nicodax.day6.Day6;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDay6 {
    private final Path PATH_TO_TEST_INPUT_1 = Paths.get(System.getProperty("user.dir")
                                                                + "\\src\\main\\resources\\test-input1-day6.txt");
    private final Path PATH_TO_TEST_INPUT_2 = Paths.get(System.getProperty("user.dir")
                                                                + "\\src\\main\\resources\\test-input2-day6.txt");
    private final Path PATH_TO_TEST_INPUT_3 = Paths.get(System.getProperty("user.dir")
                                                                + "\\src\\main\\resources\\test-input3-day6.txt");
    private final Path PATH_TO_TEST_INPUT_4 = Paths.get(System.getProperty("user.dir")
                                                                + "\\src\\main\\resources\\test-input4-day6.txt");
    private final Path PATH_TO_TEST_INPUT_5 = Paths.get(System.getProperty("user.dir")
                                                                + "\\src\\main\\resources\\test-input5-day6.txt");

    @Test
    @DisplayName("It should return the first character index after the first start-of-packet marker - input 1 part 1")
    public void findFirstMarkerIndex_input1_part1() {
        Day6 day6 = new Day6(4);
        day6.readAndParseFile(PATH_TO_TEST_INPUT_1);
        assertEquals(7, day6.getSolution());
    }

    @Test
    @DisplayName("It should return the first character index after the first start-of-packet marker - input 2 part 1")
    public void findFirstMarkerIndex_input2_part1() {
        Day6 day6 = new Day6(4);
        day6.readAndParseFile(PATH_TO_TEST_INPUT_2);
        assertEquals(5, day6.getSolution());
    }

    @Test
    @DisplayName("It should return the first character index after the first start-of-packet marker - input 3 part 1")
    public void findFirstMarkerIndex_input3_part1() {
        Day6 day6 = new Day6(4);
        day6.readAndParseFile(PATH_TO_TEST_INPUT_3);
        assertEquals(6, day6.getSolution());
    }

    @Test
    @DisplayName("It should return the first character index after the first start-of-packet marker - input 4 part 1")
    public void findFirstMarkerIndex_input4_part1() {
        Day6 day6 = new Day6(4);
        day6.readAndParseFile(PATH_TO_TEST_INPUT_4);
        assertEquals(10, day6.getSolution());
    }

    @Test
    @DisplayName("It should return the first character index after the first start-of-packet marker - input 5 part 1")
    public void findFirstMarkerIndex_input5_part1() {
        Day6 day6 = new Day6(4);
        day6.readAndParseFile(PATH_TO_TEST_INPUT_5);
        assertEquals(11, day6.getSolution());
    }

    @Test
    @DisplayName("It should return the first character index after the first start-of-packet marker - input 1 part 2")
    public void findFirstMarkerIndex_input1_part2() {
        Day6 day6 = new Day6(14);
        day6.readAndParseFile(PATH_TO_TEST_INPUT_1);
        assertEquals(19, day6.getSolution());
    }

    @Test
    @DisplayName("It should return the first character index after the first start-of-packet marker - input 2 part 2")
    public void findFirstMarkerIndex_input2_part2() {
        Day6 day6 = new Day6(14);
        day6.readAndParseFile(PATH_TO_TEST_INPUT_2);
        assertEquals(23, day6.getSolution());
    }

    @Test
    @DisplayName("It should return the first character index after the first start-of-packet marker - input 3 part 2")
    public void findFirstMarkerIndex_input3_part2() {
        Day6 day6 = new Day6(14);
        day6.readAndParseFile(PATH_TO_TEST_INPUT_3);
        assertEquals(23, day6.getSolution());
    }

    @Test
    @DisplayName("It should return the first character index after the first start-of-packet marker - input 4 part 2")
    public void findFirstMarkerIndex_input4_part2() {
        Day6 day6 = new Day6(14);
        day6.readAndParseFile(PATH_TO_TEST_INPUT_4);
        assertEquals(29, day6.getSolution());
    }

    @Test
    @DisplayName("It should return the first character index after the first start-of-packet marker - input 5 part 2")
    public void findFirstMarkerIndex_input5_part2() {
        Day6 day6 = new Day6(14);
        day6.readAndParseFile(PATH_TO_TEST_INPUT_5);
        assertEquals(26, day6.getSolution());
    }
}
