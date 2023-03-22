package validation;

import me.nicodax.day10.Day10;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDay10 {
    private final String PATH_SEPARATOR = File.separator;
    private final Path PATH_TO_TEST_INPUT_1 = getPathToTestInput(1);
    private final Path PATH_TO_TEST_INPUT_2 = getPathToTestInput(2);
    private Path getPathToTestInput(Integer sectionNumber) {
        return Paths.get(System.getProperty("user.dir")
                + PATH_SEPARATOR + "src"
                + PATH_SEPARATOR + "main"
                + PATH_SEPARATOR + "resources"
                + PATH_SEPARATOR + "test-input" + sectionNumber + "-day10.txt");
    }

    @Test
    @DisplayName("Should return the value of the X register after given cycle - test input 1")
    public void getXRegisterValueAfterGivenCycle_input1() {
        Day10 day10 = new Day10();
        day10.readAndParseFile(PATH_TO_TEST_INPUT_1);

        assertEquals(1, day10.getXRegisterValueAfterCycle(1));

        assertEquals(1, day10.getXRegisterValueAfterCycle(2));
        assertEquals(1, day10.getXRegisterValueDuringCycle(3));
        assertEquals(4, day10.getXRegisterValueAfterCycle(3));

        assertEquals(4, day10.getXRegisterValueAfterCycle(4));
        assertEquals(4, day10.getXRegisterValueDuringCycle(5));
        assertEquals(-1, day10.getXRegisterValueAfterCycle(5));
    }

    @Test
    @DisplayName("Should return the value of the X register after given cycle - test input 2")
    public void getXRegisterValueDuringGivenCycle_input2() {
        Day10 day10 = new Day10();
        day10.readAndParseFile(PATH_TO_TEST_INPUT_2);

        assertEquals(21, day10.getXRegisterValueDuringCycle(20));
        assertEquals(19, day10.getXRegisterValueDuringCycle(60));
        assertEquals(18, day10.getXRegisterValueDuringCycle(100));
        assertEquals(21, day10.getXRegisterValueDuringCycle(140));
        assertEquals(16, day10.getXRegisterValueDuringCycle(180));
        assertEquals(18, day10.getXRegisterValueDuringCycle(220));
    }

    @Test
    @DisplayName("Should return the signal strength after given cycle")
    public void getSignalStrengthAfterGivenCycle() {
        Day10 day10 = new Day10();
        day10.readAndParseFile(PATH_TO_TEST_INPUT_2);

        assertEquals(420, day10.getSignalStrengthAfterCycle(20));
        assertEquals(1140, day10.getSignalStrengthAfterCycle(60));
        assertEquals(1800, day10.getSignalStrengthAfterCycle(100));
        assertEquals(2940, day10.getSignalStrengthAfterCycle(140));
        assertEquals(2880, day10.getSignalStrengthAfterCycle(180));
        assertEquals(3960, day10.getSignalStrengthAfterCycle(220));
    }

    @Test
    @DisplayName("Should return the summed signal strength of cycle 20 and every 40 cycle after that")
    public void getSumOfEvery20PlusNTimes40CycleSignalStrength() {
        Day10 day10 = new Day10();
        day10.readAndParseFile(PATH_TO_TEST_INPUT_2);

        assertEquals(13140, day10.getPart1Solution());
    }
}