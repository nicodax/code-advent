import me.nicodax.Day4;
import me.nicodax.Day5;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day5Test {
    private final Path PATH_TO_INPUT = Paths.get(System.getProperty("user.dir")
                                                         + "\\src\\main\\resources\\input-day5.txt");
    private final Path PATH_TO_TEST_INPUT = Paths.get(System.getProperty("user.dir")
                                                              + "\\src\\main\\resources\\test-input-day5.txt");

    @Test
    @DisplayName("Should parse a crate stack base definition line and save the crates positions")
    public void parseCratesStack_stackBase() {
        Day5 day5 = new Day5();
        String line = "[B] [T] [M] [B]";
        HashMap<Integer, List<Character>> expectedCratesStack = new HashMap<>();
        expectedCratesStack.put(1, List.of('B'));
        expectedCratesStack.put(2, List.of('T'));
        expectedCratesStack.put(3, List.of('M'));
        expectedCratesStack.put(4, List.of('B'));

        day5.parseCratesStack(line);
        assertEquals(expectedCratesStack, day5.getCrateStacks());
    }

    private HashMap<Integer, List<Character>> setUpCraneStacks() {
        HashMap<Integer, List<Character>> existingCrateStacks = new HashMap<>();
        List<Character> existingCrateStack1 = new ArrayList<>();
        existingCrateStack1.add('B');
        existingCrateStack1.add('G');
        existingCrateStack1.add('S');
        existingCrateStack1.add('C');
        List<Character> existingCrateStack2 = new ArrayList<>();
        existingCrateStack2.add('T');
        existingCrateStack2.add('M');
        existingCrateStack2.add('W');
        existingCrateStack2.add('H');
        List<Character> existingCrateStack3 = new ArrayList<>();
        existingCrateStack3.add('M');
        existingCrateStack3.add('Q');
        existingCrateStack3.add('S');
        List<Character> existingCrateStack4 = new ArrayList<>();
        existingCrateStack4.add('B');
        existingCrateStack4.add('S');
        existingCrateStack4.add('L');
        existingCrateStack4.add('T');
        existingCrateStacks.put(1, existingCrateStack1);
        existingCrateStacks.put(2, existingCrateStack2);
        existingCrateStacks.put(3, existingCrateStack3);
        existingCrateStacks.put(4, existingCrateStack4);
        return existingCrateStacks;
    }

    @Test
    @DisplayName("Should parse a crate stack definition line and save the crates positions")
    public void parseCratesStack_furtherUpTheStack() {
        Day5 day5 = new Day5();
        String line = "    [J]     [W]";
        HashMap<Integer, List<Character>> existingCrateStacks = setUpCraneStacks();

        day5.setCrateStacks(existingCrateStacks);

        HashMap<Integer, List<Character>> expectedCrateStacks = new HashMap<>(existingCrateStacks);
        List<Character> crateStack2 = expectedCrateStacks.get(2);
        crateStack2.add('J');
        expectedCrateStacks.put(2, crateStack2);
        List<Character> crateStack4 = expectedCrateStacks.get(4);
        crateStack4.add('W');
        expectedCrateStacks.put(4, crateStack4);

        day5.parseCratesStack(line);
        assertEquals(expectedCrateStacks, day5.getCrateStacks());
    }

    @Test
    @DisplayName("Should read an action line and execute the requested action.")
    public void parseAndExecuteCargoCraneAction() {
        Day5 day5 = new Day5();
        String line = " move 2 from 4 to 2";
        HashMap<Integer, List<Character>> existingCrateStacks = setUpCraneStacks();
        day5.setCrateStacks(existingCrateStacks);

        HashMap<Integer, List<Character>> expectedCrateStacks = new HashMap<>(existingCrateStacks);
        List<Character> crateStack2 = expectedCrateStacks.get(2);
        crateStack2.add('T');
        crateStack2.add('L');
        expectedCrateStacks.put(2, crateStack2);
        List<Character> crateStack4 = expectedCrateStacks.get(4);
        crateStack2.remove(crateStack4.size() -1);
        crateStack2.remove(crateStack4.size() -1);
        expectedCrateStacks.put(4, crateStack4);

        day5.parseAndExecuteCargoCraneAction(line);
        assertEquals(expectedCrateStacks, day5.getCrateStacks());
    }

    @Test
    @DisplayName("Should return a string containing each stack's top crate, in order")
    public void getTopCrateOnEachStack() {
        Day5 day5 = new Day5();
        HashMap<Integer, List<Character>> existingCrateStacks = setUpCraneStacks();
        day5.setCrateStacks(existingCrateStacks);
        String expectedResult = "CHST";

        assertEquals(expectedResult, day5.getTopCrateOnEachStack());
    }

    @Test
    @DisplayName("Should read and parse the input file.")
    public void readAndParseFile() {
        Day5 day5 = new Day5();
        HashMap<Integer, List<Character>> expectedCrateStacks = new HashMap<>();
        List<Character> expectedCrateStack1 = new ArrayList<>();
        expectedCrateStack1.add('B');
        expectedCrateStack1.add('G');
        List<Character> expectedCrateStack2 = new ArrayList<>();
        expectedCrateStack2.add('T');
        expectedCrateStack2.add('M');
        expectedCrateStack2.add('W');
        expectedCrateStack2.add('H');
        List<Character> expectedCrateStack3 = new ArrayList<>();
        expectedCrateStack3.add('M');
        expectedCrateStack3.add('Q');
        expectedCrateStack3.add('S');
        expectedCrateStack3.add('N');
        expectedCrateStack3.add('M');
        expectedCrateStack3.add('G');
        expectedCrateStack3.add('V');
        expectedCrateStack3.add('N');
        expectedCrateStack3.add('J');
        List<Character> expectedCrateStack4 = new ArrayList<>();
        expectedCrateStack4.add('B');
        expectedCrateStack4.add('S');
        expectedCrateStack4.add('L');
        expectedCrateStack4.add('T');
        expectedCrateStack4.add('W');
        expectedCrateStack4.add('C');
        expectedCrateStack4.add('S');
        expectedCrateStacks.put(1, expectedCrateStack1);
        expectedCrateStacks.put(2, expectedCrateStack2);
        expectedCrateStacks.put(3, expectedCrateStack3);
        expectedCrateStacks.put(4, expectedCrateStack4);

        day5.readAndParseFile(PATH_TO_TEST_INPUT);
        assertEquals(expectedCrateStacks, day5.getCrateStacks());
    }

    @Disabled("PART 1 SOLUTION")
    @Test
    public void day5_part1() {
        Day5 day5 = new Day5();
        day5.readAndParseFile(PATH_TO_INPUT);
        System.out.println("PART 1 SOLUTION: " + day5.getTopCrateOnEachStack());
    }
}
