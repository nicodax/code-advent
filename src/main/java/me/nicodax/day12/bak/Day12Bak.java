package me.nicodax.day12.bak;

import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class Day12Bak {

    @Getter
    private final List<List<Character>> grid = new ArrayList<>();
    @Getter
    private final List<List<CoordinateBak>> pathToBestSignalList = new ArrayList<>();
    @Getter
    private final HashMap<CoordinateBak, Integer> minNumberOfJumpsToCoordinateHashMap = new HashMap<>();
    @Getter
    private CoordinateBak startCoordinateBak = null;
    @Getter
    private CoordinateBak endCoordinateBak = null;

    public void readAndParseFile(Path path) {
        try (Stream<String> linesStream = Files.lines(path)) {
            List<String> lines = linesStream.toList();
            for (String line : lines) {
                if (line.isBlank()) continue;
                grid.add(line.chars().mapToObj(c -> (char) c).toList());
            }
            findCoordinatesOfInterest();
            new PathBak(this, null, null);
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    private void findCoordinatesOfInterest() {
        for (int y = 0; y < grid.size(); y++) {
            for (int x = 0; x < grid.get(0).size(); x++) {
                if (grid.get(y).get(x).equals('S')) {
                    startCoordinateBak = new CoordinateBak(x, y);
                }
                if (grid.get(y).get(x).equals('E')) {
                    endCoordinateBak = new CoordinateBak(x, y);
                }
                if (startCoordinateBak != null && endCoordinateBak != null) break;
            }
            if (startCoordinateBak != null && endCoordinateBak != null) break;
        }
    }

    public Integer getPart1Solution() throws Exception {
        return pathToBestSignalList.stream().map(List::size).mapToInt(i -> i).min().orElseThrow(Exception::new) - 1;
    }
}
