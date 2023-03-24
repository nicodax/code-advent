package validation;

import me.nicodax.day11.Day11;
import me.nicodax.day11.Monkey;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.List.of;
import static me.nicodax.day11.Monkey.OPERATION_NUMBER_IS_ITSELF_MARKER;
import static me.nicodax.day11.OperationTypes.ADD;
import static me.nicodax.day11.OperationTypes.MULTIPLY;
import static org.junit.jupiter.api.Assertions.*;

public class TestDay11 {
    private final String PATH_SEPARATOR = File.separator;
    private final Path PATH_TO_TEST_INPUT = Paths.get(System.getProperty("user.dir")
            + PATH_SEPARATOR + "src"
            + PATH_SEPARATOR + "main"
            + PATH_SEPARATOR + "resources"
            + PATH_SEPARATOR + "test-input-day11.txt");
    private final Integer RELIEF_DIVIDER_PART_1 = 3;
    private final Integer RELIEF_DIVIDER_PART_2 = 1;

    @Test
    @DisplayName("Should read and parse test input, and create initial monkey inventories")
    public void readAndParseFile_initialMonkeyInventories() {
        Day11 day11 = new Day11(RELIEF_DIVIDER_PART_1);
        day11.readAndParseFile(PATH_TO_TEST_INPUT);
        List<Monkey> monkeyList = day11.getMonkeyList();

        assertEquals(4, monkeyList.size());
        Monkey monkey0 = monkeyList.get(0);
        Monkey monkey1 = monkeyList.get(1);
        Monkey monkey2 = monkeyList.get(2);
        Monkey monkey3 = monkeyList.get(3);

        assertEquals(asList(79L, 98L), monkey0.getItemWorryLevelList());
        assertEquals(asList(54L, 65L, 75L, 74L), monkey1.getItemWorryLevelList());
        assertEquals(asList(79L, 60L, 97L), monkey2.getItemWorryLevelList());
        assertEquals(of(74L), monkey3.getItemWorryLevelList());
    }

    @Test
    @DisplayName("Should read and parse test input, and set monkey operation")
    public void readAndParseFile_setMonkeyOperation() {
        Day11 day11 = new Day11(RELIEF_DIVIDER_PART_1);
        day11.readAndParseFile(PATH_TO_TEST_INPUT);
        List<Monkey> monkeyList = day11.getMonkeyList();

        assertEquals(4, monkeyList.size());
        Monkey monkey0 = monkeyList.get(0);
        Monkey monkey1 = monkeyList.get(1);
        Monkey monkey2 = monkeyList.get(2);
        Monkey monkey3 = monkeyList.get(3);

        assertEquals(MULTIPLY, monkey0.getOperation());
        assertEquals(ADD, monkey1.getOperation());
        assertEquals(MULTIPLY, monkey2.getOperation());
        assertEquals(ADD, monkey3.getOperation());

        assertEquals(19, monkey0.getOperationNumber());
        assertEquals(6, monkey1.getOperationNumber());
        assertEquals(79, monkey2.getOperationNumber());
        assertEquals(3, monkey3.getOperationNumber());
    }

    @Test
    @DisplayName("Should read and parse test input, and set monkey test condition")
    public void readAndParseFile_setMonkeyTestCondition() {
        Day11 day11 = new Day11(RELIEF_DIVIDER_PART_1);
        day11.readAndParseFile(PATH_TO_TEST_INPUT);
        List<Monkey> monkeyList = day11.getMonkeyList();

        assertEquals(4, monkeyList.size());
        Monkey monkey0 = monkeyList.get(0);
        Monkey monkey1 = monkeyList.get(1);
        Monkey monkey2 = monkeyList.get(2);
        Monkey monkey3 = monkeyList.get(3);

        assertEquals(23, monkey0.getConditionDivider());
        assertEquals(19, monkey1.getConditionDivider());
        assertEquals(13, monkey2.getConditionDivider());
        assertEquals(17, monkey3.getConditionDivider());

        assertEquals(2, monkey0.getTrueTestMonkeyTarget());
        assertEquals(3, monkey0.getFalseTestMonkeyTarget());
        assertEquals(2, monkey1.getTrueTestMonkeyTarget());
        assertEquals(0, monkey1.getFalseTestMonkeyTarget());
        assertEquals(1, monkey2.getTrueTestMonkeyTarget());
        assertEquals(3, monkey2.getFalseTestMonkeyTarget());
        assertEquals(0, monkey3.getTrueTestMonkeyTarget());
        assertEquals(1, monkey3.getFalseTestMonkeyTarget());
    }

    private List<Monkey> setupInitialMonkeyList(Integer reliefDivider) {
        Monkey monkey0 = new Monkey(asList(79L, 98L), MULTIPLY, 19, 23, 2, 3, reliefDivider);
        Monkey monkey1 = new Monkey(asList(54L, 65L, 75L, 74L), ADD, 6, 19, 2, 0, reliefDivider);
        Monkey monkey2 = new Monkey(asList(79L, 60L, 97L), MULTIPLY, OPERATION_NUMBER_IS_ITSELF_MARKER, 13, 1, 3, reliefDivider);
        Monkey monkey3 = new Monkey(of(74L), ADD, 3, 17, 0, 1, reliefDivider);
        return asList(monkey0, monkey1, monkey2, monkey3);
    }

    @Test
    @DisplayName("Should apply operation on given item worry level")
    public void applyOperationOnItemWorryLevel() {
        List<Monkey> initialMonkeyList = setupInitialMonkeyList(RELIEF_DIVIDER_PART_1);
        Monkey monkey0 = initialMonkeyList.get(0);
        Monkey monkey1 = initialMonkeyList.get(1);
        Monkey monkey2 = initialMonkeyList.get(2);
        Monkey monkey3 = initialMonkeyList.get(3);

        monkey0.inspectItem();
        assertTrue(monkey0.getItemWorryLevelList().size() > 0);
        assertEquals(1501, monkey0.getItemWorryLevelList().get(0));

        monkey1.inspectItem();
        assertTrue(monkey1.getItemWorryLevelList().size() > 0);
        assertEquals(60, monkey1.getItemWorryLevelList().get(0));

        monkey2.inspectItem();
        assertTrue(monkey2.getItemWorryLevelList().size() > 0);
        assertEquals(6241, monkey2.getItemWorryLevelList().get(0));

        monkey3.inspectItem();
        assertTrue(monkey3.getItemWorryLevelList().size() > 0);
        assertEquals(77, monkey3.getItemWorryLevelList().get(0));
    }

    @Test
    @DisplayName("Should divide given worry level by 3 and round it down to the nearest integer")
    public void applyReliefTo() {
        List<Monkey> initialMonkeyList = setupInitialMonkeyList(RELIEF_DIVIDER_PART_1);
        Monkey monkey0 = initialMonkeyList.get(0);
        monkey0.setWorryLevelListFor(0, 1501L);
        Monkey monkey1 = initialMonkeyList.get(1);
        monkey1.setWorryLevelListFor(0, 60L);
        Monkey monkey2 = initialMonkeyList.get(2);
        monkey2.setWorryLevelListFor(0, 6241L);
        Monkey monkey3 = initialMonkeyList.get(3);
        monkey3.setWorryLevelListFor(0, 77L);

        monkey0.applyReliefTo();
        assertEquals(500, monkey0.getItemWorryLevelList().get(0));

        monkey1.applyReliefTo();
        assertEquals(20, monkey1.getItemWorryLevelList().get(0));

        monkey2.applyReliefTo();
        assertEquals(2080, monkey2.getItemWorryLevelList().get(0));

        monkey3.applyReliefTo();
        assertEquals(25, monkey3.getItemWorryLevelList().get(0));
    }

    @Test
    @DisplayName("Should test first item on given monkey's test conditions")
    public void testItem() {
        List<Monkey> initialMonkeyList = setupInitialMonkeyList(RELIEF_DIVIDER_PART_1);
        Monkey monkey0 = initialMonkeyList.get(0);
        monkey0.setWorryLevelListFor(0, 500L);
        Monkey monkey1 = initialMonkeyList.get(1);
        monkey1.setWorryLevelListFor(0, 20L);
        Monkey monkey2 = initialMonkeyList.get(2);
        monkey2.setWorryLevelListFor(0, 2080L);
        Monkey monkey3 = initialMonkeyList.get(3);
        monkey3.setWorryLevelListFor(0, 25L);

        monkey0.getsBored();
        assertFalse(monkey0.testItem());

        monkey1.getsBored();
        assertFalse(monkey1.testItem());

        monkey2.getsBored();
        assertTrue(monkey2.testItem());

        monkey3.getsBored();
        assertFalse(monkey3.testItem());
    }

    @Test
    @DisplayName("Should process the first round")
    public void processFirstRound() {
        Day11 day11 = new Day11(RELIEF_DIVIDER_PART_1);
        day11.readAndParseFile(PATH_TO_TEST_INPUT);
        List<Monkey> initialMonkeyList = new ArrayList<>(day11.getMonkeyList());
        for (Monkey monkey : day11.getMonkeyList()) {
            initialMonkeyList.add(monkey.clone());
        }

        day11.processRounds(1);
        List<Monkey> monkeyList = day11.getMonkeyList();

        assertNotEquals(initialMonkeyList, monkeyList);

        assertEquals(asList(20L, 23L, 27L, 26L), monkeyList.get(0).getItemWorryLevelList());
        assertEquals(asList(2080L, 25L, 167L, 207L, 401L, 1046L), monkeyList.get(1).getItemWorryLevelList());
        assertEquals(emptyList(), monkeyList.get(2).getItemWorryLevelList());
        assertEquals(emptyList(), monkeyList.get(3).getItemWorryLevelList());
    }

    @Test
    @DisplayName("Should process the first five rounds")
    public void processFirstFiveRounds() {
        Day11 day11 = new Day11(RELIEF_DIVIDER_PART_1);
        day11.readAndParseFile(PATH_TO_TEST_INPUT);
        List<Monkey> initialMonkeyList = new ArrayList<>(day11.getMonkeyList());
        for (Monkey monkey : day11.getMonkeyList()) {
            initialMonkeyList.add(monkey.clone());
        }

        day11.processRounds(5);
        List<Monkey> monkeyList = day11.getMonkeyList();

        assertNotEquals(initialMonkeyList, monkeyList);

        assertEquals(asList(15L, 17L, 16L, 88L, 1037L), monkeyList.get(0).getItemWorryLevelList());
        assertEquals(asList(20L, 110L, 205L, 524L, 72L), monkeyList.get(1).getItemWorryLevelList());
        assertEquals(emptyList(), monkeyList.get(2).getItemWorryLevelList());
        assertEquals(emptyList(), monkeyList.get(3).getItemWorryLevelList());
    }

    @Test
    @DisplayName("Should process the first ten rounds")
    public void processFirstTenRounds() {
        Day11 day11 = new Day11(RELIEF_DIVIDER_PART_1);
        day11.readAndParseFile(PATH_TO_TEST_INPUT);
        List<Monkey> initialMonkeyList = new ArrayList<>(day11.getMonkeyList());
        for (Monkey monkey : day11.getMonkeyList()) {
            initialMonkeyList.add(monkey.clone());
        }

        day11.processRounds(10);
        List<Monkey> monkeyList = day11.getMonkeyList();

        assertNotEquals(initialMonkeyList, monkeyList);

        assertEquals(asList(91L, 16L, 20L, 98L), monkeyList.get(0).getItemWorryLevelList());
        assertEquals(asList(481L, 245L, 22L, 26L, 1092L, 30L), monkeyList.get(1).getItemWorryLevelList());
        assertEquals(emptyList(), monkeyList.get(2).getItemWorryLevelList());
        assertEquals(emptyList(), monkeyList.get(3).getItemWorryLevelList());
    }

    @Test
    @DisplayName("Should process the first fifteen rounds")
    public void processFirstFifteenRounds() {
        Day11 day11 = new Day11(RELIEF_DIVIDER_PART_1);
        day11.readAndParseFile(PATH_TO_TEST_INPUT);
        List<Monkey> initialMonkeyList = new ArrayList<>(day11.getMonkeyList());
        for (Monkey monkey : day11.getMonkeyList()) {
            initialMonkeyList.add(monkey.clone());
        }

        day11.processRounds(15);
        List<Monkey> monkeyList = day11.getMonkeyList();

        assertNotEquals(initialMonkeyList, monkeyList);

        assertEquals(asList(83L, 44L, 8L, 184L, 9L, 20L, 26L, 102L), monkeyList.get(0).getItemWorryLevelList());
        assertEquals(asList(110L, 36L), monkeyList.get(1).getItemWorryLevelList());
        assertEquals(emptyList(), monkeyList.get(2).getItemWorryLevelList());
        assertEquals(emptyList(), monkeyList.get(3).getItemWorryLevelList());
    }

    @Test
    @DisplayName("Should process the first twenty rounds")
    public void processFirstTwentyRounds() {
        Day11 day11 = new Day11(RELIEF_DIVIDER_PART_1);
        day11.readAndParseFile(PATH_TO_TEST_INPUT);
        List<Monkey> initialMonkeyList = new ArrayList<>(day11.getMonkeyList());
        for (Monkey monkey : day11.getMonkeyList()) {
            initialMonkeyList.add(monkey.clone());
        }

        day11.processRounds(20);
        List<Monkey> monkeyList = day11.getMonkeyList();

        assertNotEquals(initialMonkeyList, monkeyList);

        assertEquals(asList(10L, 12L, 14L, 26L, 34L), monkeyList.get(0).getItemWorryLevelList());
        assertEquals(asList(245L, 93L, 53L, 199L, 115L), monkeyList.get(1).getItemWorryLevelList());
        assertEquals(emptyList(), monkeyList.get(2).getItemWorryLevelList());
        assertEquals(emptyList(), monkeyList.get(3).getItemWorryLevelList());
    }

    @Test
    @DisplayName("Should return the total of inspected items for given monkey, after twenty rounds - part 1")
    public void getTotalInspectedItemsAfterTwentyRounds_part1() {
        Day11 day11 = new Day11(RELIEF_DIVIDER_PART_1);
        day11.readAndParseFile(PATH_TO_TEST_INPUT);

        day11.processRounds(20);
        List<Monkey> monkeyList = day11.getMonkeyList();

        assertEquals(101, monkeyList.get(0).getTotalInspectedItems());
        assertEquals(95, monkeyList.get(1).getTotalInspectedItems());
        assertEquals(7, monkeyList.get(2).getTotalInspectedItems());
        assertEquals(105, monkeyList.get(3).getTotalInspectedItems());
    }

    @Test
    @DisplayName("Should return the level of monkey business after twenty rounds - part 1")
    public void getMonkeyBusinessLevelAfterTwentyRounds_part1() {
        Day11 day11 = new Day11(RELIEF_DIVIDER_PART_1);
        day11.readAndParseFile(PATH_TO_TEST_INPUT);

        assertEquals(10605, day11.getSolution(20));
    }

    @Test
    @DisplayName("Should return the total of inspected items for given monkey, after first round - part 2")
    public void getTotalInspectedItemsAfterFirstRound_part2() {
        Day11 day11 = new Day11(RELIEF_DIVIDER_PART_2);
        day11.readAndParseFile(PATH_TO_TEST_INPUT);

        day11.processRounds(1);
        List<Monkey> monkeyList = day11.getMonkeyList();

        assertEquals(2, monkeyList.get(0).getTotalInspectedItems());
        assertEquals(4, monkeyList.get(1).getTotalInspectedItems());
        assertEquals(3, monkeyList.get(2).getTotalInspectedItems());
        assertEquals(6, monkeyList.get(3).getTotalInspectedItems());
    }

    @Test
    @DisplayName("Should return the total of inspected items for given monkey, after twenty rounds - part 2")
    public void getTotalInspectedItemsAfterTwentyRounds_part2() {
        Day11 day11 = new Day11(RELIEF_DIVIDER_PART_2);
        day11.readAndParseFile(PATH_TO_TEST_INPUT);

        day11.processRounds(20);
        List<Monkey> monkeyList = day11.getMonkeyList();

        assertEquals(99, monkeyList.get(0).getTotalInspectedItems());
        assertEquals(97, monkeyList.get(1).getTotalInspectedItems());
        assertEquals(8, monkeyList.get(2).getTotalInspectedItems());
        assertEquals(103, monkeyList.get(3).getTotalInspectedItems());
    }

    @Test
    @DisplayName("Should return the total of inspected items for given monkey, after 1000 rounds - part 2")
    public void getTotalInspectedItemsAfter1000Rounds_part2() {
        Day11 day11 = new Day11(RELIEF_DIVIDER_PART_2);
        day11.readAndParseFile(PATH_TO_TEST_INPUT);

        day11.processRounds(1000);
        List<Monkey> monkeyList = day11.getMonkeyList();

        assertEquals(5204, monkeyList.get(0).getTotalInspectedItems());
        assertEquals(4792, monkeyList.get(1).getTotalInspectedItems());
        assertEquals(199, monkeyList.get(2).getTotalInspectedItems());
        assertEquals(5192, monkeyList.get(3).getTotalInspectedItems());
    }

    @Test
    @DisplayName("Should return the total of inspected items for given monkey, after 5000 rounds - part 2")
    public void getTotalInspectedItemsAfter5000Rounds_part2() {
        Day11 day11 = new Day11(RELIEF_DIVIDER_PART_2);
        day11.readAndParseFile(PATH_TO_TEST_INPUT);

        day11.processRounds(5000);
        List<Monkey> monkeyList = day11.getMonkeyList();

        assertEquals(26075, monkeyList.get(0).getTotalInspectedItems());
        assertEquals(23921, monkeyList.get(1).getTotalInspectedItems());
        assertEquals(974, monkeyList.get(2).getTotalInspectedItems());
        assertEquals(26000, monkeyList.get(3).getTotalInspectedItems());
    }

    @Test
    @DisplayName("Should return the total of inspected items for given monkey, after 10000 rounds - part 2")
    public void getTotalInspectedItemsAfter10000Rounds_part2() {
        Day11 day11 = new Day11(RELIEF_DIVIDER_PART_2);
        day11.readAndParseFile(PATH_TO_TEST_INPUT);

        day11.processRounds(10000);
        List<Monkey> monkeyList = day11.getMonkeyList();

        assertEquals(52166, monkeyList.get(0).getTotalInspectedItems());
        assertEquals(47830, monkeyList.get(1).getTotalInspectedItems());
        assertEquals(1938, monkeyList.get(2).getTotalInspectedItems());
        assertEquals(52013, monkeyList.get(3).getTotalInspectedItems());
    }

    @Test
    @DisplayName("Should return the level of monkey business after 10000 rounds - part 2")
    public void getMonkeyBusinessLevelAfter10000Rounds_part2() {
        Day11 day11 = new Day11(RELIEF_DIVIDER_PART_2);
        day11.readAndParseFile(PATH_TO_TEST_INPUT);

        assertEquals(2713310158L, day11.getSolution(10000));
    }
}
