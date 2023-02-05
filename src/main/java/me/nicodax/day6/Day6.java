package me.nicodax.day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Collections.frequency;

public class Day6 {
    private final Integer MARKER_SIZE;
    private Integer firstMarkerIndex = -1;

    public Day6(Integer markerSize) {
        this.MARKER_SIZE = markerSize;
    }

    public void readAndParseFile(Path path) {
        try (Stream<String> linesStream = Files.lines(path)) {
            List<String> lines = linesStream.toList();
            for (String line : lines) {
                if (line.isBlank()) continue;
                firstMarkerIndex = findFirstMarkerIndex(line);
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public Integer findFirstMarkerIndex(String line) {
        for (int startIndex = 0; startIndex < line.length() - MARKER_SIZE; startIndex++) {
            List<Character> potentialMarker = line.substring(startIndex, startIndex + MARKER_SIZE)
                                                  .chars()
                                                  .mapToObj(i -> (char) i)
                                                  .toList();
            boolean couldBeAMarker = true;
            for (int i = 0; i < MARKER_SIZE; i++) {
                couldBeAMarker = frequency(potentialMarker, potentialMarker.get(i)) == 1;
                if (!couldBeAMarker) break;
            }
            if (couldBeAMarker) return startIndex + MARKER_SIZE;
        }
        return -1;
    }

    public Integer getSolution() {
        return firstMarkerIndex;
    }
}
