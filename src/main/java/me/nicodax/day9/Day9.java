package me.nicodax.day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class Day9 {
    private Position tailPositionRelativeToHead = new Position(0, 0);
    private final List<Position> tailPositionList = new ArrayList<>(singletonList(new Position(0, 0)));

    public List<Position> getTailPositionList() {
        return tailPositionList;
    }

    public void readAndParseFile(Path path) {
        try (Stream<String> lines = Files.lines(path)) {
            for (String line : lines.toList()) {
                if (line.isBlank()) continue;
                executeAction(line);
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public void executeAction(String line) {
        String[] args = line.trim().split(" ");
        String action = args[0];
        Integer numberOfRepetitions = parseInt(args[1]);
        switch (action) {
            case "R" -> moveHeadRight(numberOfRepetitions);
            case "U" -> moveHeadUp(numberOfRepetitions);
            case "L" -> moveHeadLeft(numberOfRepetitions);
            case "D" -> moveHeadDown(numberOfRepetitions);
        }
    }

    public void moveTail() {
        // MOVEMENT STRAIGHT UP
        if (tailPositionRelativeToHead.getX() == 0 && tailPositionRelativeToHead.getY() == -2) {
            tailPositionRelativeToHead = new Position(tailPositionRelativeToHead.getX(),
                                                      tailPositionRelativeToHead.getY() + 1);
            Position newTailPosition = new Position(tailPositionList.get(tailPositionList.size() - 1).getX(),
                                                    tailPositionList.get(tailPositionList.size() - 1).getY() + 1);
            tailPositionList.add(newTailPosition);
        }
        // MOVEMENT STRAIGHT DOWN
        else if (tailPositionRelativeToHead.getX() == 0 && tailPositionRelativeToHead.getY() == 2) {
            tailPositionRelativeToHead = new Position(tailPositionRelativeToHead.getX(),
                                                      tailPositionRelativeToHead.getY() - 1);
            Position newTailPosition = new Position(tailPositionList.get(tailPositionList.size() - 1).getX(),
                                                    tailPositionList.get(tailPositionList.size() - 1).getY() - 1);
            tailPositionList.add(newTailPosition);
        }
        // MOVEMENT STRAIGHT RIGHT
        else if (tailPositionRelativeToHead.getY() == 0 && tailPositionRelativeToHead.getX() == -2) {
            tailPositionRelativeToHead = new Position(tailPositionRelativeToHead.getX() + 1,
                                                      tailPositionRelativeToHead.getY());
            Position newTailPosition = new Position(tailPositionList.get(tailPositionList.size() - 1).getX() + 1,
                                                    tailPositionList.get(tailPositionList.size() - 1).getY());
            tailPositionList.add(newTailPosition);
        }
        // MOVEMENT STRAIGHT LEFT
        else if (tailPositionRelativeToHead.getY() == 0 && tailPositionRelativeToHead.getX() == 2) {
            tailPositionRelativeToHead = new Position(tailPositionRelativeToHead.getX() - 1,
                                                      tailPositionRelativeToHead.getY());
            Position newTailPosition = new Position(tailPositionList.get(tailPositionList.size() - 1).getX() - 1,
                                                    tailPositionList.get(tailPositionList.size() - 1).getY());
            tailPositionList.add(newTailPosition);
        }
        // MOVEMENT DIAGONALLY DOWN-RIGHT
        else if ((tailPositionRelativeToHead.getX() == -2 && tailPositionRelativeToHead.getY() >= 1) ||
                (tailPositionRelativeToHead.getX() == -1 && tailPositionRelativeToHead.getY() == 2)) {
            tailPositionRelativeToHead = new Position(tailPositionRelativeToHead.getX() + 1,
                                                      tailPositionRelativeToHead.getY() - 1);
            Position newTailPosition = new Position(tailPositionList.get(tailPositionList.size() - 1).getX() + 1,
                                                    tailPositionList.get(tailPositionList.size() - 1).getY() - 1);
            tailPositionList.add(newTailPosition);
        }
        // MOVEMENT DIAGONALLY UP-RIGHT
        else if ((tailPositionRelativeToHead.getX() == -2 && tailPositionRelativeToHead.getY() <= -1) ||
                (tailPositionRelativeToHead.getX() == -1 && tailPositionRelativeToHead.getY() == -2)) {
            tailPositionRelativeToHead = new Position(tailPositionRelativeToHead.getX() + 1,
                                                      tailPositionRelativeToHead.getY() + 1);
            Position newTailPosition = new Position(tailPositionList.get(tailPositionList.size() - 1).getX() + 1,
                                                    tailPositionList.get(tailPositionList.size() - 1).getY() + 1);
            tailPositionList.add(newTailPosition);
        }
        // MOVEMENT DIAGONALLY DOWN-LEFT
        else if ((tailPositionRelativeToHead.getX() == 2 && tailPositionRelativeToHead.getY() >= 1) ||
                (tailPositionRelativeToHead.getX() == 1 && tailPositionRelativeToHead.getY() == 2)) {
            tailPositionRelativeToHead = new Position(tailPositionRelativeToHead.getX() - 1,
                                                      tailPositionRelativeToHead.getY() - 1);
            Position newTailPosition = new Position(tailPositionList.get(tailPositionList.size() - 1).getX() - 1,
                                                    tailPositionList.get(tailPositionList.size() - 1).getY() - 1);
            tailPositionList.add(newTailPosition);
        }
        // MOVEMENT DIAGONALLY UP-LEFT
        else if ((tailPositionRelativeToHead.getX() == 2 && tailPositionRelativeToHead.getY() <= -1) ||
                (tailPositionRelativeToHead.getX() == 1 && tailPositionRelativeToHead.getY() == -2)) {
            tailPositionRelativeToHead = new Position(tailPositionRelativeToHead.getX() - 1,
                                                      tailPositionRelativeToHead.getY() + 1);
            Position newTailPosition = new Position(tailPositionList.get(tailPositionList.size() - 1).getX() - 1,
                                                    tailPositionList.get(tailPositionList.size() - 1).getY() + 1);
            tailPositionList.add(newTailPosition);
        }

    }

    public void moveHeadRight(Integer numberOfRepetitions) {
        for (int i = 0; i < numberOfRepetitions; i++) {
            tailPositionRelativeToHead = new Position(tailPositionRelativeToHead.getX() - 1,
                                                      tailPositionRelativeToHead.getY());
            moveTail();
        }
    }

    public void moveHeadUp(Integer numberOfRepetitions) {
        for (int i = 0; i < numberOfRepetitions; i++) {
            tailPositionRelativeToHead = new Position(tailPositionRelativeToHead.getX(),
                                                      tailPositionRelativeToHead.getY() - 1);
            moveTail();
        }
    }

    public void moveHeadLeft(Integer numberOfRepetitions) {
        for (int i = 0; i < numberOfRepetitions; i++) {
            tailPositionRelativeToHead = new Position(tailPositionRelativeToHead.getX() + 1,
                                                      tailPositionRelativeToHead.getY());
            moveTail();
        }
    }

    public void moveHeadDown(Integer numberOfRepetitions) {
        for (int i = 0; i < numberOfRepetitions; i++) {
            tailPositionRelativeToHead = new Position(tailPositionRelativeToHead.getX(),
                                                      tailPositionRelativeToHead.getY() + 1);
            moveTail();
        }
    }

    public Integer getNumberOfPositionsVisitedAtLeastOnce() {
        return tailPositionList.stream()
                               .map(p -> asList(p.getX(), p.getY()))
                               .collect(Collectors.toSet())
                               .size();

    }
}
