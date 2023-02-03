package me.nicodax.day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class Day8 {
    private final HashMap<String, Tree> visibleTrees = new HashMap<>();
    private Integer highestScenicScore = 0;

    public Integer getHighestScenicScore() {
        return highestScenicScore;
    }

    public HashMap<String, Tree> getVisibleTrees() {
        return visibleTrees;
    }

    public void readAndParseFile(Path path) {
        try (Stream<String> linesStream = Files.lines(path)) {
            List<String> lines = linesStream.toList();
            List<String> columns = new ArrayList<>();
            for (int col = 0; col < lines.get(0).length(); col++) {
                StringBuilder column = new StringBuilder();
                for (String line : lines) column.append(line.charAt(col));
                columns.add(column.toString());
            }
            for (int y = 1; y <= lines.size(); y++) {
                for (int x = 1; x <= columns.size(); x++) {
                    Integer scenicScoreToRight = scenicScoreToRight(lines.get(y - 1), x);
                    Integer scenicScoreToLeft = scenicScoreToLeft(lines.get(y - 1), x);
                    Integer scenicScoreToDown = scenicScoreToDown(columns.get(x - 1), y);
                    Integer scenicScoreToUp = scenicScoreToUp(columns.get(x - 1), y);
                    int scenicScore = scenicScoreToDown * scenicScoreToLeft * scenicScoreToRight * scenicScoreToUp;
                    if (scenicScore > highestScenicScore) highestScenicScore = scenicScore;
                }
            }
            for (String line : lines) {
                leftRight(line, lines.indexOf(line) + 1);
                rightLeft(line, lines.indexOf(line) + 1);
            }
            for (String column : columns) {
                topDown(column, columns.indexOf(column) + 1);
                downTop(column, columns.indexOf(column) + 1);
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public void topDown(String column, Integer x) {
        List<Integer> columnAsArrayOfInt = column.chars().boxed().toList();
        Integer highestTreeSizeInFront = -1;
        for (int y = 1; y <= column.length(); y++) {
            Integer treeSize = columnAsArrayOfInt.get(y - 1);
            if (treeSize > highestTreeSizeInFront) {
                highestTreeSizeInFront = treeSize;
                if (treeIsVisible(x, y)) continue;
                saveTree(x, y);
            }
        }
    }

    public void downTop(String column, Integer x) {
        List<Integer> columnAsArrayOfInt = column.chars().boxed().toList();
        Integer highestTreeSizeInFront = -1;
        for (int y = column.length(); y > 0; y--) {
            Integer treeSize = columnAsArrayOfInt.get(y - 1);
            if (treeSize > highestTreeSizeInFront) {
                highestTreeSizeInFront = treeSize;
                if (treeIsVisible(x, y)) continue;
                saveTree(x, y);
            }
        }
    }

    public void leftRight(String line, Integer y) {
        List<Integer> lineAsArrayOfInt = line.chars().boxed().toList();
        Integer highestTreeSizeInFront = -1;
        for (int x = 1; x <= line.length(); x++) {
            Integer treeSize = lineAsArrayOfInt.get(x - 1);
            if (treeSize > highestTreeSizeInFront) {
                highestTreeSizeInFront = treeSize;
                if (treeIsVisible(x, y)) continue;
                saveTree(x, y);
            }
        }
    }

    public void rightLeft(String line, Integer y) {
        List<Integer> lineAsArrayOfInt = line.chars().boxed().toList();
        Integer highestTreeSizeInFront = -1;
        for (int x = line.length(); x > 0; x--) {
            Integer treeSize = lineAsArrayOfInt.get(x - 1);
            if (treeSize > highestTreeSizeInFront) {
                highestTreeSizeInFront = treeSize;
                if (treeIsVisible(x, y)) continue;
                saveTree(x, y);
            }
        }
    }

    public Integer scenicScoreToRight(String line, Integer x) {
        List<Integer> lineAsArrayOfInt = line.chars().boxed().toList();
        Integer seenTrees = 0;
        Integer treeHeight = lineAsArrayOfInt.get(x - 1);
        for (int col = x; col < line.length(); col++) {
            seenTrees++;
            if (treeHeight <= lineAsArrayOfInt.get(col)) return seenTrees;
        }
        return seenTrees;
    }

    public Integer scenicScoreToLeft(String line, Integer x) {
        List<Integer> lineAsArrayOfInt = line.chars().boxed().toList();
        Integer seenTrees = 0;
        Integer treeHeight = lineAsArrayOfInt.get(x - 1);
        for (int col = x - 1; col > 0; col--) {
            seenTrees++;
            if (treeHeight <= lineAsArrayOfInt.get(col - 1)) return seenTrees;
        }
        return seenTrees;
    }

    public Integer scenicScoreToUp(String column, Integer y) {
        List<Integer> columnAsArrayOfInt = column.chars().boxed().toList();
        Integer seenTrees = 0;
        Integer treeHeight = columnAsArrayOfInt.get(y - 1);
        for (int line = y - 1; line > 0; line--) {
            seenTrees++;
            if (treeHeight <= columnAsArrayOfInt.get(line - 1)) return seenTrees;
        }
        return seenTrees;
    }

    public Integer scenicScoreToDown(String column, Integer y) {
        List<Integer> columnAsArrayOfInt = column.chars().boxed().toList();
        Integer seenTrees = 0;
        Integer treeHeight = columnAsArrayOfInt.get(y - 1);
        for (int line = y; line < column.length(); line++) {
            seenTrees++;
            if (treeHeight <= columnAsArrayOfInt.get(line)) return seenTrees;
        }
        return seenTrees;
    }

    public Boolean treeIsVisible(Integer x, Integer y) {
        return visibleTrees.get(generateTreeId(x, y)) != null;
    }

    public void saveTree(Integer x, Integer y) {
        visibleTrees.put(generateTreeId(x, y), new Tree(x, y));
    }

    public String generateTreeId(Integer x, Integer y) {
        return x + "-" + y;
    }

    public Integer getNumberOfVisibleTrees() {
        return visibleTrees.size();
    }
}
