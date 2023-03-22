package me.nicodax.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Day10 {

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

    public Integer getXRegisterValueAfterCycle(Integer cycleNumber) {
        // TO DO
        return -1;
    }

    public Integer getSignalStrengthAfterCycle(Integer cycleNumber) {
        // TO DO
        return -1;
    }

    public Integer getPart1Solution() {
        // TO DO
        return -1;
    }
}
