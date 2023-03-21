package validation;

import me.nicodax.day8.Day8;
import me.nicodax.day8.Tree;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDay8 {
    private final String PATH_SEPARATOR = File.separator;
    private final Path PATH_TO_TEST_INPUT = Paths.get(System.getProperty("user.dir")
            + PATH_SEPARATOR + "src"
            + PATH_SEPARATOR + "main"
            + PATH_SEPARATOR + "resources"
            + PATH_SEPARATOR + "test-input-day8.txt");

    private HashMap<String, Tree> treeHashMap;

    private void setUpTestTrees(Day8 day8) {
        treeHashMap = new HashMap<>();
        // line 1
        treeHashMap.put("0-0", new Tree(0, 0, 3));
        treeHashMap.put("1-0", new Tree(1, 0, 0));
        treeHashMap.put("2-0", new Tree(2, 0, 3));
        treeHashMap.put("3-0", new Tree(3, 0, 7));
        treeHashMap.put("4-0", new Tree(4, 0, 3));
        // line 2
        treeHashMap.put("0-1", new Tree(0, 1, 2));
        treeHashMap.put("1-1", new Tree(1, 1, 5));
        treeHashMap.put("2-1", new Tree(2, 1, 5));
        treeHashMap.put("3-1", new Tree(3, 1, 1));
        treeHashMap.put("4-1", new Tree(4, 1, 2));
        // line 3
        treeHashMap.put("0-2", new Tree(0, 2, 6));
        treeHashMap.put("1-2", new Tree(1, 2, 5));
        treeHashMap.put("2-2", new Tree(2, 2, 3));
        treeHashMap.put("3-2", new Tree(3, 2, 3));
        treeHashMap.put("4-2", new Tree(4, 2, 2));
        // line 4
        treeHashMap.put("0-3", new Tree(0, 3, 3));
        treeHashMap.put("1-3", new Tree(1, 3, 3));
        treeHashMap.put("2-3", new Tree(2, 3, 5));
        treeHashMap.put("3-3", new Tree(3, 3, 4));
        treeHashMap.put("4-3", new Tree(4, 3, 9));
        // line 5
        treeHashMap.put("0-4", new Tree(0, 4, 3));
        treeHashMap.put("1-4", new Tree(1, 4, 5));
        treeHashMap.put("2-4", new Tree(2, 4, 3));
        treeHashMap.put("3-4", new Tree(3, 4, 9));
        treeHashMap.put("4-4", new Tree(4, 4, 0));
    }

    @Test
    public void treesAroundEdgesAreVisible() {
        Day8 day8 = new Day8();
        setUpTestTrees(day8);
        day8.readAndParseFile(PATH_TO_TEST_INPUT);
        day8.getPart1Solution();

        assertTrue(day8.getVisibleTrees().contains(treeHashMap.get("0-0")));
        assertTrue(day8.getVisibleTrees().contains(treeHashMap.get("1-0")));
        assertTrue(day8.getVisibleTrees().contains(treeHashMap.get("2-0")));
        assertTrue(day8.getVisibleTrees().contains(treeHashMap.get("3-0")));
        assertTrue(day8.getVisibleTrees().contains(treeHashMap.get("4-0")));

        assertTrue(day8.getVisibleTrees().contains(treeHashMap.get("0-4")));
        assertTrue(day8.getVisibleTrees().contains(treeHashMap.get("1-4")));
        assertTrue(day8.getVisibleTrees().contains(treeHashMap.get("2-4")));
        assertTrue(day8.getVisibleTrees().contains(treeHashMap.get("3-4")));
        assertTrue(day8.getVisibleTrees().contains(treeHashMap.get("4-4")));

        assertTrue(day8.getVisibleTrees().contains(treeHashMap.get("0-1")));
        assertTrue(day8.getVisibleTrees().contains(treeHashMap.get("0-2")));
        assertTrue(day8.getVisibleTrees().contains(treeHashMap.get("0-3")));

        assertTrue(day8.getVisibleTrees().contains(treeHashMap.get("4-1")));
        assertTrue(day8.getVisibleTrees().contains(treeHashMap.get("4-2")));
        assertTrue(day8.getVisibleTrees().contains(treeHashMap.get("4-3")));
    }

    @Test
    public void topLeftTreeIsVisible() {
        Day8 day8 = new Day8();
        setUpTestTrees(day8);
        day8.readAndParseFile(PATH_TO_TEST_INPUT);
        day8.getPart1Solution();

        assertTrue(day8.getVisibleTrees().contains(treeHashMap.get("1-1")));
    }

    @Test
    public void topMiddleTreeIsVisible() {
        Day8 day8 = new Day8();
        setUpTestTrees(day8);
        day8.readAndParseFile(PATH_TO_TEST_INPUT);
        day8.getPart1Solution();

        assertTrue(day8.getVisibleTrees().contains(treeHashMap.get("2-1")));
    }

    @Test
    public void topRightTreeIsNotVisible() {
        Day8 day8 = new Day8();
        setUpTestTrees(day8);
        day8.readAndParseFile(PATH_TO_TEST_INPUT);
        day8.getPart1Solution();

        assertFalse(day8.getVisibleTrees().contains(treeHashMap.get("3-1")));
    }

    @Test
    public void LeftMiddleTreeIsVisible() {
        Day8 day8 = new Day8();
        setUpTestTrees(day8);
        day8.readAndParseFile(PATH_TO_TEST_INPUT);
        day8.getPart1Solution();

        assertTrue(day8.getVisibleTrees().contains(treeHashMap.get("1-2")));
    }

    @Test
    public void centerTreeIsNotVisible() {
        Day8 day8 = new Day8();
        setUpTestTrees(day8);
        day8.readAndParseFile(PATH_TO_TEST_INPUT);
        day8.getPart1Solution();

        assertFalse(day8.getVisibleTrees().contains(treeHashMap.get("2-2")));
    }

    @Test
    public void rightMiddleTreeIsVisible() {
        Day8 day8 = new Day8();
        setUpTestTrees(day8);
        day8.readAndParseFile(PATH_TO_TEST_INPUT);
        day8.getPart1Solution();

        assertTrue(day8.getVisibleTrees().contains(treeHashMap.get("3-2")));
    }

    @Test
    public void bottomLeftTreeIsNotVisible() {
        Day8 day8 = new Day8();
        setUpTestTrees(day8);
        day8.readAndParseFile(PATH_TO_TEST_INPUT);
        day8.getPart1Solution();

        assertFalse(day8.getVisibleTrees().contains(treeHashMap.get("1-3")));
    }

    @Test
    public void bottomRightTreeIsNotVisible() {
        Day8 day8 = new Day8();
        setUpTestTrees(day8);
        day8.readAndParseFile(PATH_TO_TEST_INPUT);
        day8.getPart1Solution();

        assertFalse(day8.getVisibleTrees().contains(treeHashMap.get("3-3")));
    }

    @Test
    public void bottomMiddleTreeIsVisible() {
        Day8 day8 = new Day8();
        setUpTestTrees(day8);
        day8.readAndParseFile(PATH_TO_TEST_INPUT);
        day8.getPart1Solution();

        assertTrue(day8.getVisibleTrees().contains(treeHashMap.get("2-3")));
    }

    @Test
    public void countVisibleTrees() {
        Day8 day8 = new Day8();
        day8.readAndParseFile(PATH_TO_TEST_INPUT);

        assertEquals(21, day8.getPart1Solution());
    }

    @Test
    public void treesScenicScore() {
        Day8 day8 = new Day8();
        setUpTestTrees(day8);
        day8.readAndParseFile(PATH_TO_TEST_INPUT);
        HashMap<String, Integer> scenicScoreTreeHashMap = day8.getAllTreesScenicScore();

        assertEquals(4, scenicScoreTreeHashMap.get("2-1"));
        assertEquals(8, scenicScoreTreeHashMap.get("2-3"));
    }

    @Test
    public void getMaxScenicScore() {
        Day8 day8 = new Day8();
        day8.readAndParseFile(PATH_TO_TEST_INPUT);

        assertEquals(8, day8.getPart2Solution());
    }
}
