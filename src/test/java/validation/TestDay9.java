package validation;

import me.nicodax.day9.Day9;
import me.nicodax.day9.Position;
import me.nicodax.day9.Rope;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDay9 {
    private final String PATH_SEPARATOR = File.separator;
    private final Path PATH_TO_TEST_INPUT_PART_1 = getPathToTestInput(1);
    private final Path PATH_TO_TEST_INPUT_PART_2 = getPathToTestInput(2);
    private final Integer ROPE_LENGTH_PART_1 = 2;

    private Path getPathToTestInput(Integer partNumber) {
        return Paths.get(System.getProperty("user.dir")
                + PATH_SEPARATOR + "src"
                + PATH_SEPARATOR + "main"
                + PATH_SEPARATOR + "resources"
                + PATH_SEPARATOR + "test-input-day9-part" + partNumber + ".txt");
    }

    private Rope setupInitialRopePositions(Integer ropeLength, Position initialHeadPosition) {
        Rope rope = new Rope(ropeLength);
        List<Position> sectionPositionList = new ArrayList<>();
        sectionPositionList.add(initialHeadPosition);
        for (int i = 0; i < ropeLength - 1; i++) sectionPositionList.add(new Position(0, 0));
        List<Position> tailPositionList = new ArrayList<>();
        tailPositionList.add(new Position(0, 0));
        rope.setSectionPositionList(sectionPositionList);
        rope.setTailPositionList(tailPositionList);
        return rope;
    }

    @Test
    @DisplayName("If, after a head movement, the tail is directly adjacent to the head, the tail should not move")
    public void tailDidNotMove() {
        Rope rope = setupInitialRopePositions(ROPE_LENGTH_PART_1, new Position(1, 0));

        // MOVE HEAD AROUND TAIL:
        rope.moveHead(1, 0, 1);
        assertEquals(1, rope.getTailPositionList().size());

        rope.moveHead(1, -1, 0);
        assertEquals(1, rope.getTailPositionList().size());

        rope.moveHead(1, -1, 0);
        assertEquals(1, rope.getTailPositionList().size());

        rope.moveHead(1, 0, -1);
        assertEquals(1, rope.getTailPositionList().size());

        rope.moveHead(1, 0, -1);
        assertEquals(1, rope.getTailPositionList().size());

        rope.moveHead(1, 1, 0);
        assertEquals(1, rope.getTailPositionList().size());

        rope.moveHead(1, 1, 0);
        assertEquals(1, rope.getTailPositionList().size());

        rope.moveHead(1, 0, 1);
        assertEquals(1, rope.getTailPositionList().size());
    }

    @Test
    @DisplayName("If the head is ever two steps directly right from the tail, " +
            "the tail must also move one step in that direction so it remains close enough")
    public void headMovedDirectlyRight() {
        Rope rope = setupInitialRopePositions(ROPE_LENGTH_PART_1, new Position(1, 0));

        // MOVE HEAD AROUND TAIL:
        rope.moveHead(1, 1, 0);
        assertEquals(2, rope.getTailPositionList().size());
        assertEquals(new Position(1, 0), rope.getTailPositionList().get(1));
    }

    @Test
    @DisplayName("If the head is ever two steps directly down from the tail, " +
            "the tail must also move one step in that direction so it remains close enough")
    public void headMovedDirectlyDown() {
        Rope rope = setupInitialRopePositions(ROPE_LENGTH_PART_1, new Position(0, -1));

        rope.moveHead(1, 0, -1);
        assertEquals(2, rope.getTailPositionList().size());
        assertEquals(new Position(0, -1), rope.getTailPositionList().get(1));
    }

    @Test
    @DisplayName("If the head and tail aren't touching and aren't in the same row or column, " +
            "the tail always moves one step diagonally to keep up")
    public void headMovedDiagonallyAwayFromTail() {
        Rope rope = setupInitialRopePositions(ROPE_LENGTH_PART_1, new Position(1, 1));

        // MOVE HEAD RIGHT
        rope.moveHead(1, 0, 1);
        assertEquals(new Position(1, 1), rope.getTailPositionList().get(1));

        // REINITIALIZE SECTION POSITIONS
        rope = setupInitialRopePositions(ROPE_LENGTH_PART_1, new Position(1, 1));

        // MOVE HEAD UP
        rope.moveHead(1, 1, 0);
        assertEquals(new Position(1, 1), rope.getTailPositionList().get(1));
    }

    @Test
    @DisplayName("It should return the number of unique positions visited by the tail - part 1")
    public void countUniquePositionsVisitedByTail_part1() {
        Day9 day9 = new Day9(ROPE_LENGTH_PART_1);
        day9.readAndParseFile(PATH_TO_TEST_INPUT_PART_1);

        assertEquals(13, day9.getSolution());
    }

    @Test
    @DisplayName("It should return the number of unique positions visited by the tail - part 2")
    public void countUniquePositionsVisitedByTail_part2() {
        Integer ROPE_LENGTH_PART_2 = 10;
        Day9 day9 = new Day9(ROPE_LENGTH_PART_2);
        day9.readAndParseFile(PATH_TO_TEST_INPUT_PART_2);

        assertEquals(36, day9.getSolution());
    }
}
