package validation;

import me.nicodax.day12.Day12;
import me.nicodax.day12.Node;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDay12 {
    private final String PATH_SEPARATOR = File.separator;
    private final Path PATH_TO_TEST_INPUT = Paths.get(System.getProperty("user.dir")
            + PATH_SEPARATOR + "src"
            + PATH_SEPARATOR + "main"
            + PATH_SEPARATOR + "resources"
            + PATH_SEPARATOR + "test-input-day12.txt");

    @Test
    @DisplayName("Should read and parse the given input - generates all nodes")
    public void readAndParse_nodes() {
        Day12 day12 = new Day12(PATH_TO_TEST_INPUT, true);
        List<List<Node>> nodes = day12.getNodes();

        // LINE 1
        assertEquals(new Node(0, 0, 'S'), nodes.get(0).get(0));
        assertEquals(new Node(1, 0, 'a'), nodes.get(0).get(1));
        assertEquals(new Node(2, 0, 'b'), nodes.get(0).get(2));
        assertEquals(new Node(3, 0, 'q'), nodes.get(0).get(3));
        assertEquals(new Node(4, 0, 'p'), nodes.get(0).get(4));
        assertEquals(new Node(5, 0, 'o'), nodes.get(0).get(5));
        assertEquals(new Node(6, 0, 'n'), nodes.get(0).get(6));
        assertEquals(new Node(7, 0, 'm'), nodes.get(0).get(7));

        // LINE 2
        assertEquals(new Node(0, 1, 'a'), nodes.get(1).get(0));
        assertEquals(new Node(1, 1, 'b'), nodes.get(1).get(1));
        assertEquals(new Node(2, 1, 'c'), nodes.get(1).get(2));
        assertEquals(new Node(3, 1, 'r'), nodes.get(1).get(3));
        assertEquals(new Node(4, 1, 'y'), nodes.get(1).get(4));
        assertEquals(new Node(5, 1, 'x'), nodes.get(1).get(5));
        assertEquals(new Node(6, 1, 'x'), nodes.get(1).get(6));
        assertEquals(new Node(7, 1, 'l'), nodes.get(1).get(7));

        // LINE 3
        assertEquals(new Node(0, 2, 'a'), nodes.get(2).get(0));
        assertEquals(new Node(1, 2, 'c'), nodes.get(2).get(1));
        assertEquals(new Node(2, 2, 'c'), nodes.get(2).get(2));
        assertEquals(new Node(3, 2, 's'), nodes.get(2).get(3));
        assertEquals(new Node(4, 2, 'z'), nodes.get(2).get(4));
        assertEquals(new Node(5, 2, 'E'), nodes.get(2).get(5));
        assertEquals(new Node(6, 2, 'x'), nodes.get(2).get(6));
        assertEquals(new Node(7, 2, 'k'), nodes.get(2).get(7));

        // LINE 4
        assertEquals(new Node(0, 3, 'a'), nodes.get(3).get(0));
        assertEquals(new Node(1, 3, 'c'), nodes.get(3).get(1));
        assertEquals(new Node(2, 3, 'c'), nodes.get(3).get(2));
        assertEquals(new Node(3, 3, 't'), nodes.get(3).get(3));
        assertEquals(new Node(4, 3, 'u'), nodes.get(3).get(4));
        assertEquals(new Node(5, 3, 'v'), nodes.get(3).get(5));
        assertEquals(new Node(6, 3, 'w'), nodes.get(3).get(6));
        assertEquals(new Node(7, 3, 'j'), nodes.get(3).get(7));

        // LINE 5
        assertEquals(new Node(0, 4, 'a'), nodes.get(4).get(0));
        assertEquals(new Node(1, 4, 'b'), nodes.get(4).get(1));
        assertEquals(new Node(2, 4, 'd'), nodes.get(4).get(2));
        assertEquals(new Node(3, 4, 'e'), nodes.get(4).get(3));
        assertEquals(new Node(4, 4, 'f'), nodes.get(4).get(4));
        assertEquals(new Node(5, 4, 'g'), nodes.get(4).get(5));
        assertEquals(new Node(6, 4, 'h'), nodes.get(4).get(6));
        assertEquals(new Node(7, 4, 'i'), nodes.get(4).get(7));
    }

    @Test
    @DisplayName("Should read and parse the given input - saves start, current and end nodes")
    public void readAndParse_specialNodes() {
        Day12 day12 = new Day12(PATH_TO_TEST_INPUT, true);
        Node startNode = day12.getStartNode();
        Node endNode = day12.getEndNode();

        assertEquals(new Node(0, 0, 'S'), endNode);
        assertEquals(new Node(5, 2, 'E'), startNode);
    }

    @Test
    @DisplayName("Should return the length of the shortest path from 'S' to 'E'")
    public void getSumOfEvery20PlusNTimes40CycleSignalStrength() {
        Day12 day12 = new Day12(PATH_TO_TEST_INPUT, true);
        assertEquals(31, day12.getPart1Solution());
    }
}
