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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDay5 {
    private final String PATH_SEPARATOR = File.separator;
    private final Path PATH_TO_TEST_INPUT = Paths.get(System.getProperty("user.dir")
            + PATH_SEPARATOR + "src"
            + PATH_SEPARATOR + "main"
            + PATH_SEPARATOR + "resources"
            + PATH_SEPARATOR + "test-input-day5.txt");
    private final Integer PART_1 = 1;
    private final Integer PART_2 = 2;

    private Day5 setUpCrateStacks(Integer part) {
        Day5 day5 = new Day5(part);
        List<String> lines = new ArrayList<>();
        lines.add("    [D]");
        lines.add("[N] [C]");
        lines.add("[Z] [M] [P]");

        day5.createCraneStacks(lines, lines.size() - 1);
        return day5;
    }

    private void instruction1(Day5 day5) {
        Integer numberOfCrates = 1; // move 1
        CrateStack origin = day5.getCrateStackList().get(1); // from 2
        CrateStack destination = day5.getCrateStackList().get(0); // to 1
        origin.moveTo(day5.getCRANE_MODE(), destination, numberOfCrates);
    }

    private void instruction2(Day5 day5) {
        Integer numberOfCrates = 3; // move 3
        CrateStack origin = day5.getCrateStackList().get(0); // from 1
        CrateStack destination = day5.getCrateStackList().get(2); // to 3
        origin.moveTo(day5.getCRANE_MODE(), destination, numberOfCrates);
    }

    private void instruction3(Day5 day5) {
        Integer numberOfCrates = 2; // move 2
        CrateStack origin = day5.getCrateStackList().get(1); // from 2
        CrateStack destination = day5.getCrateStackList().get(0); // to 1
        origin.moveTo(day5.getCRANE_MODE(), destination, numberOfCrates);
    }

    private void instruction4(Day5 day5) {
        Integer numberOfCrates = 1; // move 1
        CrateStack origin = day5.getCrateStackList().get(0); // from 1
        CrateStack destination = day5.getCrateStackList().get(1); // to 2
        origin.moveTo(day5.getCRANE_MODE(), destination, numberOfCrates);
    }

    @Test
    @DisplayName("It should create the crate stacks based on input")
    public void createCraneStacks() {
        Day5 day5 = setUpCrateStacks(PART_1); // could be part 1 or 2, the setup process is exactly the same

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
        Day5 day5 = setUpCrateStacks(PART_1);
        instruction1(day5);

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
        Day5 day5 = setUpCrateStacks(PART_1);
        instruction1(day5);
        instruction2(day5);

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
        Day5 day5 = setUpCrateStacks(PART_1);
        instruction1(day5);
        instruction2(day5);
        instruction3(day5);

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
        Day5 day5 = setUpCrateStacks(PART_1);
        instruction1(day5);
        instruction2(day5);
        instruction3(day5);
        instruction4(day5);

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
        Day5 day5 = setUpCrateStacks(PART_1);
        day5.readAndParseFile(PATH_TO_TEST_INPUT);
        assertEquals("CMZ", day5.getSolution());
    }

    @Test
    @DisplayName("It should move the given number of crates from a given crate stack to another "
            + "- first instruction part 2")
    public void moveTo_firstInstruction_part2() {
        // Since the first instruction only moves one crate, the result is exactly the same as the result from part 1
        Day5 day5 = setUpCrateStacks(PART_2);
        instruction1(day5);

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
        Day5 day5 = setUpCrateStacks(PART_2);
        instruction1(day5);
        instruction2(day5);

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
        Day5 day5 = setUpCrateStacks(PART_2);
        instruction1(day5);
        instruction2(day5);
        instruction3(day5);

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
        Day5 day5 = setUpCrateStacks(PART_2);
        instruction1(day5);
        instruction2(day5);
        instruction3(day5);
        instruction4(day5);

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
        Day5 day5 = setUpCrateStacks(PART_2);
        day5.readAndParseFile(PATH_TO_TEST_INPUT);
        assertEquals("MCD", day5.getSolution());
    }
}
