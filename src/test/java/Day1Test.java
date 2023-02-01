import me.nicodax.Day1;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day1Test {
    private final Path PATH_TO_INPUT = Paths.get(System.getProperty("user.dir")
                                                         + "\\src\\main\\resources\\input-day1.txt");
    private final Path PATH_TO_TEST_INPUT = Paths.get(System.getProperty("user.dir")
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

    @Disabled("PART 1 SOLUTION")
    @Test
    public void day1_part1() {
        Day1 day1 = new Day1();
        day1.readAndParseFile(PATH_TO_INPUT);
        System.out.println("PART 1 SOLUTION: " + day1.getMaxCalories());
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

    @Disabled("PART 2 SOLUTION")
    @Test
    public void day1_part2() {
        Day1 day1 = new Day1();
        day1.readAndParseFile(PATH_TO_INPUT);
        System.out.println("PART 2 SOLUTION: " + day1.getTopThreeSumCalories());
    }

}
