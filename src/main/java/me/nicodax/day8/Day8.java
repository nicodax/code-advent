package me.nicodax.day8;

import lombok.Setter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static java.lang.Math.toIntExact;

@Setter
public class Day8 {
    private HashMap<String, Tree> treeHashMap = new HashMap<>();
    private Integer GRID_WIDTH;
    private Integer GRID_HEIGHT;

    public void readAndParseFile(Path path) {
        try (Stream<String> linesStream = Files.lines(path)) {
            List<String> lines = linesStream.toList();
            GRID_HEIGHT = lines.size();
            GRID_WIDTH = lines.get(0).length();
            for (String line : lines) {
                if (line.isBlank()) continue;
                readAndParseLine(line, lines.indexOf(line));
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public void readAndParseLine(String line, Integer index) {
        List<Integer> treeHeightList = line.chars().mapToObj(c -> (char) c).map(this::intValue).toList();
        for (Integer height : treeHeightList) {
            Tree tree = new Tree(treeHeightList.indexOf(height), index, height);
            treeHashMap.put(treeHeightList.indexOf(height) + "-" + index, tree);
        }
    }

    public Integer intValue(Character character) {
        return parseInt(character.toString());
    }

    public Integer getPart1Solution() {
        return toIntExact(treeHashMap.values().stream().filter(this::treeIsVisibleFromOutside).count());
    }

    public Boolean treeIsVisibleFromOutside(Tree tree) {
        if (isVisibleInGivenDirection(tree,
                                      treeHashMap.get(tree.getNextTreeId(1, 0)),
                                      1,
                                      0))
            return true;
        else if (isVisibleInGivenDirection(tree,
                                           treeHashMap.get(tree.getNextTreeId(-1, 0)),
                                           -1,
                                           0))
            return true;
        else if (isVisibleInGivenDirection(tree,
                                           treeHashMap.get(tree.getNextTreeId(0, 1)),
                                           0,
                                           1))
            return true;
        return isVisibleInGivenDirection(tree,
                                         treeHashMap.get(tree.getNextTreeId(0, -1)),
                                         0,
                                         -1);
    }

    private boolean isVisibleInGivenDirection(Tree subject, Tree other, Integer xModifier, Integer yModifier) {
        if (other == null) return true;
        if (subject.height() <= other.height()) return false;
        return isVisibleInGivenDirection(subject,
                                         treeHashMap.get(other.getNextTreeId(xModifier, yModifier)),
                                         xModifier,
                                         yModifier);
    }

    public Integer getPart2Solution() {
        // TO DO
        return -1;
    }
}
