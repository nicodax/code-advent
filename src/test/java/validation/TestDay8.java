package validation;

import me.nicodax.day8.Day8;
import me.nicodax.day8.Tree;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDay8 {
    private HashMap<String, Tree> treeHashMap;

    private void setUpTrees(Day8 day8) {
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

        day8.setTreeHashMap(treeHashMap);
        day8.setGRID_WIDTH(5);
        day8.setGRID_HEIGHT(5);
    }

    @Test
    public void treesAroundEdgesAreVisible() {
        Day8 day8 = new Day8();
        setUpTrees(day8);

        assertTrue(day8.treeIsVisibleFromOutside(treeHashMap.get("0-0")));
        assertTrue(day8.treeIsVisibleFromOutside(treeHashMap.get("1-0")));
        assertTrue(day8.treeIsVisibleFromOutside(treeHashMap.get("2-0")));
        assertTrue(day8.treeIsVisibleFromOutside(treeHashMap.get("3-0")));
        assertTrue(day8.treeIsVisibleFromOutside(treeHashMap.get("4-0")));

        assertTrue(day8.treeIsVisibleFromOutside(treeHashMap.get("0-4")));
        assertTrue(day8.treeIsVisibleFromOutside(treeHashMap.get("1-4")));
        assertTrue(day8.treeIsVisibleFromOutside(treeHashMap.get("2-4")));
        assertTrue(day8.treeIsVisibleFromOutside(treeHashMap.get("3-4")));
        assertTrue(day8.treeIsVisibleFromOutside(treeHashMap.get("4-4")));

        assertTrue(day8.treeIsVisibleFromOutside(treeHashMap.get("0-1")));
        assertTrue(day8.treeIsVisibleFromOutside(treeHashMap.get("0-2")));
        assertTrue(day8.treeIsVisibleFromOutside(treeHashMap.get("0-3")));

        assertTrue(day8.treeIsVisibleFromOutside(treeHashMap.get("4-1")));
        assertTrue(day8.treeIsVisibleFromOutside(treeHashMap.get("4-2")));
        assertTrue(day8.treeIsVisibleFromOutside(treeHashMap.get("4-3")));
    }

    @Test
    public void topLeftTreeIsVisible() {
        Day8 day8 = new Day8();
        setUpTrees(day8);

        assertTrue(day8.treeIsVisibleFromOutside(treeHashMap.get("1-1")));
    }

    @Test
    public void topMiddleTreeIsVisible() {
        Day8 day8 = new Day8();
        setUpTrees(day8);

        assertTrue(day8.treeIsVisibleFromOutside(treeHashMap.get("2-1")));
    }

    @Test
    public void topRightTreeIsNotVisible() {
        Day8 day8 = new Day8();
        setUpTrees(day8);

        assertFalse(day8.treeIsVisibleFromOutside(treeHashMap.get("3-1")));
    }

    @Test
    public void LeftMiddleTreeIsVisible() {
        Day8 day8 = new Day8();
        setUpTrees(day8);

        assertTrue(day8.treeIsVisibleFromOutside(treeHashMap.get("1-2")));
    }

    @Test
    public void centerTreeIsNotVisible() {
        Day8 day8 = new Day8();
        setUpTrees(day8);

        assertFalse(day8.treeIsVisibleFromOutside(treeHashMap.get("2-2")));
    }

    @Test
    public void rightMiddleTreeIsVisible() {
        Day8 day8 = new Day8();
        setUpTrees(day8);

        assertTrue(day8.treeIsVisibleFromOutside(treeHashMap.get("3-2")));
    }

    @Test
    public void bottomLeftTreeIsNotVisible() {
        Day8 day8 = new Day8();
        setUpTrees(day8);

        assertFalse(day8.treeIsVisibleFromOutside(treeHashMap.get("1-3")));
    }

    @Test
    public void bottomRightTreeIsNotVisible() {
        Day8 day8 = new Day8();
        setUpTrees(day8);

        assertFalse(day8.treeIsVisibleFromOutside(treeHashMap.get("3-3")));
    }

    @Test
    public void bottomMiddleTreeIsVisible() {
        Day8 day8 = new Day8();
        setUpTrees(day8);

        assertTrue(day8.treeIsVisibleFromOutside(treeHashMap.get("2-3")));
    }

    @Test
    public void countVisibleTrees() {
        Day8 day8 = new Day8();
        setUpTrees(day8);

        assertEquals(21, day8.getPart1Solution());
    }
}
