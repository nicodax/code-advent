package me.nicodax.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.String.valueOf;
import static java.util.Arrays.asList;
import static me.nicodax.day3.Item.fromName;

public class Day3 {
    private Integer currentPrioritySum = 0;
    private Integer currentGroupBadgePrioritySum = 0;

    public void readAndParseFile(Path path) {
        try (Stream<String> linesStream = Files.lines(path)) {
            List<String> lines = linesStream.toList();
            for (String line : lines) {
                if (line.isBlank()) continue;
                if (lines.indexOf(line) % 3 == 0) {
                    // everytime the current line is the first line for a group (every other 3 lines)
                    currentGroupBadgePrioritySum += findGroupItem(lines, lines.indexOf(line)).getPriority();
                }
                currentPrioritySum += findItemAppearingInBothCompartments(line).getPriority();
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public Item findGroupItem(List<String> lines, Integer firstGroupMemberIndex) {
        String firstLine = lines.get(firstGroupMemberIndex);
        String secondLine = lines.get(firstGroupMemberIndex + 1);
        String thirdLine = lines.get(firstGroupMemberIndex + 2);
        for (int i = 0; i < firstLine.length(); i++) {
            Character character = firstLine.charAt(i);
            // if the current first line character is contained by both the second and third lines
            if (secondLine.contains(valueOf(character)) && thirdLine.contains(valueOf(character)))
                return fromName(character);
        }
        return fromName('0');
    }

    public Integer getPart1Solution() {
        return currentPrioritySum;
    }

    public Integer getPart2Solution() {
        return currentGroupBadgePrioritySum;
    }

    public Item findItemAppearingInBothCompartments(String line) {
        int firstCompartment2ItemIndex = line.length() / 2;
        // compartments are the two halves of the line
        List<String> lineCompartments = asList(line.substring(0, firstCompartment2ItemIndex),
                                               line.substring(firstCompartment2ItemIndex));
        for (int i = 0; i < lineCompartments.get(0).length(); i++) {
            Character character = lineCompartments.get(0).charAt(i);
            // if the current first compartment character is contained in the second compartment
            if (lineCompartments.get(1).contains(valueOf(character))) return fromName(character);
        }
        return fromName('a');
    }
}
