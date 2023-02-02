import me.nicodax.Day4;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Day4Test {
    private final Path PATH_TO_INPUT = Paths.get(System.getProperty("user.dir")
                                                         + "\\src\\main\\resources\\input-day4.txt");
    private final Path PATH_TO_TEST_INPUT = Paths.get(System.getProperty("user.dir")
                                                              + "\\src\\main\\resources\\test-input-day4.txt");

    @Test
    @DisplayName("Should return the line parsed as a list of list of integers.")
    public void convertLineToListOfListOfIntegers() {
        Day4 day4 = new Day4();
        String line = "2-4,6-8";
        List<Integer> expectedElf1Sections = asList(2, 4);
        List<Integer> expectedElf2Sections = asList(6, 8);
        List<List<Integer>> expectedResult = asList(expectedElf1Sections, expectedElf2Sections);
        assertEquals(expectedResult, day4.convertLineToListOfListOfIntegers(line));
    }

    @Test
    @DisplayName("Should return false since the given lists do not overlap")
    public void areFullyOverlapped_notContained() {
        Day4 day4 = new Day4();
        List<Integer> elf1Sections = asList(2, 4);
        List<Integer> elf2Sections = asList(6, 8);
        assertFalse(day4.areFullyOverlapped(elf1Sections, elf2Sections));
    }

    @Test
    @DisplayName("Should return true since the given lists do overlap")
    public void areFullyOverlapped_contained() {
        Day4 day4 = new Day4();
        List<Integer> elf1Sections = asList(2, 8);
        List<Integer> elf2Sections = asList(3, 7);
        assertTrue(day4.areFullyOverlapped(elf1Sections, elf2Sections));
    }

    @Test
    @DisplayName("Should read and parse the input file - PART 1")
    public void readAndParseFile_PART_1() {
        Day4 day4 = new Day4();
        day4.readAndParseFile(PATH_TO_TEST_INPUT);
        Integer expectedOverlapCount = 8;
        assertEquals(expectedOverlapCount, day4.getFullyOverlappedCount());
    }

    @Disabled("PART 1 SOLUTION")
    @Test
    public void day4_part1() {
        Day4 day4 = new Day4();
        day4.readAndParseFile(PATH_TO_INPUT);
        System.out.println("PART 1 SOLUTION: " + day4.getFullyOverlappedCount());
    }


    @Test
    @DisplayName("Should return false since the given lists do not overlap")
    public void areAtLeastPartiallyOverlapped_notContained() {
        Day4 day4 = new Day4();
        List<Integer> elf1Sections = asList(2, 4);
        List<Integer> elf2Sections = asList(6, 8);
        assertFalse(day4.areAtLeastPartiallyOverlapped(elf1Sections, elf2Sections));
    }

    @Test
    @DisplayName("Should return true since the given lists do fully overlap")
    public void areAtLeastPartiallyOverlapped_fullyContained() {
        Day4 day4 = new Day4();
        List<Integer> elf1Sections = asList(2, 8);
        List<Integer> elf2Sections = asList(3, 7);
        assertTrue(day4.areAtLeastPartiallyOverlapped(elf1Sections, elf2Sections));
    }

    @Test
    @DisplayName("Should return true since the given lists do partially overlap")
    public void areAtLeastPartiallyOverlapped_partiallyContained() {
        Day4 day4 = new Day4();
        List<Integer> elf1Sections = asList(2, 8);
        List<Integer> elf2Sections = asList(3, 9);
        assertTrue(day4.areAtLeastPartiallyOverlapped(elf1Sections, elf2Sections));
    }

    @Test
    @DisplayName("Should read and parse the input file - PART 2")
    public void readAndParseFile_PART_2() {
        Day4 day4 = new Day4();
        day4.readAndParseFile(PATH_TO_TEST_INPUT);
        Integer expectedFullyOverlappedCount = 8;
        Integer expectedOverlappedCount = 9;
        assertEquals(expectedFullyOverlappedCount, day4.getFullyOverlappedCount());
        assertEquals(expectedOverlappedCount, day4.getOverlappedCount());
    }

    @Disabled("PART 2 SOLUTION")
    @Test
    public void day4_part2() {
        Day4 day4 = new Day4();
        day4.readAndParseFile(PATH_TO_INPUT);
        System.out.println("PART 2 SOLUTION: " + day4.getOverlappedCount());
    }
}
