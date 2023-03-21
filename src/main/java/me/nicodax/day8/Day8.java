package me.nicodax.day8;

import com.google.common.annotations.VisibleForTesting;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

@Getter
public class Day8 {
    private final Integer MAX_TREE_HEIGHT = 9;
    private final List<List<Tree>> treeLines = new ArrayList<>();
    private final List<List<Tree>> treeColumns = new ArrayList<>();
    private final Set<Tree> visibleTrees = new HashSet<>();
    private final Set<Tree> allTrees = new HashSet<>();

    public void readAndParseFile(Path path) {
        try (Stream<String> linesStream = Files.lines(path)) {
            List<String> lines = linesStream.toList();
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if (line.isBlank()) continue;
                List<Tree> treeLine = new ArrayList<>();
                for (int j = 0; j < line.length(); j++) {
                    treeLine.add(new Tree(j, i, intValue(line.charAt(j))));
                }
                treeLines.add(treeLine);
                treeLines.forEach(tl -> allTrees.addAll(tl));
            }
            for (int i = 0; i < lines.get(0).length(); i++) {
                List<Tree> treeColumn = new ArrayList<>();
                for (List<Tree> treeLine : treeLines) treeColumn.add(treeLine.get(i));
                treeColumns.add(treeColumn);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private Integer intValue(Character character) {
        return parseInt(character.toString());
    }

    private void getVisibleTreesInDirection(List<Tree> treeList,
                                            Integer direction,
                                            Integer startIndex,
                                            Integer stopIndex) {
        int maxTreeHeight = -1;
        for (int i = startIndex; i != stopIndex; i += direction) {
            if (maxTreeHeight >= MAX_TREE_HEIGHT) return;
            if (treeList.get(i).height() > maxTreeHeight) {
                visibleTrees.add(treeList.get(i));
                maxTreeHeight = treeList.get(i).height();
            }
        }
    }

    private Integer getScenicScoreInDirection(Tree tree,
                                              List<Tree> treeList,
                                              Integer direction,
                                              Integer startIndex,
                                              Integer endIndex) {
        if (startIndex < 0 || startIndex > treeList.size() - 1) return 0;
        int scenicScore = 0;
        for (int i = startIndex; i != endIndex; i += direction) {
            scenicScore++;
            if (tree.height() <= treeList.get(i).height()) break;
        }
        return scenicScore;
    }

    public Integer getPart1Solution() {
        treeLines.forEach(tl -> getVisibleTreesInDirection(tl, 1, 0, tl.size() - 1));
        treeLines.forEach(tl -> getVisibleTreesInDirection(tl, -1, tl.size() - 1, 0));
        treeColumns.forEach(tc -> getVisibleTreesInDirection(tc, 1, 0, tc.size() - 1));
        treeColumns.forEach(tc -> getVisibleTreesInDirection(tc, -1, tc.size() - 1, 0));
        return visibleTrees.size();
    }

    public Integer getPart2Solution() {
        int highestScenicScore = -1;
        for (Tree tree : allTrees) {
            int treeScenicScore =
                    getScenicScoreInDirection(tree, treeColumns.get(tree.x()), -1, tree.y() - 1, -1) *
                            getScenicScoreInDirection(tree, treeLines.get(tree.y()), +1, tree.x() + 1, treeLines.size()) *
                            getScenicScoreInDirection(tree, treeColumns.get(tree.x()), +1, tree.y() + 1, treeColumns.size()) *
                            getScenicScoreInDirection(tree, treeLines.get(tree.y()), -1, tree.x() - 1, -1);
            if (treeScenicScore > highestScenicScore) highestScenicScore = treeScenicScore;
        }
        return highestScenicScore;
    }

    /**
     * This method is only used for testing (specific trees should have the expected scenic score).
     */
    public HashMap<String, Integer> getAllTreesScenicScore() {
        HashMap<String, Integer> scenicScoreHashMap = new HashMap<>();
        for (Tree tree : allTrees) {
            int treeScenicScore =
                    getScenicScoreInDirection(tree, treeColumns.get(tree.x()), -1, tree.y() - 1, -1) *
                            getScenicScoreInDirection(tree, treeLines.get(tree.y()), +1, tree.x() + 1, treeLines.size()) *
                            getScenicScoreInDirection(tree, treeColumns.get(tree.x()), +1, tree.y() + 1, treeColumns.size()) *
                            getScenicScoreInDirection(tree, treeLines.get(tree.y()), -1, tree.x() - 1, -1);
            scenicScoreHashMap.put(tree.x() + "-" + tree.y(), treeScenicScore);
        }
        return scenicScoreHashMap;
    }
}
