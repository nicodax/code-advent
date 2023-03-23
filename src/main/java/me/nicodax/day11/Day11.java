package me.nicodax.day11;

import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day11 {
    @Getter
    private final List<Monkey> monkeyList = new ArrayList<>();

    public void readAndParseFile(Path path) {
        try (Stream<String> lines = Files.lines(path)) {
            for (String line : lines.toList()) {
                if (line.isBlank()) continue;
                // TO DO
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public void processRounds(Integer numberOfRoundsToProcess) {
        // TO DO
    }

    public Integer getPart1Solution() {
        // TO DO
        return -1;
    }
}
