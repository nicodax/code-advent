package me.nicodax.day12;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.awt.Color.black;
import static java.lang.Integer.MAX_VALUE;

public class Day12 extends JPanel {

    public Node[][] display;
    @Getter
    private final List<List<Node>> nodes = new ArrayList<>();
    @Getter
    private Node currentNode;
    @Getter
    private Node startNode;
    private final Character START_NODE_CHAR = 'E';
    private final Character START_NODE_ELEVATION = 'z';
    private final Character END_NODE_CHAR = 'S';
    private final Character END_NODE_ELEVATION = 'a';
    @Getter
    private Node endNode;
    private final List<Node> openList = new ArrayList<>();
    private final List<Node> path = new ArrayList<>();

    private Boolean endReached = false;
    private List<String> lines;

    public Day12(Path path, Boolean isExecutedFromTest) {
        try (Stream<String> linesStream = Files.lines(path)) {
            lines = linesStream.toList();
            if (!isExecutedFromTest) {
                int SCREEN_COLUMNS = lines.get(0).length();
                int SCREEN_ROWS = lines.size();
                int NODE_WIDTH = 20;
                int PANEL_WIDTH = SCREEN_COLUMNS * NODE_WIDTH;
                int PANEL_HEIGHT = SCREEN_ROWS * NODE_WIDTH;
                display = new Node[SCREEN_COLUMNS][SCREEN_ROWS];
                this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
                this.setBackground(black);
                this.setLayout(new GridLayout(SCREEN_ROWS, SCREEN_COLUMNS));
                this.addKeyListener(new KeyHandler(this));
                this.setFocusable(true);
            }
            readAndParse(isExecutedFromTest);
//            setCostOnNodes();
            if (isExecutedFromTest) {
                resolve();
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public void reset() {
        endReached = false;
        currentNode = startNode;
        for (List<Node> line : nodes) {
            for (Node node : line) {
                openList.remove(node);
                path.remove(node);
                node.setBackground(node.setColorFromElevation());
                node.setForeground(black);
                node.open = false;
                node.checked = false;
            }
        }
    }

    public void readAndParse(Boolean isExecutedFromTest) {
        for (int y = 0; y < lines.size(); y++) {
            if (lines.get(y).isBlank()) continue;
            List<Character> line = lines.get(y).chars().mapToObj(c -> (char) c).toList();
            List<Node> nodeList = new ArrayList<>();
            for (int x = 0; x < line.size(); x++) {
                Node node = new Node(x, y, line.get(x));
                nodeList.add(node);
                if (!isExecutedFromTest) {
                    display[x][y] = node;
                    this.add(display[x][y]);
                }
                if (line.get(x).equals(START_NODE_CHAR)) setStartNode(nodeList.get(x));
                if (line.get(x).equals(END_NODE_CHAR)) setEndNode(nodeList.get(x));
            }
            nodes.add(nodeList);
        }
    }

    private void setStartNode(Node node) {
        node.setAsStart();
        startNode = node;
        currentNode = startNode;
    }

    private void setEndNode(Node node) {
        node.setAsEnd();
        endNode = node;
    }

    public void resolve() {
        for (int step = 0; step < MAX_VALUE; step++) {
            if (endReached) break;
            search();
        }
    }

    public void search() {
        int x = currentNode.col;
        int y = currentNode.row;
        currentNode.setAsChecked();
        openList.remove(currentNode);

        // OPEN THE UP NODE
        if (y - 1 >= 0) openNode(nodes.get(y - 1).get(x));
        // OPEN THE LEFT NODE
        if (x - 1 >= 0) openNode(nodes.get(y).get(x - 1));
        // OPEN THE DOWN NODE
        if (y + 1 < nodes.size()) openNode(nodes.get(y + 1).get(x));
        // OPEN THE RIGHT NODE
        if (x + 1 < nodes.get(0).size()) openNode(nodes.get(y).get(x + 1));

        currentNode = openList.get(0);
        if (currentNode == endNode) {
            endReached = true;
            trackThePath();
        }
    }

    private void openNode(Node node) {
        if (!node.open && !node.checked && nodeIsValid(node)) {
            node.setAsOpen();
            node.parent = currentNode;
            openList.add(node);
        }
    }

    private Boolean nodeIsValid(Node node) {
        char currentNodeElevation = currentNode.elevation == START_NODE_CHAR ? START_NODE_ELEVATION :
                currentNode.elevation;
        char nodeElevation = node.elevation == END_NODE_CHAR ? END_NODE_ELEVATION : node.elevation;
        return START_NODE_ELEVATION == 'a' ? (nodeElevation <= currentNodeElevation + 1) :
                (nodeElevation >= currentNodeElevation - 1);
    }

    private void trackThePath() {
        Node current = endNode;
        path.add(current);
        while (current != startNode) {
            current = current.parent;
            if (current != startNode) {
                path.add(current);
                current.setAsPath();
            }
        }
    }
    public Integer getPart1Solution() {
        return path.size();
    }
}
