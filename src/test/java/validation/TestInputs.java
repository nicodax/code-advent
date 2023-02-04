package validation;

import me.nicodax.day1.Day1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestInputs {
    private final Path PATH_TO_DAY_1_TEST_INPUT = Paths.get(System.getProperty("user.dir")
                                                                    + "\\src\\main\\resources\\test-input-day1.txt");
    @Test
    @DisplayName("It should return the list of total carried calories per elf.")
    public void getTotalCaloriesPerElf() {
        Day1 day1 = new Day1();
        List<Integer> expectedTotalCaloriesPerElfList = asList(6000, 4000, 11000, 24000, 10000);

        day1.readAndParseFile(PATH_TO_DAY_1_TEST_INPUT);

        assertEquals(expectedTotalCaloriesPerElfList, day1.getTotalCaloriesPerElfList());
    }

    @Test
    @DisplayName("It should return the the total amount of calories carried by the elf carrying the most calories.")
    public void getTotalCaloriesForHeaviestElf() {
        Day1 day1 = new Day1();
        Integer expectedTotalCalories = 24000;

        day1.readAndParseFile(PATH_TO_DAY_1_TEST_INPUT);

        assertEquals(expectedTotalCalories, day1.getPart1Solution());
    }

    @Test
    @DisplayName("It should return the the total amount of calories carried by the three elves "
            + "carrying the most calories.")
    public void getTotalCaloriesForHeaviestThreeElves() {
        Day1 day1 = new Day1();
        Integer expectedTotalCalories = 45000;

        day1.readAndParseFile(PATH_TO_DAY_1_TEST_INPUT);

        assertEquals(expectedTotalCalories, day1.getPart2Solution());
    }
}
