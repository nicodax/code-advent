import me.nicodax.Day1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Day1Test {
    private final Path PATH_TO_INPUT = Paths.get("C:\\Users\\Nicolas Daxhelet\\Documents\\code-advent"
                                                         + "\\src\\main\\resources\\input-day1.txt");
    private final Path PATH_TO_TEST_INPUT = Paths.get("C:\\Users\\Nicolas Daxhelet\\Documents\\code-advent"
                                                         + "\\src\\main\\resources\\test-input-day1.txt");

    @Test
    @DisplayName("Should retrieve the list of total carried calories per elf and return the maximum value")
    public void getMaxCalories() {
        Day1 day1 = new Day1();
        List<Integer> fakeListOfCaloriesPerElf = asList(1000, 2000, 4000, 6000, 3000, 8000, 7000);
        Integer expectedMaxCalories = 8000;
        day1.setListOfCaloriesPerElf(fakeListOfCaloriesPerElf);

        assertEquals(expectedMaxCalories, day1.getMaxCalories());
    }

    @Test
    @DisplayName("Should parse the file and save total carried calories per elf in a list")
    public void parseFile() {
        Day1 day1 = new Day1();
        List<Integer> expectedListOfCaloriesPerElf = asList(17000, 24000, 15000);

        day1.readAndParseFile(PATH_TO_TEST_INPUT);
        assertEquals(expectedListOfCaloriesPerElf, day1.getListOfCaloriesPerElf());
    }

    @Test
    @DisplayName("Should be able to digest the input file and return the maximum calories carried by a single elf")
    public void day1_part1() {
        Day1 day1 = new Day1();
        day1.readAndParseFile(PATH_TO_INPUT);
        System.out.println(day1.getMaxCalories());
    }

    @Test
    @DisplayName("Should retrieve the list of total carried calories per elf and return the three biggest values")
    public void getTopThreeSumCalories() {
        Day1 day1 = new Day1();
        List<Integer> fakeListOfCaloriesPerElf = asList(1000, 2000, 4000, 6000, 3000, 8000, 7000);
        Integer expectedMaxCalories = 8000 + 7000 + 6000;
        day1.setListOfCaloriesPerElf(fakeListOfCaloriesPerElf);

        assertEquals(expectedMaxCalories, day1.getTopThreeSumCalories());
    }

    @Test
    @DisplayName("Should be able to digest the input file and return the sum of calories carried by "
            + "the three elves carrying the most calories")
    public void day1_part2() {
        Day1 day1 = new Day1();
        day1.readAndParseFile(PATH_TO_INPUT);
        System.out.println(day1.getTopThreeSumCalories());
    }

}
