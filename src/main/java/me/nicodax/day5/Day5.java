package me.nicodax.day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

public class Day5 {
    private HashMap<Integer, List<Character>> crateStacks = new HashMap<>();

    public void readAndParseFile(Path path, Integer part) {
        crateStacks = new HashMap<>();
        try (Stream<String> linesStream = Files.lines(path)){
            List<String> lines = linesStream.toList();
            boolean parsingActions = false;
            for (String line : lines) {
                if (line.isBlank()) continue;
                if (!parsingActions && Objects.equals(line.trim().split(" ")[0], "1")) {
                    for (int i = lines.indexOf(line) - 1; i >= 0; i--) {
                        String crateDefinitionLine = lines.get(i);
                        parseCratesStack(crateDefinitionLine);
                    }
                    parsingActions = true;
                } else if (parsingActions) {
                    parseAndExecuteCargoCraneAction(line, part);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void parseCratesStack(String line) {
        int numberOfStacks = (line.length() + 1) / 4;
        for (int stack = 0; stack < numberOfStacks; stack++) {
            Integer STACK_WIDTH = 3;
            Integer STACK_SEPARATOR_WIDTH = 1;
            int CHAR_POSITION_WITHIN_STACK = 1;
            int characterIndex = stack * (STACK_WIDTH + STACK_SEPARATOR_WIDTH) + CHAR_POSITION_WITHIN_STACK;
            char crate = line.charAt(characterIndex);
            if (crate == ' ') continue;
            List<Character> crateStackList = crateStacks.get(stack + 1);
            if (crateStackList == null) crateStackList = new ArrayList<>();
            crateStackList.add(crate);
            crateStacks.put(stack + 1, crateStackList);
        }
    }

    public void parseAndExecuteCargoCraneAction(String line, Integer part) {
        String[] args = line.trim().split(" ");
        Integer fromCraneStackIndex = parseInt(args[3]);
        Integer toCraneStackIndex = parseInt(args[5]);
        int numberOfCratesToMove = parseInt(args[1]);
        List<Character> cratesOnCrane = new ArrayList<>();
        for (int i = 0; i < numberOfCratesToMove; i++) {
            List<Character> fromCrateStack = crateStacks.get(fromCraneStackIndex);
            Character movingCrate = fromCrateStack.remove(fromCrateStack.size() - 1);
            if (part == 2) {
                cratesOnCrane.add(movingCrate);
            } else {
                List<Character> toCrateStack = crateStacks.get(toCraneStackIndex);
                toCrateStack.add(movingCrate);
                crateStacks.put(toCraneStackIndex, toCrateStack);
            }
            crateStacks.put(fromCraneStackIndex, fromCrateStack);
        }
        if (cratesOnCrane.isEmpty()) return;
        int cratesOnCraneInitialSize = cratesOnCrane.size();
        for (int i = 0; i < cratesOnCraneInitialSize; i++) {
            List<Character> toCrateStack = crateStacks.get(toCraneStackIndex);
            Character movingCrate = cratesOnCrane.remove(cratesOnCrane.size() - 1);
            toCrateStack.add(movingCrate);
            crateStacks.put(toCraneStackIndex, toCrateStack);
        }
    }

    public HashMap<Integer, List<Character>> getCrateStacks() {
        return crateStacks;
    }

    public void setCrateStacks(HashMap<Integer, List<Character>> crateStacks) {
        this.crateStacks = crateStacks;
    }

    public String getSolution() {
        StringBuilder topCrates = new StringBuilder();
        for (List<Character> crateStack : crateStacks.values()) {
            topCrates.append(crateStack.get(crateStack.size() - 1));
        }
        return topCrates.toString();
    }
}
