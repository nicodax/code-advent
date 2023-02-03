package me.nicodax.day9;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day9Test {
    private final Path PATH_TO_INPUT = Paths.get(System.getProperty("user.dir")
                                                         + "\\src\\main\\resources\\input-day9.txt");
    private final Path PATH_TO_TEST_INPUT = Paths.get(System.getProperty("user.dir")
                                                              + "\\src\\main\\resources\\test-input-day9.txt");

    @Test
    @DisplayName("Should move the rope's head to the right, followed by the rope's tail, "
            + "and keep track of the rope's tail positions")
    public void executeAction_right() {
        Day9 day9 = new Day9();
        String line = "R 4";
        day9.executeAction(line);

        Position expectedPosition0 = new Position(0, 0);
        Position expectedPosition1 = new Position(1, 0);
        Position expectedPosition2 = new Position(2, 0);
        Position expectedPosition3 = new Position(3, 0);

        assertEquals(4, day9.getTailPositionList().size());
        assertEquals(expectedPosition0.getX(), day9.getTailPositionList().get(0).getX());
        assertEquals(expectedPosition0.getY(), day9.getTailPositionList().get(0).getY());
        assertEquals(expectedPosition1.getX(), day9.getTailPositionList().get(1).getX());
        assertEquals(expectedPosition1.getY(), day9.getTailPositionList().get(1).getY());
        assertEquals(expectedPosition2.getX(), day9.getTailPositionList().get(2).getX());
        assertEquals(expectedPosition2.getY(), day9.getTailPositionList().get(2).getY());
        assertEquals(expectedPosition3.getX(), day9.getTailPositionList().get(3).getX());
        assertEquals(expectedPosition3.getY(), day9.getTailPositionList().get(3).getY());
    }

    @Test
    @DisplayName("Should move the rope's head upward, followed by the rope's tail, "
            + "and keep track of the rope's tail positions")
    public void executeAction_up() {
        Day9 day9 = new Day9();
        String line = "U 4";
        day9.executeAction(line);

        Position expectedPosition0 = new Position(0, 0);
        Position expectedPosition1 = new Position(0, 1);
        Position expectedPosition2 = new Position(0, 2);
        Position expectedPosition3 = new Position(0, 3);

        assertEquals(4, day9.getTailPositionList().size());
        assertEquals(expectedPosition0.getX(), day9.getTailPositionList().get(0).getX());
        assertEquals(expectedPosition0.getY(), day9.getTailPositionList().get(0).getY());
        assertEquals(expectedPosition1.getX(), day9.getTailPositionList().get(1).getX());
        assertEquals(expectedPosition1.getY(), day9.getTailPositionList().get(1).getY());
        assertEquals(expectedPosition2.getX(), day9.getTailPositionList().get(2).getX());
        assertEquals(expectedPosition2.getY(), day9.getTailPositionList().get(2).getY());
        assertEquals(expectedPosition3.getX(), day9.getTailPositionList().get(3).getX());
        assertEquals(expectedPosition3.getY(), day9.getTailPositionList().get(3).getY());
    }

    @Test
    @DisplayName("Should move the rope's head downward, followed by the rope's tail, "
            + "and keep track of the rope's tail positions")
    public void executeAction_down() {
        Day9 day9 = new Day9();
        String line = "D 4";
        day9.executeAction(line);

        Position expectedPosition0 = new Position(0, 0);
        Position expectedPosition1 = new Position(0, -1);
        Position expectedPosition2 = new Position(0, -2);
        Position expectedPosition3 = new Position(0, -3);

        assertEquals(4, day9.getTailPositionList().size());
        assertEquals(expectedPosition0.getX(), day9.getTailPositionList().get(0).getX());
        assertEquals(expectedPosition0.getY(), day9.getTailPositionList().get(0).getY());
        assertEquals(expectedPosition1.getX(), day9.getTailPositionList().get(1).getX());
        assertEquals(expectedPosition1.getY(), day9.getTailPositionList().get(1).getY());
        assertEquals(expectedPosition2.getX(), day9.getTailPositionList().get(2).getX());
        assertEquals(expectedPosition2.getY(), day9.getTailPositionList().get(2).getY());
        assertEquals(expectedPosition3.getX(), day9.getTailPositionList().get(3).getX());
        assertEquals(expectedPosition3.getY(), day9.getTailPositionList().get(3).getY());
    }

    @Test
    @DisplayName("Should move the rope's head to the left, followed by the rope's tail, "
            + "and keep track of the rope's tail positions")
    public void executeAction_left() {
        Day9 day9 = new Day9();
        String line = "L 4";
        day9.executeAction(line);

        Position expectedPosition0 = new Position(0, 0);
        Position expectedPosition1 = new Position(-1, 0);
        Position expectedPosition2 = new Position(-2, 0);
        Position expectedPosition3 = new Position(-3, 0);

        assertEquals(4, day9.getTailPositionList().size());
        assertEquals(expectedPosition0.getX(), day9.getTailPositionList().get(0).getX());
        assertEquals(expectedPosition0.getY(), day9.getTailPositionList().get(0).getY());
        assertEquals(expectedPosition1.getX(), day9.getTailPositionList().get(1).getX());
        assertEquals(expectedPosition1.getY(), day9.getTailPositionList().get(1).getY());
        assertEquals(expectedPosition2.getX(), day9.getTailPositionList().get(2).getX());
        assertEquals(expectedPosition2.getY(), day9.getTailPositionList().get(2).getY());
        assertEquals(expectedPosition3.getX(), day9.getTailPositionList().get(3).getX());
        assertEquals(expectedPosition3.getY(), day9.getTailPositionList().get(3).getY());
    }

    @Test
    @DisplayName("Should read and parse input file")
    public void readAndParseFile() {
        Day9 day9 = new Day9();
        day9.readAndParseFile(PATH_TO_TEST_INPUT);
        assertEquals(13, day9.getNumberOfPositionsVisitedAtLeastOnce());
    }

    @Disabled
    @Test
    public void day9_part1() {
        Day9 day9 = new Day9();
        day9.readAndParseFile(PATH_TO_INPUT);
        System.out.println("SOLUTION PART 1: " + day9.getNumberOfPositionsVisitedAtLeastOnce());
    }
}
