package me.nicodax.day4;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static me.nicodax.day4.OverlapType.FULL;
import static me.nicodax.day4.OverlapType.PARTIAL;

@NoArgsConstructor
public class Day4 {
    private Integer fullyContainedCounter = 0;
    private Integer partiallyContainedCounter = 0;

    public Integer getPart1Solution() {
        return fullyContainedCounter;
    }

    public Integer getPart2Solution() {
        return fullyContainedCounter + partiallyContainedCounter;
    }

    public void readAndParseFile(Path path) {
        try (Stream<String> linesStream = Files.lines(path)) {
            List<String> lines = linesStream.toList();
            for (String line : lines) {
                if (line.isBlank()) continue;
                List<String> sectionArgs = stream(line.trim().split(",")).toList();
                List<Integer> sectionAIdRange =
                        stream(sectionArgs.get(0).split("-")).mapToInt(Integer::parseInt).boxed().toList();
                Section sectionA = new Section(sectionAIdRange.get(0), sectionAIdRange.get(1));
                List<Integer> sectionBIdRange =
                        stream(sectionArgs.get(1).split("-")).mapToInt(Integer::parseInt).boxed().toList();
                Section sectionB = new Section(sectionBIdRange.get(0), sectionBIdRange.get(1));
                if (sectionA.contains(sectionB).equals(FULL) || sectionB.contains(sectionA).equals(FULL))
                    fullyContainedCounter++;
                else if (sectionA.contains(sectionB).equals(PARTIAL)) partiallyContainedCounter++;
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}
