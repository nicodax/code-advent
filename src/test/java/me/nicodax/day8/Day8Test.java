package me.nicodax.day8;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Day8Test {
    private final Path PATH_TO_INPUT = Paths.get(System.getProperty("user.dir")
                                                         + "\\src\\main\\resources\\input-day8.txt");
    private final Path PATH_TO_TEST_INPUT = Paths.get(System.getProperty("user.dir")
                                                              + "\\src\\main\\resources\\test-input-day8.txt");

    @Test
    @DisplayName("Should generate a unique tree id")
    public void generateTreeId() {
        Day8 day8 = new Day8();
        Integer x = 123;
        Integer y = 6;
        String expectedTreeId = "123-6";
        assertEquals(expectedTreeId, day8.generateTreeId(x, y));
    }

    @Test
    @DisplayName("Should save a new visible tree")
    public void saveTree() {
        Day8 day8 = new Day8();
        Integer x = 123;
        Integer y = 6;
        String treeId = day8.generateTreeId(x, y);
        day8.saveTree(x, y);
        assertNotNull(day8.getVisibleTrees().get(treeId));
        assertEquals(x, day8.getVisibleTrees().get(treeId).getX());
        assertEquals(y, day8.getVisibleTrees().get(treeId).getY());
    }

    @Test
    @DisplayName("Should return true if a given tree is visible. Returns false otherwise")
    public void treeIsVisible() {
        Day8 day8 = new Day8();
        Tree visibleTree = new Tree(123, 6);
        Tree hiddenTree = new Tree(456, 7);
        day8.saveTree(visibleTree.getX(), visibleTree.getY());

        assertTrue(day8.treeIsVisible(visibleTree.getX(), visibleTree.getY()));
        assertFalse(day8.treeIsVisible(hiddenTree.getX(), hiddenTree.getY()));
    }

    @Test
    @DisplayName("Should read a tree line from right to left and save visible trees")
    public void rightLeft() {
        Day8 day8 = new Day8();
        String line = "30373";
        day8.rightLeft(line, 1);

        assertEquals(2, day8.getNumberOfVisibleTrees());
        assertNotNull(day8.getVisibleTrees().get("5-1"));
        assertNotNull(day8.getVisibleTrees().get("4-1"));
    }

    @Test
    @DisplayName("Should read a tree line from left to right and save visible trees")
    public void leftRight() {
        Day8 day8 = new Day8();
        String line = "30373";
        day8.leftRight(line, 1);

        assertEquals(2, day8.getNumberOfVisibleTrees());
        assertNotNull(day8.getVisibleTrees().get("1-1"));
        assertNotNull(day8.getVisibleTrees().get("4-1"));
    }

    @Test
    @DisplayName("Should read a tree column from bottom to top and save visible trees")
    public void downTop() {
        Day8 day8 = new Day8();
        String column = "32633";
        day8.downTop(column, 1);

        assertEquals(2, day8.getNumberOfVisibleTrees());
        assertNotNull(day8.getVisibleTrees().get("1-5"));
        assertNotNull(day8.getVisibleTrees().get("1-3"));
    }

    @Test
    @DisplayName("Should read a tree column from top to bottom and save visible trees")
    public void topDown() {
        Day8 day8 = new Day8();
        String column = "32633";
        day8.topDown(column, 1);

        assertEquals(2, day8.getNumberOfVisibleTrees());
        assertNotNull(day8.getVisibleTrees().get("1-1"));
        assertNotNull(day8.getVisibleTrees().get("1-3"));
    }

    @Test
    @DisplayName("Should read and parse the input file")
    public void readAndParseFile() {
        Day8 day8 = new Day8();
        day8.readAndParseFile(PATH_TO_TEST_INPUT);

        assertEquals(21, day8.getNumberOfVisibleTrees());
        // BORDERS
        assertNotNull(day8.getVisibleTrees().get("1-1"));
        assertNotNull(day8.getVisibleTrees().get("2-1"));
        assertNotNull(day8.getVisibleTrees().get("3-1"));
        assertNotNull(day8.getVisibleTrees().get("4-1"));
        assertNotNull(day8.getVisibleTrees().get("5-1"));

        assertNotNull(day8.getVisibleTrees().get("1-5"));
        assertNotNull(day8.getVisibleTrees().get("2-5"));
        assertNotNull(day8.getVisibleTrees().get("3-5"));
        assertNotNull(day8.getVisibleTrees().get("4-5"));
        assertNotNull(day8.getVisibleTrees().get("5-5"));

        assertNotNull(day8.getVisibleTrees().get("1-2"));
        assertNotNull(day8.getVisibleTrees().get("1-3"));
        assertNotNull(day8.getVisibleTrees().get("1-4"));

        assertNotNull(day8.getVisibleTrees().get("5-2"));
        assertNotNull(day8.getVisibleTrees().get("5-3"));
        assertNotNull(day8.getVisibleTrees().get("5-4"));

        // INSIDE
        assertNotNull(day8.getVisibleTrees().get("2-2"));
        assertNotNull(day8.getVisibleTrees().get("3-2"));
        assertNotNull(day8.getVisibleTrees().get("2-3"));
        assertNotNull(day8.getVisibleTrees().get("4-3"));
        assertNotNull(day8.getVisibleTrees().get("3-4"));
    }

    @Disabled
    @Test
    public void day8_part1() {
        Day8 day8 = new Day8();
        day8.readAndParseFile(PATH_TO_INPUT);
        System.out.println("PART 1 SOLUTION: " + day8.getNumberOfVisibleTrees());
    }

    @Test
    @DisplayName("Should return the downward scenic score of a given tree in a given column")
    public void scenicScoreToDown() {
        Day8 day8 = new Day8();
        String column = "32633";
        assertEquals(2, day8.scenicScoreToDown(column, 1));
        assertEquals(1, day8.scenicScoreToDown(column, 2));
        assertEquals(2, day8.scenicScoreToDown(column, 3));
        assertEquals(1, day8.scenicScoreToDown(column, 4));
        assertEquals(0, day8.scenicScoreToDown(column, 5));
    }

    @Test
    @DisplayName("Should return the upward scenic score of a given tree in a given column")
    public void scenicScoreToUp() {
        Day8 day8 = new Day8();
        String column = "32633";
        assertEquals(0, day8.scenicScoreToUp(column, 1));
        assertEquals(1, day8.scenicScoreToUp(column, 2));
        assertEquals(2, day8.scenicScoreToUp(column, 3));
        assertEquals(1, day8.scenicScoreToUp(column, 4));
        assertEquals(1, day8.scenicScoreToUp(column, 5));
    }

    @Test
    @DisplayName("Should return the left scenic score of a given tree in a given line")
    public void scenicScoreToLeft() {
        Day8 day8 = new Day8();
        String line = "30373";
        assertEquals(0, day8.scenicScoreToLeft(line, 1));
        assertEquals(1, day8.scenicScoreToLeft(line, 2));
        assertEquals(2, day8.scenicScoreToLeft(line, 3));
        assertEquals(3, day8.scenicScoreToLeft(line, 4));
        assertEquals(1, day8.scenicScoreToLeft(line, 5));
    }

    @Test
    @DisplayName("Should return the right scenic score of a given tree in a given line")
    public void scenicScoreToRight() {
        Day8 day8 = new Day8();
        String line = "30373";
        assertEquals(2, day8.scenicScoreToRight(line, 1));
        assertEquals(1, day8.scenicScoreToRight(line, 2));
        assertEquals(1, day8.scenicScoreToRight(line, 3));
        assertEquals(1, day8.scenicScoreToRight(line, 4));
        assertEquals(0, day8.scenicScoreToRight(line, 5));
    }

    @Test
    @DisplayName("Should read and parse the input file")
    public void readAndParseFileThenGetHighestScenicScore() {
        Day8 day8 = new Day8();
        day8.readAndParseFile(PATH_TO_TEST_INPUT);
        assertEquals(8, day8.getHighestScenicScore());
    }

    @Disabled
    @Test
    public void day8_part2() {
        Day8 day8 = new Day8();
        day8.readAndParseFile(PATH_TO_INPUT);
        System.out.println("PART 2 SOLUTION: " + day8.getHighestScenicScore());
    }
}
