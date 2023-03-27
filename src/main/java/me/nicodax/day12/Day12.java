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

    @Getter
    private final Integer PART;

    public Node[][] display;
    @Getter
    private final List<List<Node>> nodes = new ArrayList<>();
    @Getter
    private Node currentNode;
    @Getter
    private Node startNode;
    @Getter
    private final List<Node> startNodeList = new ArrayList<>();
    private final Character START_NODE_CHAR = 'S';
    private final Character START_NODE_ELEVATION = 'a';
    private final Character END_NODE_CHAR = 'E';
    private final Character END_NODE_ELEVATION = 'z';
    @Getter
    private Node endNode;
    private final List<Node> openList = new ArrayList<>();
    @Getter
    private final List<Node> checkedList = new ArrayList<>();
    private Integer shortestPath = MAX_VALUE;
    @Getter
    private final List<Node> path = new ArrayList<>();

    private Boolean endReached = false;
    private Boolean deadEndReached = false;
    @Getter
    private Boolean finishedPart2 = false;
    private List<String> lines;

    private double scale = 1.0;

    public Day12(Path path, Boolean isExecutedFromTest, Integer part) {
        PART = part;
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
            if (isExecutedFromTest) {
                if (PART == 1) {
                    resolve();
                    trackThePath(endNode);
                }
                else {
                    resolvePart2();
                    trackThePath(endNode);
                }
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.scale(scale, scale);
    }

    public void reset() {
        endReached = false;
        deadEndReached = false;
        finishedPart2 = false;
        currentNode = startNode;
        for (List<Node> line : nodes) {
            for (Node node : line) {
                openList.remove(node);
                checkedList.remove(node);
                path.remove(node);
                node.open = false;
                node.checked = false;
                node.wasDrawnChecked = false;
                node.path = false;
                node.setText("<html>" + node.elevation + "</html>");
                node.setColorFromElevation();
                node.setForeground(black);
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

    public void setStartNode(Node node) {
        node.setAsStart();
        startNode = node;
        currentNode = startNode;
    }

    private void setEndNode(Node node) {
        node.setAsEnd();
        endNode = node;
    }
    
    public void resolvePart2GUI() {
        resolve();
        trackThePath(endNode);
        setShortestPath();
        startNodeList.remove(0);
        if (startNodeList.size() == 0) finishedPart2 = true;
    }

    public void resolvePart2() {
        saveStartNodes();
        for (Node startNode : startNodeList) {
            if (startNodeList.indexOf(startNode) > 0) {
                this.startNode = startNode;
                reset();
            }
            resolve();
            trackThePath(endNode);
            setShortestPath();
        }
    }

    private void setShortestPath() {
        if (endReached && shortestPath > getPart1Solution()) shortestPath = getPart1Solution();
    }

    public void saveStartNodes() {
        for (List<Node> nodeLine : nodes) {
            for (Node node : nodeLine) {
                if (node.elevation == 'a' && nodeIsNextToB(node)) startNodeList.add(node);
            }
        }
    }

    private boolean nodeIsNextToB(Node node) {
        if (node.row - 1 >= 0 && nodes.get(node.row - 1).get(node.col).elevation == 'b') return true;
        else if (node.col - 1 >= 0 && nodes.get(node.row).get(node.col - 1).elevation == 'b') return true;
        else if (node.row + 1 < nodes.size() && nodes.get(node.row + 1).get(node.col).elevation == 'b') return true;
        else return node.col + 1 < nodes.get(0).size() && nodes.get(node.row).get(node.col + 1).elevation == 'b';
    }

    public void resolve() {
        for (int step = 0; step < MAX_VALUE; step++) {
            if (endReached) break;
            if (deadEndReached) break;
            search();
        }
    }

    public void search() {
        int x = currentNode.col;
        int y = currentNode.row;
        currentNode.setAsChecked(checkedList);
        openList.remove(currentNode);

        // OPEN THE UP NODE
        if (y - 1 >= 0) openNode(nodes.get(y - 1).get(x));
        // OPEN THE LEFT NODE
        if (x - 1 >= 0) openNode(nodes.get(y).get(x - 1));
        // OPEN THE DOWN NODE
        if (y + 1 < nodes.size()) openNode(nodes.get(y + 1).get(x));
        // OPEN THE RIGHT NODE
        if (x + 1 < nodes.get(0).size()) openNode(nodes.get(y).get(x + 1));

        if (openList.size() > 0) currentNode = openList.get(0);
        else deadEndReached = true;
        if (currentNode == endNode) endReached = true;
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

    public void trackThePath(Node current) {
        path.add(current);
        while (current != startNode) {
            current = current.parent;
            if (current != startNode) {
                path.add(current);
                current.startNode = startNode;
                current.endNode = endNode;
            }
        }
    }

    public Integer getPart1Solution() {
        return path.size();
    }

    public Integer getPart2Solution() {
        return shortestPath;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }
}
