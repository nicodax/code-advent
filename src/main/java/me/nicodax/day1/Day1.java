package me.nicodax.day1;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static java.util.Collections.max;

@NoArgsConstructor
@Getter
@Setter
public class Day1 {
    private final List<Integer> totalCaloriesPerElfList = new ArrayList<>(Collections.singletonList(0));
    private Integer currentElfIndex = 0;

    public void readAndParseFile(Path path) {
        try (Stream<String> lines = Files.lines(path)) {
            for (String line : lines.toList()) {
                if (line.isBlank()) { // elf inventory separator
                    currentElfIndex++;
                    continue;
                }
                if (currentElfIndex >= totalCaloriesPerElfList.size()) totalCaloriesPerElfList.add(0); // initialize empty
                // increment total inventory size for current elf:
                addCalorieToCurrentTotalFor(parseInt(line));
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    private void addCalorieToCurrentTotalFor(Integer caloriesToAdd) {
        totalCaloriesPerElfList.set(currentElfIndex, totalCaloriesPerElfList.get(currentElfIndex) + caloriesToAdd);
    }

    public Integer getPart1Solution() {
        // get max amount of calories carried by a single elf
        return max(totalCaloriesPerElfList);
    }

    public Integer getPart2Solution() {
        Collections.sort(totalCaloriesPerElfList);
        Collections.reverse(totalCaloriesPerElfList);
        return totalCaloriesPerElfList.subList(0, 3).stream().mapToInt(Integer::intValue).sum();
    }
}
