package me.nicodax.day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Day6 {
    private final Integer MARKER_SIZE;
    private Integer firstMarkerIndex = -1;

    public Day6(Integer markerSize) {
        this.MARKER_SIZE = markerSize;
    }

    public void readAndParseFile(Path path) {
        try (Stream<String> linesStream = Files.lines(path)){
            List<String> lines = linesStream.toList();
            for (String line : lines) {
                if (line.isBlank()) continue;
                firstMarkerIndex = findFirstMarkerIndex(line);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public Integer getSolution() {
        return firstMarkerIndex;
    }

    public Integer findFirstMarkerIndex(String line) {
        for (int startIndex = 0; startIndex < line.length() - MARKER_SIZE; startIndex++) {
            Set<Character> characterSet = new HashSet<>();
            for (int c = startIndex; c < startIndex + MARKER_SIZE; c++) {
                characterSet.add(line.charAt(c));
            }
            if (characterSet.size() == MARKER_SIZE) return startIndex + MARKER_SIZE;
        }
        return -1;
    }
}
