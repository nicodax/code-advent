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

    @Test
    @DisplayName("Should read and parse test input, and create initial monkey inventories")
    public void readAndParseFile_initialMonkeyInventories() {
        Day11 day11 = new Day11();
        day11.readAndParseFile(PATH_TO_TEST_INPUT);
        List<Monkey> monkeyList = day11.getMonkeyList();

        assertEquals(4, monkeyList.size());
        Monkey monkey0 = monkeyList.get(0);
        Monkey monkey1 = monkeyList.get(1);
        Monkey monkey2 = monkeyList.get(2);
        Monkey monkey3 = monkeyList.get(3);

        assertEquals(asList(79, 98), monkey0.getItemWorryLevelList());
        assertEquals(asList(54, 65, 75, 74), monkey1.getItemWorryLevelList());
        assertEquals(asList(79, 60, 97), monkey2.getItemWorryLevelList());
        assertEquals(of(74), monkey3.getItemWorryLevelList());
    }

    @Test
    @DisplayName("Should read and parse test input, and set monkey operation")
    public void readAndParseFile_setMonkeyOperation() {
        Day11 day11 = new Day11();
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
        Day11 day11 = new Day11();
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

    private List<Monkey> setupInitialMonkeyList() {
        Monkey monkey0 = new Monkey(asList(79, 98), MULTIPLY, 19, 23, 2, 3);
        Monkey monkey1 = new Monkey(asList(54, 65, 75, 74), ADD, 6, 19, 2, 0);
        Monkey monkey2 = new Monkey(asList(79, 60, 97), MULTIPLY, OPERATION_NUMBER_IS_ITSELF_MARKER, 13, 1, 3);
        Monkey monkey3 = new Monkey(of(74), ADD, 3, 17, 0, 1);
        return asList(monkey0, monkey1, monkey2, monkey3);
    }

    @Test
    @DisplayName("Should apply operation on given item worry level")
    public void applyOperationOnItemWorryLevel() {
        List<Monkey> initialMonkeyList = setupInitialMonkeyList();
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
        List<Monkey> initialMonkeyList = setupInitialMonkeyList();
        Monkey monkey0 = initialMonkeyList.get(0);
        monkey0.setWorryLevelListFor(0, 1501);
        Monkey monkey1 = initialMonkeyList.get(1);
        monkey1.setWorryLevelListFor(0, 60);
        Monkey monkey2 = initialMonkeyList.get(2);
        monkey2.setWorryLevelListFor(0, 6241);
        Monkey monkey3 = initialMonkeyList.get(3);
        monkey3.setWorryLevelListFor(0, 77);

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
        List<Monkey> initialMonkeyList = setupInitialMonkeyList();
        Monkey monkey0 = initialMonkeyList.get(0);
        monkey0.setWorryLevelListFor(0, 500);
        Monkey monkey1 = initialMonkeyList.get(1);
        monkey1.setWorryLevelListFor(0, 20);
        Monkey monkey2 = initialMonkeyList.get(2);
        monkey2.setWorryLevelListFor(0, 2080);
        Monkey monkey3 = initialMonkeyList.get(3);
        monkey3.setWorryLevelListFor(0, 25);

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
        Day11 day11 = new Day11();
        day11.readAndParseFile(PATH_TO_TEST_INPUT);
        List<Monkey> initialMonkeyList = new ArrayList<>(day11.getMonkeyList());
        for (Monkey monkey : day11.getMonkeyList()) {
            initialMonkeyList.add(monkey.clone());
        }

        day11.processRounds(1);
        List<Monkey> monkeyList = day11.getMonkeyList();

        assertNotEquals(initialMonkeyList, monkeyList);

        assertEquals(asList(20, 23, 27, 26), monkeyList.get(0).getItemWorryLevelList());
        assertEquals(asList(2080, 25, 167, 207, 401, 1046), monkeyList.get(1).getItemWorryLevelList());
        assertEquals(emptyList(), monkeyList.get(2).getItemWorryLevelList());
        assertEquals(emptyList(), monkeyList.get(3).getItemWorryLevelList());
    }

    @Test
    @DisplayName("Should process the first five rounds")
    public void processFirstFiveRounds() {
        Day11 day11 = new Day11();
        day11.readAndParseFile(PATH_TO_TEST_INPUT);
        List<Monkey> initialMonkeyList = new ArrayList<>(day11.getMonkeyList());
        for (Monkey monkey : day11.getMonkeyList()) {
            initialMonkeyList.add(monkey.clone());
        }

        day11.processRounds(5);
        List<Monkey> monkeyList = day11.getMonkeyList();

        assertNotEquals(initialMonkeyList, monkeyList);

        assertEquals(asList(15, 17, 16, 88, 1037), monkeyList.get(0).getItemWorryLevelList());
        assertEquals(asList(20, 110, 205, 524, 72), monkeyList.get(1).getItemWorryLevelList());
        assertEquals(emptyList(), monkeyList.get(2).getItemWorryLevelList());
        assertEquals(emptyList(), monkeyList.get(3).getItemWorryLevelList());
    }

    @Test
    @DisplayName("Should process the first ten rounds")
    public void processFirstTenRounds() {
        Day11 day11 = new Day11();
        day11.readAndParseFile(PATH_TO_TEST_INPUT);
        List<Monkey> initialMonkeyList = new ArrayList<>(day11.getMonkeyList());
        for (Monkey monkey : day11.getMonkeyList()) {
            initialMonkeyList.add(monkey.clone());
        }

        day11.processRounds(10);
        List<Monkey> monkeyList = day11.getMonkeyList();

        assertNotEquals(initialMonkeyList, monkeyList);

        assertEquals(asList(91, 16, 20, 98), monkeyList.get(0).getItemWorryLevelList());
        assertEquals(asList(481, 245, 22, 26, 1092, 30), monkeyList.get(1).getItemWorryLevelList());
        assertEquals(emptyList(), monkeyList.get(2).getItemWorryLevelList());
        assertEquals(emptyList(), monkeyList.get(3).getItemWorryLevelList());
    }

    @Test
    @DisplayName("Should process the first fifteen rounds")
    public void processFirstFifteenRounds() {
        Day11 day11 = new Day11();
        day11.readAndParseFile(PATH_TO_TEST_INPUT);
        List<Monkey> initialMonkeyList = new ArrayList<>(day11.getMonkeyList());
        for (Monkey monkey : day11.getMonkeyList()) {
            initialMonkeyList.add(monkey.clone());
        }

        day11.processRounds(15);
        List<Monkey> monkeyList = day11.getMonkeyList();

        assertNotEquals(initialMonkeyList, monkeyList);

        assertEquals(asList(83, 44, 8, 184, 9, 20, 26, 102), monkeyList.get(0).getItemWorryLevelList());
        assertEquals(asList(110, 36), monkeyList.get(1).getItemWorryLevelList());
        assertEquals(emptyList(), monkeyList.get(2).getItemWorryLevelList());
        assertEquals(emptyList(), monkeyList.get(3).getItemWorryLevelList());
    }

    @Test
    @DisplayName("Should process the first twenty rounds")
    public void processFirstTwentyRounds() {
        Day11 day11 = new Day11();
        day11.readAndParseFile(PATH_TO_TEST_INPUT);
        List<Monkey> initialMonkeyList = new ArrayList<>(day11.getMonkeyList());
        for (Monkey monkey : day11.getMonkeyList()) {
            initialMonkeyList.add(monkey.clone());
        }

        day11.processRounds(20);
        List<Monkey> monkeyList = day11.getMonkeyList();

        assertNotEquals(initialMonkeyList, monkeyList);

        assertEquals(asList(10, 12, 14, 26, 34), monkeyList.get(0).getItemWorryLevelList());
        assertEquals(asList(245, 93, 53, 199, 115), monkeyList.get(1).getItemWorryLevelList());
        assertEquals(emptyList(), monkeyList.get(2).getItemWorryLevelList());
        assertEquals(emptyList(), monkeyList.get(3).getItemWorryLevelList());
    }

    @Test
    @DisplayName("Should return the total of inspected items for given monkey, after twenty rounds")
    public void getTotalInspectedItemsAfterTwentyRounds() {
        Day11 day11 = new Day11();
        day11.readAndParseFile(PATH_TO_TEST_INPUT);

        day11.processRounds(20);
        List<Monkey> monkeyList = day11.getMonkeyList();

        assertEquals(101, monkeyList.get(0).getTotalInspectedItems());
        assertEquals(95, monkeyList.get(1).getTotalInspectedItems());
        assertEquals(7, monkeyList.get(2).getTotalInspectedItems());
        assertEquals(105, monkeyList.get(3).getTotalInspectedItems());
    }

    @Test
    @DisplayName("Should return the level of monkey business after twenty rounds")
    public void getMonkeyBusinessLevelAfterTwentyRounds() {
        Day11 day11 = new Day11();
        day11.readAndParseFile(PATH_TO_TEST_INPUT);

        assertEquals(10605, day11.getPart1Solution());
    }
}
