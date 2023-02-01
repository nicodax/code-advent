import me.nicodax.Day3;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day3Test {
    private final Path PATH_TO_INPUT = Paths.get(System.getProperty("user.dir")
                                                         + "\\src\\main\\resources\\input-day3.txt");
    private final Path PATH_TO_TEST_INPUT = Paths.get(System.getProperty("user.dir")
                                                              + "\\src\\main\\resources\\test-input-day3.txt");

    @Test
    @DisplayName("Should return a two item list. "
            + "The first item is a substring with every elements in compartment 1; "
            + "The second item is a substring with every elements in compartment 2")
    public void splitRucksackInventoryIntoCompartments() {
        Day3 day3 = new Day3();
        String rucksackInventory = "vJrwpWtwJgWrhcsFMMfFFhFp";
        List<String> expectedRucksackCompartmentInventory = asList("vJrwpWtwJgWr", "hcsFMMfFFhFp");
        assertEquals(expectedRucksackCompartmentInventory,
                     day3.splitRucksackInventoryIntoCompartments(rucksackInventory));
    }

    @Test
    @DisplayName("Should return the item present in both compartments.")
    public void findItemSharedBetweenCompartments() {
        Day3 day3 = new Day3();
        List<String> rucksackCompartmentInventory = asList("vJrwpWtwJgWr", "hcsFMMfFFhFp");
        Character expectedSharedItem = 'p';
        assertEquals(expectedSharedItem, day3.findItemSharedBetweenCompartments(rucksackCompartmentInventory.get(0),
                                                                                rucksackCompartmentInventory.get(1)));
    }

    @Test
    @DisplayName("Should map an item to its priority.")
    public void mapItemToPriority() {
        Day3 day3 = new Day3();
        Character item1 = 'A';
        Character item2 = 'g';
        Character item3 = 'z';
        Character item4 = 'W';
        Character item5 = 'H';

        Integer expectedItem1Priority = 27;
        Integer expectedItem2Priority = 7;
        Integer expectedItem3Priority = 26;
        Integer expectedItem4Priority = 49;
        Integer expectedItem5Priority = 34;

        assertEquals(expectedItem1Priority, day3.mapItemToPriority(item1));
        assertEquals(expectedItem2Priority, day3.mapItemToPriority(item2));
        assertEquals(expectedItem3Priority, day3.mapItemToPriority(item3));
        assertEquals(expectedItem4Priority, day3.mapItemToPriority(item4));
        assertEquals(expectedItem5Priority, day3.mapItemToPriority(item5));
    }

    @Test
    @DisplayName("Should return the sum of all misplaced items' priority.")
    public void getPrioritySum() {
        Day3 day3 = new Day3();
        List<Integer> fakeMisplacedItemPriorityList = asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        day3.setMisplacedItemPriorityList(fakeMisplacedItemPriorityList);
        Integer expectedPrioritySum = 45;
        assertEquals(expectedPrioritySum, day3.getPrioritySum());
    }

    @Test
    @DisplayName("Should read and parse the input file")
    public void readAndParseFile() {
        Day3 day3 = new Day3();
        day3.readAndParseFile(PATH_TO_TEST_INPUT);
        List<Integer> expectedMisplacedItemPriorityList = asList(12, 26, 22, 49, 46, 26, 17, 43, 32, 17, 7, 34);
        assertEquals(expectedMisplacedItemPriorityList, day3.getMisplacedItemPriorityList());
    }

    @Disabled("PART 1 SOLUTION")
    @Test
    public void day3_part1() {
        Day3 day3 = new Day3();
        day3.readAndParseFile(PATH_TO_INPUT);
        System.out.println("PART 1 SOLUTION: " + day3.getPrioritySum());
    }

    @Test
    @DisplayName("Should return the group badge")
    public void findGroupBadge() {
        Day3 day3 = new Day3();
        List<String> lines = asList("GGVGlqWFgVfFqqVZGFlblJPMsDbbMrDMpDsJRn",
                                    "LwzHtwdLHHwDrzPZzzsJbJ",
                                    "wdLTBvSvHvZVGCjhfN");
        Integer firstElfIndex = 0;
        assertEquals('Z', day3.findGroupBadge(lines, firstElfIndex));
    }

    @Test
    @DisplayName("Should return the sum of all group badges' priority")
    public void getGroupBadgePrioritySum() {
        Day3 day3 = new Day3();
        List<Integer> fakeGroupBadgeItemPriorityList = asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        day3.setGroupBadgeItemPriorityList(fakeGroupBadgeItemPriorityList);
        Integer expectedPrioritySum = 45;
        assertEquals(expectedPrioritySum, day3.getGroupBadgePrioritySum());
    }

    @Disabled("PART 2 SOLUTION")
    @Test
    public void day3_part2() {
        Day3 day3 = new Day3();
        day3.readAndParseFile(PATH_TO_INPUT);
        System.out.println("PART 2 SOLUTION: " + day3.getGroupBadgePrioritySum());
    }
}
