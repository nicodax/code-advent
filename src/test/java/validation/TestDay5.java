package validation;

import me.nicodax.day5.CrateStack;
import me.nicodax.day5.Day5;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static me.nicodax.day5.CraneMode.FULL;
import static me.nicodax.day5.CraneMode.ONE_BY_ONE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDay5 {
    private final String PATH_SEPARATOR = File.separator;
    private final Path PATH_TO_TEST_INPUT = Paths.get(System.getProperty("user.dir")
            + PATH_SEPARATOR + "src"
            + PATH_SEPARATOR + "main"
            + PATH_SEPARATOR + "resources"
            + PATH_SEPARATOR + "test-input-day5.txt");

    private Day5 setUpCrateStacks(Integer part) {
        Day5 day5 = new Day5(part);
        List<String> lines = new ArrayList<>();
        lines.add("    [D]");
        lines.add("[N] [C]");
        lines.add("[Z] [M] [P]");

        day5.createCraneStacks(lines, lines.size() - 1);
        return day5;
    }

    @Test
    @DisplayName("It should create the crate stacks based on input")
    public void createCraneStacks() {
        Day5 day5 = setUpCrateStacks(1); // could be part 1 or 2, the setup process is exactly the same

        assertEquals(3, day5.getCrateStackList().size());

        assertEquals(2, day5.getCrateStackList().get(0).getStack().size());
        assertEquals('Z', day5.getCrateStackList().get(0).getStack().get(0).label());
        assertEquals('N', day5.getCrateStackList().get(0).getStack().get(1).label());

        assertEquals(3, day5.getCrateStackList().get(1).getStack().size());
        assertEquals('M', day5.getCrateStackList().get(1).getStack().get(0).label());
        assertEquals('C', day5.getCrateStackList().get(1).getStack().get(1).label());
        assertEquals('D', day5.getCrateStackList().get(1).getStack().get(2).label());

        assertEquals(1, day5.getCrateStackList().get(2).getStack().size());
        assertEquals('P', day5.getCrateStackList().get(2).getStack().get(0).label());
    }

    @Test
    @DisplayName("It should move the given number of crates from a given crate stack to another "
            + "- first instruction part 1")
    public void moveTo_firstInstruction_part1() {
        Day5 day5 = setUpCrateStacks(1);
        Integer numberOfCrates = 1; // move 1
        CrateStack origin = day5.getCrateStackList().get(1); // from 2
        CrateStack destination = day5.getCrateStackList().get(0); // to 1
        origin.moveTo(ONE_BY_ONE, destination, numberOfCrates);

        assertEquals(3, day5.getCrateStackList().size());

        assertEquals(3, day5.getCrateStackList().get(0).getStack().size());
        assertEquals('Z', day5.getCrateStackList().get(0).getStack().get(0).label());
        assertEquals('N', day5.getCrateStackList().get(0).getStack().get(1).label());
        assertEquals('D', day5.getCrateStackList().get(0).getStack().get(2).label());

        assertEquals(2, day5.getCrateStackList().get(1).getStack().size());
        assertEquals('M', day5.getCrateStackList().get(1).getStack().get(0).label());
        assertEquals('C', day5.getCrateStackList().get(1).getStack().get(1).label());

        assertEquals(1, day5.getCrateStackList().get(2).getStack().size());
        assertEquals('P', day5.getCrateStackList().get(2).getStack().get(0).label());
    }

    @Test
    @DisplayName("It should move the given number of crates from a given crate stack to another "
            + "- second instruction part 1")
    public void moveTo_secondInstruction_part1() {
        Day5 day5 = setUpCrateStacks(1);
        // FIRST INSTRUCTION
        int numberOfCrates = 1; // move 1
        CrateStack origin = day5.getCrateStackList().get(1); // from 2
        CrateStack destination = day5.getCrateStackList().get(0); // to 1
        origin.moveTo(ONE_BY_ONE, destination, numberOfCrates);

        // SECOND INSTRUCTION
        numberOfCrates = 3; // move 3
        origin = day5.getCrateStackList().get(0); // from 1
        destination = day5.getCrateStackList().get(2); // to 3
        origin.moveTo(ONE_BY_ONE, destination, numberOfCrates);

        assertEquals(3, day5.getCrateStackList().size());

        assertEquals(0, day5.getCrateStackList().get(0).getStack().size());

        assertEquals(2, day5.getCrateStackList().get(1).getStack().size());
        assertEquals('M', day5.getCrateStackList().get(1).getStack().get(0).label());
        assertEquals('C', day5.getCrateStackList().get(1).getStack().get(1).label());

        assertEquals(4, day5.getCrateStackList().get(2).getStack().size());
        assertEquals('P', day5.getCrateStackList().get(2).getStack().get(0).label());
        assertEquals('D', day5.getCrateStackList().get(2).getStack().get(1).label());
        assertEquals('N', day5.getCrateStackList().get(2).getStack().get(2).label());
        assertEquals('Z', day5.getCrateStackList().get(2).getStack().get(3).label());
    }

    @Test
    @DisplayName("It should move the given number of crates from a given crate stack to another "
            + "- third instruction part 1")
    public void moveTo_thirdInstruction_part1() {
        Day5 day5 = setUpCrateStacks(1);
        // FIRST INSTRUCTION
        int numberOfCrates = 1; // move 1
        CrateStack origin = day5.getCrateStackList().get(1); // from 2
        CrateStack destination = day5.getCrateStackList().get(0); // to 1
        origin.moveTo(ONE_BY_ONE, destination, numberOfCrates);

        // SECOND INSTRUCTION
        numberOfCrates = 3; // move 3
        origin = day5.getCrateStackList().get(0); // from 1
        destination = day5.getCrateStackList().get(2); // to 3
        origin.moveTo(ONE_BY_ONE, destination, numberOfCrates);

        // THIRD INSTRUCTION
        numberOfCrates = 2; // move 2
        origin = day5.getCrateStackList().get(1); // from 2
        destination = day5.getCrateStackList().get(0); // to 1
        origin.moveTo(ONE_BY_ONE, destination, numberOfCrates);

        assertEquals(3, day5.getCrateStackList().size());

        assertEquals(2, day5.getCrateStackList().get(0).getStack().size());
        assertEquals('C', day5.getCrateStackList().get(0).getStack().get(0).label());
        assertEquals('M', day5.getCrateStackList().get(0).getStack().get(1).label());

        assertEquals(0, day5.getCrateStackList().get(1).getStack().size());

        assertEquals(4, day5.getCrateStackList().get(2).getStack().size());
        assertEquals('P', day5.getCrateStackList().get(2).getStack().get(0).label());
        assertEquals('D', day5.getCrateStackList().get(2).getStack().get(1).label());
        assertEquals('N', day5.getCrateStackList().get(2).getStack().get(2).label());
        assertEquals('Z', day5.getCrateStackList().get(2).getStack().get(3).label());
    }

    @Test
    @DisplayName("It should move the given number of crates from a given crate stack to another "
            + "- fourth instruction part 1")
    public void moveTo_fourthInstruction_part1() {
        Day5 day5 = setUpCrateStacks(1);
        // FIRST INSTRUCTION
        int numberOfCrates = 1; // move 1
        CrateStack origin = day5.getCrateStackList().get(1); // from 2
        CrateStack destination = day5.getCrateStackList().get(0); // to 1
        origin.moveTo(ONE_BY_ONE, destination, numberOfCrates);

        // SECOND INSTRUCTION
        numberOfCrates = 3; // move 3
        origin = day5.getCrateStackList().get(0); // from 1
        destination = day5.getCrateStackList().get(2); // to 3
        origin.moveTo(ONE_BY_ONE, destination, numberOfCrates);

        // THIRD INSTRUCTION
        numberOfCrates = 2; // move 2
        origin = day5.getCrateStackList().get(1); // from 2
        destination = day5.getCrateStackList().get(0); // to 1
        origin.moveTo(ONE_BY_ONE, destination, numberOfCrates);

        // FOURTH INSTRUCTION
        numberOfCrates = 1; // move 1
        origin = day5.getCrateStackList().get(0); // from 1
        destination = day5.getCrateStackList().get(1); // to 2
        origin.moveTo(ONE_BY_ONE, destination, numberOfCrates);

        assertEquals(3, day5.getCrateStackList().size());

        assertEquals(1, day5.getCrateStackList().get(0).getStack().size());
        assertEquals('C', day5.getCrateStackList().get(0).getStack().get(0).label());

        assertEquals(1, day5.getCrateStackList().get(1).getStack().size());
        assertEquals('M', day5.getCrateStackList().get(1).getStack().get(0).label());

        assertEquals(4, day5.getCrateStackList().get(2).getStack().size());
        assertEquals('P', day5.getCrateStackList().get(2).getStack().get(0).label());
        assertEquals('D', day5.getCrateStackList().get(2).getStack().get(1).label());
        assertEquals('N', day5.getCrateStackList().get(2).getStack().get(2).label());
        assertEquals('Z', day5.getCrateStackList().get(2).getStack().get(3).label());
    }

    @Test
    @DisplayName("It should return the list of labels from the crates on top of each crate stack - part 1")
    public void getTopCratesLabel_part1() {
        Day5 day5 = setUpCrateStacks(1);
        day5.readAndParseFile(PATH_TO_TEST_INPUT);
        assertEquals("CMZ", day5.getSolution());
    }

    @Test
    @DisplayName("It should move the given number of crates from a given crate stack to another "
            + "- first instruction part 2")
    public void moveTo_firstInstruction_part2() {
        // Since the first instruction only moves one crate, the result is exactly the same as the result from part 1
        Day5 day5 = setUpCrateStacks(2);
        Integer numberOfCrates = 1; // move 1
        CrateStack origin = day5.getCrateStackList().get(1); // from 2
        CrateStack destination = day5.getCrateStackList().get(0); // to 1
        origin.moveTo(FULL, destination, numberOfCrates);

        assertEquals(3, day5.getCrateStackList().size());

        assertEquals(3, day5.getCrateStackList().get(0).getStack().size());
        assertEquals('Z', day5.getCrateStackList().get(0).getStack().get(0).label());
        assertEquals('N', day5.getCrateStackList().get(0).getStack().get(1).label());
        assertEquals('D', day5.getCrateStackList().get(0).getStack().get(2).label());

        assertEquals(2, day5.getCrateStackList().get(1).getStack().size());
        assertEquals('M', day5.getCrateStackList().get(1).getStack().get(0).label());
        assertEquals('C', day5.getCrateStackList().get(1).getStack().get(1).label());

        assertEquals(1, day5.getCrateStackList().get(2).getStack().size());
        assertEquals('P', day5.getCrateStackList().get(2).getStack().get(0).label());
    }

    @Test
    @DisplayName("It should move the given number of crates from a given crate stack to another "
            + "- second instruction part 2")
    public void moveTo_secondInstruction_part2() {
        Day5 day5 = setUpCrateStacks(2);
        // FIRST INSTRUCTION
        int numberOfCrates = 1; // move 1
        CrateStack origin = day5.getCrateStackList().get(1); // from 2
        CrateStack destination = day5.getCrateStackList().get(0); // to 1
        origin.moveTo(FULL, destination, numberOfCrates);

        // SECOND INSTRUCTION
        numberOfCrates = 3; // move 3
        origin = day5.getCrateStackList().get(0); // from 1
        destination = day5.getCrateStackList().get(2); // to 3
        origin.moveTo(FULL, destination, numberOfCrates);

        assertEquals(3, day5.getCrateStackList().size());

        assertEquals(0, day5.getCrateStackList().get(0).getStack().size());

        assertEquals(2, day5.getCrateStackList().get(1).getStack().size());
        assertEquals('M', day5.getCrateStackList().get(1).getStack().get(0).label());
        assertEquals('C', day5.getCrateStackList().get(1).getStack().get(1).label());

        assertEquals(4, day5.getCrateStackList().get(2).getStack().size());
        assertEquals('P', day5.getCrateStackList().get(2).getStack().get(0).label());
        assertEquals('Z', day5.getCrateStackList().get(2).getStack().get(1).label());
        assertEquals('N', day5.getCrateStackList().get(2).getStack().get(2).label());
        assertEquals('D', day5.getCrateStackList().get(2).getStack().get(3).label());
    }

    @Test
    @DisplayName("It should move the given number of crates from a given crate stack to another "
            + "- third instruction part 2")
    public void moveTo_thirdInstruction_part2() {
        Day5 day5 = setUpCrateStacks(2);
        // FIRST INSTRUCTION
        int numberOfCrates = 1; // move 1
        CrateStack origin = day5.getCrateStackList().get(1); // from 2
        CrateStack destination = day5.getCrateStackList().get(0); // to 1
        origin.moveTo(FULL, destination, numberOfCrates);

        // SECOND INSTRUCTION
        numberOfCrates = 3; // move 3
        origin = day5.getCrateStackList().get(0); // from 1
        destination = day5.getCrateStackList().get(2); // to 3
        origin.moveTo(FULL, destination, numberOfCrates);

        // THIRD INSTRUCTION
        numberOfCrates = 2; // move 2
        origin = day5.getCrateStackList().get(1); // from 2
        destination = day5.getCrateStackList().get(0); // to 1
        origin.moveTo(FULL, destination, numberOfCrates);

        assertEquals(3, day5.getCrateStackList().size());

        assertEquals(2, day5.getCrateStackList().get(0).getStack().size());
        assertEquals('M', day5.getCrateStackList().get(0).getStack().get(0).label());
        assertEquals('C', day5.getCrateStackList().get(0).getStack().get(1).label());

        assertEquals(0, day5.getCrateStackList().get(1).getStack().size());

        assertEquals(4, day5.getCrateStackList().get(2).getStack().size());
        assertEquals('P', day5.getCrateStackList().get(2).getStack().get(0).label());
        assertEquals('Z', day5.getCrateStackList().get(2).getStack().get(1).label());
        assertEquals('N', day5.getCrateStackList().get(2).getStack().get(2).label());
        assertEquals('D', day5.getCrateStackList().get(2).getStack().get(3).label());
    }

    @Test
    @DisplayName("It should move the given number of crates from a given crate stack to another "
            + "- fourth instruction part 2")
    public void moveTo_fourthInstruction_part2() {
        Day5 day5 = setUpCrateStacks(2);
        // FIRST INSTRUCTION
        int numberOfCrates = 1; // move 1
        CrateStack origin = day5.getCrateStackList().get(1); // from 2
        CrateStack destination = day5.getCrateStackList().get(0); // to 1
        origin.moveTo(FULL, destination, numberOfCrates);

        // SECOND INSTRUCTION
        numberOfCrates = 3; // move 3
        origin = day5.getCrateStackList().get(0); // from 1
        destination = day5.getCrateStackList().get(2); // to 3
        origin.moveTo(FULL, destination, numberOfCrates);

        // THIRD INSTRUCTION
        numberOfCrates = 2; // move 2
        origin = day5.getCrateStackList().get(1); // from 2
        destination = day5.getCrateStackList().get(0); // to 1
        origin.moveTo(FULL, destination, numberOfCrates);

        // FOURTH INSTRUCTION
        numberOfCrates = 1; // move 1
        origin = day5.getCrateStackList().get(0); // from 1
        destination = day5.getCrateStackList().get(1); // to 2
        origin.moveTo(FULL, destination, numberOfCrates);

        assertEquals(3, day5.getCrateStackList().size());

        assertEquals(1, day5.getCrateStackList().get(0).getStack().size());
        assertEquals('M', day5.getCrateStackList().get(0).getStack().get(0).label());

        assertEquals(1, day5.getCrateStackList().get(1).getStack().size());
        assertEquals('C', day5.getCrateStackList().get(1).getStack().get(0).label());

        assertEquals(4, day5.getCrateStackList().get(2).getStack().size());
        assertEquals('P', day5.getCrateStackList().get(2).getStack().get(0).label());
        assertEquals('Z', day5.getCrateStackList().get(2).getStack().get(1).label());
        assertEquals('N', day5.getCrateStackList().get(2).getStack().get(2).label());
        assertEquals('D', day5.getCrateStackList().get(2).getStack().get(3).label());
    }

    @Test
    @DisplayName("It should return the list of labels from the crates on top of each crate stack - part 2")
    public void getTopCratesLabel_part2() {
        Day5 day5 = setUpCrateStacks(2);
        day5.readAndParseFile(PATH_TO_TEST_INPUT);
        assertEquals("MCD", day5.getSolution());
    }
}
